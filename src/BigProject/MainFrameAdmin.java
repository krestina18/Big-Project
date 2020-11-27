package BigProject;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MainFrameAdmin extends JFrame {
    private MainMenuPageAdmin mainMenuPageAdmin;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Socket socket;
    private ArrayList<City> cities;
    private ArrayList<Aircraft> aircrafts;
    private ArrayList<Flight> flights;
    private ArrayList<String> aircraftsNames;
    private ArrayList<String> citiesNames;
    private int aircraftId;
    private int cityId;
    private SettingsCitiesMenuPageAdmin settingsCitiesMenuPageAdmin;
    private SettingsAircraftsPageAdmin settingsAircraftsPageAdmin;
    private SettingsFlightsPageAdmin settingsFlightsPageAdmin;
    public MainFrameAdmin() throws Exception {
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Air ticket sales system");

        Socket socket = new Socket("127.0.0.1", 2050);

        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainMenuPageAdmin = new MainMenuPageAdmin(this);
        add(mainMenuPageAdmin);
        mainMenuPageAdmin.setVisible(true);

        settingsCitiesMenuPageAdmin = new SettingsCitiesMenuPageAdmin(this);
        add(settingsCitiesMenuPageAdmin);
        settingsCitiesMenuPageAdmin.setVisible(false);

        settingsAircraftsPageAdmin = new SettingsAircraftsPageAdmin(this);
        add(settingsAircraftsPageAdmin);
        settingsAircraftsPageAdmin.setVisible(false);

        settingsFlightsPageAdmin = new SettingsFlightsPageAdmin(this);
        add(settingsFlightsPageAdmin);
        settingsFlightsPageAdmin.setVisible(false);

    }

    public MainMenuPageAdmin getMainMenuPageAdmin() {
        return mainMenuPageAdmin;
    }

    public void setMainMenuPageAdmin(MainMenuPageAdmin mainMenuPageAdmin) {
        this.mainMenuPageAdmin = mainMenuPageAdmin;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public SettingsCitiesMenuPageAdmin getSettingsCitiesMenuPageAdmin() {
        return settingsCitiesMenuPageAdmin;
    }

    public void setSettingsCitiesMenuPageAdmin(SettingsCitiesMenuPageAdmin settingsCitiesMenuPageAdmin) {
        this.settingsCitiesMenuPageAdmin = settingsCitiesMenuPageAdmin;
    }

    public SettingsAircraftsPageAdmin getSettingsAircraftsPageAdmin() {
        return settingsAircraftsPageAdmin;
    }

    public void setSettingsAircraftsPageAdmin(SettingsAircraftsPageAdmin settingsAircraftsPageAdmin) {
        this.settingsAircraftsPageAdmin = settingsAircraftsPageAdmin;
    }

    public SettingsFlightsPageAdmin getSettingsFlightsPageAdmin() {
        return settingsFlightsPageAdmin;
    }

    public void setSettingsFlightsPageAdmin(SettingsFlightsPageAdmin settingsFlightsPageAdmin) {
        this.settingsFlightsPageAdmin = settingsFlightsPageAdmin;
    }

    public void addCity(City city)  {
        PackageData pd = new PackageData("Add city", city);
        try {
            outputStream.writeObject(pd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeCity(String value){
        PackageData pd = new PackageData("Remove city", value);
        try {
            outputStream.writeObject(pd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCity(City city){
        PackageData pd = new PackageData("Update city", city);
        try {
            outputStream.writeObject(pd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateAircraft(Aircraft aircraft){
        PackageData pd = new PackageData("Update aircraft", aircraft);
        try {
            outputStream.writeObject(pd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listOfCities(){
        PackageData pd = new PackageData("List of cities");
        try {
            outputStream.writeObject(pd);
            cities = new ArrayList<>();
            cities = (ArrayList<City>)inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void generateCitiesTable(){
        listOfCities();
        settingsCitiesMenuPageAdmin.generateTable(cities);
    }

    public void listAicrafts(){
        PackageData pd = new PackageData("List of aircrafts");
        try {
            outputStream.writeObject(pd);
            aircrafts = new ArrayList<>();
            aircrafts = (ArrayList<Aircraft>)inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void listFlights(){
        PackageData pd = new PackageData("List of flights");
        try {
            outputStream.writeObject(pd);
            flights = new ArrayList<>();
            flights = (ArrayList<Flight>)inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void generateFlightsTable(){
        listFlights();
        settingsFlightsPageAdmin.generateTable(flights);
    }

    public void addAircraft(Aircraft aircraft){
        PackageData pd = new PackageData("Add aircraft", aircraft);
        try {
            outputStream.writeObject(pd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeAircraft(String value){
        PackageData pd = new PackageData("Remove aircraft", value);
        try {
            outputStream.writeObject(pd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateAircraftsTable(){
        listAicrafts();
        settingsAircraftsPageAdmin.generateTable(aircrafts);
    }

    public ArrayList<String> getAircraftsNames(){
        aircraftsNames = new ArrayList<>();
        PackageData pd = new PackageData("Get aircrafts names");
        try {
            outputStream.writeObject(pd);
            aircraftsNames = (ArrayList<String>)inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aircraftsNames;
    }
    public ArrayList<String> getCitiesNames(){
        citiesNames = new ArrayList<>();
        PackageData pd = new PackageData("Get cities names");
        try {
            outputStream.writeObject(pd);
            citiesNames = (ArrayList<String>)inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return citiesNames;
    }

    public int getAircraftId(String aircraftLabel){
        PackageDataAircraft pda = new PackageDataAircraft("Get aircraft id", aircraftLabel);
        try {
            outputStream.writeObject(pda);
            aircraftId = (Integer)inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aircraftId;
    }
    public int getCityId(String cityLabel){
        PackageDataCity pdc = new PackageDataCity("Get city id", cityLabel);
        try {
            outputStream.writeObject(pdc);
            cityId = (Integer)inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityId;
    }

    public void addFlight(Flight flight){
        PackageData pd = new PackageData("Add flight", flight);
        try {
            outputStream.writeObject(pd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
