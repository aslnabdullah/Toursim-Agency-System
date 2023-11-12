package ToursimAgency.Model;

import ToursimAgency.Helper.DBConnector;
import ToursimAgency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Facility_features {

    private int id;
    private int hotel_id;
    private String hotel_features;

    public Facility_features(){}

    public Facility_features(int id, int hotel_id, String hotel_features) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.hotel_features = hotel_features;
    }


    public static boolean add(int hotel_id , String hotel_features){
        String query = "INSERT INTO facility_features (hotel_id , hotel_features) VALUES (?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);

            pr.setInt(1,hotel_id);
            pr.setString(2,hotel_features);

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
    public static ArrayList<Facility_features> GetFacilityFeatures(int hotel_id) {
        ArrayList<Facility_features> facility_featuresList = new ArrayList<>();
        String query = "SELECT * FROM facility_features WHERE hotel_id = ?";
        Facility_features obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Facility_features();
                obj.setHotel_features(rs.getString("hotel_features"));
                facility_featuresList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return facility_featuresList;
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

    public String getHotel_features() {
        return hotel_features;
    }

    public void setHotel_features(String hotel_features) {
        this.hotel_features = hotel_features;
    }
}
