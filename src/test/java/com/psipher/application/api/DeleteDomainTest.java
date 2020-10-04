package com.psipher.application.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psipher.application.actions.UserDetailsOperations;
import com.psipher.application.exceptions.DDBException;
import com.psipher.application.model.DeleteDomainInput;
import com.psipher.application.model.DeleteDomainOutput;
import org.junit.AfterClass;
import org.junit.BeforeClass;
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
@WebMvcTest(controllers = DeleteDomain.class)
public class DeleteDomainTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsOperations userDetailsOperations;


    private final ObjectMapper objectMapper = new ObjectMapper();

    private static DeleteDomainOutput deleteDomainOutputSuccess;
    private static DeleteDomainOutput deleteDomainOutputInvalid;
    private static DeleteDomainOutput deleteDomainOutpuFailure;

    @BeforeClass
    public static void setUpBeforeClass() {
        deleteDomainOutputSuccess = new DeleteDomainOutput(SUCCESS);
        deleteDomainOutputInvalid = new DeleteDomainOutput(INVALID_INPUT);
        deleteDomainOutpuFailure = new DeleteDomainOutput(FAILURE);
    }

    @AfterClass
    public static void teardownAfterClass() {
        deleteDomainOutputSuccess = null;
        deleteDomainOutputInvalid = null;
        deleteDomainOutpuFailure = null;
    }

    @Test
    public void testDeleteDomain_WhenDomainIsValidAndOperationCallIsSuccess_ThenReturnOK() throws Exception {

        final DeleteDomainInput deleteDomainInput = new DeleteDomainInput(ANY, ANY);

        when(userDetailsOperations.deleteDomain(anyString(), anyString())).thenReturn(SUCCESS);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(deleteDomainInput)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(deleteDomainOutputSuccess)));
    }

    @Test
    public void testDeleteDomain_WhenDomainIsValidAndOperationThrowException_ThenReturnFailure() throws Exception {

        final DeleteDomainInput deleteDomainInput = new DeleteDomainInput(ANY, ANY);

        when(userDetailsOperations.deleteDomain(anyString(), anyString())).thenThrow(DDBException.class);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(deleteDomainInput)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(deleteDomainOutpuFailure)));
    }


    @Test
    public void testDeleteDomain_WhenDomainIsNull_ThenReturnInvalid() throws Exception {
        when(userDetailsOperations.deleteDomain(anyString(), anyString())).thenReturn(SUCCESS);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteDomain_WhenDomainHasNullProperties_ThenReturnINvalid() throws Exception {

        DeleteDomainInput deleteDomainInput = new DeleteDomainInput(null, ANY);

        when(userDetailsOperations.deleteDomain(anyString(), anyString())).thenReturn(SUCCESS);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(deleteDomainInput)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(deleteDomainOutputInvalid)));


        deleteDomainInput = new DeleteDomainInput(ANY, null);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(deleteDomainInput)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(deleteDomainOutputInvalid)));
    }
}
