package controllers.HoKhauManagerController;

import Bean.HoKhauBean;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import services.HoKhauService;
import utility.TableModelHoKhau;

/**
 *
 * @author Hai
 */
public class ChuyenDiNoiKhacController {
    private HoKhauBean hoKhauBean;
    private final HoKhauService hoKhauService = new HoKhauService();
    private JTextField searchJtf;
    private JPanel tableJpn;
    private List<HoKhauBean> list;
    private final TableModelHoKhau tableModelHoKhau = new TableModelHoKhau();
    private final String COLUNMS[] = {"Mã hộ khẩu", "Họ tên chủ hộ", "Địa chỉ"}; 
    private JTextField maHoKhauJtf;
    private JTextField tenChuHoJtf;
    private JTextField maKhuVucJtf;
    private JTextField diaChiJtf;

    public ChuyenDiNoiKhacController(HoKhauBean hoKhauBean, JTextField searchJtf, JPanel tableJpn) {
        this.hoKhauBean = hoKhauBean;
        this.searchJtf = searchJtf;
        this.tableJpn = tableJpn;
        this.list = hoKhauService.getListHoKhau();
        setData();
        initAction();
    }
    
    public void setDataJtf(JTextField maHoKhauJtf, JTextField tenChuHoJtf, JTextField maKhuVucJtf, JTextField diaChiJtf) {
        this.diaChiJtf = diaChiJtf;
        this.maHoKhauJtf = maHoKhauJtf;
        this.tenChuHoJtf = tenChuHoJtf;
        this.maKhuVucJtf = maKhuVucJtf;
    }
    
    public void initAction() {
        this.searchJtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String key = searchJtf.getText().trim();
                if (key.isEmpty()) {
                    list = hoKhauService.getListHoKhau();
                } else {
                    list = hoKhauService.search(key);
                }
                setData();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String key = searchJtf.getText().trim();
                if (key.isEmpty()) {
                    list = hoKhauService.getListHoKhau();
                } else {
                    list = hoKhauService.search(key);
                }
                setData();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String key = searchJtf.getText().trim();
                if (key.isEmpty()) {
                    list = hoKhauService.getListHoKhau();
                } else {
                    list = hoKhauService.search(key);
                }
                setData();
            }
        });
    }

    
    public void setData() {
        DefaultTableModel model = tableModelHoKhau.setTableHoKhau(list, COLUNMS);
        
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
                HoKhauBean temp = list.get(table.getSelectedRow());
                hoKhauBean.setChuHo(temp.getChuHo());
                hoKhauBean.setHoKhauModel(temp.getHoKhauModel());
                hoKhauBean.setListNhanKhauModels(temp.getListNhanKhauModels());
                hoKhauBean.setListThanhVienCuaHo(temp.getListThanhVienCuaHo());
                
                maHoKhauJtf.setText(hoKhauBean.getHoKhauModel().getMaHoKhau());
                tenChuHoJtf.setText(hoKhauBean.getChuHo().getHoTen());
                maKhuVucJtf.setText(hoKhauBean.getHoKhauModel().getMaKhuVuc());
                diaChiJtf.setText(hoKhauBean.getHoKhauModel().getDiaChi());
                System.out.println(hoKhauBean.getHoKhauModel().getMaKhuVuc());
            }
            
        });
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        tableJpn.removeAll();
        tableJpn.setLayout(new BorderLayout());
        tableJpn.add(scroll);
        tableJpn.validate();
        tableJpn.repaint();
    }

    public HoKhauBean getHoKhauBean() {
        return hoKhauBean;
    }

    public void setHoKhauBean(HoKhauBean hoKhauBean) {
        this.hoKhauBean = hoKhauBean;
    }

    public HoKhauService getHoKhauService() {
        return hoKhauService;
    }

    public JTextField getSearchJtf() {
        return searchJtf;
    }

    public void setSearchJtf(JTextField searchJtf) {
        this.searchJtf = searchJtf;
    }

    public JPanel getTableJpn() {
        return tableJpn;
    }

    public void setTableJpn(JPanel tableJpn) {
        this.tableJpn = tableJpn;
    }

    public List<HoKhauBean> getList() {
        return list;
    }

    public void setList(List<HoKhauBean> list) {
        this.list = list;
    }
    
    
}
