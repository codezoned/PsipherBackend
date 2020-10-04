package com.psipher.application.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psipher.application.actions.UserDetailsOperations;
import com.psipher.application.model.SaveDetailsInput;
import com.psipher.application.model.SaveDetailsOutput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.ThreadPoolExecutor;

import static com.psipher.application.api.ApiConstants.INVALID_INPUT;
import static com.psipher.application.api.ApiConstants.UNKNOWN_USER;
import static com.psipher.application.api.Constants.ANY;
import static com.psipher.application.api.Constants.SUCCESS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = SaveDetails.class)
public class SaveDetailsTest {


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;


//    @MockBean
//    private Gson gson;

    @MockBean
    private UserDetailsOperations userDetailsOperations;

    private SaveDetailsOutput saveDetailsOutput;

    private static final String PATH = "/savedetails";

    @Before
    public void setUp() {
        saveDetailsOutput = new SaveDetailsOutput();
    }

    @After
    public void teardown() {
        saveDetailsOutput = null;
    }

    @Test
    public void testSavedDetailsInvalidInput() throws Exception {
        saveDetailsOutput.setStatus(INVALID_INPUT);

        final SaveDetailsInput saveDetailsInput = new SaveDetailsInput();
        saveDetailsInput.setUserId(null);
        saveDetailsInput.setDomain(null);
        saveDetailsInput.setPassword(null);
        saveDetailsInput.setUserAccount(null);
        saveDetailsOutput.setUserId(ApiConstants.UNKNOWN_USER);

        mockMvc.perform(post(PATH)
                .content(objectMapper.writeValueAsString(saveDetailsInput))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(saveDetailsOutput)));


        saveDetailsInput.setUserId(null);
        saveDetailsInput.setDomain(ANY);
        saveDetailsInput.setPassword(null);
        saveDetailsInput.setUserAccount(null);

        mockMvc.perform(post(PATH)
                .content(objectMapper.writeValueAsString(saveDetailsInput))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(saveDetailsOutput)));


        saveDetailsInput.setUserId(null);
        saveDetailsInput.setDomain(null);
        saveDetailsInput.setPassword(ANY);
        saveDetailsInput.setUserAccount(null);

        mockMvc.perform(post(PATH)
                .content(objectMapper.writeValueAsString(saveDetailsInput))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(saveDetailsOutput)));


        saveDetailsInput.setUserId(null);
        saveDetailsInput.setDomain(null);
        saveDetailsInput.setPassword(null);
        saveDetailsInput.setUserAccount(ANY);

        mockMvc.perform(post(PATH)
                .content(objectMapper.writeValueAsString(saveDetailsInput))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(saveDetailsOutput)));


        saveDetailsOutput.setUserId(ANY);

        saveDetailsInput.setUserId(ANY);
        saveDetailsInput.setDomain(ANY);
        saveDetailsInput.setPassword(null);
        saveDetailsInput.setUserAccount(null);

        mockMvc.perform(post(PATH)
                .content(objectMapper.writeValueAsString(saveDetailsInput))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(saveDetailsOutput)));


        saveDetailsInput.setUserId(ANY);
        saveDetailsInput.setDomain(null);
        saveDetailsInput.setPassword(ANY);
        saveDetailsInput.setUserAccount(null);

        mockMvc.perform(post(PATH)
                .content(objectMapper.writeValueAsString(saveDetailsInput))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(saveDetailsOutput)));


        saveDetailsInput.setUserId(ANY);
        saveDetailsInput.setDomain(null);
        saveDetailsInput.setPassword(null);
        saveDetailsInput.setUserAccount(null);

        mockMvc.perform(post(PATH)
                .content(objectMapper.writeValueAsString(saveDetailsInput))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(saveDetailsOutput)));

        saveDetailsInput.setUserId(ANY);
        saveDetailsInput.setDomain(null);
        saveDetailsInput.setPassword(null);
        saveDetailsInput.setUserAccount(ANY);

        mockMvc.perform(post(PATH)
                .content(objectMapper.writeValueAsString(saveDetailsInput))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(saveDetailsOutput)));


        saveDetailsInput.setUserId(null);
        saveDetailsInput.setDomain(ANY);
        saveDetailsInput.setPassword(ANY);
        saveDetailsInput.setUserAccount(ANY);

        saveDetailsOutput.setUserId(UNKNOWN_USER);
        mockMvc.perform(post(PATH)
                .content(objectMapper.writeValueAsString(saveDetailsInput))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(saveDetailsOutput)));

        saveDetailsInput.setUserId(null);
        saveDetailsInput.setDomain(ANY);
        saveDetailsInput.setPassword(null);
        saveDetailsInput.setUserAccount(ANY);

        mockMvc.perform(post(PATH)
                .content(objectMapper.writeValueAsString(saveDetailsInput))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(saveDetailsOutput)));


        saveDetailsInput.setUserId(null);
        saveDetailsInput.setDomain(null);
        saveDetailsInput.setPassword(ANY);
        saveDetailsInput.setUserAccount(ANY);

        mockMvc.perform(post(PATH)
                .content(objectMapper.writeValueAsString(saveDetailsInput))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(saveDetailsOutput)));
    }

    @Test
    public void testSaveDetailsValidInput() throws Exception {

        Mockito.when(userDetailsOperations.saveDetails(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(SUCCESS);

        saveDetailsOutput.setStatus(SUCCESS);
        saveDetailsOutput.setUserId(ANY);

        final SaveDetailsInput saveDetailsInput = new SaveDetailsInput();

        saveDetailsInput.setUserId(ANY);
        saveDetailsInput.setDomain(ANY);
        saveDetailsInput.setPassword(ANY);
        saveDetailsInput.setUserAccount(ANY);

        mockMvc.perform(post(PATH)
                .content(objectMapper.writeValueAsString(saveDetailsInput))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(saveDetailsOutput)));
    }

}
