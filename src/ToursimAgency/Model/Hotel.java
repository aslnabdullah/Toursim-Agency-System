package ToursimAgency.Model;

import ToursimAgency.Helper.DBConnector;
import ToursimAgency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private String city;
    private String adress;
    private String email;
    private String phone;
    private String star;

    public Hotel(){}

    public Hotel(int id, String name, String city, String adress, String email, String phone, String star) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.adress = adress;
        this.email = email;
        this.phone = phone;
        this.star = star;
    }

    public static boolean add(String name, String city, String adress, String email, String phone, String star){
        String query = "INSERT INTO hotel (name,city,adress,email,phone,star) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);

            pr.setString(1,name);
            pr.setString(2,city);
            pr.setString(3,adress);
            pr.setString(4,email);
            pr.setString(5,phone);
            pr.setString(6,star);

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
    public static ArrayList<Hotel> getList(){
        ArrayList<Hotel> userList = new ArrayList<>();
        String query = "SELECT * FROM hotel";
        Hotel obj;
        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setCity(rs.getString("city"));
                obj.setAdress(rs.getString("adress"));
                obj.setEmail(rs.getString("email"));
                obj.setPhone(rs.getString("phone"));
                obj.setStar(rs.getString("star"));
                userList.add(obj);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
    public static ArrayList<Hotel> getList(int id){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotel WHERE id = ?";
        Hotel obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setCity(rs.getString("city"));
                obj.setAdress(rs.getString("adress"));
                obj.setEmail(rs.getString("email"));
                obj.setPhone(rs.getString("phone"));
                obj.setStar(rs.getString("star"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }
    public static String getFetchHotelName(int hotel_id){
        String hotelName = "";
        Hotel hotel;
        String sql = "SELECT * FROM hotel WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                hotel = new Hotel();
                hotel.setName(rs.getString("name"));
                hotelName = hotel.getName();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelName;
    }
    public static String getFetchHotelCity(int hotel_id){
        String hotelCity = "";
        Hotel hotel;
        String sql = "SELECT * FROM hotel WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                hotel = new Hotel();
                hotel.setCity(rs.getString("city"));
                hotelCity = hotel.getCity();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelCity;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
