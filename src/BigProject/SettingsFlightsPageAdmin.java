package BigProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SettingsFlightsPageAdmin extends JPanel {
    MainFrameAdmin mainFrameAdmin;
    private String[] columnNames = {
            "Id",
            "Aircraft",
            "Departure city",
            "Arrival city",
            "Departure time",
            "Econom place price",
            "Business place price"

    };
    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel model;
    JTextField idField;
    JTextField departureTimeField;
    JTextField economPlacePriceField;
    JTextField businessPlacePriceField;
    JButton addButton;
    JButton removeButton;
    JButton backButton;
    JButton editButton;
    JComboBox aircraftBox;
    JComboBox departureCityBox;
    JComboBox arrivalCityBox;
    public SettingsFlightsPageAdmin(MainFrameAdmin mainFrameAdmin) {
        this.mainFrameAdmin = mainFrameAdmin;
        setSize(800, 600);
        setLayout(null);

        table = new JTable();
        table.setFont(new Font("Calibri", Font.PLAIN, 12));
        table.setRowHeight(30);


        scrollPane = new JScrollPane(table);
        scrollPane.setSize(300, 200);
        scrollPane.setLocation(200, 20);
        add(scrollPane);

        JLabel idLabel = new JLabel("Id");
        idLabel.setBounds(200, 230, 200, 20);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(400, 230, 200, 20);
        add(idField);

        JLabel aircraftIdLabel = new JLabel("Aircraft");
        aircraftIdLabel.setBounds(200, 260, 200, 20);
        add(aircraftIdLabel);

        aircraftBox = new JComboBox(getAircraftsNames());
        aircraftBox.setBounds(400, 260, 200, 20);
        add(aircraftBox);


        JLabel departureCityIdLabel = new JLabel("Departure city");
        departureCityIdLabel.setBounds(200, 290, 200, 20);
        add(departureCityIdLabel);

        departureCityBox = new JComboBox(getCityNames());
        departureCityBox.setBounds(400, 290, 200, 20);
        add(departureCityBox);


        JLabel arrivalCityIdLabel = new JLabel("Arrival city");
        arrivalCityIdLabel.setBounds(200, 320, 200, 20);
        add(arrivalCityIdLabel);

        arrivalCityBox = new JComboBox(getCityNames());
        arrivalCityBox.setBounds(400, 320, 200, 20);
        add(arrivalCityBox);


        JLabel departureTimeLabel = new JLabel("Departure time");
        departureTimeLabel.setBounds(200, 350, 200, 20);
        add(departureTimeLabel);

        departureTimeField = new JTextField();
        departureTimeField.setBounds(400, 350, 200, 20);
        add(departureTimeField);

        JLabel economPlacePriceLabel = new JLabel("Econom price");
        economPlacePriceLabel.setBounds(200, 380, 200, 20);
        add(economPlacePriceLabel);

        economPlacePriceField = new JTextField();
        economPlacePriceField.setBounds(400, 380, 200, 20);
        add(economPlacePriceField);

        JLabel businessPlacePriceLabel = new JLabel("Business price");
        businessPlacePriceLabel.setBounds(200, 410, 200, 20);
        add(businessPlacePriceLabel);

        businessPlacePriceField = new JTextField();
        businessPlacePriceField.setBounds(400, 410, 200, 20);
        add(businessPlacePriceField);

        addButton = new JButton("ADD FLIGHT");
        addButton.setBounds(50, 500, 150, 40);
        add(addButton);

        removeButton = new JButton("REMOVE FLIGHT");
        removeButton.setBounds(210, 500, 150, 40);
        add(removeButton);

        editButton = new JButton("EDIT FLIGHT");
        editButton.setBounds(370, 500, 150, 40);
        add(editButton);


        backButton = new JButton("BACK");
        backButton.setBounds(530, 500, 150, 40);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrameAdmin.getSettingsFlightsPageAdmin().setVisible(false);
                mainFrameAdmin.getMainMenuPageAdmin().setVisible(true);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int aircraftId = Math.toIntExact(mainFrameAdmin.listAicrafts().get(aircraftBox.getSelectedIndex()).getId());
                int departureCityId = Math.toIntExact(mainFrameAdmin.listOfCities().get(departureCityBox.getSelectedIndex()).getId());
                int arrivalCityId = Math.toIntExact(mainFrameAdmin.listOfCities().get(arrivalCityBox.getSelectedIndex()).getId());
                String departureTime = departureTimeField.getText();
                int epp = Integer.parseInt(economPlacePriceField.getText());
                int bpp = Integer.parseInt(businessPlacePriceField.getText());
                Flight flight = new Flight(null, aircraftId, departureCityId, arrivalCityId, departureTime, epp, bpp);
                mainFrameAdmin.addFlight(flight);
            }
        });
    }

    public String [] getCityNames(){
        String [] names = new String[mainFrameAdmin.listOfCities().size()];
        for(int i = 0; i < mainFrameAdmin.listOfCities().size(); i++){
            names[i] = mainFrameAdmin.listOfCities().get(i).getName();
        }
        return names;
    }

    public String [] getAircraftsNames(){
        String [] aircrafts = new String[mainFrameAdmin.listAicrafts().size()];
        for(int i = 0; i < mainFrameAdmin.listAicrafts().size(); i++){
            aircrafts[i] = mainFrameAdmin.listAicrafts().get(i).getName()+" "+mainFrameAdmin.listAicrafts().get(i).getModel();
        }
        return aircrafts;
    }

    public void generateTable(ArrayList<PackageDataFlight> packageDataFlights){
        Object [][] data = new Object[packageDataFlights.size()][7];
        for(int i = 0; i < packageDataFlights.size(); i++){
            data[i][0] = packageDataFlights.get(i).getFlight().getId();
            data[i][1] = packageDataFlights.get(i).getAircraft().getName();
            data[i][2] = packageDataFlights.get(i).getDepartureCity().getName();
            data[i][3] = packageDataFlights.get(i).getArrivalCity().getName();
            data[i][4] = packageDataFlights.get(i).getFlight().getDeparture_time();
            data[i][5] = packageDataFlights.get(i).getFlight().getEconom_place_price();
            data[i][6] = packageDataFlights.get(i).getFlight().getBusiness_place_price();
        }
        model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
    }
}
