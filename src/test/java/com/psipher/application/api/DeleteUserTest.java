package com.psipher.application.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psipher.application.actions.UserDetailsOperations;
import com.psipher.application.exceptions.DDBException;
import com.psipher.application.model.DeleteUserInput;
import com.psipher.application.model.DeleteUserOutput;
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

import static com.psipher.application.api.Constants.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = DeleteUser.class)
public class DeleteUserTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsOperations userDetailsOperations;

    private static final String PATH = "/deleteuser";
    private final static String INVALID_INPUT = "Invalid input";

    private DeleteUserOutput deleteUserOutput;


    @Before
    public void setUp() {
        deleteUserOutput = new DeleteUserOutput();
    }

    @After
    public void teardown() {
        deleteUserOutput = null;
    }

    @Test
    public void testDeleteUser_WhenInputIdIsNull_ThenInvalidInput() throws Exception {
        deleteUserOutput.setStatus(INVALID_INPUT);
        final DeleteUserInput deleteUserInput = new DeleteUserInput(null);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(deleteUserInput)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(deleteUserOutput)));
    }


    @Test
    public void testDeleteUser_WhenUserDetailsOperationsReturnSuccess_ThenSuccess() throws Exception {

        when(userDetailsOperations.deleteUser(anyString())).thenReturn(SUCCESS);

        deleteUserOutput.setStatus(SUCCESS);
        final DeleteUserInput deleteUserInput = new DeleteUserInput(ANY);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(deleteUserInput)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(deleteUserOutput)));
    }


    @Test
    public void testDeleteUser_WhenUserDetailsOperationsThrowExceptions_ThenFailure() throws Exception {

        when(userDetailsOperations.deleteUser(anyString())).thenThrow(DDBException.class);

        deleteUserOutput.setStatus(FAILURE);
        final DeleteUserInput deleteUserInput = new DeleteUserInput(ANY);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(deleteUserInput)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(deleteUserOutput)));
    }
}
