package ToursimAgency.View;

import ToursimAgency.Helper.Config;
import ToursimAgency.Helper.Helper;
import ToursimAgency.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReservationGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wTop;
    private JPanel wbottom;
    private JPanel RoomInfoEnter;
    private JPanel AccomodationDay1;
    private JPanel PriceAndBed;
    private JPanel Room_features;
    private JPanel HotelInfo;
    private JPanel HotelFeatures;
    private JTextField fld_hostel_type;
    private JTextField fld_checkIn_date;
    private JTextField fld_checkOut_date;
    private JComboBox cmb_number_adult;
    private JComboBox cmb_number_child;
    private JTable tbl_room_features;
    private JTable tbl_price_and_bed;
    private JComboBox cmb_number_of_day;
    private JTextField fld_general_total;
    private JTable tbl_hotel_ınfo;
    private JTable tbl_hotel_features;
    private JTextField fld_custome_name;
    private JTextField fld_customer_ID;
    private JTextField fld_customer_phone;
    private JButton btn_create_rezervation;

    private DefaultTableModel mdl_room_features;
    private Object[] row_room_features;

    private DefaultTableModel mdl_price_and_bed;
    private Object[] row_price_and_bed;

    private DefaultTableModel mdl_hotel_Info;
    private Object[] row_hotel_Info;

    private DefaultTableModel mdl_facility_features;
    private Object[] row_facility_features;

    public ReservationGUI(){
        add(wrapper);
        setSize(1200,500);
        setLocation(Helper.screenCenterPoint("x" , getSize()) , Helper.screenCenterPoint("y" , getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        fld_hostel_type.setText(UserGUI.selectedRoomName);
        fld_checkIn_date.setText(UserGUI.searchCheckIn);
        fld_checkOut_date.setText(UserGUI.searchCheckOut);

        mdl_room_features = new DefaultTableModel();
        Object[] col_room_features = {"Room Features"};
        mdl_room_features.setColumnIdentifiers(col_room_features);
        row_room_features = new Object[col_room_features.length];
        tbl_room_features.setModel(mdl_room_features);
        tbl_room_features.getColumnModel().getColumn(0).setMaxWidth(100);
        tbl_room_features.getTableHeader().setReorderingAllowed(false);
        loadRoomFeaturesModel();


        mdl_price_and_bed = new DefaultTableModel();
        Object[] col_price_and_bed = {"Adult Price" , "Child Price" , "Bed"};
        mdl_price_and_bed.setColumnIdentifiers(col_price_and_bed);
        row_price_and_bed = new Object[col_price_and_bed.length];
        tbl_price_and_bed.setModel(mdl_price_and_bed);
        tbl_price_and_bed.getColumnModel().getColumn(0).setMaxWidth(100);
        tbl_price_and_bed.getTableHeader().setReorderingAllowed(false);
        loadPriceBedModel();


        mdl_hotel_Info = new DefaultTableModel();
        Object[] col_hotel_Info = {"ID","Name","City","Adress","E-mail","Phone","Star"};
        mdl_hotel_Info.setColumnIdentifiers(col_hotel_Info);
        row_hotel_Info = new Object[col_hotel_Info.length];
        tbl_hotel_ınfo.setModel(mdl_hotel_Info);
        tbl_hotel_ınfo.getColumnModel().getColumn(0).setMaxWidth(100);
        tbl_hotel_ınfo.getTableHeader().setReorderingAllowed(false);
        loadHotelInfoModel();


        mdl_facility_features = new DefaultTableModel();
        Object[] col_facility_features = {"Hotel Features"};
        mdl_facility_features.setColumnIdentifiers(col_facility_features);
        row_facility_features = new Object[col_facility_features.length];
        tbl_hotel_features.setModel(mdl_facility_features);
        tbl_hotel_features.getColumnModel().getColumn(0).setMaxWidth(100);
        tbl_hotel_features.getTableHeader().setReorderingAllowed(false);
        loadHotelFeaturesModel();


        btn_create_rezervation.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_custome_name) || Helper.isFieldEmpty(fld_customer_ID) || Helper.isFieldEmpty(fld_customer_phone) ||
                    (Integer.parseInt(cmb_number_adult.getSelectedItem().toString()) == 0 && Integer.parseInt(cmb_number_child.getSelectedItem().toString()) == 0)){
                Helper.showMessage("fill");
            }else {
                int hotel_id = Room.getFetchHotelID(UserGUI.selectedRoomID);
                int room_id = UserGUI.selectedRoomID;
                String name_surname = fld_custome_name.getText();
                String phone_number = fld_customer_phone.getText();
                String customer_ID = fld_customer_ID.getText();
                int adult_number = Integer.parseInt(cmb_number_adult.getSelectedItem().toString());
                int child_number = Integer.parseInt(cmb_number_child.getSelectedItem().toString());
                int total_price = Integer.parseInt(fld_general_total.getText());

                if (Customer.add(hotel_id,room_id,name_surname,phone_number,customer_ID,adult_number,child_number,total_price)){
                    Helper.showMessage("done");
                    fld_custome_name.setText(null);
                    fld_customer_phone.setText(null);
                    fld_customer_ID.setText(null);
                    fld_general_total.setText(null);
                }
            }
            Room.stockReduction(UserGUI.selectedRoomID);
            UserGUI userGUI = new UserGUI();
            dispose();

        });


        cmb_number_of_day.addActionListener(e -> {
            int total_Price = (priceCalc(Room.getFetchAdultPrice(UserGUI.selectedRoomID),Room.getFetchChildPrice(UserGUI.selectedRoomID)));
            fld_general_total.setText(String.valueOf(total_Price));
        });
    }

    private int priceCalc(int adult_price , int child_price){
        int adultPrice = adult_price * Integer.parseInt(cmb_number_adult.getSelectedItem().toString());
        int childPrice = child_price * Integer.parseInt(cmb_number_child.getSelectedItem().toString());
        int totalPrice = (adultPrice + childPrice) * Integer.parseInt(cmb_number_of_day.getSelectedItem().toString());
        return totalPrice;
    }


    private void loadPriceBedModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_price_and_bed.getModel();
        clearModel.setRowCount(0);
        ArrayList<Room> roomList = Room.getListPriceBed(UserGUI.selectedRoomID);
        if (roomList.isEmpty()){
            Helper.showMessage("fill");
        }else {
            for (Room obj : roomList){
                int i = 0;
                row_price_and_bed[i++] = obj.getAdultPrice();
                row_price_and_bed[i++] = obj.getChildPrice();
                row_price_and_bed[i++] = obj.getBed();
                mdl_price_and_bed.addRow(row_price_and_bed);
            }
        }
    }



    private void loadHotelFeaturesModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_features.getModel();
        clearModel.setRowCount(0);
        ArrayList<Facility_features> facility_features = Facility_features.GetFacilityFeatures(Room.getFetchHotelID(UserGUI.selectedRoomID));
        if (facility_features.isEmpty()) {
            Helper.showMessage("fill");
        } else {
            for (Facility_features obj : facility_features) {
                int i = 0;
                row_facility_features[i++] = obj.getHotel_features();
                mdl_facility_features.addRow(row_facility_features);
            }
        }
    }

    private void loadHotelInfoModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_ınfo.getModel();
        clearModel.setRowCount(0);
        ArrayList<Hotel> hotelList = Hotel.getList(Room.getFetchHotelID(UserGUI.selectedRoomID));
        if (hotelList.isEmpty()){
            Helper.showMessage("fill");
        }else {
            for (Hotel obj : hotelList){
                int i = 0;
                row_hotel_Info[i++] = obj.getId();
                row_hotel_Info[i++] = obj.getName();
                row_hotel_Info[i++] = obj.getCity();
                row_hotel_Info[i++] = obj.getAdress();
                row_hotel_Info[i++] = obj.getEmail();
                row_hotel_Info[i++] = obj.getPhone();
                row_hotel_Info[i++] = obj.getStar();
                mdl_hotel_Info.addRow(row_hotel_Info);
            }
        }
    }

    private void loadRoomFeaturesModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_features.getModel();
        clearModel.setRowCount(0);
        ArrayList<Room_Features> room_features = Room_Features.GetRoomFeatures(UserGUI.selectedRoomID);
        if (room_features.isEmpty()){
            Helper.showMessage("fill");
        }else {
            for (Room_Features obj : room_features){
                int i = 0;
                row_room_features[i++] = obj.getRoom_features();
                mdl_room_features.addRow(row_room_features);
            }
        }

    }

}
