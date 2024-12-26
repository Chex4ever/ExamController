package pro.sky.exever.examiner;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import pro.sky.exever.examiner.exception.QuestionAlreadyExistsException;
import pro.sky.exever.examiner.exception.QuestionNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MathQuestionRepository implements QuestionRepository {
    Set<Question> mathQuestions = new HashSet<>();

    @PostConstruct
    private void init() {
        add(new Question("Вопрос по математике 1", "Ответ по математике 1"));
        add(new Question("Вопрос по математике 2", "Ответ по математике 2"));
        add(new Question("Вопрос по математике 3", "Ответ по математике 3"));
        add(new Question("Вопрос по математике 4", "Ответ по математике 4"));
        add(new Question("Вопрос по математике 5", "Ответ по математике 5"));
    }

    @Override
    public Question add(Question question) {
        if (!mathQuestions.add(question)) {
            throw new QuestionAlreadyExistsException("Такой вопрос уже есть в базе: " + question);
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!mathQuestions.remove(question)) {
            throw new QuestionNotFoundException("Такого вопроса нет в базе: " + question);
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return mathQuestions;
    }
}
