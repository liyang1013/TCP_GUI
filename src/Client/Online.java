package Client;

import javax.swing.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Online {
    private static JList online;
    private static DefaultListModel dlm;


    static {
        dlm = new DefaultListModel();
        online=new JList();
        online.setModel(dlm);
    }

    public static JList getJList(){return online;}
    public static DefaultListModel getDlm(){return dlm;}

}
