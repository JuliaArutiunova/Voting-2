package by.it_academy.jd2.exception;

import java.util.List;

public class VotingFormException extends RuntimeException{
    private List <Throwable> errors;
    public VotingFormException(List <Throwable> errors){
        this.errors = errors;
    }

    public List<Throwable> getErrors() {
        return errors;
    }
}
