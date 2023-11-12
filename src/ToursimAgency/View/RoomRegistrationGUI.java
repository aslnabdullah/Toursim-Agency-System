package ToursimAgency.View;

import ToursimAgency.Helper.Config;
import ToursimAgency.Helper.Helper;
import ToursimAgency.Model.Hostel_types;
import ToursimAgency.Model.Room;
import ToursimAgency.Model.Season;

import javax.swing.*;

public class RoomRegistrationGUI extends JFrame{
    private JPanel wrapper;
    private JComboBox cmb_room_name;
    private JComboBox cmb_room_season;
    private JComboBox cmb_room_pension;
    private JTextField fld_room_stock;
    private JTextField fld_room_adultPrice;
    private JTextField fld_room_childPrice;
    private JComboBox cmb_room_bed;
    private JTextField fld_hotel_name;
    private JButton btn_room_add;

    private String selectedHotelName = UserGUI.selectedHotelName;
    private int selectedHotelID = UserGUI.selectedHotelId;

    public RoomRegistrationGUI(){

        add(wrapper);
        setSize(500,500);
        setLocation(Helper.screenCenterPoint("x" , getSize()) , Helper.screenCenterPoint("y" , getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(true);
        setVisible(true);

        loadPensionCombo();
        loadSeasonCombo();
        fld_hotel_name.setText(selectedHotelName);


        btn_room_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_room_stock) || Helper.isFieldEmpty(fld_room_adultPrice) || Helper.isFieldEmpty(fld_room_childPrice)){
                Helper.showMessage("fill");
            }else {
                String name = cmb_room_name.getSelectedItem().toString();
                int stock = Integer.parseInt(fld_room_stock.getText());
                int seasonID = Season.getFetchSeasonID(cmb_room_season.getSelectedItem().toString(),selectedHotelID);
                int pensionID = Hostel_types.getFetchPensionID(cmb_room_pension.getSelectedItem().toString(),selectedHotelID);
                int adultPrice = Integer.parseInt(fld_room_adultPrice.getText());
                int childPrice = Integer.parseInt(fld_room_childPrice.getText());
                int bed  = Integer.parseInt(cmb_room_bed.getSelectedItem().toString());

                if (Room.add(selectedHotelID,name,seasonID,pensionID,stock,adultPrice,childPrice,bed)){
                    Helper.showMessage("done");
                    loadPensionCombo();
                    loadSeasonCombo();
                    fld_room_stock.setText(null);
                    fld_room_adultPrice.setText(null);
                    fld_room_childPrice.setText(null);

                }
            }
            UserGUI userGUI = new UserGUI();

        });

    }

    public void loadPensionCombo(){
        cmb_room_pension.removeAllItems();
        for(Hostel_types hostel_types : Hostel_types.getList(selectedHotelID)){
            cmb_room_pension.addItem(hostel_types.getHostel_type());
        }
    }
    public void loadSeasonCombo(){
        cmb_room_season.removeAllItems();
        for(Season season : Season.getList(selectedHotelID)){
            cmb_room_season.addItem(season.getName());
        }
    }



}
