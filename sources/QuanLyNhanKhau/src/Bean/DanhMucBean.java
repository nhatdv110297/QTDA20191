/*
 * lớp tạo ra để hỗ trợ việc bắt sự kiện mỗi khi nhấn vào 1 label trong mainFrame
 * tạo danh sách các panel trong mainframe
 */
package Bean;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Hai
 */
public class DanhMucBean {
    private String kind;
    private JPanel jpn;
    private JLabel jlb;

    public DanhMucBean() {
    }

    public DanhMucBean(String kind, JPanel jpn, JLabel jlb) {
        this.kind = kind;
        this.jpn = jpn;
        this.jlb = jlb;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public JPanel getJpn() {
        return jpn;
    }

    public void setJpn(JPanel jpn) {
        this.jpn = jpn;
    }

    public JLabel getJlb() {
        return jlb;
    }

    public void setJlb(JLabel jlb) {
        this.jlb = jlb;
    }
    
    
}
