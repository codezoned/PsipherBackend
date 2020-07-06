package com.psipher.application.actions;

import com.psipher.application.ddbmodel.UserDDBModel;
import com.psipher.application.exceptions.DDBException;

/**
 * Add all the required functions for operation such as
 * update, delete, save etc.
 */
public interface UserDetailsOperations {
    /**
     * This will save details for the user whether it is update, create it handles all cases
     * @param userId userId psipher specific
     * @param domain domain like google.com etc
     * @param userAccount account of the user in the domain
     * @param type type of the user account E/P/U
     * @param password password
     * @return status
     */
    String saveDetails(String userId, String domain, String userAccount, String type, String password);

    /**
     * This will create new user if the in the table dont exists
     * @param userId userId psipher specific
     * @param domain domain like google.com etc
     * @param userAccount account of the user in the domain
     * @param type type of the user account E/P/U
     * @param password password
     */
    void createNewUser(String userId, String domain, String userAccount, String type, String password);

    /**
     * updateUser will update the data in the db
     * @param userDDBModel existing data from the dynamoDB
     * @param userId userId psipher specific
     * @param domain domain like google.com etc
     * @param userAccount account of the user in the domain
     * @param type type of the user account E/P/U
     * @param password password
     */
    void updateUser(UserDDBModel userDDBModel, String userId, String domain, String userAccount, String type, String password);

    /**
     * viewDetails returns all user details
     * @param userId user id
     * @return UserDDBModel
     * @throws DDBException dynamoDB custom exception
     */
    UserDDBModel viewDetails(String userId) throws DDBException;

    /**
     * This will delete domain for the specified userId
     * @param userId userId psipher specific
     * @param domain domain like google.com etc
     * @return status
     * @throws DDBException dynamoDB custom exception
     */
    String deleteDomain(String userId, String domain) throws DDBException;

    /**
     * This will delete user permanently from psipher
     * @param userId userId psipher specific
     * @return status
     * @throws DDBException dynamoDB custom exception
     */
    String deleteUser(String userId) throws DDBException;

    /**
     * This will delete userAccount in a particular domain of a user
     * @param userId      userId psipher specific
     * @param domain      domain like google.com etc
     * @param userAccount account of the user in the domain
     * @return status
     * @throws DDBException dynamoDB custom exception
     */
    String deleteUserAccount(String userId, String domain, String userAccount) throws DDBException;
}
