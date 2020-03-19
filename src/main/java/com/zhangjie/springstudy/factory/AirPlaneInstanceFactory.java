package com.zhangjie.springstudy.factory;

import com.zhangjie.springstudy.AirPlane;

public class AirPlaneInstanceFactory {

    public AirPlane getAirPlane(String jzName){
        AirPlane airPlane = new AirPlane();
        airPlane.setFdj("太行发动机");
        airPlane.setJzName(jzName);
        airPlane.setFjsName("王麻子");
        airPlane.setYc(500);
        airPlane.setPersonNumber("132");
        return airPlane;
    }

}
