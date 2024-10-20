import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;


class RoundJTextField extends JTextField {
    private Shape shape;
    public RoundJTextField(int size) {
        super(size);
        setOpaque(false); // As suggested by @AVD in comment.
    }
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }
}
public class testSwing {
    public static void main(String[] args) {
        JFrame bFrame = new JFrame();
        bFrame.setSize(500,700);
        bFrame.setVisible(true);
        bFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        bFrame.setTitle("arleasCHAT");
        JTextField r = new RoundJTextField(15);
        r.setBounds(5,610,440,40);
        r.setBackground(Color.darkGray);
        bFrame.add(r);
        r.setForeground(Color.WHITE);
        JScrollPane cArea = new JScrollPane();
        cArea.setBounds(7,5,470,600);
        ImageIcon sendB = new ImageIcon("sendB.png");
        JButton jb = new JButton("Send",sendB);
        JTextArea Ar = new JTextArea();
        Ar.setBackground(Color.DARK_GRAY);
        Ar.setForeground(Color.WHITE);
        cArea.getViewport().add(Ar);
        jb.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Ar.append(r.getText()+"\n");
                Ar.setCaretPosition(Ar.getDocument().getLength());
                r.setText("");
            }
        });
        jb.setBackground(Color.DARK_GRAY);
        jb.setBounds(450,610,40,40);
        bFrame.add(jb);
        bFrame.add(cArea);
        bFrame.setResizable(false);
        bFrame.getContentPane().setBackground(Color.DARK_GRAY);
    }

}

