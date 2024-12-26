package pro.sky.exever.examiner.constant;

import pro.sky.exever.examiner.Question;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public final class Questions {
    public static final String Q1 = "Вопрос1";
    public static final String Q2 = "Вопрос2";
    public static final String Q3 = "Вопрос3";
    public static final String Q4 = "Вопрос4";
    public static final String Q5 = "Вопрос5";
    public static final String Q6 = "Вопрос6";
    public static final String Q7 = "Вопрос7";
    public static final String Q8 = "Вопрос8";
    public static final String A1 = "Ответ1";
    public static final String A2 = "Ответ2";
    public static final String A3 = "Ответ3";
    public static final String A4 = "Ответ4";
    public static final String A5 = "Ответ5";
    public static final String A6 = "Ответ6";
    public static final String A7 = "Ответ7";
    public static final String A8 = "Ответ8";
    public static final Question QUESTION1 = new Question(Q1, A1);
    public static final Question QUESTION2 = new Question(Q2, A2);
    public static final Question QUESTION3 = new Question(Q3, A3);
    public static final Question QUESTION4 = new Question(Q4, A4);
    public static final Question QUESTION5 = new Question(Q5, A5);
    public static final Question QUESTION6 = new Question(Q6, A6);
    public static final Question QUESTION7 = new Question(Q7, A7);
    public static final Question QUESTION8 = new Question(Q8, A8);
    public static final Collection<Question> QUESTIONS = new HashSet<>(Arrays.asList(QUESTION1, QUESTION2, QUESTION3, QUESTION4));
    public static final Collection<Question> QUESTIONS2 = new HashSet<>(Arrays.asList(QUESTION5, QUESTION6, QUESTION7, QUESTION8));
}
