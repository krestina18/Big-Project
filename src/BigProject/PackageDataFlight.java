package BigProject;

public class PackageDataFlight {
    private Flight flight;
    private Aircraft aircraft;
    private City departureCity;
    private City arrivalCity;


    public PackageDataFlight(Flight flight, Aircraft aircraft, City departureCity, City arrivalCity) {
        this.flight = flight;
        this.aircraft = aircraft;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(City arrivalCity) {
        this.arrivalCity = arrivalCity;
    }
}
