package BigProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SettingsAircraftsPageAdmin extends JPanel {
    MainFrameAdmin mainFrameAdmin;
    private String[] columnNames = {
            "Id",
            "Name",
            "Model",
            "Business class capacity",
            "Econom class capacity"

    };
    JTable table;
    JScrollPane scrollPane;
    JButton addButton;
    JButton removeButton;
    JButton backButton;
    JButton editButton;
    DefaultTableModel model;
    public SettingsAircraftsPageAdmin(MainFrameAdmin mainFrameAdmin){
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

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(200, 280, 200, 30);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(400, 280, 200, 30);
        add(nameField);

        JLabel modelLabel = new JLabel("Model");
        modelLabel.setBounds(200, 330, 200, 30);
        add(modelLabel);

        JTextField modelField = new JTextField();
        modelField.setBounds(400, 330, 200, 30);
        add(modelField);

        JLabel bcLabel = new JLabel("Business class capacity");
        bcLabel.setBounds(200, 380, 200, 30);
        add(bcLabel);

        JTextField bcField = new JTextField();
        bcField.setBounds(400, 380, 200, 30);
        add(bcField);

        JLabel ecLabel = new JLabel("Econom class capacity");
        ecLabel.setBounds(200, 430, 200, 30);
        add(ecLabel);

        JTextField ecField = new JTextField();
        ecField.setBounds(400, 430, 200, 30);
        add(ecField);

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
                String name = nameField.getText();
                String model = modelField.getText();
                int bcc = Integer.parseInt(bcField.getText());
                int ecc = Integer.parseInt(ecField.getText());
                Aircraft aircraft = new Aircraft(null, name, model, bcc, ecc);
                mainFrameAdmin.addAircraft(aircraft);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(row!=-1){
                    String value = table.getModel().getValueAt(row, 0).toString();
                    model.removeRow(row);
                    mainFrameAdmin.removeAircraft(value);
                }
            }
        });
    }
    public void generateTable(ArrayList<Aircraft> aircrafts){
        Object [][] data = new Object[aircrafts.size()][5];
        for(int i = 0; i < aircrafts.size(); i++){
            data[i][0] = aircrafts.get(i).getId();
            data[i][1] = aircrafts.get(i).getName();
            data[i][2] = aircrafts.get(i).getModel();
            data[i][3] = aircrafts.get(i).getBusiness_class_capacity();
            data[i][4] = aircrafts.get(i).getEconom_class_capacity();

        }
        model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
    }
}
