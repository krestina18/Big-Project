package BigProject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class Server {
    private static Connection connection;
    public static void main(String[] args) throws Exception {
        ArrayList<City> cities = new ArrayList<>();
        ArrayList<String> aircraftsNames = new ArrayList<>();
        ArrayList<String> citiesNames = new ArrayList<>();
        ArrayList<Aircraft> aircrafts = new ArrayList<>();
        ServerSocket server = new ServerSocket(2050);
        System.out.println("Waiting for a client...");
        Socket socket = server.accept();
        System.out.println("Client Connected");

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/airportinformationsystem?useUnicode=true&serverTimezone=UTC","root", ""
        );
        System.out.println("Conneted");

        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

        PackageData pd = null;
        PackageDataAircraft pda = null;
        PackageDataCity pdc = null;
        while(true){
            pd = (PackageData)inputStream.readObject();
            pda = (PackageDataAircraft)inputStream.readObject();
            pdc = (PackageDataCity)inputStream.readObject();
            if(pd.getOperationType().equals("List of cities")){
                cities = getCities();
                outputStream.writeObject(cities);
            }
            if(pd.getOperationType().equals("Add city")){
                addCity(pd.getCity());
            }
            if(pd.getOperationType().equals("Remove city")){
                deleteCity(pd.getValue());
            }
            if(pd.getOperationType().equals("Update city")){
                updateCity(pd.getCity());
            }
            if(pd.getOperationType().equals("List of aircrafts")){
                aircrafts = getAircrafts();
                outputStream.writeObject(aircrafts);
            }
            if(pd.getOperationType().equals("Add aircraft")){
                addAircraft(pd.getAircraft());
            }
            if(pd.getOperationType().equals("Remove aircraft")){
                deleteAircraft(pd.getValue());
            }
            if(pd.getOperationType().equals("Get aircrafts names")){
                aircraftsNames = getAircraftName();
                outputStream.writeObject(aircraftsNames);
            }
            if(pd.getOperationType().equals("Get cities names")){
                citiesNames = getCityName();
                outputStream.writeObject(citiesNames);
            }
            if(pd.getOperationType().equals("Update aircraft")){
                updateAircraft(pd.getAircraft());
            }
            if(pda.getOperationType().equals("Get aircraft id")){
                outputStream.writeObject(getAircraftId(pda.getAircraftLabel()));
            }
            if(pdc.getOperationType().equals("Get city id")){
                outputStream.writeObject(getCityId(pdc.getCityLabel()));
            }
            if(pd.getOperationType().equals("Add flight")){
                addFlight(pd.getFlight());
            }
        }
    }

    private static ArrayList<City> getCities(){
        ArrayList<City> cities = new ArrayList<>();
        try{
            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM cities");
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Long id = result.getLong("id");
                String name = result.getString("name");
                String country = result.getString("country");
                String shortName = result.getString("short_name");
                cities.add(new City(id, name,country,shortName));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return cities;
    }
    public static void addCity(City city){
        try{
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO cities (id, name, country, short_name) VALUES (NULL, ?, ?, ?)");
            statement.setString(1, city.getName());
            statement.setString(2, city.getCountry());
            statement.setString(3, city.getShort_name());
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void deleteCity(String value){
        try{
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM cities WHERE id = "+ value);
            statement.executeUpdate();
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void addAircraft(Aircraft aircraft){
        try{
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO aircrafts (id, name, model, business_class_capacity, econom_class_capacity) VALUES (NULL, ?, ?, ?, ?)");
            statement.setString(1, aircraft.getName());
            statement.setString(2, aircraft.getModel());
            statement.setInt(3, aircraft.getBusiness_class_capacity());
            statement.setInt(4, aircraft.getEconom_class_capacity());
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static ArrayList<Aircraft> getAircrafts(){
        ArrayList<Aircraft> aircrafts = new ArrayList<>();
        try{
            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM aircrafts");
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Long id = result.getLong("id");
                String name = result.getString("name");
                String model = result.getString("model");
                int bcc = result.getInt("business_class_capacity");
                int ecc = result.getInt("econom_class_capacity");
                aircrafts.add(new Aircraft(id, name,model,bcc, ecc));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return aircrafts;
    }
    private static void deleteAircraft(String value){
        try{
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM aircrafts WHERE id = "+ value);
            statement.executeUpdate();
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void updateCity(City city){
        try{
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE cities SET name = ?, country = ?, short_name = ? WHERE id = ?");
            statement.setString(1, city.getName());
            statement.setString(2, city.getCountry());
            statement.setString(3, city.getShort_name());
            statement.setLong(4, city.getId());
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void updateAircraft(Aircraft aircraft){
        try{
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE aircrafts SET name = ?, model = ?, business_class_capacity = ?, econom_class_capacity = ? WHERE id = ?");
            statement.setString(1, aircraft.getName());
            statement.setString(2, aircraft.getModel());
            statement.setInt(3, aircraft.getBusiness_class_capacity());
            statement.setInt(4, aircraft.getEconom_class_capacity());
            statement.setLong(5, aircraft.getId());
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getAircraftName(){
        ArrayList<String> aircraftsNames = new ArrayList<>();
        try {
            PreparedStatement statement =
                    connection.prepareStatement("SELECT name FROM aircrafts");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                String name = result.getString("name");
                aircraftsNames.add(name);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aircraftsNames;
    }

    public static ArrayList<String> getCityName(){
        ArrayList<String> citiesNames = new ArrayList<>();
        try {
            PreparedStatement statement =
                    connection.prepareStatement("SELECT name FROM cities");
            ResultSet result = statement.executeQuery();
            while (result.next()){
                String name = result.getString("name");
                citiesNames.add(name);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return citiesNames;
    }

    public static int getAircraftId(String aircraftLabel){
        int aircraftId = 0;

        try {
            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM aircrafts WHERE name = ?");
            statement.setString(1, aircraftLabel);
            ResultSet result = statement.executeQuery();

            if(result.next()){
                aircraftId = result.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aircraftId;
    }
    public static int getCityId(String cityLabel){
        int cityId = 0;
        try {
            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM cities WHERE name = ?");
            statement.setString(1, cityLabel);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                cityId = result.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityId;
    }

    public static void addFlight(Flight flight){
        try{
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO flights (id, aircraft_id, departure_city_id, arrival_city_id, departure_time, econom_place_price, business_place_price) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, flight.getAircraft_id());
            statement.setInt(2, flight.getDeparture_city_id());
            statement.setInt(3, flight.getArrival_city_id());
            statement.setString(4, flight.getDeparture_time());
            statement.setInt(5, flight.getEconom_place_price());
            statement.setInt(6, flight.getBusiness_place_price());
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
