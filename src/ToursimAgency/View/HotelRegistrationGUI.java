package ToursimAgency.View;

import ToursimAgency.Helper.Config;
import ToursimAgency.Helper.Helper;
import ToursimAgency.Model.Hotel;

import javax.swing.*;

public class HotelRegistrationGUI extends JFrame{

    private JPanel wrapper;
    private JPanel wTop;
    private JPanel wmiddle;
    private JLabel label_hotel_name;
    private JTextField fld_hotel_name;
    private JLabel label_hotel_city;
    private JTextField fld_hotel_city;
    private JLabel label_hotel_adres;
    private JTextField fld_hotel_adres;
    private JLabel label_hotel_email;
    private JTextField fld_hotel_email;
    private JTextField fld_hotel_phone;
    private JLabel label_hotel_phone;
    private JTextField fld_hotel_star;
    private JLabel label_hotel_star;
    private JButton btn_hotel_add;

    public HotelRegistrationGUI(){
        add(wrapper);
        setSize(800,400);
        setLocation(Helper.screenCenterPoint("x" , getSize()) , Helper.screenCenterPoint("y" , getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        btn_hotel_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_name) || Helper.isFieldEmpty(fld_hotel_city) || Helper.isFieldEmpty(fld_hotel_adres)|| Helper.isFieldEmpty(fld_hotel_email)|| Helper.isFieldEmpty(fld_hotel_phone)|| Helper.isFieldEmpty(fld_hotel_star)){
                Helper.showMessage("fill");
            }else {
                String name = fld_hotel_name.getText();
                String city = fld_hotel_city.getText();
                String adress = fld_hotel_adres.getText();
                String email = fld_hotel_email.getText();
                String phone = fld_hotel_phone.getText();
                String star = fld_hotel_star.getText();

                if (Hotel.add(name,city,adress,email,phone,star)){
                    Helper.showMessage("done");
                    fld_hotel_name.setText(null);
                    fld_hotel_city.setText(null);
                    fld_hotel_adres.setText(null);
                    fld_hotel_email.setText(null);
                    fld_hotel_phone.setText(null);
                    fld_hotel_star.setText(null);
                }
            }
            UserGUI userGUI = new UserGUI();
        });
    }

}
