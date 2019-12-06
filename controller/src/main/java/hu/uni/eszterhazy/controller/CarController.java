package hu.uni.eszterhazy.controller;

import hu.uni.eszterhazy.model.Car;
import hu.uni.eszterhazy.service.CarService;
import hu.uni.eszterhazy.service.exceptions.CarAlreadyExists;
import hu.uni.eszterhazy.service.exceptions.CarNotFound;
import hu.uni.eszterhazy.service.exceptions.InvalidRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.util.Collection;
import java.util.GregorianCalendar;

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

    @RequestMapping(value = "/car/{vin:[A-Za-z0-9]{17}}")
    @ResponseBody
    public Car getCarByID(@PathVariable String vin) throws IOException, CarNotFound {
        
        return service.getCarByVIN(vin);
    }

    @RequestMapping(value = "/listCars/years")
    @ResponseBody
    public Collection<Car> getCarsBetweenYears(
            @RequestParam(required = true) int fromYear,
            @RequestParam(required = true) int toYear) throws IOException, InvalidRange {
        return service.getCarsBetweenYears(fromYear,toYear);
    }

    @RequestMapping(value = "/listCars/{fromYear}-{toYear}")
    @ResponseBody
    public Collection<Car> getCarsBetweenYearsPath(
            @PathVariable int fromYear, @PathVariable int toYear
    ) throws IOException, InvalidRange {
        return service.getCarsBetweenYears(fromYear,toYear);
    }

}
