package pro.sky.exever.examiner;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import pro.sky.exever.examiner.exception.QuestionAlreadyExistsException;
import pro.sky.exever.examiner.exception.QuestionNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository {
    Set<Question> javaQuestions = new HashSet<>();

    @PostConstruct
    private void init() {
        add(new Question("Вопрос по Java 2", "Ответ по Java 2"));
        add(new Question("Вопрос по Java 3", "Ответ по Java 3"));
        add(new Question("Вопрос по Java 4", "Ответ по Java 4"));
        add(new Question("Вопрос по Java 5", "Ответ по Java 5"));
        add(new Question("Вопрос по Java 1", "Ответ по Java 1"));
    }

    @Override
    public Question add(Question question) {
        if (!javaQuestions.add(question)) {
            throw new QuestionAlreadyExistsException("Такой вопрос уже есть в базе: " + question);
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!javaQuestions.remove(question)) {
            throw new QuestionNotFoundException("Такого вопроса нет в базе: " + question);
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(javaQuestions);
    }
}
