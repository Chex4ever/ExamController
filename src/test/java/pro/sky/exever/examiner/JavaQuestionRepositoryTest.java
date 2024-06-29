package pro.sky.exever.examiner;

public class JavaQuestionRepositoryTest implements QuestionRepositoryTest<JavaQuestionRepository> {

    @Override
    public JavaQuestionRepository createQuestionRepository() {
        return new JavaQuestionRepository();
    }
}
