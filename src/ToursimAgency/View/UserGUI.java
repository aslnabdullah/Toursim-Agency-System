package ToursimAgency.View;

import ToursimAgency.Helper.Config;
import ToursimAgency.Helper.Helper;
import ToursimAgency.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UserGUI extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel wTop;
    private JButton btn_user_exit;
    private JButton btn_user_add_hotel;
    private JPanel wrapper;
    private JTable tbl_hotel_list;
    private JButton btn_add_features_types;
    private JTextField fld_selected_hotel;
    private JTable tbl_room_list;
    private JButton btn_room_features_add;
    private JButton btn_add_room;
    private JTextField fld_room_name;
    private JButton btn_room_make_rez;
    private JTextField fld_search_name;
    private JTextField fld_search_check_in;
    private JTextField fld_search_check_out;
    private JButton btn_search;
    private JTable tbl_rezervation_list;
    private JTextField fld_selected_rezervation;
    private JButton btn_delete_rez;

    private DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;

    private DefaultTableModel mdl_room_list;
    private Object[] row_room_list;

    private DefaultTableModel mdl_rezervation_list;
    private Object[] row_rezervation_list;

    public static int selectedHotelId;
    public static String selectedHotelName;

    public static int selectedRoomID;
    public static String selectedRoomName;

    public static String searchCheckIn;//Search kısmında kullanılıyor
    public static String searchCheckOut;//Search kısmında kullanılıyor





    public UserGUI() {
        add(wrapper);
        setSize(1200,500);
        setLocation(Helper.screenCenterPoint("x" , getSize()) , Helper.screenCenterPoint("y" , getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        mdl_hotel_list = new DefaultTableModel();
        Object[] col_hotel_list = {"ID","Name","City","Adress","E-mail","Phone","Star"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list = new Object[col_hotel_list.length];
        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(100);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);
        loadHotelListModel();


        mdl_room_list = new DefaultTableModel();
        Object[] col_room_list = {"ID","Hotel Name","City","Room Type","Season Name","Pension Name","Stock","Adult Price","Child Price","Number of Bed"};
        mdl_room_list.setColumnIdentifiers(col_room_list);
        row_room_list = new Object[col_room_list.length];
        tbl_room_list.setModel(mdl_room_list);
        tbl_room_list.getColumnModel().getColumn(0).setMaxWidth(100);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);
        loadRoomListModel();


        mdl_rezervation_list = new DefaultTableModel();
        Object[] col_rezervation_list = {"Hotel ID","Room ID","Name Surname","Phone Number","Customer ID","Adult Number","Child Number","Total Price"};
        mdl_rezervation_list.setColumnIdentifiers(col_rezervation_list);
        row_rezervation_list = new Object[col_rezervation_list.length];
        tbl_rezervation_list.setModel(mdl_rezervation_list);
        tbl_rezervation_list.getColumnModel().getColumn(0).setMaxWidth(100);
        tbl_rezervation_list.getTableHeader().setReorderingAllowed(false);
        loadRezervationListModel();



        tbl_hotel_list.getSelectionModel().addListSelectionListener(e -> {
            String selectedHotelName1 = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),1).toString();
            int selectedHotelID = (int) tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),0);
            selectedHotelId = selectedHotelID;
            selectedHotelName = selectedHotelName1;
            fld_selected_hotel.setText(selectedHotelName1);
        });


        btn_user_add_hotel.addActionListener(e -> {
            HotelRegistrationGUI hotelRegistrationGUI = new HotelRegistrationGUI();

        });


        btn_add_features_types.addActionListener(e -> {
            FeaturesAndTypesGUI featuresAndTypesGUI = new FeaturesAndTypesGUI();
        });


        btn_user_exit.addActionListener(e -> {
            dispose();
            LoginGUI login = new LoginGUI();
        });


        btn_add_room.addActionListener(e -> {
            RoomRegistrationGUI roomRegistrationGUI = new RoomRegistrationGUI();
        });


        btn_room_features_add.addActionListener(e -> {
            RoomFeaturesGUI roomFeaturesGUI = new RoomFeaturesGUI();
        });


        btn_search.addActionListener(e -> {
            String searchText = fld_search_name.getText();
            searchCheckIn = fld_search_check_in.getText();
            if (!searchCheckIn.isEmpty()){
                searchCheckIn = LocalDate.parse(searchCheckIn, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
            }
            searchCheckOut = fld_search_check_out.getText();
            if (!searchCheckOut.isEmpty()){
                searchCheckOut = LocalDate.parse(searchCheckOut, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
            }
            String query = Room.searchQuery(searchText,searchCheckIn,searchCheckOut);
            ArrayList<RoomSearchDTO> roomSearch = Room.searchRoomList(query);
            loadRoomListModel(roomSearch);
        });


        btn_room_make_rez.addActionListener(e -> {
            searchCheckIn = fld_search_check_in.getText();
            searchCheckOut = fld_search_check_out.getText();
            if (searchCheckIn.isEmpty() || searchCheckOut.isEmpty()){
                Helper.showMessage("fill");
            }
            else {
                ReservationGUI reservationGUI = new ReservationGUI();
            }
        });

        tbl_room_list.getSelectionModel().addListSelectionListener(e -> {
            String selectedRoom = tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),3).toString();
            int selectedRoomid = (int) tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),0);
            fld_room_name.setText(selectedRoom);
            selectedRoomID = selectedRoomid;
            selectedRoomName = selectedRoom;
        });

    }

    private void loadRezervationListModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_rezervation_list.getModel();
        clearModel.setRowCount(0);
        ArrayList<Customer> customerList = Customer.getList();
        if (customerList.isEmpty()){
            Helper.showMessage("fill");
        }else {
            for (Customer obj : customerList){
                int i = 0;
                row_rezervation_list[i++] = obj.getHotel_id();
                row_rezervation_list[i++] = obj.getRoom_id();
                row_rezervation_list[i++] = obj.getName_surname();
                row_rezervation_list[i++] = obj.getPhone_number();
                row_rezervation_list[i++] = obj.getCustomer_ID();
                row_rezervation_list[i++] = obj.getAdult_number();
                row_rezervation_list[i++] = obj.getChild_number();
                row_rezervation_list[i++] = obj.getTotal_price();
                mdl_rezervation_list.addRow(row_rezervation_list);
            }
        }
    }

    private void loadRoomListModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        ArrayList<Room> hotelList = Room.getList();
        if (hotelList.isEmpty()){
            Helper.showMessage("fill");
        }else {
            for (Room obj : hotelList){
                int i = 0;
                row_room_list[i++] = obj.getId();
                row_room_list[i++] = Hotel.getFetchHotelName(obj.getHotel_id());
                row_room_list[i++] = Hotel.getFetchHotelCity(obj.getHotel_id());
                row_room_list[i++] = obj.getName();
                row_room_list[i++] = Season.getFetchSeasonName(obj.getSeason_id());
                row_room_list[i++] = Hostel_types.getFetchPensionName(obj.getPension_id());
                row_room_list[i++] = obj.getStock();
                row_room_list[i++] = obj.getAdultPrice();
                row_room_list[i++] = obj.getChildPrice();
                row_room_list[i++] = obj.getBed();

                mdl_room_list.addRow(row_room_list);
            }
        }
    }
    private void loadRoomListModel(ArrayList<RoomSearchDTO> arrayList) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
            for (RoomSearchDTO obj : arrayList){
                int i = 0;
                row_room_list[i++] = obj.getRoom_id();
                row_room_list[i++] = obj.getHotel_name();
                row_room_list[i++] = obj.getHotel_city();
                row_room_list[i++] = obj.getRoom_type();
                row_room_list[i++] = obj.getSeason_name();
                row_room_list[i++] = obj.getPension_name();
                row_room_list[i++] = obj.getStock();
                row_room_list[i++] = obj.getAdult_price();
                row_room_list[i++] = obj.getChild_price();
                row_room_list[i++] = obj.getRoom_bed();
                mdl_room_list.addRow(row_room_list);
        }
    }

    private void loadHotelListModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        ArrayList<Hotel> hotelList = Hotel.getList();
        if (hotelList.isEmpty()){
            Helper.showMessage("fill");
        }else {
            for (Hotel obj : hotelList){
                int i = 0;
                row_hotel_list[i++] = obj.getId();
                row_hotel_list[i++] = obj.getName();
                row_hotel_list[i++] = obj.getCity();
                row_hotel_list[i++] = obj.getAdress();
                row_hotel_list[i++] = obj.getEmail();
                row_hotel_list[i++] = obj.getPhone();
                row_hotel_list[i++] = obj.getStar();
                mdl_hotel_list.addRow(row_hotel_list);
            }
        }
    }
}
