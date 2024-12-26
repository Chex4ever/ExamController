package pro.sky.exever.examiner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.exever.examiner.exception.QuestionsOutOfBoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;
import static pro.sky.exever.examiner.constant.Questions.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceTest {

    JavaQuestionService javaQuestionService = Mockito.mock(JavaQuestionService.class);
    MathQuestionService mathQuestionService = Mockito.mock(MathQuestionService.class);

    @InjectMocks
    ExaminerServiceImpl examinerService;

    @Test
    void getQuestionsTest() {
        when(javaQuestionService.getAll()).thenReturn(QUESTIONS);
        when(mathQuestionService.getAll()).thenReturn(QUESTIONS2);
        when(javaQuestionService.getRandomQuestion()).thenReturn(QUESTION1, QUESTION1, QUESTION4, QUESTION2, QUESTION2, QUESTION3);
        when(mathQuestionService.getRandomQuestion()).thenReturn(QUESTION5, QUESTION6, QUESTION6, QUESTION8, QUESTION6, QUESTION7);
        Collection<Question> expected = java.util.stream.Stream.concat(QUESTIONS.stream(), QUESTIONS2.stream()).collect(Collectors.toCollection(HashSet::new));

        Collection<Question> actual = examinerService.getQuestions(8);

        Assertions.assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
        verify(javaQuestionService, times(6)).getRandomQuestion();
        verify(mathQuestionService, times(6)).getRandomQuestion();
    }

    @Test
    void getQuestionsNegativeTest() {
        assertThatExceptionOfType(QuestionsOutOfBoundException.class).isThrownBy(() -> examinerService.getQuestions(5));
    }
}