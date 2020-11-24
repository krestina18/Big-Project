package BigProject;

import java.io.Serializable;

public class PackageData implements Serializable {
    private String operationType;
    private City city;
    private String value;
    private Aircraft aircraft;


    public PackageData(String operationType, City city) {
        this.operationType = operationType;
        this.city = city;
    }

    public PackageData(String operationType) {
        this.operationType = operationType;
    }

    public PackageData(String operationType,String value) {
        this.operationType = operationType;
        this.value = value;
    }


    public PackageData(String operationType, Aircraft aircraft) {
        this.operationType = operationType;
        this.aircraft = aircraft;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }
}
