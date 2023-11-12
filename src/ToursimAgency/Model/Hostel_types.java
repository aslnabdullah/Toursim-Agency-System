package ToursimAgency.Model;

import ToursimAgency.Helper.DBConnector;
import ToursimAgency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Hostel_types {

    private int id;
    private int hotel_id;
    private String hostel_type;

    public Hostel_types(){}

    public Hostel_types(int id, int hotel_id, String hostel_type) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.hostel_type = hostel_type;
    }

    public static boolean add(int hotel_id , String hostel_type){
        String query = "INSERT INTO hostel_type (hotel_id , hostel_type) VALUES (?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);

            pr.setInt(1,hotel_id);
            pr.setString(2,hostel_type);

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

    public static ArrayList<Hostel_types> getList(int hotel_id){
        ArrayList<Hostel_types> hostelTypeList = new ArrayList<>();
        String query = "SELECT * FROM hostel_type WHERE hotel_id = ?";
        Hostel_types obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Hostel_types();
                obj.setHostel_type(rs.getString("hostel_type"));
                hostelTypeList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hostelTypeList;
    }

    public static int getFetchPensionID(String hostel_type,int hotel_id){
        int pId = 0;
        Hostel_types hostelTypes;
        String sql = "SELECT * FROM hostel_type WHERE hostel_type = ? AND hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setString(1,hostel_type);
            pr.setInt(2,hotel_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                hostelTypes = new Hostel_types();
                hostelTypes.setId(rs.getInt("id"));
                pId = hostelTypes.getId();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pId;
    }
    public static String getFetchPensionName(int id){
        String pensionName = "";
        Hostel_types hostelTypes;
        String sql = "SELECT * FROM hostel_type WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                hostelTypes = new Hostel_types();
                hostelTypes.setHostel_type(rs.getString("hostel_type"));
                pensionName = hostelTypes.getHostel_type();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pensionName;
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

    public String getHostel_type() {
        return hostel_type;
    }

    public void setHostel_type(String hostel_type) {
        this.hostel_type = hostel_type;
    }
}
