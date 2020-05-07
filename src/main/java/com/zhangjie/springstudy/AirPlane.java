package com.zhangjie.springstudy;

public class AirPlane {

    private String fdj;
    private Integer yc;
    private String jzName;
    private String fjsName;
    private String personNumber;

    public String getFdj() {
        return fdj;
    }

    public void setFdj(String fdj) {
        this.fdj = fdj;
    }

    public Integer getYc() {
        return yc;
    }

    public void setYc(Integer yc) {
        this.yc = yc;
    }

    public String getJzName() {
        return jzName;
    }

    public void setJzName(String jzName) {
        this.jzName = jzName;
    }

    public String getFjsName() {
        return fjsName;
    }

    public void setFjsName(String fjsName) {
        this.fjsName = fjsName;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }


    public AirPlane(String fdj, Integer yc, String jzName, String fjsName, String personNumber) {
        this.fdj = fdj;
        this.yc = yc;
        this.jzName = jzName;
        this.fjsName = fjsName;
        this.personNumber = personNumber;
    }

    public AirPlane() {
    }

    public String toString() {
        return "AirPlane{" +
                "fdj='" + fdj + '\'' +
                ", yc=" + yc +
                ", jzName='" + jzName + '\'' +
                ", fjsName='" + fjsName + '\'' +
                ", personNumber='" + personNumber + '\'' +
                '}';
    }

}
