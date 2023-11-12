package ToursimAgency.View;

import ToursimAgency.Helper.Config;
import ToursimAgency.Helper.Helper;
import ToursimAgency.Model.Room_Features;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomFeaturesGUI extends JFrame{
    private JTextField fld_features_room_name;
    private JCheckBox televisionCheckBox;
    private JCheckBox miniBarCheckBox;
    private JCheckBox gameConsoleCheckBox;
    private JCheckBox safeBoxCheckBox;
    private JCheckBox projectionCheckBox;
    private JButton btn_features_Add;
    private JPanel wrapper;

    private int selectedRoomID = UserGUI.selectedRoomID;

    public RoomFeaturesGUI(){
        add(wrapper);
        setSize(500,250);
        setLocation(Helper.screenCenterPoint("x" , getSize()) , Helper.screenCenterPoint("y" , getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(true);
        setVisible(true);

        fld_features_room_name.setText(UserGUI.selectedRoomName);




        btn_features_Add.addActionListener(e -> {
            if (televisionCheckBox.isSelected()){
                Room_Features.add(selectedRoomID,televisionCheckBox.getText());
            }
            if (miniBarCheckBox.isSelected()){
                Room_Features.add(selectedRoomID,miniBarCheckBox.getText());
            }
            if (gameConsoleCheckBox.isSelected()){
                Room_Features.add(selectedRoomID,gameConsoleCheckBox.getText());
            }
            if (safeBoxCheckBox.isSelected()){
                Room_Features.add(selectedRoomID,safeBoxCheckBox.getText());
            }
            if (projectionCheckBox.isSelected()){
                Room_Features.add(selectedRoomID,projectionCheckBox.getText());
            }
            Helper.showMessage("done");
            televisionCheckBox.setSelected(false);
            miniBarCheckBox.setSelected(false);
            gameConsoleCheckBox.setSelected(false);
            safeBoxCheckBox.setSelected(false);
            projectionCheckBox.setSelected(false);
        });
    }
}
