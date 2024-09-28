package by.it_academy.jd2.exception;

public class DeleteParticipantException extends RuntimeException{

    private String message = "Невозможно удалить участника, т.к. за него уже проголосовали";


    public DeleteParticipantException(String message) {
        this.message = message;
    }

    public DeleteParticipantException() {
    }


    @Override
    public String getMessage() {
        return message;
    }

}
