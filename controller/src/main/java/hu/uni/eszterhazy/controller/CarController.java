package hu.uni.eszterhazy.controller;

import hu.uni.eszterhazy.model.Car;
import hu.uni.eszterhazy.service.CarService;
import hu.uni.eszterhazy.service.exceptions.CarAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.util.Collection;

@Controller
public class CarController {
    CarService service;

    public CarController(@Autowired CarService service) {
        this.service = service;
    }

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello(){
        return "Hello";
    }

    @RequestMapping(value = "/count")
    @ResponseBody
    public int getCarNumber() throws IOException {
        return service.listAllCars().size();
    }

    @RequestMapping(value = "/listCars")
    @ResponseBody
    public Collection<Car> listCars() throws IOException {
        return service.listAllCars();
    }

    @RequestMapping(value = "/addCar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addCars(@RequestBody Car car) throws IOException, CarAlreadyExists {
        service.addCar(car);
        return "New Car added: "+car.getVin();
    }


}
