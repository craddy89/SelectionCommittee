package model.service.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = -2421124038734685946L;

    ServiceException(String message) {
        super(message);
    }
}