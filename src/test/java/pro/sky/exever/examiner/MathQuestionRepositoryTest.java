package pro.sky.exever.examiner;

public class MathQuestionRepositoryTest implements QuestionRepositoryTest<MathQuestionRepository> {

    @Override
    public MathQuestionRepository createQuestionRepository() {
        return new MathQuestionRepository();
    }
}
