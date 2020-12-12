package BigProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPageAdmin extends JPanel {
    MainFrameAdmin mainFrameAdmin;
    public MainMenuPageAdmin(MainFrameAdmin mainFrameAdmin){
        this.mainFrameAdmin = mainFrameAdmin;
        setSize(700, 600);
        setLayout(null);

        JLabel labelText = new JLabel("SETTINGS MENU");
        labelText.setBounds(300, 100, 100, 40);
        add(labelText);

        JButton citiesButton = new JButton("CITIES");
        citiesButton.setBounds(250, 150, 200, 50);
        add(citiesButton);

        JButton aircraftsButton = new JButton("AIRCRAFTS");
        aircraftsButton.setBounds(250, 220, 200, 50);
        add(aircraftsButton);

        JButton flightsButton = new JButton("FLIGHTS");
        flightsButton.setBounds(250, 290, 200, 50);
        add(flightsButton);

        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(250, 360, 200, 50);
        add(exitButton);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        citiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrameAdmin.getMainMenuPageAdmin().setVisible(false);
                mainFrameAdmin.getSettingsCitiesMenuPageAdmin().setVisible(true);
                mainFrameAdmin.listOfCities();
                mainFrameAdmin.generateCitiesTable();
            }
        });

        aircraftsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrameAdmin.getMainMenuPageAdmin().setVisible(false);
                mainFrameAdmin.getSettingsAircraftsPageAdmin().setVisible(true);
                mainFrameAdmin.listAicrafts();
                mainFrameAdmin.generateAircraftsTable();
            }
        });

        flightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrameAdmin.getMainMenuPageAdmin().setVisible(false);
                mainFrameAdmin.getSettingsFlightsPageAdmin().setVisible(true);
                mainFrameAdmin.listOfCities();
                mainFrameAdmin.listAicrafts();
                mainFrameAdmin.getPackageDataFlight();
                mainFrameAdmin.generateFlightsTable();
            }
        });

    }
}
