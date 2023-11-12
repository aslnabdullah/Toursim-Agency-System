package ToursimAgency.View;

import ToursimAgency.Helper.Config;
import ToursimAgency.Helper.Helper;
import ToursimAgency.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminGUI extends JFrame{
    private JPanel wrapper;
    private JButton btn_admin_exit;
    private JTable tbl_user_list;
    private JPanel wrapperTop;
    private JPanel wbottom;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JPasswordField fld_user_pass;
    private JButton btn_user_add;
    private JComboBox cmb_admin_type;

    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;

    public AdminGUI(){
        add(wrapper);
        setSize(800,400);
        setLocation(Helper.screenCenterPoint("x" , getSize()) , Helper.screenCenterPoint("y" , getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        mdl_user_list = new DefaultTableModel();
        Object[] col_user_list = {"ID" ,"Name","Username","Password","User Type"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list = new Object[col_user_list.length];
        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getColumnModel().getColumn(0).setMaxWidth(100);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);
        loadUserListModel();


        btn_user_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_pass)){
                Helper.showMessage("fill");
            }else {
                String name = fld_user_name.getText();
                String uname = fld_user_uname.getText();
                String pass = fld_user_pass.getText();
                String type = cmb_admin_type.getSelectedItem().toString();
                if (User.add(name,uname,pass,type)){
                    Helper.showMessage("done");
                    loadUserListModel();
                    fld_user_name.setText(null);
                    fld_user_uname.setText(null);
                    fld_user_pass.setText(null);
                }
            }

        });
        btn_admin_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginGUI login = new LoginGUI();
            }
        });
    }

    private void loadUserListModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        ArrayList<User> contentList = User.getList();
        if (contentList.isEmpty()){
            Helper.showMessage("fill");
        }else {
            for (User obj : contentList){
                int i = 0;
                row_user_list[i++] = obj.getId();
                row_user_list[i++] = obj.getName();
                row_user_list[i++] = obj.getUname();
                row_user_list[i++] = obj.getPass();
                row_user_list[i++] = obj.getType();
                mdl_user_list.addRow(row_user_list);
            }
        }
    }


}
