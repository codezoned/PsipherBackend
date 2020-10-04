package com.psipher.application.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psipher.application.actions.GeneratePasswordOperations;
import com.psipher.application.model.GeneratePasswordOutput;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.psipher.application.api.Constants.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = GeneratePassword.class)
public class GeneratePasswordTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeneratePasswordOperations generatePasswordOperations;

    private static GeneratePasswordOutput generatePasswordSuccess;
    private static GeneratePasswordOutput generatePasswordFail;

    private static final String PATH = "/generatepassword";

    @BeforeClass
    public static void setUpBeforeClass() {
        generatePasswordSuccess = new GeneratePasswordOutput();
        generatePasswordSuccess.setPassword(ANY);
        generatePasswordSuccess.setStatus(SUCCESS);

        generatePasswordFail = new GeneratePasswordOutput();
        generatePasswordFail.setStatus(FAILURE);
    }

    @AfterClass
    public static void teardownAfterClass() {
        generatePasswordSuccess = null;
        generatePasswordFail = null;
    }

    @Test
    public void testGeneratePasswordFailure() throws Exception {


        mockMvc.perform(get(PATH)
                .param("length", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(generatePasswordFail)));
    }

    @Test
    public void testGeneratePasswordFailure2() throws Exception {


        mockMvc.perform(get(PATH))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(generatePasswordFail)));
    }


    @Test
    public void testGeneratePasswordSuccess() throws Exception {

        when(generatePasswordOperations.generatePassword(anyInt())).thenReturn(Constants.ANY);

        mockMvc.perform(get(PATH)
                .param("length", "6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(generatePasswordSuccess)));
    }
}
