package pro.sky.exever.examiner;

import static org.mockito.Mockito.mock;

public class MathQuestionServiceTest implements QuestionServiceTest<MathQuestionService, MathQuestionRepository> {
    MathQuestionRepository mathQuestionRepository = mock(MathQuestionRepository.class);

    @Override
    public MathQuestionService createQuestionService() {
        return new MathQuestionService(mathQuestionRepository);
    }

    @Override
    public MathQuestionRepository createQuestionRepository() {
        return mathQuestionRepository;
    }

}
