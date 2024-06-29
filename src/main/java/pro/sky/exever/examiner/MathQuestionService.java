package pro.sky.exever.examiner;

import org.springframework.stereotype.Service;
import pro.sky.exever.examiner.exception.QuestionsIsEmptyException;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MathQuestionService implements QuestionService {
    private final MathQuestionRepository mathQuestionRepository;

    public MathQuestionService(MathQuestionRepository mathQuestionRepository) {

        this.mathQuestionRepository = mathQuestionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        return mathQuestionRepository.add(question);
    }

    @Override
    public Question remove(String question, String answer) {
        return remove(new Question(question, answer));
    }

    @Override
    public Question remove(Question question) {
        return mathQuestionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return mathQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Set<Question> randomQuestions = new HashSet<>(List.copyOf(getAll()));
        if (randomQuestions.isEmpty()) {
            throw new QuestionsIsEmptyException("Список вопросов пустой");
        }
        int rnd = ThreadLocalRandom.current().nextInt(randomQuestions.size());
        return randomQuestions.stream().skip(rnd).findFirst().orElseThrow();
    }
}
