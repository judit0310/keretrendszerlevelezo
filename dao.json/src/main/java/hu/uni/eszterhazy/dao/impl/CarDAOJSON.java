package hu.uni.eszterhazy.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hu.uni.eszterhazy.dao.CarDAO;
import hu.uni.eszterhazy.model.Car;
import hu.uni.eszterhazy.service.exceptions.CarAlreadyExists;
import hu.uni.eszterhazy.service.exceptions.CarNotFound;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CarDAOJSON implements CarDAO {
    File jsonfile;
    ObjectMapper mapper;

    public CarDAOJSON(String filepath) throws IOException {
        jsonfile = new File(filepath);
        if (!jsonfile.exists()) {
            jsonfile.createNewFile();
            FileWriter writer = new FileWriter(jsonfile);
            writer.write("[]");
            writer.close();
        }
        if (jsonfile.getTotalSpace() <= 0) {
            FileWriter writer = new FileWriter(jsonfile);
            writer.write("[]");
            writer.close();
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }


    public Collection<Car> readCars() throws IOException {
        Collection<Car> result = new ArrayList<Car>();
        TypeReference type = new TypeReference<ArrayList<Car>>() {
        };
        result = mapper.readValue(jsonfile, type);
        return result;
    }

    public Car readCarById(String id) throws IOException, CarNotFound {
        Collection<Car> cars = readCars();
        for (Car c : cars) {
            if(c.getVin().equalsIgnoreCase(id)){
                return c;
            }
        }
        throw new CarNotFound(id);
    }

    public void createCar(Car car) throws IOException, CarAlreadyExists {
        Collection<Car> cars = readCars();
        try {
            readCarById(car.getVin());
            throw new CarAlreadyExists(car.getVin());
        } catch (CarNotFound carNotFound) {
            cars.add(car);
            mapper.writeValue(jsonfile, cars);
        }

    }

    public void updateCar(Car car) throws IOException, CarNotFound {
        Collection<Car> cars = readCars();
        Car c = readCarById(car.getVin());
        List<Car> carlist = new ArrayList<Car>(cars);
        int index = carlist.indexOf(c);
        carlist.set(index, car);
        mapper.writeValue(jsonfile,carlist);

    }

    public void removeCar(Car car) throws IOException, CarNotFound {
        Collection<Car> cars = readCars();
        Car c = readCarById(car.getVin());
        cars.remove(c);
        mapper.writeValue(jsonfile, cars);
    }
}
