package pro.sky.exever.examiner;

import org.springframework.stereotype.Service;
import pro.sky.exever.examiner.exception.QuestionsOutOfBoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final JavaQuestionService javaQuestionService;
    private final MathQuestionService mathQuestionService;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService,
                               MathQuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        int javaQuestionsAmount = countJavaQuestionsAmount(amount);
        Collection<Question> ticketQuestions = new HashSet<>(amount);
        while (ticketQuestions.size() < javaQuestionsAmount) {
            ticketQuestions.add(javaQuestionService.getRandomQuestion());
        }
        while (ticketQuestions.size() < amount) {
            ticketQuestions.add(mathQuestionService.getRandomQuestion());
        }
        return ticketQuestions;
    }

    private int countJavaQuestionsAmount(int amount) {
        checkIsPositive(amount);
        int javaQuestionsCount = javaQuestionService.getAll().size();
        int mathQuestionsCount = mathQuestionService.getAll().size();
        checkOutOfBound(amount, javaQuestionsCount + mathQuestionsCount);
        int javaQuestionsMinAmount = Math.max(amount - mathQuestionsCount, 1);
        int javaQuestionsMaxAmount = Math.min(javaQuestionsCount + 1, amount);
        return ThreadLocalRandom.current().nextInt(javaQuestionsMinAmount, javaQuestionsMaxAmount);
    }

    private void checkIsPositive(int amount) {
        if (amount < 1) {
            throw new QuestionsOutOfBoundException("Запрошенно %d вопросов".formatted(amount));
        }
    }

    private void checkOutOfBound(int amount, int bound) {
        if (amount > bound) {
            throw new QuestionsOutOfBoundException("Запрошено слишком много вопросов (%d), у нас столько нет".formatted(amount));
        }
    }
}
