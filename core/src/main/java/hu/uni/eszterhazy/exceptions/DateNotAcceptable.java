package hu.uni.eszterhazy.exceptions;

import java.time.LocalDate;

public class DateNotAcceptable extends Throwable {
    public DateNotAcceptable(LocalDate date_of_registration) {
        super(date_of_registration.toString());
    }
}
