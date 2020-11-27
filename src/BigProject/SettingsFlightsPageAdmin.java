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
            "Name",
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
    public SettingsFlightsPageAdmin(MainFrameAdmin mainFrameAdmin){
        this.mainFrameAdmin = mainFrameAdmin;
        setSize(800, 600);
        setLayout(null);

        table = new JTable();
        table.setFont(new Font("Calibri", Font.PLAIN, 12));
        table.setRowHeight(30);


        scrollPane = new JScrollPane(table);
        scrollPane.setSize(300,200);
        scrollPane.setLocation(200,20);
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

        aircraftBox = new JComboBox(mainFrameAdmin.getAircraftsNames().toArray());
        aircraftBox.setBounds(400, 260, 200, 20);
        add(aircraftBox);

        JLabel departureCityIdLabel = new JLabel("Departure city");
        departureCityIdLabel.setBounds(200, 290, 200, 20);
        add(departureCityIdLabel);

        departureCityBox = new JComboBox(mainFrameAdmin.getCitiesNames().toArray());
        departureCityBox.setBounds(400, 290, 200, 20);
        add(departureCityBox);

        JLabel arrivalCityIdLabel = new JLabel("Arrival city");
        arrivalCityIdLabel.setBounds(200, 320, 200, 20);
        add(arrivalCityIdLabel);

        arrivalCityBox = new JComboBox(mainFrameAdmin.getCitiesNames().toArray());
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

        addButton = new JButton("ADD AIRCRAFT");
        addButton.setBounds(50, 500, 150, 40);
        add(addButton);

        removeButton = new JButton("REMOVE AIRCRAFT");
        removeButton.setBounds(210, 500, 150, 40 );
        add(removeButton);

        editButton = new JButton("EDIT AIRCRAFT");
        editButton.setBounds(370, 500, 150, 40);
        add(editButton);


        backButton = new JButton("BACK");
        backButton.setBounds(530, 500, 150, 40 );
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrameAdmin.getSettingsAircraftsPageAdmin().setVisible(false);
                mainFrameAdmin.getMainMenuPageAdmin().setVisible(true);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int aircraftId = mainFrameAdmin.getAircraftId(aircraftBox.getSelectedItem().toString());
                int departureCityId = mainFrameAdmin.getCityId(departureCityBox.getSelectedItem().toString());
                int arrivalCityId = mainFrameAdmin.getCityId(arrivalCityBox.getSelectedItem().toString());
                String departureTime = departureTimeField.getText();
                int epp = Integer.parseInt(economPlacePriceField.getText());
                int bpp = Integer.parseInt(businessPlacePriceField.getText());
                if(verifTextAdd()){
                    Flight flight = new Flight(null, aircraftId, departureCityId, arrivalCityId, departureTime, epp, bpp);
                    mainFrameAdmin.addFlight(flight);
                }
            }
        });
    }
    public void generateTable(ArrayList<Flight> flights){
        Object [][] data = new Object[flights.size()][5];
        for(int i = 0; i < flights.size(); i++){
            data[i][0] = flights.get(i).getId();
            data[i][1] = flights.get(i).getAircraft_id();
            data[i][2] = flights.get(i).getDeparture_city_id();
            data[i][3] = flights.get(i).getArrival_city_id();
            data[i][5] = flights.get(i).getEconom_place_price();
            data[i][6] = flights.get(i).getBusiness_place_price();
        }
        model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
    }
    public boolean verifTextAdd(){
        if(aircraftBox.getSelectedItem().equals("")|| departureCityBox.getSelectedItem().equals("")||arrivalCityBox.getSelectedItem().equals("")||economPlacePriceField.getText().equals("")||businessPlacePriceField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "One or more empty field");
            return false;
        }
        else{
            JOptionPane.showMessageDialog(null, "New flight added");
            return true;
        }
    }
}
