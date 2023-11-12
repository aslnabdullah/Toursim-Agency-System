package ToursimAgency.Model;

import ToursimAgency.Helper.DBConnector;
import ToursimAgency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room_Features {
    private int id;
    private int room_id;
    private String room_features;

    public Room_Features(){}

    public Room_Features(int id, int room_id, String room_features) {
        this.id = id;
        this.room_id = room_id;
        this.room_features = room_features;
    }

    public static boolean add(int room_id , String room_features){
        String query = "INSERT INTO room_features (room_id , room_features) VALUES (?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);

            pr.setInt(1,room_id);
            pr.setString(2,room_features);

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
    public static ArrayList<Room_Features> GetRoomFeatures(int room_id) {
        ArrayList<Room_Features> roomFeaturesList = new ArrayList<>();
        String query = "SELECT * FROM room_features WHERE room_id = ?";
        Room_Features obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Room_Features();
                obj.setRoom_features(rs.getString("room_features"));
                roomFeaturesList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomFeaturesList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getRoom_features() {
        return room_features;
    }

    public void setRoom_features(String room_features) {
        this.room_features = room_features;
    }
}
