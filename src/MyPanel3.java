import java.awt.*;
import javax.swing.*;

public class MyPanel3 extends JPanel {
    private JLabel jcomp1;
    private JTextField jcomp2;
    private JButton jcomp3;

    public MyPanel3() {
        //construct components
        jcomp1 = new JLabel("검색내용: ");
        jcomp2 = new JTextField(10);
        jcomp3 = new JButton("검색");

        // SET TEXT COLOR BLUE
        jcomp2.setForeground(Color.BLUE);

        FlowLayout layout = new FlowLayout();
        layout.setHgap(5);
        layout.setVgap(5);
        setLayout(layout);

        //add components
        add(jcomp1);
        add(jcomp2);
        add(jcomp3);
    }

    public JTextField getTextField4() {
        return jcomp2;
    }

    public JButton getJcomp3() {
        return jcomp3;
    }

}