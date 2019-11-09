package hu.uni.eszterhazy.service.impl;

import hu.uni.eszterhazy.dao.CarDAO;
import hu.uni.eszterhazy.model.Car;
import hu.uni.eszterhazy.service.CarService;
import hu.uni.eszterhazy.service.exceptions.CarAlreadyExists;
import hu.uni.eszterhazy.service.exceptions.CarNotFound;

import java.io.IOException;
import java.util.Collection;

public class CarServiceImpl implements CarService {
    CarDAO dao;

    public CarServiceImpl(CarDAO dao) {
        this.dao = dao;
    }

    public Collection<Car> listAllCars() throws IOException {
        return dao.readCars();
    }

    public Car getCarByVIN(String vin) throws CarNotFound, IOException {
        return dao.readCarById(vin);
    }

    public void addCar(Car car) throws CarAlreadyExists, IOException {
        dao.createCar(car);

    }

    public void updateCar(Car car) throws CarNotFound, IOException {
        dao.updateCar(car);

    }

    public void deleteCar(Car car) throws CarNotFound, IOException {
        dao.removeCar(car);

    }
}
