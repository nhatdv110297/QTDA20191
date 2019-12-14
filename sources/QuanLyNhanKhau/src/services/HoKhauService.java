package services;

import Bean.HoKhauBean;
import controllers.LoginController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.HoKhauModel;
import models.NhanKhauModel;
import models.ThanhVienCuaHoModel;

/**
 *
 * @author Hai
 */
public class HoKhauService {
    // them moi ho khau
    public boolean addNew(HoKhauBean hoKhauBean) throws ClassNotFoundException, SQLException{
        Connection connection = MysqlConnection.getMysqlConnection();
        String query = "INSERT INTO ho_khau(maHoKhau, idChuHo, maKhuVuc, diaChi, ngayLap)" 
                    + " values (?, ?, ?, ?, NOW())";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, hoKhauBean.getHoKhauModel().getMaHoKhau());
        preparedStatement.setInt(2, hoKhauBean.getChuHo().getID());
        preparedStatement.setString(3, hoKhauBean.getHoKhauModel().getMaKhuVuc());
        preparedStatement.setString(4, hoKhauBean.getHoKhauModel().getDiaChi());

        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            int genID = rs.getInt(1);
            String sql = "INSERT INTO thanh_vien_cua_ho(idNhanKhau, idHoKhau, quanHeVoiChuHo)" 
                            + " values (?, ?, ?)";
            hoKhauBean.getListThanhVienCuaHo().forEach((ThanhVienCuaHoModel thanhVien) -> {     
                try { 
                    PreparedStatement preStatement = connection.prepareStatement(sql);
                    preStatement.setInt(1, thanhVien.getIdNhanKhau());
                    preStatement.setInt(2, genID);
                    preStatement.setString(3, thanhVien.getQuanHeVoiChuHo());
                    preStatement.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(HoKhauService.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
        }
        preparedStatement.close();
        connection.close();
        return true;
    }
    
    
    public boolean checkPerson(int id) {
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM ho_khau INNER JOIN thanh_vien_cua_ho ON ho_khau.ID = thanh_vien_cua_ho.idHoKhau"
                        + " WHERE ho_khau.idChuHo = "
                        + id 
                        + " OR thanh_vien_cua_ho.idNhanKhau = "
                        + id;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
        }
        return true;
    }
     
    // lay ra 10 ho khau
    public List<HoKhauBean> getListHoKhau() {
        List<HoKhauBean> list = new ArrayList<>();
        
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM ho_khau INNER JOIN nhan_khau ON ho_khau.idChuHo = nhan_khau.ID ORDER BY ngayTao DESC LIMIT 0, 10";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                HoKhauBean temp = new HoKhauBean();
                HoKhauModel hoKhauModel = temp.getHoKhauModel();
                hoKhauModel.setID(rs.getInt("ID"));
                hoKhauModel.setIdChuHo(rs.getInt("idCHuHo"));
                hoKhauModel.setMaHoKhau(rs.getString("maHoKhau"));
                hoKhauModel.setMaKhuVuc(rs.getString("maKhuVuc"));
                hoKhauModel.setNgayLap(rs.getDate("ngayLap"));
                hoKhauModel.setDiaChi(rs.getString("diaChi"));
                NhanKhauModel chuHo = temp.getChuHo();
                chuHo.setID(rs.getInt("ID"));
                chuHo.setHoTen(rs.getString("hoTen"));
                chuHo.setGioiTinh(rs.getString("gioiTinh"));
                chuHo.setNamSinh(rs.getDate("namSinh"));
                chuHo.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                try {
                    String sql = "SELECT * FROM nhan_khau INNER JOIN thanh_vien_cua_ho ON nhan_khau.ID = thanh_vien_cua_ho.idNhanKhau "
                            + "WHERE thanh_vien_cua_ho.idHoKhau = "
                            + hoKhauModel.getID();
                    PreparedStatement prst = connection.prepareStatement(sql);
                    ResultSet rs_1 = prst.executeQuery();
                    List<NhanKhauModel> listNhanKhau = temp.getListNhanKhauModels();
                    List<ThanhVienCuaHoModel> listThanhVienCuaHo = temp.getListThanhVienCuaHo();
                    while (rs_1.next()) {
                        NhanKhauModel nhanKhauModel = new NhanKhauModel();
                        ThanhVienCuaHoModel thanhVienCuaHoModel = new ThanhVienCuaHoModel();
                        nhanKhauModel.setID(rs_1.getInt("idNhanKhau"));
                        nhanKhauModel.setBietDanh(rs_1.getString("bietDanh"));
                        nhanKhauModel.setHoTen(rs_1.getString("hoTen"));
                        nhanKhauModel.setGioiTinh(rs_1.getString("gioiTinh"));
                        nhanKhauModel.setNamSinh(rs_1.getDate("namSinh"));
                        nhanKhauModel.setNguyenQuan(rs_1.getString("nguyenQuan"));
                        nhanKhauModel.setTonGiao(rs_1.getString("tonGiao"));
                        nhanKhauModel.setDanToc(rs_1.getString("danToc"));
                        nhanKhauModel.setQuocTich(rs_1.getString("quocTich"));
                        nhanKhauModel.setSoHoChieu(rs_1.getString("soHoChieu"));
                        nhanKhauModel.setNoiThuongTru(rs_1.getString("noiThuongTru"));
                        nhanKhauModel.setDiaChiHienNay(rs_1.getString("diaChiHienNay"));
                        
                        thanhVienCuaHoModel.setIdHoKhau(rs_1.getInt("idHoKhau"));
                        thanhVienCuaHoModel.setIdNhanKhau(rs_1.getInt("idNhanKhau"));
                        thanhVienCuaHoModel.setQuanHeVoiChuHo(rs_1.getString("quanHeVoiChuHo"));
                        listNhanKhau.add(nhanKhauModel);
                        listThanhVienCuaHo.add(thanhVienCuaHoModel);
                    }
                } catch (Exception e) {
                    System.out.println("services.HoKhauService.getListHoKhau()");
                    System.out.println(e.getMessage());
                }
                list.add(temp);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    // tim kiem the ten chu ho va ma ho khau
    public List<HoKhauBean> search(String key) {
        List<HoKhauBean> list = new ArrayList<>();
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * "
                        + "FROM ho_khau "
                        + "INNER JOIN nhan_khau "
                        + "ON ho_khau.idChuHo = nhan_khau.ID "
                        + "WHERE MATCH(maHoKhau) AGAINST ('"
                        + key
                        + "' IN NATURAL LANGUAGE MODE);";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                HoKhauBean temp = new HoKhauBean();
                HoKhauModel hoKhauModel = temp.getHoKhauModel();
                hoKhauModel.setID(rs.getInt("ID"));
                hoKhauModel.setIdChuHo(rs.getInt("idChuHo"));
                hoKhauModel.setMaHoKhau(rs.getString("maHoKhau"));
                hoKhauModel.setMaKhuVuc(rs.getString("maKhuVuc"));
                hoKhauModel.setNgayLap(rs.getDate("ngayLap"));
                hoKhauModel.setDiaChi(rs.getString("diaChi"));
                NhanKhauModel chuHo = temp.getChuHo();
                chuHo.setID(rs.getInt("ID"));
                chuHo.setHoTen(rs.getString("hoTen"));
                chuHo.setGioiTinh(rs.getString("gioiTinh"));
                chuHo.setNamSinh(rs.getDate("namSinh"));
                chuHo.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                try {
                    String sql = "SELECT * FROM nhan_khau INNER JOIN thanh_vien_cua_ho ON nhan_khau.ID = thanh_vien_cua_ho.idNhanKhau "
                            + "WHERE thanh_vien_cua_ho.idHoKhau = "
                            + hoKhauModel.getID();
                    PreparedStatement prst = connection.prepareStatement(sql);
                    ResultSet rs_1 = prst.executeQuery();
                    List<NhanKhauModel> listNhanKhau = temp.getListNhanKhauModels();
                    List<ThanhVienCuaHoModel> listThanhVienCuaHo = temp.getListThanhVienCuaHo();
                    while (rs_1.next()) {
                        NhanKhauModel nhanKhauModel = new NhanKhauModel();
                        ThanhVienCuaHoModel thanhVienCuaHoModel = new ThanhVienCuaHoModel();
                        nhanKhauModel.setID(rs_1.getInt("ID"));
                        nhanKhauModel.setBietDanh(rs_1.getString("bietDanh"));
                        nhanKhauModel.setHoTen(rs_1.getString("hoTen"));
                        nhanKhauModel.setGioiTinh(rs_1.getString("gioiTinh"));
                        nhanKhauModel.setNamSinh(rs_1.getDate("namSinh"));
                        nhanKhauModel.setNguyenQuan(rs_1.getString("nguyenQuan"));
                        nhanKhauModel.setTonGiao(rs_1.getString("tonGiao"));
                        nhanKhauModel.setDanToc(rs_1.getString("danToc"));
                        nhanKhauModel.setQuocTich(rs_1.getString("quocTich"));
                        nhanKhauModel.setSoHoChieu(rs_1.getString("soHoChieu"));
                        nhanKhauModel.setNoiThuongTru(rs_1.getString("noiThuongTru"));
                        nhanKhauModel.setDiaChiHienNay(rs_1.getString("diaChiHienNay"));
                        
                        thanhVienCuaHoModel.setIdHoKhau(rs_1.getInt("idHoKhau"));
                        thanhVienCuaHoModel.setIdNhanKhau(rs_1.getInt("ID"));
                        thanhVienCuaHoModel.setQuanHeVoiChuHo(rs_1.getString("quanHeVoiChuHo"));
                        listNhanKhau.add(nhanKhauModel);
                        listThanhVienCuaHo.add(thanhVienCuaHoModel);
                    }
                } catch (Exception e) {
                    System.out.println("services.HoKhauService.search()");
                    System.out.println(e.getMessage());
                }
                list.add(temp);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    /**
     * ham tao moi ho khau va up date lai thong tin nhan khau co trong ho cu
     * @param hoKhauBean ho khau moi duoc tach ra
     */
    public void tachHoKhau(HoKhauBean hoKhauBean) {
        /**
         * xoa cac thanh vien co trong moi ra khoi bang thanh_vien_cua_ho
         */
        
        // xoa chu ho
        String query = "DELETE FROM thanh_vien_cua_ho WHERE idNhanKhau = " + hoKhauBean.getChuHo().getID();   
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int rs = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // xoa cac thanh vien
        
        hoKhauBean.getListThanhVienCuaHo().forEach((ThanhVienCuaHoModel item) -> {
            String sql = "DELETE FROM thanh_vien_cua_ho WHERE idNhanKhau = " + item.getIdHoKhau();
            try {
                Connection connection = MysqlConnection.getMysqlConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                int rs = preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        
        /**
         * tao ho khau moi voi hoKhauBean
         */
        try {
            this.addNew(hoKhauBean);
            JOptionPane.showMessageDialog(null, "Thêm thành công!");
        } catch (Exception e) {
            System.out.println("services.HoKhauService.tachHoKhau()");
        } 
    }
    public void chuyenDi(int idhoKhau, String noiChuyenDen, String lyDoChuyen) {
        String sql = "UPDATE ho_khau SET lyDoChuyen = '"
                + lyDoChuyen
                + "',"
                + "ngayChuyenDi = NOW(), "
                + "diaChi = '"
                + noiChuyenDen
                + "',"
                + "nguoiThucHien = "
                + LoginController.currentUser.getID()
                + " WHERE ho_khau.ID = " + idhoKhau;
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int rs = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("services.HoKhauService.chuyenDi()");
            System.out.println(e.getMessage());
        }
    }
}
