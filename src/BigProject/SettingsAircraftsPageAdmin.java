package BigProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    JTextField idField;
    JTextField nameField;
    JTextField modelField;
    JTextField bcField;
    JTextField ecField;
    public SettingsAircraftsPageAdmin(MainFrameAdmin mainFrameAdmin){
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
        idLabel.setBounds(200, 230, 200, 30);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(400, 230, 200, 30);
        add(idField);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(200, 280, 200, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(400, 280, 200, 30);
        add(nameField);

        JLabel modelLabel = new JLabel("Model");
        modelLabel.setBounds(200, 330, 200, 30);
        add(modelLabel);

        modelField = new JTextField();
        modelField.setBounds(400, 330, 200, 30);
        add(modelField);

        JLabel bcLabel = new JLabel("Business class capacity");
        bcLabel.setBounds(200, 380, 200, 30);
        add(bcLabel);

        bcField = new JTextField();
        bcField.setBounds(400, 380, 200, 30);
        add(bcField);

        JLabel ecLabel = new JLabel("Econom class capacity");
        ecLabel.setBounds(200, 430, 200, 30);
        add(ecLabel);

        ecField = new JTextField();
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

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = table.getSelectedRow();
                model = (DefaultTableModel)table.getModel();
                idField.setText(model.getValueAt(rowIndex, 0).toString());
                nameField.setText(model.getValueAt(rowIndex, 1).toString());
                modelField.setText(model.getValueAt(rowIndex, 2).toString());
                bcField.setText(model.getValueAt(rowIndex, 3).toString());
                ecField.setText(model.getValueAt(rowIndex, 4).toString());
            }
        });

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
                if(verifTextAdd()){
                    Aircraft aircraft = new Aircraft(null, name, model, bcc, ecc);
                    mainFrameAdmin.addAircraft(aircraft);
                }
                nameField.setText("");
                modelField.setText("");
                bcField.setText("");
                ecField.setText("");
                mainFrameAdmin.generateAircraftsTable();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.valueOf(idField.getText());
                String name = nameField.getText();
                String model = modelField.getText();
                int bcFieldText = Integer.parseInt(bcField.getText());
                int ecFieldText = Integer.parseInt(ecField.getText());
                if(verifTextUpdate()){
                    Aircraft aircraft = new Aircraft(id, name, model, bcFieldText, ecFieldText);
                    mainFrameAdmin.updateAircraft(aircraft);
                }
                mainFrameAdmin.generateAircraftsTable();
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

    public boolean verifTextUpdate(){
        if(idField.getText().equals("")|| nameField.getText().equals("")|| modelField.getText().equals("")||bcField.getText().equals("")||ecField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "One or more empty field");
            return false;
        }else{
            JOptionPane.showMessageDialog(null, "Aircraft data updated");
            return true;
        }
    }

    public boolean verifTextAdd(){
        if(nameField.getText().equals("")|| modelField.getText().equals("")||bcField.getText().equals("")||ecField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "One or more empty field");
            return false;
        }else{
            JOptionPane.showMessageDialog(null, "New aircraft added");
            return true;
        }
    }
}
