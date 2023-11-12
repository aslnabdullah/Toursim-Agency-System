package ToursimAgency.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if ("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    public static int screenCenterPoint(String axis , Dimension size){
        int point;
        switch (axis){
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default:
                point = 0;
        }
        return point;

    }
    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }
    public static void showMessage(String str){
        optionPaneTR();
        String message;
        String title;

        switch (str){
            case "fill":
                message = "Lütfen tüm alanları doldurunuz!";
                title = "Hata!";
                break;
            case "done":
                message = "İşlem başarılı ! ";
                title = "Sonuç";
                break;
            case "error":
                message = "Bir hata oluştu! ";
                title = "Hata";
            default:
                message = str;
                title = "Hata mesajı!";
                break;
        }

        JOptionPane.showMessageDialog(null , message,title,JOptionPane.INFORMATION_MESSAGE);
    }
    public static boolean confirm(String str){
        optionPaneTR();
        String msg;

        switch (str){
            case "sure":
                msg = "Bu işlemi gerçekleştirmek istediğinize emin misiniz ?";
                break;
            default:
                msg = str;
        }

        return JOptionPane.showConfirmDialog(null , msg , "Son Kararınız mı ? " , JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void optionPaneTR(){
        UIManager.put("OptionPane.okButtonText" , "Tamam");
        UIManager.put("OptionPane.yesButtonText" , "Evet");
        UIManager.put("OptionPane.noButtonText" , "Hayır");
    }
}
