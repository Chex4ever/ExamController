package pro.sky.exever.examiner;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
