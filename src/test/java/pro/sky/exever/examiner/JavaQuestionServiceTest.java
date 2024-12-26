package pro.sky.exever.examiner;

import static org.mockito.Mockito.mock;

public class JavaQuestionServiceTest implements QuestionServiceTest<JavaQuestionService, JavaQuestionRepository> {
    JavaQuestionRepository javaQuestionRepository = mock(JavaQuestionRepository.class);

    @Override
    public JavaQuestionService createQuestionService() {
        return new JavaQuestionService(javaQuestionRepository);
    }

    @Override
    public JavaQuestionRepository createQuestionRepository() {
        return javaQuestionRepository;
    }

}
