package Class;

import Class.GetTask;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class CompletedOrder {
    
     private String orderID; 
    private String address;
    private String name;
    private String phoneNum;
    private static ArrayList<GetTask> orderList;
    private static DefaultTableModel tableModel;

    public CompletedOrder(String orderID, String address, String name, String phoneNum) {
        this.orderID = orderID;
        this.address = address;
        this.name = name;
        this.phoneNum = phoneNum;
    }
    public static void main(String[] args) {
        // Load orders from order.txt
        ArrayList<Order> orders = loadOrders("order.txt");

        // Identify orders with "Done Delivery" status
        ArrayList<Order> completedOrders = new ArrayList<>();
        ArrayList<Order> remainingOrders = new ArrayList<>();

        for (Order order : orders) {
            if ("Done Delivery".equals(order.getStatus())) {
                completedOrders.add(order);
            } else {
                remainingOrders.add(order);
            }
        }

        // Save remaining orders back to order.txt
        saveToFile(remainingOrders, "order.txt");

        // Save completed orders to completedOrder.txt
        saveToFile(completedOrders, "completedOrder.txt");
    }

    private static ArrayList<Order> loadOrders(String filename) {
        ArrayList<Order> orders = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] orderData = line.split(", ");
                Order order = new Order(orderData[0], orderData[1], orderData[2], orderData[3], orderData[4]);
                orders.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return orders;
    }

    private static void saveToFile(ArrayList<Order> orders, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Order order : orders) {
                String line = order.getOrderID() + ", " + order.getAddress() + ", " +
                        order.getName() + ", " + order.getPhoneNum() + ", " + order.getStatus();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}