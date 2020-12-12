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
    private ArrayList<PackageDataFlight> packageDataFlights;
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

    public ArrayList<City> listOfCities(){
        PackageData pd = new PackageData("List of cities");
        try {
            outputStream.writeObject(pd);
            cities = new ArrayList<>();
            cities = (ArrayList<City>)inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }


    public void generateCitiesTable(){
        settingsCitiesMenuPageAdmin.generateTable(cities);
    }

    public ArrayList<Aircraft> listAicrafts(){
        PackageData pd = new PackageData("List of aircrafts");
        try {
            outputStream.writeObject(pd);
            aircrafts = new ArrayList<>();
            aircrafts = (ArrayList<Aircraft>)inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aircrafts;
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
        settingsAircraftsPageAdmin.generateTable(aircrafts);
    }

    public void addFlight(Flight flight){
        PackageData pd = new PackageData("Add flight", flight);
        try {
            outputStream.writeObject(pd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PackageDataFlight> getPackageDataFlight(){
        PackageData packageData = new PackageData("List of flights");
        try {
            outputStream.writeObject(packageData);
            packageDataFlights = new ArrayList<>();
            packageDataFlights = (ArrayList<PackageDataFlight>)inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packageDataFlights;
    }
    public void generateFlightsTable(){
        settingsFlightsPageAdmin.generateTable(packageDataFlights);
    }

}
