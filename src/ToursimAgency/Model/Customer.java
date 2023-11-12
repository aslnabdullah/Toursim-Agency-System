package ToursimAgency.Model;

import ToursimAgency.Helper.DBConnector;
import ToursimAgency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Customer {
    private int id;
    private int hotel_id;
    private int room_id;
    private String name_surname;
    private String phone_number;
    private String customer_ID;
    private int adult_number;
    private int child_number;
    private int total_price;

    public Customer(){}

    public Customer(int id, int hotel_id, int room_id, String name_surname, String phone_number, String customer_ID, int adult_number, int child_number, int total_price) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.room_id = room_id;
        this.name_surname = name_surname;
        this.phone_number = phone_number;
        this.customer_ID = customer_ID;
        this.adult_number = adult_number;
        this.child_number = child_number;
        this.total_price = total_price;
    }

    public static boolean add(int hotel_id, int room_id, String name_surname, String phone_number, String customer_ID, int adult_number, int child_number, int total_price){
        String query = "INSERT INTO customer (hotel_id,room_id,name_surname,phone_number,customer_ID,adult_number,child_number,total_price) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);

            pr.setInt(1,hotel_id);
            pr.setInt(2,room_id);
            pr.setString(3,name_surname);
            pr.setString(4,phone_number);
            pr.setString(5,customer_ID);
            pr.setInt(6,adult_number);
            pr.setInt(7,child_number);
            pr.setInt(8,total_price);


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

    public static boolean delete(int room_id , String phone_number){
        String query = "DELETE FROM customer WHERE room_id = ? AND phone_number = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_id);
            pr.setString(2,phone_number);

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static ArrayList<Customer> getList() {
        ArrayList<Customer> customerList = new ArrayList<>();
        String query = "SELECT * FROM customer";
        Customer obj;
        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                obj = new Customer();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setRoom_id(rs.getInt("room_id"));
                obj.setName_surname(rs.getString("name_surname"));
                obj.setPhone_number(rs.getString("phone_number"));
                obj.setCustomer_ID(rs.getString("customer_ID"));
                obj.setAdult_number(rs.getInt("adult_number"));
                obj.setChild_number(rs.getInt("child_number"));
                obj.setTotal_price(rs.getInt("total_price"));
                customerList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
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

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getName_surname() {
        return name_surname;
    }

    public void setName_surname(String name_surname) {
        this.name_surname = name_surname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        this.customer_ID = customer_ID;
    }

    public int getAdult_number() {
        return adult_number;
    }

    public void setAdult_number(int adult_number) {
        this.adult_number = adult_number;
    }

    public int getChild_number() {
        return child_number;
    }

    public void setChild_number(int child_number) {
        this.child_number = child_number;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}
