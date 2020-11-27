package BigProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SettingsCitiesMenuPageAdmin extends JPanel {
    MainFrameAdmin mainFrameAdmin;
    private String[] columnNames = {
            "Id",
            "Name",
            "Country",
            "Short name"
    };
    JTable table;
    JScrollPane scrollPane;
    JButton addButton;
    JButton removeButton;
    JButton backButton;
    JButton editButton;
    DefaultTableModel model;
    JTextField nameField;
    JTextField countryField;
    JTextField shortNameField;
    JTextField idField;
    public SettingsCitiesMenuPageAdmin(MainFrameAdmin mainFrameAdmin){
        this.mainFrameAdmin = mainFrameAdmin;
        setSize(800, 600);
        setLayout(null);

        table = new JTable();
        table.setFont(new Font("Calibri", Font.PLAIN, 12));
        table.setRowHeight(30);


        scrollPane = new JScrollPane(table);
        scrollPane.setSize(300,200);
        scrollPane.setLocation(200,50);
        add(scrollPane);

        JLabel idLabel = new JLabel("Id");
        idLabel.setBounds(200, 280, 100, 40);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(300, 280, 200, 30);
        add(idField);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(200, 330, 100, 40);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(300, 330, 200, 30);
        add(nameField);

        JLabel countryLabel = new JLabel("Country");
        countryLabel.setBounds(200, 380, 100, 40);
        add(countryLabel);

        countryField = new JTextField();
        countryField.setBounds(300, 380, 200, 30);
        add(countryField);

        JLabel shortNameLabel = new JLabel("Short name");
        shortNameLabel.setBounds(200, 430, 100, 40);
        add(shortNameLabel);

        shortNameField = new JTextField();
        shortNameField.setBounds(300, 430, 200, 30);
        add(shortNameField);

        addButton = new JButton("ADD CITY");
        addButton.setBounds(50, 500, 150, 40);
        add(addButton);

        removeButton = new JButton("REMOVE CITY");
        removeButton.setBounds(210, 500, 150, 40 );
        add(removeButton);

        editButton = new JButton("EDIT CITY");
        editButton.setBounds(370, 500, 150, 40);
        add(editButton);


        backButton = new JButton("BACK");
        backButton.setBounds(530, 500, 150, 40 );
        add(backButton);


        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(row!=-1){
                    String value = table.getModel().getValueAt(row, 0).toString();
                    model.removeRow(row);
                    mainFrameAdmin.removeCity(value);
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String country = countryField.getText();
                String shortName = shortNameField.getText();
                if(verifText()){
                    City city = new City(null, name, country, shortName);
                    mainFrameAdmin.addCity(city);
                }
                nameField.setText("");
                countryField.setText("");
                shortNameField.setText("");
                mainFrameAdmin.generateCitiesTable();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.valueOf(idField.getText());
                String name = nameField.getText();
                String country = countryField.getText();
                String shortName = shortNameField.getText();
                if(verifTextUpdate()){
                    City city = new City(id, name, country, shortName);
                    mainFrameAdmin.updateCity(city);
                }
                mainFrameAdmin.generateCitiesTable();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrameAdmin.getSettingsCitiesMenuPageAdmin().setVisible(false);
                mainFrameAdmin.getMainMenuPageAdmin().setVisible(true);
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = table.getSelectedRow();
                model = (DefaultTableModel)table.getModel();
                idField.setText(model.getValueAt(rowIndex, 0).toString());
                nameField.setText(model.getValueAt(rowIndex, 1).toString());
                countryField.setText(model.getValueAt(rowIndex, 2).toString());
                shortNameField.setText(model.getValueAt(rowIndex, 3).toString());
            }
        });
    }

    public boolean verifText(){
        if(nameField.getText().equals("")|| countryField.getText().equals("")||shortNameField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "One or more empty field");
            return false;
        }else{
            JOptionPane.showMessageDialog(null, "New city added");
            return true;
        }
    }
    public boolean verifTextUpdate(){
        if(idField.getText().equals("")|| nameField.getText().equals("")|| countryField.getText().equals("")||shortNameField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "One or more empty field");
            return false;
        }else{
            JOptionPane.showMessageDialog(null, "City data updated");
            return true;
        }
    }

    public void generateTable(ArrayList<City> cities){
        Object [][] data = new Object[cities.size()][4];
        for(int i = 0; i < cities.size(); i++){
            data[i][0] = cities.get(i).getId();
            data[i][1] = cities.get(i).getName();
            data[i][2] = cities.get(i).getCountry();
            data[i][3] = cities.get(i).getShort_name();
        }
        model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
    }

}
