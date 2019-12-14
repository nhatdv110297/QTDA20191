package models;

import java.util.Date;

/**
 *
 * @author Hai
 */
public class GiaDinhModel {
    private int ID;
    private int idNhanKhau;
    private String hoTen;
    private Date namSinh;
    private String gioiTinh;
    private String quanHeVoiNhanKhau;
    private String ngheNghiep;
    private String diaChiHienTai;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Date namSinh) {
        this.namSinh = namSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQuanHeVoiNhanKhau() {
        return quanHeVoiNhanKhau;
    }

    public void setQuanHeVoiNhanKhau(String quanHeVoiNhanKhau) {
        this.quanHeVoiNhanKhau = quanHeVoiNhanKhau;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getDiaChiHienTai() {
        return diaChiHienTai;
    }

    public void setDiaChiHienTai(String diaChiHienTai) {
        this.diaChiHienTai = diaChiHienTai;
    }
    
    
}
