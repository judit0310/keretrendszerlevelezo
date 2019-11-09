package hu.uni.eszterhazy.exceptions;

public class VINNotMatching extends Throwable {
    public VINNotMatching(String vin) {
        super("Not maching VIN with the standard: "+vin);
    }
}
