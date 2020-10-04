package com.psipher.application.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psipher.application.actions.PasswordPawnedOperations;
import com.psipher.application.model.PasswordPawnedOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.psipher.application.api.Constants.ANY;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = PasswordPawnedChecker.class)
public class PasswordPawnedCheckerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PasswordPawnedOperations passwordPawnedOperations;

    @Test
    public void testPasswordpawnedoutput() throws Exception {

        when(passwordPawnedOperations.passwordPawnedChecker(anyString())).thenReturn(true);

        PasswordPawnedOutput passwordPawnedOutput = new PasswordPawnedOutput(true);

        mockMvc.perform(post("/passwordpawnedchecker")
                .param("pass", ANY))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(passwordPawnedOutput)));
    }
}
