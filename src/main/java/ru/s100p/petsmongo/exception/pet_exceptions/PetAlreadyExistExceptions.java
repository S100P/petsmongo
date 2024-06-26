package ru.s100p.petsmongo.exception.pet_exceptions;

public class PetAlreadyExistExceptions extends RuntimeException {
    public PetAlreadyExistExceptions(String message) {
        super(message);
    }
}
