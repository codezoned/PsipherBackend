package com.psipher.application.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psipher.application.actions.UserDetailsOperations;
import com.psipher.application.ddbmodel.UserDDBModel;
import com.psipher.application.ddbmodel.WebsiteDDBModel;
import com.psipher.application.exceptions.DDBException;
import com.psipher.application.model.ViewDetailsOutput;
import org.assertj.core.util.Lists;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.psipher.application.api.Constants.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = ViewDetails.class)
public class ViewDetailsTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsOperations userDetailsOperations;

    private static final String PATH = "/viewdetails";

    private static ViewDetailsOutput viewDetailsOutputSuccess;
    private static ViewDetailsOutput viewDetailsOutputFail;
    private static ViewDetailsOutput viewDetailsOutputUserIsNotPresent;
    private static UserDDBModel userDDBModel;
    private static WebsiteDDBModel websiteDDBModel;

    @BeforeClass
    public static void setUpBeforeClass() {
        websiteDDBModel = new WebsiteDDBModel();
        websiteDDBModel.setDomain(ANY);
        websiteDDBModel.setUserAccountsDDBModel(Lists.newArrayList());
        userDDBModel = new UserDDBModel();
        userDDBModel.setWebsites(Lists.newArrayList(websiteDDBModel));
        viewDetailsOutputFail = new ViewDetailsOutput(ANY, null, FAILURE);
        viewDetailsOutputUserIsNotPresent = new ViewDetailsOutput(ANY, null, "userId not present");
        viewDetailsOutputSuccess = new ViewDetailsOutput(ANY, Lists.newArrayList(websiteDDBModel), SUCCESS);
    }

    @AfterClass
    public static void tearDownAfterClass() {
        viewDetailsOutputFail = null;
        viewDetailsOutputSuccess = null;
        userDDBModel = null;
        websiteDDBModel = null;
        viewDetailsOutputUserIsNotPresent = null;
    }

    @Test
    public void testViewDetails_Success() throws Exception {

        when(userDetailsOperations.viewDetails(anyString())).thenReturn(userDDBModel);

        mockMvc.perform(get(PATH)
                .param("userId", ANY))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(viewDetailsOutputSuccess)));
    }

    @Test
    public void testViewDetails_WhenUserDetailsOperationsDbModelIsNull_ThenStatusUserIdIsNotPresent() throws Exception {

        when(userDetailsOperations.viewDetails(anyString())).thenReturn(null);

        mockMvc.perform(get(PATH)
                .param("userId", ANY))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(viewDetailsOutputUserIsNotPresent)));
    }

    @Test
    public void testViewDetails_WhenUserDetailsOperationsThrowsException_ThenStatusUserIdIsNotPresent() throws Exception {

        DDBException ddbException = Mockito.mock(DDBException.class);
        when(ddbException.getMessage()).thenReturn(ANY);
        when(userDetailsOperations.viewDetails(anyString())).thenThrow(ddbException);

        mockMvc.perform(get(PATH)
                .param("userId", ANY))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(viewDetailsOutputFail)));
    }
}
