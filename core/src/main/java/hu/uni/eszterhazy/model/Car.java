package hu.uni.eszterhazy.model;

import hu.uni.eszterhazy.exceptions.DateNotAcceptable;
import hu.uni.eszterhazy.exceptions.VINNotMatching;
import hu.uni.eszterhazy.exceptions.YearOfManufacturingNotSet;

import java.awt.*;
import java.time.LocalDate;

public class Car {
    /**
     * Alvazszam
     */
    private String vin;
    /**
     * Uzemanyag
     */
    private Fuel fuel;
    /**
     * Marka
     */
    private String brand;
    /**
     * Model
     */
    private String model;
    /**
     * Valto tipusa
     */
    private GearShift gear_shift;
    private String color;
    /**
     * Fenyezes
     */
    private VarnishStyle varnish;
    private boolean crashed;
    /**
     * Gyartas eve
     */
    private int year_of_maufacturing =-1;
    /**
     * Forgalomba helyezes
     */
    private LocalDate date_of_registration;
    /**
     * Muszaki ervenyesseg
     */
    private LocalDate date_of_techical_validity;

    public Car() {
    }

    public Car(String vin, Fuel fuel, String brand,
               String model, GearShift gear_shift, String color,
               VarnishStyle varnish, boolean crashed, int year_of_maufacturing,
               LocalDate date_of_registration,
               LocalDate date_of_techical_validity)
            throws VINNotMatching, YearOfManufacturingNotSet, DateNotAcceptable {
        setVin(vin);
        this.fuel = fuel;
        this.brand = brand;
        this.model = model;
        this.gear_shift = gear_shift;
        this.color = color;
        this.varnish = varnish;
        this.crashed = crashed;
        this.year_of_maufacturing = year_of_maufacturing;
        setDate_of_registration(date_of_registration);
        this.date_of_techical_validity = date_of_techical_validity;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) throws VINNotMatching {
        if(!vin.matches("([A-Z]|[a-z]|\\d){17}")){
            throw new VINNotMatching(vin);
        }
        this.vin = vin;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public GearShift getGear_shift() {
        return gear_shift;
    }


    public void setGear_shift(GearShift gear_shift) {
        this.gear_shift = gear_shift;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VarnishStyle getVarnish() {
        return varnish;
    }

    public void setVarnish(VarnishStyle varnish) {
        this.varnish = varnish;
    }

    public boolean isCrashed() {
        return crashed;
    }

    public void setCrashed(boolean crashed) {
        this.crashed = crashed;
    }

    public int getYear_of_maufacturing() {
        return year_of_maufacturing;
    }

    public void setYear_of_maufacturing(int year_of_maufacturing) {
        this.year_of_maufacturing = year_of_maufacturing;
    }

    public LocalDate getDate_of_registration() {
        return date_of_registration;
    }

    public void setDate_of_registration(LocalDate date_of_registration) throws YearOfManufacturingNotSet, DateNotAcceptable {
        if(this.year_of_maufacturing <0){
            throw new YearOfManufacturingNotSet();
        }
        if(date_of_registration.getYear()< this.year_of_maufacturing){
            throw new DateNotAcceptable(date_of_registration);
        }
        this.date_of_registration = date_of_registration;
    }

    public LocalDate getDate_of_techical_validity() {
        return date_of_techical_validity;
    }

    public void setDate_of_techical_validity(LocalDate date_of_techical_validity) {
        this.date_of_techical_validity = date_of_techical_validity;
    }
}
