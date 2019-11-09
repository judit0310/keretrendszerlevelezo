package hu.uni.eszterhazy.service;

import hu.uni.eszterhazy.service.exceptions.CarAlreadyExists;

import hu.uni.eszterhazy.model.Car;
import hu.uni.eszterhazy.service.exceptions.CarNotFound;

import java.io.IOException;
import java.util.Collection;

public interface CarService {
    Collection<Car> listAllCars() throws IOException;

    Car getCarByVIN(String vin) throws CarNotFound, IOException;

    void addCar(Car car) throws CarAlreadyExists, IOException;

    void updateCar(Car car) throws CarNotFound, IOException;

    void deleteCar(Car car) throws CarNotFound, IOException;

}
