package controllers;

import Bean.NhanKhauBean;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import models.NhanKhauModel;
import services.NhanKhauService;
import services.StringService;
import utility.ClassTableModel;


/**
 *
 * @author Hai
 */
public class ThongKePanelController {
    private JComboBox GenderJcb;
    private JComboBox StatusJcb;
    private JTextField tuTuoiJtf;
    private JTextField denTuoiJtf;
    private JTextField tuNamJtf;
    private JTextField denNamJtf;
    private JPanel jpnView;
    private NhanKhauService nhanKhauService;
    private List<NhanKhauBean> listNhanKhauBeans;
    private ClassTableModel classTableModel;
    private final String[] COLUMNS = {"ID", "Họ tên", "Ngày sinh", "Giới tính", "Địa chỉ hiện nay"};

    public ThongKePanelController(JComboBox genderJcb, JComboBox statusJcb, JTextField tuTuoiJtf, JTextField denTuoiJtf, JTextField tuNamJtf, JTextField denNamJtf, JPanel jpnView) {
        this.GenderJcb = genderJcb;
        this.StatusJcb = statusJcb;
        this.tuTuoiJtf = tuTuoiJtf;
        this.denTuoiJtf = denTuoiJtf;
        this.tuNamJtf = tuNamJtf;
        this.denNamJtf = denNamJtf;
        this.jpnView = jpnView;
        this.nhanKhauService = new NhanKhauService();
        this.listNhanKhauBeans = new ArrayList<>();
        this.listNhanKhauBeans = this.nhanKhauService.getListNhanKhau();
        this.classTableModel = new ClassTableModel();
    }
    
    public JPanel getJpnView() {
        return jpnView;
    }

    public void setJpnView(JPanel jpnView) {
        this.jpnView = jpnView;
    }

    public void setData() {
        int tuTuoi = -1;
        int denTuoi = 200;
        int tuNam = 0;
        int denNam = 2100;
        String gender = StringService.covertToString((String)this.GenderJcb.getSelectedItem());
        String status = StringService.covertToString((String)this.StatusJcb.getSelectedItem());
        try {
            if (!this.tuTuoiJtf.getText().trim().isEmpty()) {
                tuTuoi = Integer.parseInt(this.tuTuoiJtf.getText().trim());
            } else {
                tuTuoi = -1;
            }
            if (!this.denTuoiJtf.getText().trim().isEmpty()) {
                denTuoi = Integer.parseInt(this.denTuoiJtf.getText().trim());
            } else {
                denTuoi = 200;
            }
            if (!this.tuNamJtf.getText().trim().isEmpty()) {
                tuNam = Integer.parseInt(this.tuNamJtf.getText().trim());
            }
            if (!this.denNamJtf.getText().trim().isEmpty()) {
                denNam = Integer.parseInt(this.denNamJtf.getText().trim());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(denTuoiJtf, "Vui lòng nhập đúng kiểu dữ liệu!!", "Warring", JOptionPane.ERROR_MESSAGE);
        }
        this.listNhanKhauBeans = this.nhanKhauService.statisticNhanKhau(tuTuoi, denTuoi, gender, status, tuNam, denNam);
        setDataTable();
    }
    
    public void setDataTable() {
        List<NhanKhauModel> listItem = new ArrayList<>();
        this.listNhanKhauBeans.forEach(nhankhau -> {
            listItem.add(nhankhau.getNhanKhauModel());
        });
        DefaultTableModel model = classTableModel.setTableNhanKhau(listItem, COLUMNS);
        JTable table = new JTable(model);
        
        // thiet ke bang
        
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getColumnModel().getColumn(0).setMaxWidth(80);
        table.getColumnModel().getColumn(0).setMinWidth(80);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        jpnView.removeAll();
        jpnView.setLayout(new CardLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint();
    }

    public JComboBox getGenderJcb() {
        return GenderJcb;
    }

    public void setGenderJcb(JComboBox GenderJcb) {
        this.GenderJcb = GenderJcb;
    }

    public JComboBox getStatusJcb() {
        return StatusJcb;
    }

    public void setStatusJcb(JComboBox StatusJcb) {
        this.StatusJcb = StatusJcb;
    }

    public JTextField getTuTuoiJtf() {
        return tuTuoiJtf;
    }

    public void setTuTuoiJtf(JTextField tuTuoiJtf) {
        this.tuTuoiJtf = tuTuoiJtf;
    }

    public JTextField getDenTuoiJtf() {
        return denTuoiJtf;
    }

    public void setDenTuoiJtf(JTextField denTuoiJtf) {
        this.denTuoiJtf = denTuoiJtf;
    }

    public JTextField getTuNamJtf() {
        return tuNamJtf;
    }

    public void setTuNamJtf(JTextField tuNamJtf) {
        this.tuNamJtf = tuNamJtf;
    }

    public JTextField getDenNamJtf() {
        return denNamJtf;
    }

    public void setDenNamJtf(JTextField denNamJtf) {
        this.denNamJtf = denNamJtf;
    }
    
    
}
