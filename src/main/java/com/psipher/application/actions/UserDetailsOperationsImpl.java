package com.psipher.application.actions;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.psipher.application.ddbmodel.UserAccountDDBModel;
import com.psipher.application.ddbmodel.UserDDBModel;
import com.psipher.application.ddbmodel.WebsiteDDBModel;
import com.psipher.application.exceptions.DDBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements UserDetailsOperations and provide all functionality mention in the interface
 */
@Component
public class UserDetailsOperationsImpl implements UserDetailsOperations {

    DynamoDBMapper dynamoDBMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsOperationsImpl.class);

    @Autowired
    public UserDetailsOperationsImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public String saveDetails(String userId, String domain, String userAccount, String type, String password) {
        // TODO Code for JWT implementation once FrontEnd part is ready leaving for now
        // TODO Currently UserId is coming as input once frontend is ready then we accept token from header
        // TODO After getting auth token we make call to Cognito to get the UserName/ID
        String status;
        try {
            UserDDBModel userDDBModel = viewDetails(userId);
            if (userDDBModel != null) {
                updateUser(userDDBModel, userId, domain, userAccount, type, password);
            } else {
                createNewUser(userId, domain, userAccount, type, password);
            }
            status = "success";
        } catch (Exception e) {
            //TODO change with more specific exception when other components are implemented
            logger.error(e.getMessage());
            status = "Failed at persistence layer";
        }
        return status;
    }

    @Override
    public UserDDBModel viewDetails(String userId) throws DDBException {
        UserDDBModel userDDBModel;
        try {
            userDDBModel = dynamoDBMapper.load(UserDDBModel.class, userId);
        } catch (Exception e) {
            logger.error(String.format("Failed to load data for userId:%s error:%s", userId, e.getMessage()));
            throw new DDBException(String.format("Failed to load data for userId:%s", userId));
        }
        return userDDBModel;
    }


    @Override
    public void createNewUser(String userId, String domain, String userAccount, String type, String password) {
        List<WebsiteDDBModel> websiteDDBModels = new ArrayList<>();
        createNewDomain(websiteDDBModels, domain, userAccount, type, password);
        UserDDBModel userDDBModel = new UserDDBModel();
        userDDBModel.setUserId(userId);
        userDDBModel.setWebsites(websiteDDBModels);
        dynamoDBMapper.save(userDDBModel);
    }

    @Override
    public void updateUser(UserDDBModel userDDBModel, String userId, String domain, String userAccount, String type, String password) {
        boolean match = false;
        List<WebsiteDDBModel> websiteDDBModels = userDDBModel.getWebsites();
        for(WebsiteDDBModel websiteDDBModel: websiteDDBModels) {
            if(websiteDDBModel.getDomain().equals(domain)) {
                searchAccountAndUpdate(websiteDDBModel.getUserAccountsDDBModel(),
                        userAccount, type, password);
                match = true;
            }
        }
        if (!match) {
            createNewDomain(websiteDDBModels, domain, userAccount, type, password);
        }
        dynamoDBMapper.save(userDDBModel);
    }

    @Override
    public String deleteDomain(String userId, String domain) throws DDBException {
        UserDDBModel userDDBModel = viewDetails(userId);
        List<WebsiteDDBModel> websiteDDBModels = userDDBModel.getWebsites();
        for (WebsiteDDBModel websiteDDBModel : websiteDDBModels) {
            if (websiteDDBModel.getDomain().equals(domain)) {
                websiteDDBModels.remove(websiteDDBModel);
                break;
            }
        }
        String status;
        try {
            dynamoDBMapper.save(userDDBModel);
            status = "success";
        } catch (Exception e) {
            logger.error(String.format("Failed to delete domain: %s for userId: %s exception: %s", domain, userId, e.getMessage()));
            throw new DDBException("Failed to delete domain");
        }
        return status;
    }

    @Override
    public String deleteUser(String userId) throws DDBException {
        UserDDBModel userDDBModel = viewDetails(userId);
        String status;
        try {
            dynamoDBMapper.delete(userDDBModel);
            status = "success";
        } catch (Exception e) {
            logger.error(String.format("Failed to delete userId: %s exception: %s", userId, e.getMessage()));
            throw new DDBException("Failed to delete user");
        }
        return status;
    }

    @Override
    public String deleteUserAccount(String userId, String domain, String userAccount) throws DDBException {
        UserDDBModel userDDBModel = viewDetails(userId);
        List<WebsiteDDBModel> websiteDDBModels = userDDBModel.getWebsites();
        for (WebsiteDDBModel websiteDDBModel : websiteDDBModels) {
            if (websiteDDBModel.getDomain().equals(domain)) {
                searchAccountAndDelete(websiteDDBModel.getUserAccountsDDBModel(), userAccount);
                break;
            }
        }
        String status;
        try {
            dynamoDBMapper.save(userDDBModel);
            status = "success";
        } catch (Exception e) {
            logger.error(String.format("Failed to delete user account: %s in domain: %s for userId: %s exception: %s",
                    userAccount, domain, userId, e.getMessage()));
            throw new DDBException("Failed to delete userAccount");
        }
        return status;
    }

    /**
     * Search for the account and deletes the record
     *
     * @param userAccountDDBModels list of account present in the domain
     * @param userAccount          user account which is going to be deleted
     */
    private void searchAccountAndDelete(List<UserAccountDDBModel> userAccountDDBModels, String userAccount) {
        for (UserAccountDDBModel userAccountDDBModel : userAccountDDBModels) {
            if (userAccountDDBModel.getId().equals(userAccount)) {
                userAccountDDBModels.remove(userAccountDDBModel);
                break;
            }
        }
    }

    /**
     * Search for the account if already exists then update it else create a new one
     * @param userAccountDDBModels list of account present in the domain
     * @param userAccount user account which is going to be added or updated
     * @param type type of account
     * @param password password
     */
    private void searchAccountAndUpdate(List<UserAccountDDBModel> userAccountDDBModels,
                                        String userAccount, String type, String password) {
        boolean match = false;
        for (UserAccountDDBModel userAccountDDBModel: userAccountDDBModels) {
            if (userAccountDDBModel.getId().equals(userAccount)) {
                userAccountDDBModel.setType(type);
                userAccountDDBModel.setPassword(password);
                match = true;
                break;
            }
        }
        if(!match) {
            createNewUserAccount(userAccountDDBModels, userAccount, type, password);
        }
    }

    /**
     * Creates new domain
     * @param websiteDDBModels list of all the domains
     * @param domain domain which will be created
     * @param userAccount user account
     * @param type type
     * @param password password
     */

    private void createNewDomain(List<WebsiteDDBModel> websiteDDBModels,
                                 String domain, String userAccount,
                                 String type, String password) {
        List<UserAccountDDBModel> userAccountDDBModels = new ArrayList<>();
        createNewUserAccount(userAccountDDBModels, userAccount, type, password);
        WebsiteDDBModel websiteDDBModel = new WebsiteDDBModel();
        websiteDDBModel.setDomain(domain);
        websiteDDBModel.setUserAccountsDDBModel(userAccountDDBModels);
        websiteDDBModels.add(websiteDDBModel);
    }

    /**
     * Create new userAccount in the domain
     * @param userAccountDDBModels userAccount lists present in the domain
     * @param userAccount userAccount
     * @param type type
     * @param password password
     */
    private void createNewUserAccount(List<UserAccountDDBModel> userAccountDDBModels,
                                      String userAccount, String type, String password) {
        UserAccountDDBModel userAccountDDBModel = new UserAccountDDBModel();
        userAccountDDBModel.setId(userAccount);
        userAccountDDBModel.setType(type);
        userAccountDDBModel.setPassword(password);
        userAccountDDBModels.add(userAccountDDBModel);
    }
}
