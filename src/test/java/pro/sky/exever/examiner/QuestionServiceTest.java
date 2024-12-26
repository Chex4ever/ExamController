package pro.sky.exever.examiner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.exever.examiner.exception.QuestionsIsEmptyException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;
import static pro.sky.exever.examiner.constant.Questions.*;

@ExtendWith(MockitoExtension.class)
public interface QuestionServiceTest<Service extends QuestionService, Repo extends QuestionRepository> {
    Service createQuestionService();

    Repo createQuestionRepository();

    @Test
    default void addStringTest() {
        var questionService = createQuestionService();
        var questionRepository = createQuestionRepository();
        questionService.add(Q1, A1);
        verify(questionRepository, times(1)).add(QUESTION1);
    }

    @Test
    default void addQuestionTest() {
        var questionService = createQuestionService();
        var questionRepository = createQuestionRepository();
        questionService.add(QUESTION1);
        verify(questionRepository, times(1)).add(QUESTION1);
    }

    @Test
    default void removeStringTest() {
        var questionService = createQuestionService();
        var questionRepository = createQuestionRepository();
        questionService.remove(Q1, A1);
        verify(questionRepository, times(1)).remove(QUESTION1);
    }

    @Test
    default void removeQuestionTest() {
        var questionService = createQuestionService();
        var questionRepository = createQuestionRepository();
        questionService.remove(QUESTION1);
        verify(questionRepository, times(1)).remove(QUESTION1);
    }

    @Test
    default void getAllTest() {
        var questionService = createQuestionService();
        var questionRepository = createQuestionRepository();
        questionService.getAll();
        verify(questionRepository, times(1)).getAll();
    }

    @Test
    default void getRandomQuestionTest() {
        var questionService = createQuestionService();
        var questionRepository = createQuestionRepository();
        when(questionService.getAll()).thenReturn(QUESTIONS);
        int count = QUESTIONS.size() * 1000;
        List<Question> actual = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            actual.add(questionService.getRandomQuestion());
        }
        verify(questionRepository, times(count)).getAll();
        assertThat(actual).containsAll(QUESTIONS);
    }

    @Test
    default void getRandomQuestionNegativeTest() {
        var questionService = createQuestionService();
        assertThatExceptionOfType(QuestionsIsEmptyException.class)
                .isThrownBy(questionService::getRandomQuestion);
    }
}