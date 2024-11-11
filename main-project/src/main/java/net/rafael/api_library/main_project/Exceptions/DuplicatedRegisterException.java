package net.rafael.api_library.main_project.Exceptions;

public class DuplicatedRegisterException extends RuntimeException{
    public DuplicatedRegisterException(String message) {
        super(message);
    }
}
