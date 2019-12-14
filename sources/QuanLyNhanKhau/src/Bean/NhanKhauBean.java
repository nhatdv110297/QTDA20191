package Bean;

import java.util.ArrayList;
import java.util.List;
import models.ChungMinhThuModel;
import models.GiaDinhModel;
import models.NhanKhauModel;
import models.TieuSuModel;

/**
 *
 * @author Hai
 */
public class NhanKhauBean {
    private NhanKhauModel nhanKhauModel;
    private ChungMinhThuModel chungMinhThuModel;
    private List<TieuSuModel> listTieuSuModels;
    private List<GiaDinhModel> listGiaDinhModels;
    
    public NhanKhauBean(NhanKhauModel nhanKhauModel, ChungMinhThuModel chungMinhThuModel, List<TieuSuModel> listTieuSuModels, List<GiaDinhModel> listGiaDinhModels) {
        this.nhanKhauModel = nhanKhauModel;
        this.chungMinhThuModel = chungMinhThuModel;
        this.listTieuSuModels = listTieuSuModels;
        this.listGiaDinhModels = listGiaDinhModels;
    }

    public NhanKhauBean() {
        this.nhanKhauModel = new NhanKhauModel();
        this.chungMinhThuModel = new ChungMinhThuModel();
        this.listGiaDinhModels = new ArrayList<>();
        this.listTieuSuModels = new ArrayList<>();
    }
    
    public NhanKhauModel getNhanKhauModel() {
        return nhanKhauModel;
    }

    public void setNhanKhauModel(NhanKhauModel nhanKhauModel) {
        this.nhanKhauModel = nhanKhauModel;
    }

    public ChungMinhThuModel getChungMinhThuModel() {
        return chungMinhThuModel;
    }

    public void setChungMinhThuModel(ChungMinhThuModel chungMinhThuModel) {
        this.chungMinhThuModel = chungMinhThuModel;
    }

    public List<TieuSuModel> getListTieuSuModels() {
        return listTieuSuModels;
    }

    public void setListTieuSuModels(List<TieuSuModel> listTieuSuModels) {
        this.listTieuSuModels = listTieuSuModels;
    }

    public List<GiaDinhModel> getListGiaDinhModels() {
        return listGiaDinhModels;
    }

    public void setListGiaDinhModels(List<GiaDinhModel> listGiaDinhModels) {
        this.listGiaDinhModels = listGiaDinhModels;
    }
    
    @Override
    public String toString() {
        String res =  "<html><style>p {padding: 5px; margin-left: 20px} table, th, td {border: 1px solid black; border-collapse: collapse;} table {width: 500px}</style> <div>"
                + "<h3>Thông tin cơ bản:"
                + "<p>Họ tên: <b>" + nhanKhauModel.getHoTen() + "</p>"
                + "<p>Năm sinh: <b>" + nhanKhauModel.getNamSinh() + "</p>"
                + "<p>Giới tính: <b>" + nhanKhauModel.getGioiTinh() + "</p>"
                + "<p>Nguyên Quán: <b>" + nhanKhauModel.getNguyenQuan()+ "</p>"
                + "<p>Dân tộc: <b>" + nhanKhauModel.getDanToc()+ "</p>"
                + "<p>Tôn giáo: <b>" + nhanKhauModel.getTonGiao()+ "</p>"
                + "<p>Quốc tịch: <b>" + nhanKhauModel.getQuocTich()+ "</p>"
                + "<p>Số CMT: <b>" + chungMinhThuModel.getSoCMT()+ "</p>"
                + "<h4>Tiểu sử<table>"
                + "<tr>"
                + "<th>Từ ngày</th>"
                + "<th>Đến ngày</th>"
                + "<th>Địa chỉ</th>"
                + "<th>Nơi làm việc</th>"
                + "</tr>";
        for (TieuSuModel tieuSuModel: listTieuSuModels) {
            res += "<tr>"
                    + "<td>"
                    + tieuSuModel.getTuNgay().toString()
                    + "</td>"
                    + "<td>"
                    + tieuSuModel.getDenNgay().toString()
                    + "</td>"
                    + "<td>"
                    + tieuSuModel.getDiaChi()
                    + "</td>"
                    + "<td>"
                    + tieuSuModel.getNoiLamViec()
                    + "</td>"
                    + "</tr>";
        }
        res += "</table><h4>Gia đình<table>"
                + "<tr>"
                + "<th>Họ tên</th>"
                + "<th>Ngày sinh</th>"
                + "<th>Giới tính</th>"
                + "<th>Quan hệ với nhân khẩu</th>"
                + "<th>Nghề nghiệp</th>"
                + "<th>Địa chỉ hiện tại</th>"
                +"</tr>";
        for (GiaDinhModel giaDinhModel: listGiaDinhModels) {
            res += "<tr>"
                    + "<td>"
                    + giaDinhModel.getHoTen()
                    + "</td>"
                    + "<td>"
                    + giaDinhModel.getNamSinh().toString()
                    + "</td>"
                    + "<td>"
                    + giaDinhModel.getGioiTinh()
                    + "</td>"
                    + "<td>"
                    + giaDinhModel.getQuanHeVoiNhanKhau()
                    + "</td>"
                    + "<td>"
                    + giaDinhModel.getNgheNghiep()
                    + "</td>"
                    + "<td>"
                    + giaDinhModel.getDiaChiHienTai()
                    + "</td>"
                    +"</tr>";
        }
        res +=  "</table>"
                + "</div></html>";
        return res;
    }
}
