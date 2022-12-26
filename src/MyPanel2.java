import java.awt.*;
import javax.swing.*;

public class MyPanel2 extends JPanel {
    private JButton jcomp1;
    private JButton jcomp2;
    private JButton jcomp3;
    private JButton jcomp4;
    private JButton jcomp5;

    public MyPanel2() {
        //construct components
        jcomp1 = new JButton("입력");
        jcomp2 = new JButton("출력");
        jcomp3 = new JButton("수정");
        jcomp4 = new JButton("삭제");
        jcomp5 = new JButton("저장");

        FlowLayout layout = new FlowLayout();
        layout.setHgap(5);
        layout.setVgap(5);
        setLayout(layout);

        //add components
        add(jcomp1);
        add(jcomp2);
        add(jcomp3);
        add(jcomp4);
        add(jcomp5);
    }

    public JButton getJcomp1() {
        return jcomp1;
    }

    public JButton getJcomp2() {
        return jcomp2;
    }

    public JButton getJcomp3() {
        return jcomp3;
    }

    public JButton getJcomp4() {
        return jcomp4;
    }

    public JButton getJcomp5() {
        return jcomp5;
    }


}
