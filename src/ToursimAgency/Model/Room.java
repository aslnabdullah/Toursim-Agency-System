package ToursimAgency.Model;

import ToursimAgency.Helper.DBConnector;
import ToursimAgency.Helper.Helper;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {
    private int id;
    private int hotel_id;
    private String name;
    private int  season_id;
    private int pension_id;
    private int stock;
    private int adultPrice;
    private int childPrice;
    private int bed;
    public Room(){

    }
    public Room(int id, int hotel_id, String name, int season_id, int pension_id, int stock, int adultPrice, int childPrice, int bed) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.name = name;
        this.season_id = season_id;
        this.pension_id = pension_id;
        this.stock = stock;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.bed = bed;
    }

    public static ArrayList<Room> getList() { //oda listesini çekmek için kullanıyoruz
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM room";
        Room obj;
        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setName(rs.getString("room_type"));
                obj.setSeason_id(rs.getInt("season_id"));
                obj.setPension_id(rs.getInt("pension_id"));
                obj.setStock(rs.getInt("stock"));
                obj.setAdultPrice(rs.getInt("adult_price"));
                obj.setChildPrice(rs.getInt("child_price"));
                obj.setBed(rs.getInt("bed"));


                roomList.add(obj);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomList;
    }
    public static ArrayList<Room> getListPriceBed(int room_id) { //rezervasyon ekranında yazdırmak istediğimiz bilgileri alıyoruz
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM room WHERE id = ?";
        Room obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Room();
                obj.setAdultPrice(rs.getInt("adult_price"));
                obj.setChildPrice(rs.getInt("child_price"));
                obj.setBed(rs.getInt("bed"));
                roomList.add(obj);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomList;
    }
    public static int getFetchHotelID(int id){ //seçtiğimiz odanın id sine göre hotel id çekiyoruz
        int hotel_id = 0;
        Room room;
        String sql = "SELECT * FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                room = new Room();
                room.setHotel_id(rs.getInt("hotel_id"));
                hotel_id = room.getHotel_id();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotel_id;
    }
    public static int getFetchAdultPrice(int id){ //oda fiyat hesaplaması için id ye göre adult price alıyoruz databaseden
        int adult_price = 0;
        Room room;
        String sql = "SELECT * FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                room = new Room();
                room.setAdultPrice(rs.getInt("adult_price"));
                adult_price = room.getAdultPrice();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return adult_price;
    }
    public static int getFetchChildPrice(int id){ //oda fiyatı için child price alıyoruz databaseden id ye göre
        int child_price = 0;
        Room room;
        String sql = "SELECT * FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                room = new Room();
                room.setChildPrice(rs.getInt("child_price"));
                child_price = room.getChildPrice();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return child_price;
    }


    public static boolean add(int hotel_id, String name, int season_id, int pension_id, int stock, int adultPrice, int childPrice, int bed){
        String query = "INSERT INTO room (hotel_id,room_type,season_id,pension_id,stock,adult_price,child_price,bed) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);

            pr.setInt(1,hotel_id);
            pr.setString(2,name);
            pr.setInt(3,season_id);
            pr.setInt(4,pension_id);
            pr.setInt(5,stock);
            pr.setInt(6,adultPrice);
            pr.setInt(7,childPrice);
            pr.setInt(8,bed);

            int response = pr.executeUpdate();
            if (response == -1){
                Helper.showMessage("error");
            }
            return response != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static void stockReduction(int id){
        String query = "UPDATE room SET stock = stock - 1 WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String searchQuery(String searchText,String checkIn,String checkOut){
        String query = "SELECT r.id,h.name,h.city,r.room_type,s.season_name,ht.hostel_type,r.stock,r.adult_price,r.child_price,r.bed FROM room AS r\n" +
                "INNER JOIN hotel AS h\n" +
                "ON r.hotel_id = h.id\n" +
                "INNER JOIN season AS s\n"+
                "ON r.season_id = s.id\n" +
                "INNER JOIN hostel_type AS ht\n" +
                "ON r.pension_id = ht.id\n";
        if (!searchText.isEmpty() || ( !checkIn.isEmpty())){
            query += " WHERE r.stock > 0 AND ";
            if (!searchText.isEmpty() ){
                query+= "(h.name LIKE '%{{name}}%' OR h.city LIKE '%{{city}}%')";
                query = query.replace("{{name}}",searchText);
                query = query.replace("{{city}}",searchText);
            }
            if (!checkIn.isEmpty()){
                if (!searchText.isEmpty()){
                    query += " AND ";
                }
                query+= "('{{checkin}}' >= s.start_date)";
                query = query.replace("{{checkin}}",checkIn);
            }
            if (!checkOut.isEmpty()){
                if (!searchText.isEmpty() ||( !checkIn.isEmpty())){
                    query += " AND ";
                }
                query+= "('{{checkout}}' <= s.finish_date)";
                query = query.replace("{{checkout}}",checkOut);
            }
        }
        return query;
    }
    public static ArrayList<RoomSearchDTO> searchRoomList(String query){ //arama yaptığımız listeyi burada çekiyoruz
        ArrayList<RoomSearchDTO> roomSearch = new ArrayList<>();
        RoomSearchDTO room;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = pr.executeQuery(query);
            while (rs.next()){
                room = new RoomSearchDTO();
                room.setRoom_id(rs.getInt("id"));
                room.setHotel_name(rs.getString("name"));
                room.setHotel_city(rs.getString("city"));
                room.setRoom_type(rs.getString("room_type"));
                room.setSeason_name(rs.getString("season_name"));
                room.setPension_name(rs.getString("hostel_type"));
                room.setStock(rs.getInt("stock"));
                room.setAdult_price(rs.getInt("adult_price"));
                room.setChild_price(rs.getInt("child_price"));
                room.setRoom_bed(rs.getInt("bed"));
                roomSearch.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomSearch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getPension_id() {
        return pension_id;
    }

    public void setPension_id(int pension_id) {
        this.pension_id = pension_id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public int getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(int childPrice) {
        this.childPrice = childPrice;
    }

    public int getBed() {
        return bed;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }
}
