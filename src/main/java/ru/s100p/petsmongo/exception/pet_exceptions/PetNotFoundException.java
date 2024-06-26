package ru.s100p.petsmongo.exception.pet_exceptions;



public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException( String message) {
        super(message);
    }
}