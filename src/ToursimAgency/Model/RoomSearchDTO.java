package ToursimAgency.Model;

public class RoomSearchDTO {
    public int room_id;
    public String hotel_name;
    public String hotel_city;
    public String room_type;
    public String season_name;
    public String pension_name;
    public int stock;
    public int adult_price;
    public int child_price;
    public int room_bed;

    public RoomSearchDTO() {
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_city() {
        return hotel_city;
    }

    public void setHotel_city(String hotel_city) {
        this.hotel_city = hotel_city;
    }

    public String getSeason_name() {
        return season_name;
    }

    public void setSeason_name(String season_name) {
        this.season_name = season_name;
    }

    public int getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(int adult_price) {
        this.adult_price = adult_price;
    }

    public int getChild_price() {
        return child_price;
    }

    public void setChild_price(int child_price) {
        this.child_price = child_price;
    }

    public int getRoom_bed() {
        return room_bed;
    }

    public void setRoom_bed(int room_bed) {
        this.room_bed = room_bed;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getPension_name() {
        return pension_name;
    }

    public void setPension_name(String pension_name) {
        this.pension_name = pension_name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
