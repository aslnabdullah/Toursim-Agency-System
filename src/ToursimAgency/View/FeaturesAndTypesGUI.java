package ToursimAgency.View;

import ToursimAgency.Helper.Config;
import ToursimAgency.Helper.Helper;
import ToursimAgency.Model.Facility_features;
import ToursimAgency.Model.Hostel_types;
import ToursimAgency.Model.Season;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FeaturesAndTypesGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wTop;
    private JPanel wBottom;
    private JCheckBox RoomServiceCheckBox;
    private JCheckBox freeParkingCheckBox;
    private JCheckBox swimmingPoolCheckBox;
    private JCheckBox hotelConciergeCheckBox;
    private JCheckBox SPACheckBox;
    private JCheckBox fitnessCenterCheckBox1;
    private JCheckBox freWifiCheckBox;
    private JCheckBox fullCreditExcludingAlcoholCheckBox;
    private JCheckBox ultraAllInclusiveCheckBox;
    private JCheckBox bedOnlyCheckBox;
    private JCheckBox halfBoardCheckBox;
    private JCheckBox fullPensionCheckBox;
    private JCheckBox roomBreakfastCheckBox;
    private JCheckBox allInclusiveCheckBox;
    private JButton btn_hotel_features_add;
    private JButton btn_hostel_types_add;
    private JCheckBox winterSeasonCheckBox;
    private JCheckBox summerSeasonCheckBox;
    private JTextField fld_season_start;
    private JTextField fld_season_finish;
    private JButton btn_hotel_seasons_add;

    private int selectedHotelId = UserGUI.selectedHotelId;


    public FeaturesAndTypesGUI(){
        add(wrapper);
        setSize(500,500);
        setLocation(Helper.screenCenterPoint("x" , getSize()) , Helper.screenCenterPoint("y" , getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        btn_hotel_features_add.addActionListener(e -> {
            if (freeParkingCheckBox.isSelected()){
                Facility_features.add(selectedHotelId,freeParkingCheckBox.getText());
            }

            if (freWifiCheckBox.isSelected()){
                Facility_features.add(selectedHotelId,freWifiCheckBox.getText());
            }

            if (swimmingPoolCheckBox.isSelected()){
                Facility_features.add(selectedHotelId,swimmingPoolCheckBox.getText());
            }

            if (fitnessCenterCheckBox1.isSelected()){
                Facility_features.add(selectedHotelId,fitnessCenterCheckBox1.getText());
            }

            if (hotelConciergeCheckBox.isSelected()){
                Facility_features.add(selectedHotelId,hotelConciergeCheckBox.getText());
            }

            if (SPACheckBox.isSelected()){
                Facility_features.add(selectedHotelId,SPACheckBox.getText());
            }

            if (RoomServiceCheckBox.isSelected()){
                Facility_features.add(selectedHotelId,RoomServiceCheckBox.getText());
            }
            Helper.showMessage("done");
            freeParkingCheckBox.setSelected(false);
            freWifiCheckBox.setSelected(false);
            swimmingPoolCheckBox.setSelected(false);
            fitnessCenterCheckBox1.setSelected(false);
            hotelConciergeCheckBox.setSelected(false);
            SPACheckBox.setSelected(false);
            RoomServiceCheckBox.setSelected(false);
        });

        btn_hostel_types_add.addActionListener(e -> {
            if (ultraAllInclusiveCheckBox.isSelected()){
                Hostel_types.add(selectedHotelId,ultraAllInclusiveCheckBox.getText());
            }
            if (allInclusiveCheckBox.isSelected()){
                Hostel_types.add(selectedHotelId,allInclusiveCheckBox.getText());
            }
            if (roomBreakfastCheckBox.isSelected()){
                Hostel_types.add(selectedHotelId,roomBreakfastCheckBox.getText());
            }
            if (fullPensionCheckBox.isSelected()){
                Hostel_types.add(selectedHotelId,fullPensionCheckBox.getText());
            }
            if (halfBoardCheckBox.isSelected()){
                Hostel_types.add(selectedHotelId,halfBoardCheckBox.getText());
            }
            if (bedOnlyCheckBox.isSelected()){
                Hostel_types.add(selectedHotelId,bedOnlyCheckBox.getText());
            }
            if (fullCreditExcludingAlcoholCheckBox.isSelected()){
                Hostel_types.add(selectedHotelId,fullCreditExcludingAlcoholCheckBox.getText());
            }
            Helper.showMessage("done");
            ultraAllInclusiveCheckBox.setSelected(false);
            allInclusiveCheckBox.setSelected(false);
            roomBreakfastCheckBox.setSelected(false);
            fullPensionCheckBox.setSelected(false);
            halfBoardCheckBox.setSelected(false);
            bedOnlyCheckBox.setSelected(false);
            fullCreditExcludingAlcoholCheckBox.setSelected(false);
        });
        btn_hotel_seasons_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_season_start) || Helper.isFieldEmpty(fld_season_finish)){
                Helper.showMessage("fill");
                if ((!summerSeasonCheckBox.isSelected() && !winterSeasonCheckBox.isSelected())|| (summerSeasonCheckBox.isSelected() && winterSeasonCheckBox.isSelected())){
                    Helper.showMessage("select which season the dates you have selected belong to");
                }
            }else {
                String start_season = fld_season_start.getText();
                String finish_season = fld_season_finish.getText();
                start_season = LocalDate.parse(start_season, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
                finish_season = LocalDate.parse(finish_season, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
                String season_name;
                if (summerSeasonCheckBox.isSelected()){
                    season_name = summerSeasonCheckBox.getText();
                }
                else {
                    season_name = winterSeasonCheckBox.getText();
                }
                if (Season.add(selectedHotelId,season_name,start_season,finish_season)){
                    Helper.showMessage("done");
                    fld_season_start.setText(null);
                    fld_season_finish.setText(null);
                }
            }
            summerSeasonCheckBox.setSelected(false);
            winterSeasonCheckBox.setSelected(false);
        });
    }
}
