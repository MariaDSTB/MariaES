package io.hansan.servicequestion;

import io.hansan.servicequestion.controller.QuestionController;
import io.hansan.servicequestion.database.entity.QuestionEntity;
import io.hansan.servicequestion.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ServiceQuestionApplicationTests {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuestionController questionController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
    }

    @Test
    public void addQuestion_ShouldCallService() throws Exception {
        mockMvc.perform(post("http://localhost:7001/questions/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"content\":\"Test question\",\"answer\":\"Test answer\",\"type\":\"Test type\",\"difficulty\":\"Test difficulty\"}"))
                .andExpect(status().isOk());

        verify(questionService, times(1)).addQuestion(any(QuestionEntity.class));
    }

    @Test
    public void addQuestion_ShouldReturnBadRequest_WhenRequestBodyIsInvalid() throws Exception {
        mockMvc.perform(post("/questions/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"content\":\"\",\"answer\":\"\",\"type\":\"\",\"difficulty\":\"\"}"))
                .andExpect(status().isBadRequest());

        verify(questionService, times(0)).addQuestion(any(QuestionEntity.class));
    }
}