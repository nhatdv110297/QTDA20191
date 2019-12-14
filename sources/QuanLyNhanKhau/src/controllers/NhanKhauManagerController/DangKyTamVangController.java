package controllers.NhanKhauManagerController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import models.TamVangModel;
import services.MysqlConnection;

/**
 *
 * @author Hai
 */
public class DangKyTamVangController {
    
    public int checkCMT(String cmt) {
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM nhan_khau LEFT JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau WHERE soCMT = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cmt);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra! vui lòng kiểm tra lại.", "Warning!!", JOptionPane.WARNING_MESSAGE);
        }
        return -1;
    }
    
    public boolean addNew(TamVangModel tamVangModel) {
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "INSERT INTO tam_vang(idNhanKhau, maGiayTamVang, noiTamTru, tuNgay, denNgay, lyDo)" + " value (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tamVangModel.getIdNhanKhau());
            preparedStatement.setString(2, tamVangModel.getMaGiayTamVang());
            preparedStatement.setString(3, tamVangModel.getNoiTamTru());
            java.sql.Date tuNgay = new java.sql.Date(tamVangModel.getTuNgay().getTime());
            preparedStatement.setDate(4, tuNgay);
            java.sql.Date denNgay = new java.sql.Date(tamVangModel.getDenNgay().getTime());
            preparedStatement.setDate(5, denNgay);
            preparedStatement.setString(6, tamVangModel.getLyDo());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra! vui lòng kiểm tra lại.", "Warning!!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
}
