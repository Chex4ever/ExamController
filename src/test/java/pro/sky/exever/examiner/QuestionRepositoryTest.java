package pro.sky.exever.examiner;

import org.junit.jupiter.api.Test;
import pro.sky.exever.examiner.exception.QuestionAlreadyExistsException;
import pro.sky.exever.examiner.exception.QuestionNotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static pro.sky.exever.examiner.constant.Questions.*;

public interface QuestionRepositoryTest<T extends QuestionRepository> {
    T createQuestionRepository();

    @Test
    default void addTest() {
        var questionRepository = createQuestionRepository();

        Question actualResp1 = questionRepository.add(QUESTION1);
        Question actualResp2 = questionRepository.add(QUESTION2);
        questionRepository.add(QUESTION3);
        questionRepository.add(QUESTION4);

        Collection<Question> actual = questionRepository.getAll();

        assertThat(actualResp1).isEqualTo(QUESTION1);
        assertThat(actualResp2).isEqualTo(QUESTION2);
        assertThat(actual).containsExactlyInAnyOrderElementsOf(QUESTIONS);
    }

    @Test
    default void addNegativeTest() {
        var questionRepository = createQuestionRepository();

        questionRepository.add(QUESTION1);
        assertThatExceptionOfType(QuestionAlreadyExistsException.class).isThrownBy(() -> questionRepository.add(QUESTION1));
    }

    @Test
    default void removeTest() {
        var questionRepository = createQuestionRepository();

        questionRepository.add(QUESTION1);
        questionRepository.add(QUESTION2);
        questionRepository.add(QUESTION3);

        Question actualResp = questionRepository.remove(QUESTION1);
        Collection<Question> expected = new HashSet<>(Arrays.asList(QUESTION2, QUESTION3));

        Collection<Question> actual = questionRepository.getAll();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
        assertThat(actualResp).isEqualTo(QUESTION1);
    }

    @Test
    default void removeNegativeTest() {
        var questionRepository = createQuestionRepository();

        assertThatExceptionOfType(QuestionNotFoundException.class).isThrownBy(() -> questionRepository.remove(QUESTION4));
    }

    @Test
    default void getAll() {
        var questionRepository = createQuestionRepository();

        questionRepository.add(QUESTION1);
        questionRepository.add(QUESTION2);
        Collection<Question> expected = new HashSet<>(Arrays.asList(QUESTION1, QUESTION2));
        Collection<Question> actual = questionRepository.getAll();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}