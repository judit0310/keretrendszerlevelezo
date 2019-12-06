package hu.uni.eszterhazy.service.exceptions;

public class CarNotFound extends Exception {
    public CarNotFound(String id) {
        super(id);
    }
}
