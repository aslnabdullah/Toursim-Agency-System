package ToursimAgency.View;

import ToursimAgency.Helper.Helper;
import ToursimAgency.Helper.Config;
import ToursimAgency.Model.Admin;
import ToursimAgency.Model.User;


import javax.swing.*;


public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wrapperTop;
    private JPanel wbottom;
    private JTextField fld_login_username;
    private JPasswordField fld_login_pass;
    private JButton btn_login;

    public LoginGUI(){
        add(wrapper);
        setSize(500,400);
        setLocation(Helper.screenCenterPoint("x" , getSize()) , Helper.screenCenterPoint("y" , getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        btn_login.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_login_username) || Helper.isFieldEmpty(fld_login_pass)){
                Helper.showMessage("fill");
            }else {
                User u = User.getFetch(fld_login_username.getText() , fld_login_pass.getText());
                if (u == null){
                    Helper.showMessage("User could not found");
                }else {
                    switch (u.getType()){
                        case "admin":
                            AdminGUI adminGUI = new AdminGUI();
                            break;
                        case"user":
                            UserGUI userGUI = new UserGUI();
                            break;
                    }
                    dispose();
                }
            }

        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI login = new LoginGUI();

    }

    }

