package utility;

import Bean.HoKhauBean;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.GiaDinhModel;
import models.NhanKhauModel;
import models.TieuSuModel;

/**
 *
 * @author Hai
 * class dinh nghia cac dang table co trong phan mem
 */
public class ClassTableModel {
    // bang cho main frame
    public DefaultTableModel setTableNhanKhau(List<NhanKhauModel> listItem, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        listItem.forEach((NhanKhauModel item) -> {
            obj[0] = item.getID();
            obj[1] = item.getHoTen();
            obj[2] = item.getNamSinh();
            obj[3] = item.getGioiTinh();
            obj[4] = item.getDiaChiHienNay();
            dtm.addRow(obj);
        });
        return dtm;
    }
    // table cho tieusu
    public DefaultTableModel setTableTieuSu(List<TieuSuModel> tieuSu, String[] listColumn) {
        final int column = listColumn.length;
        
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                 return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[column];
        
        tieuSu.forEach((TieuSuModel item) -> {
            obj[0] = item.getTuNgay().toString();
            obj[1] = item.getDenNgay().toString();
            obj[2] = item.getDiaChi();
            obj[3] = item.getNgheNghiep();
            obj[4] = item.getNoiLamViec();
            dtm.addRow(obj);
        });
        
        dtm.addRow(new Object[] {"", "", "", "", ""});
        
//        dtm.addTableModelListener(new TableModelListener() {
//            @Override
//            public void tableChanged(TableModelEvent e) {
//                int a = dtm.getRowCount();
//                if ((e.getLastRow() + 1) == dtm.getRowCount()) {
//                    System.out.println(); 
//                }
//                
//            }
//        });
        return dtm;
    }
    public DefaultTableModel setTableGiaDinh(List<GiaDinhModel> giaDinh, String[] listColumn) {
        final int column = listColumn.length;
        
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                 return columnIndex == 6 ? Boolean.class : String.class;
            }
        };
        
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[column];
        
        giaDinh.forEach((GiaDinhModel item) -> {
            obj[0] = item.getHoTen();
            obj[1] = item.getNamSinh().toString();
            obj[2] = item.getGioiTinh();
            obj[3] = item.getQuanHeVoiNhanKhau();
            obj[4] = item.getNgheNghiep();
            obj[5] = item.getDiaChiHienTai();
            dtm.addRow(obj);
        });
        
        dtm.addRow(new Object[] {"", "", "", "", "", ""});
        return dtm;
    }
    
    public DefaultTableModel setTableHoKhau(List<HoKhauBean> listItem, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        listItem.forEach((HoKhauBean item) -> {
            obj[0] = item.getHoKhauModel().getID();
            obj[1] = item.getHoKhauModel().getMaHoKhau();
            obj[2] = item.getChuHo().getHoTen();
            obj[3] = item.getHoKhauModel().getDiaChi();
            obj[4] = item.getHoKhauModel().getNgayLap();
            dtm.addRow(obj);
        });
        return dtm;
    }
}
