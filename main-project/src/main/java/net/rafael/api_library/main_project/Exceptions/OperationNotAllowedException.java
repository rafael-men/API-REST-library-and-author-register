package net.rafael.api_library.main_project.Exceptions;

public class OperationNotAllowedException extends RuntimeException{
    public OperationNotAllowedException(String message) {
        super(message);
    }
}
