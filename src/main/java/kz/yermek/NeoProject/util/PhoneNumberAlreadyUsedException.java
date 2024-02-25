package kz.yermek.NeoProject.util;

public class PhoneNumberAlreadyUsedException extends RuntimeException{
    public PhoneNumberAlreadyUsedException(String phoneNumberAlreadyUsed) {
        super(phoneNumberAlreadyUsed);
    }
}
