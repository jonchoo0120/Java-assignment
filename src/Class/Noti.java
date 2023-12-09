package Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class Noti {; 
    private String runnerID;
    private String notification;
    private String time;
    private static ArrayList<Noti> notiList;
    private static DefaultTableModel tableModel;
    
    public Noti(String runnerID, String notification, String time){
        this.runnerID = runnerID;
        this.notification = notification;
        this.time = time;
    }

    public String getRunnerID() {
        return runnerID;
    }

    public void setRunnerID(String runnerID) {
        this.runnerID = runnerID;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static ArrayList<Noti> getNotiList() {
        return notiList;
    }

    public static void setNotiList(ArrayList<Noti> notiList) {
        Noti.notiList = notiList;
    }

    public static DefaultTableModel getTableModel() {
        return tableModel;
    }

    public static void setTableModel(DefaultTableModel tableModel) {
        Noti.tableModel = tableModel;
    }
    
    public static ArrayList loadnotifications(){
        notiList = new ArrayList<>();
        String notiTxt = "C:\\Users\\theli\\Documents\\NetBeansProjects\\Java-assignment\\src\\Database\\noti.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(notiTxt))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] orderData = line.split(", ");
                String loadRunnerID = orderData[0];
                String loadNoti = orderData[1];
                String loadTime = orderData[2];
                Noti noti = new Noti(loadRunnerID,loadNoti, loadTime);
                notiList.add(noti);
        }

        }catch (IOException e) {
            e.printStackTrace();
        }
        
        return(notiList);
     }
    
    public static void saveToFile(ArrayList<Noti> notiList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\theli\\Documents\\NetBeansProjects\\Java-assignment\\src\\Database\\noti.txt"))) {
            for (Noti notiLoad : notiList) {
                String line = notiLoad.getRunnerID() + ", " + notiLoad.getNotification() + ", " + notiLoad.getTime();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static DefaultTableModel startNotiTable(ArrayList<Noti> notiList, String operator) {
        // Create the table model with column headers
        String[] columnHeaders = {"Notifications", "Time"};
        tableModel = new DefaultTableModel(columnHeaders, 0);

        // Populate the table model with item data
        for (Noti notiLoad : notiList) {
            if(notiLoad.getRunnerID().equals(operator)){
                Object[] rowData = {notiLoad.getNotification(), notiLoad.getTime()};
                tableModel.addRow(rowData);
            }
        }
        return tableModel;

    }
}
