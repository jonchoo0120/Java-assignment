package Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GetTask{

    private String orderID; 
    private String address;
    private String name;
    private String phoneNum;
    private static ArrayList<GetTask> orderList;
    private static DefaultTableModel tableModel;

    public GetTask(String orderID, String address, String name, String phoneNum) {
        this.orderID = orderID;
        this.address = address;
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public static ArrayList<GetTask> getOrderList() {
        return orderList;
    }

    public static DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public static void setOrderList(ArrayList<GetTask> orderList) {
        GetTask.orderList = orderList;
    }

    public static void setTableModel(DefaultTableModel tableModel) {
        GetTask.tableModel = tableModel;
    }
    
    
    
    //array
     public static ArrayList loadorders(){
        orderList = new ArrayList<>();
        String ordersTxt = "C:\\Users\\PC\\Documents\\NetBeansProjects\\Assignment\\src\\Database\\GetTask.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(ordersTxt))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] orderData = line.split(", ");

                String orderIDLoad = orderData[0];
                String addressLoad = orderData[1];
                String nameLoad = orderData[2];
                String phoneNumLoad = orderData[3];
                GetTask orderLoad = new GetTask(orderIDLoad,addressLoad, nameLoad, phoneNumLoad);
                orderList.add(orderLoad);
        }

        }catch (IOException e) {
            e.printStackTrace();
        }
        
        return(orderList);
     }
     
     public static void saveToFile(ArrayList<Order> orderList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Database\\GetTask.txt"))) {
            for (Order orderLoad : orderList) {
                String line = orderLoad.getOrderID() + ", " + orderLoad.getAddress() + ", " + orderLoad.getName() + ", " + orderLoad.getPhoneNum();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
     public static String incrementString(String input) {
        String numericPart = input.substring(1); 
        int incrementedValue = Integer.parseInt(numericPart) + 1;
        String formattedIncrementedValue = String.format("%04d", incrementedValue);
        return "I" + formattedIncrementedValue;
    }
     
     public static DefaultTableModel initializeTable(ArrayList<GetTask> orderList) {
        // Create the table model with column headers
        String[] columnHeaders = {"Order ID", "Address", "Customer Name", "Phone number"};
        tableModel = new DefaultTableModel(columnHeaders, 0);

        // Populate the table model with item data
        for (GetTask orderLoad : orderList) {
            Object[] rowData = {orderLoad.getOrderID(), orderLoad.getAddress(), orderLoad.getName(), orderLoad.getPhoneNum()};
            tableModel.addRow(rowData);
        }
        return tableModel;

    }
     
    public static void saveTableData(JTable jTable1, ArrayList<Order> orderList) {
    int numRows = jTable1.getRowCount();

    for (int row = 0; row < numRows; row++) {
        String orderID = (String) jTable1.getValueAt(row, 0);
        String address = (String) jTable1.getValueAt(row, 1);
        String name = (String) jTable1.getValueAt(row, 2);
        String phoneNum = (String) jTable1.getValueAt (row, 3);

        // Find the corresponding Item object in the itemList based on itemCode
        for (Order orderLoad : orderList) {
            if (orderLoad.getOrderID().equals(orderID)) {
                // Update the Item object with the edited values
                orderLoad.setAddress(address);
                orderLoad.setName(name);
                orderLoad.setPhoneNum(phoneNum);
                break;
            }
        }
    }
    Order.saveToFile(orderList);
    }
    
    public static Order getOrderByID(String orderID, ArrayList<Order> orderList) {
        for (Order orderLoad : orderList) {
            if (orderLoad.getOrderID().equals(orderID)) {
                // Update the Item object with the edited values
                return orderLoad;
            }
        }
        return null;
    }
    
    
     public String toString() {
        return "order{" +
                "order ID='" + orderID + '\'' +
                ", Address='" + address + '\'' +
                ", Customer Name=" + name +
                ", Phone Number=" + phoneNum +
                '}';
    }
    
     
     
}