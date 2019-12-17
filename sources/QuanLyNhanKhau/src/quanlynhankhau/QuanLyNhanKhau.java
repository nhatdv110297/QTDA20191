package quanlynhankhau;

import java.util.Calendar;
import javax.swing.UIManager;
import views.LoginUI;

/**
 *
 * @author Hai
 */

// day la dong comment
public class QuanLyNhanKhau {

    public  Calendar calendar = Calendar.getInstance();
    
    public static  main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
//        MainFrame mainFrame = new MainFrame();
//        mainFrame.setLocationRelativeTo(null);
//        mainFrame.setResizable(false);
//        mainFrame.setVisible(true);
         loginUI = new LoginUI();
        loginUI.setVisible(true); 
    }
    
}
