import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable{

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;
    private JTextArea Ar;
    private JTextField r;
    private int state;


    @Override
    public void run(){
        try {

            state = 0;
            JFrame bFrame = new JFrame();
            bFrame.setSize(500,700);
            bFrame.setVisible(true);
            bFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            bFrame.setTitle("arleasCHAT");
            r = new RoundJTextField(15);
            r.setBounds(5,610,440,40);
            r.setForeground(Color.white);
            bFrame.add(r);
            r.setBackground(Color.darkGray);
            r.setFont(r.getFont().deriveFont(15f));
            JScrollPane cArea = new JScrollPane();
            cArea.setBounds(7,5,470,600);
            ImageIcon sendB = new ImageIcon("sendB.png");
            JButton jb = new JButton("Send",sendB);
            Ar = new JTextArea();
            Ar.setBackground(Color.darkGray);
            Ar.setForeground(Color.white);
            Ar.setFont(Ar.getFont().deriveFont(17f));
            Ar.setEditable(false);
            cArea.getViewport().add(Ar);
            jb.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if("".equals(r.getText())){
                        state = 0;
                    }
                    else {
                        state = 1;
                    }
                    Ar.setCaretPosition(Ar.getDocument().getLength());
                }
            });
            jb.setBackground(Color.DARK_GRAY);
            jb.setBounds(450,610,35,40);
            bFrame.add(jb);
            bFrame.add(cArea);
            bFrame.setResizable(false);
            bFrame.getContentPane().setBackground(Color.DARK_GRAY);
            //galti khatam


            client = new Socket("192.168.200.159", 9999);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            InputHandler inHandler = new InputHandler();
            Thread inThread = new Thread(inHandler);
            inThread.start();

            String inMessage;
            while ((inMessage = in.readLine()) != null){
                textAreaUpdate(inMessage);
            }
        } catch (IOException e){
            shutdown();
        }
    }

    public boolean checkClick(){
        return state ==1;
    }

    public void textAreaUpdate(String x){
        Ar.append(x+"\n");
    }

    public void shutdown(){
        done = true;
        try {
            in.close();
            out.close();
            if (!client.isClosed()){
                client.close();
            }
        } catch (IOException e){
            //ignore
        }
    }

    class InputHandler implements Runnable{

        @Override
        public void run(){
            try {
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while (!done){
                    System.out.println();
                    if(checkClick()){
                        String message = r.getText();
                        r.setText("");
                        if (message.equals("/quit")) {
                            out.println(message);
                            inReader.close();
                            shutdown();
                        } else {
                            out.println(message);
                        }
                        state = 0;
                    }
                }
            } catch (IOException e){
                shutdown();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}