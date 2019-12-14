/*
 * lớp xử lý sự kiện chuyển qua lại giữa các màn hình tại mainFrame
 * tạo ra các hiệu ứng lựa chọn các label
 */
package controllers;

import Bean.DanhMucBean;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import views.HoKhauManagePanel;
import views.HomePagePanel;
import views.NhanKhauManagePanel;
import views.ThongKePanel;
import views.TimKiemPanel;

/**
 *
 * @author Hai
 */
public class MainController {
    private JFrame jfrMain;
    private JPanel root;
    private String kindSelected;
    private List<DanhMucBean> listDanhMuc;

    public MainController(JPanel root, JFrame jfrMain) {
        this.jfrMain = jfrMain;
        this.root = root;
    }
    
    
    // set panel for root
    public void setView(JPanel jpnItem, JLabel jlbItem, String kind) {
        this.kindSelected = kind;
        jpnItem.setBackground(new Color(0));
        jlbItem.setBackground(new Color(0));
        JPanel view = new  JPanel();
        switch(kind) {
                case "TrangChu":
                    view = new HomePagePanel();
                    break;
                case "NhanKhau":
                    view = new NhanKhauManagePanel(this.jfrMain);
                    break;
                case "HoKhau":
                    view = new HoKhauManagePanel(this.jfrMain);
                    break;
                case "TimKiem":
                    view = new TimKiemPanel(this.jfrMain);
                    break;
                case "ThongKe":
                    view = new ThongKePanel(this.jfrMain);
                    break;
                //any more
                default:
                    break;
            }
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(view);
        root.validate();
        root.repaint();
    } 
    
    //set animation for menu panel
    public void setEvent(List<DanhMucBean> listDanhMuc) {
        this.listDanhMuc = listDanhMuc;
        this.listDanhMuc.forEach((item) -> {
            item.getJlb().addMouseListener(new LabelEvent(this.jfrMain, item.getKind(), item.getJpn(), item.getJlb()));
        });
    }
    
    public void setDefaultColor() {
        this.listDanhMuc.forEach((item) -> {
            if (item.getKind().equals("TrangChu")) {
                item.getJlb().setBackground(new Color(0, 160, 50));
                item.getJpn().setBackground(new Color(0, 160, 50));
            } else {
                item.getJlb().setBackground(new Color(102,102,102));
                item.getJpn().setBackground(new Color(102,102,102));
            }
        });
    }
    
    class LabelEvent implements MouseListener {
        
        private JPanel view;
        private JFrame jfrMain;
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        public LabelEvent(JFrame jfrMain, String kind, JPanel jpnItem, JLabel jlbItem) {
            this.jfrMain = jfrMain;
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch(kind) {
                case "TrangChu":
                    view = new HomePagePanel();
                    break;
                case "NhanKhau":
                    view = new NhanKhauManagePanel(this.jfrMain);
                    break;
                 case "HoKhau":
                    view = new HoKhauManagePanel(this.jfrMain);
                    break;
                case "TimKiem":
                    view = new TimKiemPanel(this.jfrMain);
                    break;
                case "ThongKe":
                    view = new ThongKePanel(this.jfrMain);
                    break;
                default:
                    break;
            }
            
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(view);
            root.validate();
            root.repaint();
            setDefaultColor();
            jlbItem.setBackground(new Color(0));
            jpnItem.setBackground(new Color(0));
        }        

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jlbItem.setBackground(Color.BLACK);
            jpnItem.setBackground(Color.BLACK);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jlbItem.setBackground(Color.BLACK);
            jpnItem.setBackground(Color.BLACK);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kind.equalsIgnoreCase(kindSelected)) {
                if (kind.equals("TrangChu")) {
                    jlbItem.setBackground(new Color(0, 160, 50));
                    jpnItem.setBackground(new Color(0, 160, 50));
                } else 
                {
                    jlbItem.setBackground(new Color(102,102,102));
                    jpnItem.setBackground(new Color(102,102,102));
                }
            }
        }
        
    }
}
