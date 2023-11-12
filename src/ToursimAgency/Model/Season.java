package ToursimAgency.Model;

import ToursimAgency.Helper.DBConnector;
import ToursimAgency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Season {
    private int id;
    private int hotel_id;
    private String name;
    private String start_date;
    private String finish_date;

    public Season(){}

    public Season(int id, int hotel_id, String name, String start_date, String finish_date) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.name = name;
        this.start_date = start_date;
        this.finish_date = finish_date;
    }

    public static boolean add(int hotel_id, String name,String start_date, String finish_date){
        String query = "INSERT INTO season (hotel_id,season_name,start_date,finish_date) VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);

            pr.setInt(1,hotel_id);
            pr.setString(2,name);
            pr.setString(3,start_date);
            pr.setString(4,finish_date);

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
    public static ArrayList<Season> getList(int hotel_id){
        ArrayList<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM season WHERE hotel_id = ?";
        Season obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Season();
                obj.setName(rs.getString("season_name"));
                seasonList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasonList;
    }
    public static int getFetchSeasonID(String season_name,int hotel_id){
        int seasonID = 0;
        Season season;
        String sql = "SELECT * FROM season WHERE season_name = ? AND hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setString(1,season_name);
            pr.setInt(2,hotel_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                season = new Season();
                season.setId(rs.getInt("id"));
                seasonID = season.getId();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasonID;
    }
    public static String getFetchSeasonName(int id){
        String SeasonName = "";
        Season season;
        String sql = "SELECT * FROM season WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                season = new Season();
                season.setName(rs.getString("season_name"));
                SeasonName = season.getName();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return SeasonName;
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(String finish_date) {
        this.finish_date = finish_date;
    }
}
