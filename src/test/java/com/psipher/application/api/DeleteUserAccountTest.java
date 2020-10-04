package com.psipher.application.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psipher.application.actions.UserDetailsOperations;
import com.psipher.application.exceptions.DDBException;
import com.psipher.application.model.DeleteUserAccountInput;
import com.psipher.application.model.DeleteUserAccountOutput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.psipher.application.api.ApiConstants.INVALID_INPUT;
import static com.psipher.application.api.Constants.ANY;
import static com.psipher.application.api.Constants.SUCCESS;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = DeleteUserAccount.class)
public class DeleteUserAccountTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsOperations userDetailsOperations;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String PATH = "/deleteuseraccount";

    private DeleteUserAccountInput deleteUserAccountInput;
    private DeleteUserAccountOutput deleteUserAccountOutput;

    @Before
    public void setUp() {
        deleteUserAccountInput = new DeleteUserAccountInput();
        deleteUserAccountOutput = new DeleteUserAccountOutput();
    }

    @After
    public void teardown() {
        deleteUserAccountInput = null;
        deleteUserAccountOutput = null;
    }

    @Test
    public void testDeleteUserAccountValidInput() throws Exception {
        deleteUserAccountOutput.setStatus(SUCCESS);

        deleteUserAccountInput.setDomain(ANY);
        deleteUserAccountInput.setUserAccount(ANY);
        deleteUserAccountInput.setUserId(ANY);

        when(userDetailsOperations.deleteUserAccount(anyString(), anyString(), anyString())).thenReturn(SUCCESS);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(deleteUserAccountInput)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(deleteUserAccountOutput)));
    }

    @Test
    public void testDeleteUserAccountValidInput_WhenOperationsThrowException_ThenFailure() throws Exception {
        deleteUserAccountOutput.setStatus(Constants.FAILURE);

        deleteUserAccountInput.setDomain(ANY);
        deleteUserAccountInput.setUserAccount(ANY);
        deleteUserAccountInput.setUserId(ANY);

        when(userDetailsOperations.deleteUserAccount(anyString(), anyString(), anyString()))
                .thenThrow(DDBException.class);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(deleteUserAccountInput)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(deleteUserAccountOutput)));
    }

    @Test
    public void testDeleteUserAccountInvalidInput() throws Exception {
        deleteUserAccountOutput.setStatus(INVALID_INPUT);

        deleteUserAccountInput.setDomain(null);
        deleteUserAccountInput.setUserAccount(null);
        deleteUserAccountInput.setUserId(null);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(deleteUserAccountInput)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(deleteUserAccountOutput)));


        deleteUserAccountInput.setDomain(ANY);
        deleteUserAccountInput.setUserAccount(null);
        deleteUserAccountInput.setUserId(null);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(deleteUserAccountInput)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(deleteUserAccountOutput)));

        deleteUserAccountInput.setDomain(null);
        deleteUserAccountInput.setUserAccount(ANY);
        deleteUserAccountInput.setUserId(null);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(deleteUserAccountInput)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(deleteUserAccountOutput)));


        deleteUserAccountInput.setDomain(null);
        deleteUserAccountInput.setUserAccount(null);
        deleteUserAccountInput.setUserId(ANY);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(deleteUserAccountInput)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(deleteUserAccountOutput)));

        deleteUserAccountInput.setDomain(ANY);
        deleteUserAccountInput.setUserAccount(null);
        deleteUserAccountInput.setUserId(ANY);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(deleteUserAccountInput)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(deleteUserAccountOutput)));

        deleteUserAccountInput.setDomain(ANY);
        deleteUserAccountInput.setUserAccount(ANY);
        deleteUserAccountInput.setUserId(null);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(deleteUserAccountInput)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(deleteUserAccountOutput)));


        deleteUserAccountInput.setDomain(null);
        deleteUserAccountInput.setUserAccount(ANY);
        deleteUserAccountInput.setUserId(ANY);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(deleteUserAccountInput)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(deleteUserAccountOutput)));
    }

}
