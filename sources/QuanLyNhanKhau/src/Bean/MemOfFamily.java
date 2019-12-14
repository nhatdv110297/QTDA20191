package Bean;

import models.ThanhVienCuaHoModel;

/**
 *
 * @author Hai
 */
public class MemOfFamily {
    private NhanKhauBean nhanKhau;
    private ThanhVienCuaHoModel thanhVienCuaHoModel;

    public MemOfFamily(NhanKhauBean nhanKhau, ThanhVienCuaHoModel thanhVienCuaHoModel) {
        this.nhanKhau = nhanKhau;
        this.thanhVienCuaHoModel = thanhVienCuaHoModel;
    }
    
    public MemOfFamily() {
        this.nhanKhau = new NhanKhauBean();
        this.thanhVienCuaHoModel = new ThanhVienCuaHoModel();
    }
    
    
    public NhanKhauBean getNhanKhau() {
        return nhanKhau;
    }

    public void setNhanKhau(NhanKhauBean nhanKhau) {
        this.nhanKhau = nhanKhau;
    }

    public ThanhVienCuaHoModel getThanhVienCuaHoModel() {
        return thanhVienCuaHoModel;
    }

    public void setThanhVienCuaHoModel(ThanhVienCuaHoModel thanhVienCuaHoModel) {
        this.thanhVienCuaHoModel = thanhVienCuaHoModel;
    }
    
    
}
