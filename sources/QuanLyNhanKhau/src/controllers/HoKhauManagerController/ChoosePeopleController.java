package controllers.HoKhauManagerController;

import Bean.NhanKhauBean;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import services.HoKhauService;
import services.NhanKhauService;
import utility.TableModelHoKhau;

/**
 *
 * @author Hai
 */
public class ChoosePeopleController {
    private NhanKhauBean nhanKhauBean;
    private JTextField searchJtf;
    private JTextField selectedJtf;
    private JPanel tableJPanel;
    private List<NhanKhauBean> list;
    private final HoKhauService hoKhauService = new HoKhauService();
    private final NhanKhauService nhanKhauService = new NhanKhauService();
    private final TableModelHoKhau tableModelHoKhau  = new TableModelHoKhau();
    private final String[] COLUMNS = {"Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ hiện nay", "Số CMT"};

    /**
     * 
     * @param nhanKhauBean
     * @param searchJtf
     * @param selectedJtf
     * @param tableJPanel 
     */
    public ChoosePeopleController(NhanKhauBean nhanKhauBean, JTextField searchJtf, JTextField selectedJtf, JPanel tableJPanel) {
        this.nhanKhauBean = nhanKhauBean;
        this.searchJtf = searchJtf;
        this.selectedJtf = selectedJtf;
        this.tableJPanel = tableJPanel;
        this.list = this.nhanKhauService.getListNhanKhau();
        setData();
        initAction();
    }
    
    public void initAction() {
        this.searchJtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String key = searchJtf.getText();
                list = nhanKhauService.search(key.trim());
                setData();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String key = searchJtf.getText();
                list = nhanKhauService.search(key.trim());
                setData();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String key = searchJtf.getText();
                list = nhanKhauService.search(key.trim());
                setData();
            }
        });
    }
    
    public void setData() {
        DefaultTableModel model = this.tableModelHoKhau.setTableNhanKhau(this.list, COLUMNS);
        JTable table = new JTable(model){
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;   //To change body of generated methods, choose Tools | Templates.
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
//                JOptionPane.showConfirmDialog(null, table.getSelectedRow());
                if (e.getClickCount() > 1) {
                    JOptionPane.showMessageDialog(null, "aa");
                }
                NhanKhauBean temp = list.get(table.getSelectedRow());
                if (hoKhauService.checkPerson(temp.getNhanKhauModel().getID())) {
                    nhanKhauBean.setNhanKhauModel(temp.getNhanKhauModel());
                    nhanKhauBean.setChungMinhThuModel(temp.getChungMinhThuModel());
                    nhanKhauBean.setListGiaDinhModels(temp.getListGiaDinhModels());
                    nhanKhauBean.setListTieuSuModels(temp.getListTieuSuModels());
                    selectedJtf.setText(nhanKhauBean.getNhanKhauModel().getHoTen());
                } else {
                    JOptionPane.showMessageDialog(null, "Nhân khẩu đã nằm trong hộ khẩu khác!", "Warning", JOptionPane.NO_OPTION);
                }
            }
        });
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        tableJPanel.removeAll();
        tableJPanel.setLayout(new BorderLayout());
        tableJPanel.add(scroll);
        tableJPanel.validate();
        tableJPanel.repaint();
    }

    public NhanKhauBean getNhanKhauBean() {
        return nhanKhauBean;
    }

    public void setNhanKhauBean(NhanKhauBean nhanKhauBean) {
        this.nhanKhauBean = nhanKhauBean;
    }

    public JTextField getSearchJtf() {
        return searchJtf;
    }

    public void setSearchJtf(JTextField searchJtf) {
        this.searchJtf = searchJtf;
    }

    public JPanel getTableJPanel() {
        return tableJPanel;
    }

    public void setTableJPanel(JPanel tableJPanel) {
        this.tableJPanel = tableJPanel;
    }

    public JTextField getSelectedJtf() {
        return selectedJtf;
    }

    public void setSelectedJtf(JTextField selectedJtf) {
        this.selectedJtf = selectedJtf;
    }

    public List<NhanKhauBean> getList() {
        return list;
    }

    public void setList(List<NhanKhauBean> list) {
        this.list = list;
    }

    
}
