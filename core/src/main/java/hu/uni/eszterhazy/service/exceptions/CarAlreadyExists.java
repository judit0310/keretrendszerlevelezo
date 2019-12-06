package hu.uni.eszterhazy.service.exceptions;

public class CarAlreadyExists extends Exception {
    public CarAlreadyExists(String vin) {
        super(vin);
    }
}
