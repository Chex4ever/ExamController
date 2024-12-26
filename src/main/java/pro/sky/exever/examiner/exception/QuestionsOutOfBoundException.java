package pro.sky.exever.examiner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionsOutOfBoundException extends RuntimeException {
    public QuestionsOutOfBoundException(String message) {
        super(message);
    }
}
