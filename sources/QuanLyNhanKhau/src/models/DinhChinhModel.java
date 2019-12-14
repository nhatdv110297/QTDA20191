package models;

import java.util.Date;

/**
 *
 * @author Hai
 */
public class DinhChinhModel {
    private int ID;
    private int idHoKhau;
    private String thongTinThayDoi;
    private String thayDoiTu;
    private String thayDoiThanh;
    private Date ngayThayDoi;
    private int idNguoiThayDoi;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdHoKhau() {
        return idHoKhau;
    }

    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    public String getThongTinThayDoi() {
        return thongTinThayDoi;
    }

    public void setThongTinThayDoi(String thongTinThayDoi) {
        this.thongTinThayDoi = thongTinThayDoi;
    }

    public String getThayDoiTu() {
        return thayDoiTu;
    }

    public void setThayDoiTu(String thayDoiTu) {
        this.thayDoiTu = thayDoiTu;
    }

    public String getThayDoiThanh() {
        return thayDoiThanh;
    }

    public void setThayDoiThanh(String thayDoiThanh) {
        this.thayDoiThanh = thayDoiThanh;
    }

    public Date getNgayThayDoi() {
        return ngayThayDoi;
    }

    public void setNgayThayDoi(Date ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }

    public int getIdNguoiThayDoi() {
        return idNguoiThayDoi;
    }

    public void setIdNguoiThayDoi(int idNguoiThayDoi) {
        this.idNguoiThayDoi = idNguoiThayDoi;
    }
    
    
}
