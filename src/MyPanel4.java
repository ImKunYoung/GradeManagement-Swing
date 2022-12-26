import java.awt.*;
import javax.swing.*;

public class MyPanel4 extends JPanel {
    private JTextArea jcomp1;

    public MyPanel4() {
        // Set the layout of the panel
        setLayout(new BorderLayout());

        // Construct the JTextArea
        jcomp1 = new JTextArea();
        jcomp1.setLineWrap(true);
        jcomp1.setWrapStyleWord(true);
        jcomp1.setEditable(false);

        // set text color BLUE
        jcomp1.setForeground(Color.BLUE);

        //add components
        add(jcomp1, BorderLayout.CENTER);
    }

    public void setJcomp1(String text) {
        jcomp1.setText(text);
    }

    public JTextArea getJcomp1() {
        return jcomp1;
    }

}