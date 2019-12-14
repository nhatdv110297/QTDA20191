package controllers.HoKhauManagerController;

import Bean.HoKhauBean;
import Bean.MemOfFamily;
import Bean.NhanKhauBean;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import models.ThanhVienCuaHoModel;
import services.HoKhauService;
import utility.TableModelHoKhau;
import views.HoKhauManagerFrame.TachHoKhau;
import views.infoViews.InfoJframe;

/**
 *
 * @author Hai
 */
public class TachHoKhauController {
    private JTextField searchJtf;
    private JPanel tableTopJpn;
    private JPanel tableBotJpn;
    private JPanel tableRightJpn;
    private JTextField chuHoHienTaiJtf;
    private JTextField maKhuVucJtf;
    private JTextField diaChiJtf;
    private JTextField maHoKhauMoiJtf;
    private JTextField chuHoMoiJtf;
    private JButton addBtn;
    private JButton removeBtn;
    private JButton cancelBtn;
    private JButton acceptBtn;
    private final HoKhauService hoKhauService = new HoKhauService();
    private JFrame tachHoKhauJFrame;
    
    private List<HoKhauBean> listHoKhau;
    private final List<MemOfFamily> listThanhVienTrongHoMoi = new ArrayList<>();
    private List<MemOfFamily> listThanhVien;
    private final TableModelHoKhau tableModelHoKhau = new TableModelHoKhau();
    private final String[] COLUMNS_NK = {"Họ tên", "Ngày sinh", "Quan hệ với chủ hộ"};
    private final String[] COLUNMS = {"Mã hộ khẩu", "Họ tên chủ hộ", "Địa chỉ"}; 
    private HoKhauBean hoKhauSelected;
    private MemOfFamily thanhVienSeclected;
    private MemOfFamily thanhVienHoMoiSeclected;
    private final HoKhauBean hoKhauMoi = new HoKhauBean();
    
    public TachHoKhauController(JFrame tachHoKhauJFrame) {
        this.tachHoKhauJFrame = tachHoKhauJFrame;
    }
    
    public void init() {
        listHoKhau = this.hoKhauService.getListHoKhau();
        setData();
        
        searchJtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String key = searchJtf.getText().trim();
                if (key.isEmpty()) {
                    listHoKhau = hoKhauService.getListHoKhau();
                } else {
                    listHoKhau = hoKhauService.search(key);
                }
                setData();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String key = searchJtf.getText().trim();
                if (key.isEmpty()) {
                    listHoKhau = hoKhauService.getListHoKhau();
                } else {
                    listHoKhau = hoKhauService.search(key);
                }
                setData();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String key = searchJtf.getText().trim();
                if (key.isEmpty()) {
                    listHoKhau = hoKhauService.getListHoKhau();
                } else {
                    listHoKhau = hoKhauService.search(key);
                }
                setData();
            }
        });
        
        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    boolean isInHoMoi = false;
                    for(ThanhVienCuaHoModel item : hoKhauMoi.getListThanhVienCuaHo()) {
                        if (item.getIdNhanKhau() == thanhVienSeclected.getNhanKhau().getNhanKhauModel().getID()) {
                            isInHoMoi = true;
                        }
                    }
                    if (isInHoMoi || hoKhauMoi.getChuHo().getID() == thanhVienSeclected.getNhanKhau().getNhanKhauModel().getID()) {
                        JOptionPane.showMessageDialog(null, "Nhân khẩu đã nằm trong hộ mới.");
                    } else {
                        String quanHeVoiChuHo = "";
                        while (quanHeVoiChuHo.trim().isEmpty()) {                        
                            quanHeVoiChuHo = JOptionPane.showInputDialog(null, "Nhập quan hệ với chủ hộ: ", thanhVienSeclected.getNhanKhau().getNhanKhauModel().getHoTen(), 0);
                        }
                        if (quanHeVoiChuHo.equalsIgnoreCase("Chủ hộ")) {
                            chuHoMoiJtf.setText(thanhVienSeclected.getNhanKhau().getNhanKhauModel().getHoTen());
                            hoKhauMoi.setChuHo(thanhVienSeclected.getNhanKhau().getNhanKhauModel());
                        } else {
                            hoKhauMoi.getListNhanKhauModels().add(thanhVienSeclected.getNhanKhau().getNhanKhauModel());
                            ThanhVienCuaHoModel thanhVienCuaHoModel = new ThanhVienCuaHoModel();
                            thanhVienCuaHoModel.setIdNhanKhau(thanhVienSeclected.getNhanKhau().getNhanKhauModel().getID());
                            thanhVienCuaHoModel.setQuanHeVoiChuHo(quanHeVoiChuHo);
                            hoKhauMoi.getListThanhVienCuaHo().add(thanhVienCuaHoModel);
                            setDataHoMoi();
                        }
                    }
                } catch (Exception exception) {
                }
            }
        });
        
        removeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    for (int i = 0; i < hoKhauMoi.getListNhanKhauModels().size(); i++) {
                        if (hoKhauMoi.getListNhanKhauModels().get(i).getID() == thanhVienHoMoiSeclected.getNhanKhau().getNhanKhauModel().getID()) {
                            hoKhauMoi.getListNhanKhauModels().remove(i);
                            hoKhauMoi.getListThanhVienCuaHo().remove(i);
                            thanhVienHoMoiSeclected = null;
                            setDataHoMoi();
                        }
                    }
                } catch (Exception exception) {
                }
            }
            
        });
        
        acceptBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (maKhuVucJtf.getText().trim().isEmpty() 
                        || diaChiJtf.getText().trim().isEmpty() 
                        || maHoKhauMoiJtf.getText().trim().isEmpty() 
                        || chuHoMoiJtf.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập hết các trường bắt buộc!");
                } else {
                    hoKhauMoi.getHoKhauModel().setDiaChi(diaChiJtf.getText().trim());
                    hoKhauMoi.getHoKhauModel().setMaHoKhau( maHoKhauMoiJtf.getText().trim());
                    hoKhauMoi.getHoKhauModel().setMaKhuVuc(maKhuVucJtf.getText().trim());
                    hoKhauService.tachHoKhau(hoKhauMoi);
                    TachHoKhau tachHoKhau = (TachHoKhau)tachHoKhauJFrame;
                    tachHoKhau.getParentJFrame().setEnabled(true);
                    tachHoKhau.dispose();
                }
            }
            
        });
    }
    
    public void setData() {
        TableModel hokhau_model = this.tableModelHoKhau.setTableHoKhau(listHoKhau, COLUNMS);
        JTable table = new JTable(hokhau_model) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 30));
        table.setRowHeight(30);
        table.validate();
        table.repaint();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                HoKhauBean temp = listHoKhau.get(table.getSelectedRow());
                if (e.getClickCount() > 1) {
                    InfoJframe infoJframe = new InfoJframe(temp.toString(), tachHoKhauJFrame);
                    infoJframe.setLocationRelativeTo(null);
                    infoJframe.setVisible(true);
                } else {
                    // selected data
                    hoKhauSelected = temp;
                    chuHoHienTaiJtf.setText(hoKhauSelected.getChuHo().getHoTen());
                    setDataChoose();
                }
            }
            
        });
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(tableTopJpn.getSize());
        tableTopJpn.removeAll();
        tableTopJpn.setLayout(new BorderLayout());
        tableTopJpn.add(scroll);
        tableTopJpn.validate();
        tableTopJpn.repaint();
    }
    
    public void setDataChoose() {
        listThanhVien = new ArrayList<>();
        for (int i = 0; i < hoKhauSelected.getListNhanKhauModels().size(); i++) {
            MemOfFamily temp = new MemOfFamily();
            temp.getNhanKhau().setNhanKhauModel(hoKhauSelected.getListNhanKhauModels().get(i));
            temp.setThanhVienCuaHoModel(hoKhauSelected.getListThanhVienCuaHo().get(i));
            listThanhVien.add(temp);
        }
        DefaultTableModel model = tableModelHoKhau.setTableMember(listThanhVien, COLUMNS_NK);
        
        JTable table = new JTable(model) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
            
        };
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 30));
        table.setRowHeight(30);
        table.validate();
        table.repaint();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thanhVienSeclected = listThanhVien.get(table.getSelectedRow());
            }
            
        });
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(tableTopJpn.getSize());
        tableBotJpn.removeAll();
        tableBotJpn.setLayout(new BorderLayout());
        tableBotJpn.add(scroll);
        tableBotJpn.validate();
        tableBotJpn.repaint();
    }
    
    public void setDataHoMoi() {
        
        listThanhVienTrongHoMoi.clear();
        for (int i = 0; i < hoKhauMoi.getListNhanKhauModels().size(); i++) {
            MemOfFamily temp = new MemOfFamily();
            temp.getNhanKhau().setNhanKhauModel(hoKhauMoi.getListNhanKhauModels().get(i));
            temp.setThanhVienCuaHoModel(hoKhauMoi.getListThanhVienCuaHo().get(i));
            listThanhVienTrongHoMoi.add(temp);
        }
        DefaultTableModel model = tableModelHoKhau.setTableMember(listThanhVienTrongHoMoi, COLUMNS_NK);
        
        JTable table = new JTable(model) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
            
        };
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 30));
        table.setRowHeight(30);
        table.validate();
        table.repaint();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thanhVienHoMoiSeclected = listThanhVienTrongHoMoi.get(table.getSelectedRow());
            }
            
        });
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(tableRightJpn.getSize());
        tableRightJpn.removeAll();
        tableRightJpn.setLayout(new BorderLayout());
        tableRightJpn.add(scroll);
        tableRightJpn.validate();
        tableRightJpn.repaint();
    }

    public JFrame getTachHoKhauJFrame() {
        return tachHoKhauJFrame;
    }

    public void setTachHoKhauJFrame(JFrame tachHoKhauJFrame) {
        this.tachHoKhauJFrame = tachHoKhauJFrame;
    }

    public JTextField getSearchJtf() {
        return searchJtf;
    }

    public void setSearchJtf(JTextField searchJtf) {
        this.searchJtf = searchJtf;
    }

    public JPanel getTableTopJpn() {
        return tableTopJpn;
    }

    public void setTableTopJpn(JPanel tableTopJpn) {
        this.tableTopJpn = tableTopJpn;
    }

    public JPanel getTableBotJpn() {
        return tableBotJpn;
    }

    public void setTableBotJpn(JPanel tableBotJpn) {
        this.tableBotJpn = tableBotJpn;
    }

    public JPanel getTableRightJpn() {
        return tableRightJpn;
    }

    public void setTableRightJpn(JPanel tableRightJpn) {
        this.tableRightJpn = tableRightJpn;
    }

    public JTextField getChuHoHienTaiJtf() {
        return chuHoHienTaiJtf;
    }

    public void setChuHoHienTaiJtf(JTextField chuHoHienTaiJtf) {
        this.chuHoHienTaiJtf = chuHoHienTaiJtf;
    }

    public JTextField getMaKhuVucJtf() {
        return maKhuVucJtf;
    }

    public void setMaKhuVucJtf(JTextField maKhuVucJtf) {
        this.maKhuVucJtf = maKhuVucJtf;
    }

    public JTextField getDiaChiJtf() {
        return diaChiJtf;
    }

    public void setDiaChiJtf(JTextField diaChiJtf) {
        this.diaChiJtf = diaChiJtf;
    }

    public JTextField getMaHoKhauMoiJtf() {
        return maHoKhauMoiJtf;
    }

    public void setMaHoKhauMoiJtf(JTextField maHoKhauMoiJtf) {
        this.maHoKhauMoiJtf = maHoKhauMoiJtf;
    }

    public JTextField getChuHoMoiJtf() {
        return chuHoMoiJtf;
    }

    public void setChuHoMoiJtf(JTextField chuHoMoiJtf) {
        this.chuHoMoiJtf = chuHoMoiJtf;
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public void setAddBtn(JButton addBtn) {
        this.addBtn = addBtn;
    }

    public JButton getRemoveBtn() {
        return removeBtn;
    }

    public void setRemoveBtn(JButton removeBtn) {
        this.removeBtn = removeBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }

    public void setCancelBtn(JButton cancelBtn) {
        this.cancelBtn = cancelBtn;
    }

    public JButton getAcceptBtn() {
        return acceptBtn;
    }

    public void setAcceptBtn(JButton acceptBtn) {
        this.acceptBtn = acceptBtn;
    }
}
