package hu.uni.eszterhazy.service.impl;

import hu.uni.eszterhazy.dao.CarDAO;
import hu.uni.eszterhazy.model.Car;
import hu.uni.eszterhazy.service.CarService;
import hu.uni.eszterhazy.service.exceptions.InvalidRange;
import hu.uni.eszterhazy.service.exceptions.CarAlreadyExists;
import hu.uni.eszterhazy.service.exceptions.CarNotFound;

import java.io.IOException;
import java.util.ArrayList;
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

    public Collection<Car> getCarsBetweenYears(int fromYear, int toYear) throws IOException, InvalidRange {
        if(toYear<fromYear){
            throw new InvalidRange(fromYear+"-"+toYear);
        }
        Collection<Car> cars = listAllCars();
        Collection<Car> result = new ArrayList<Car>();
        for (Car c : cars) {
            if(c.getYear_of_maufacturing()>=fromYear
                    && c.getYear_of_maufacturing()<=toYear){
                result.add(c);
            }

        }
        return result;

    }
}
