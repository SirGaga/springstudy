package com.zhangjie.annotation.bean;

public class Color {
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String toString() {
        return "Color{" +
                "car=" + car +
                '}';
    }
}
