import java.awt.*;
import javax.swing.*;

public class MyPanel1 extends JPanel {
    private JLabel jcomp1;
    private JTextField jcomp2;
    private JLabel jcomp3;
    private JTextField jcomp4;
    private JLabel jcomp5;
    private JTextField jcomp6;

    public MyPanel1() {
        //construct components
        jcomp1 = new JLabel("이름: ");
        jcomp2 = new JTextField(10);

        jcomp3 = new JLabel("학번: ");
        jcomp4 = new JTextField(10);
        jcomp5 = new JLabel("성적: ");
        jcomp6 = new JTextField(10);

        // set text color BLUE
        jcomp2.setForeground(Color.BLUE);
        jcomp4.setForeground(Color.BLUE);
        jcomp6.setForeground(Color.BLUE);

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
        add(jcomp6);
    }


    public JTextField getTextField1() {
        return jcomp2;
    }

    public JTextField getTextField2() {
        return jcomp4;
    }

    public JTextField getTextField3() {
        return jcomp6;
    }


}
