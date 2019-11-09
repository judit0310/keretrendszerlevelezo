package hu.uni.eszterhazy.dao;

import hu.uni.eszterhazy.model.Car;
import hu.uni.eszterhazy.service.exceptions.CarAlreadyExists;
import hu.uni.eszterhazy.service.exceptions.CarNotFound;

import java.io.IOException;
import java.util.Collection;

public interface CarDAO {

    Collection<Car> readCars() throws IOException;

    Car readCarById(String id) throws IOException, CarNotFound;

    void createCar(Car car) throws IOException, CarAlreadyExists;

    void updateCar(Car car) throws IOException, CarNotFound;

    void removeCar(Car car) throws IOException, CarNotFound;
}
