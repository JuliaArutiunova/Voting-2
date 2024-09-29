package by.it_academy.jd2.validation.api;

import by.it_academy.jd2.dto.InfoFromClientDTO;

import java.util.List;

public interface IVotingFormValidator {
    void validate(InfoFromClientDTO info);
    List<Throwable> getErrors();
}
