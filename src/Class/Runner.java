/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.util.ArrayList;

/**
 *
 * @author theli
 */
public class Runner{
    private String runnerID;
    public Runner(String runnerID){
        this.runnerID = runnerID;
    }
    
    public static String getNextRunner(ArrayList<Order> orderList) {
        String curRunner = "R0";
        boolean available = true;
        for(int i=1; i<7; i++){
            for (Order orderLoad : orderList) {
                available = true;
                if (orderLoad.getRunnerID().equals(curRunner + String.valueOf(i)) && (orderLoad.getStatus().equals("out for delivery") || orderLoad.getStatus().equals("pending") || orderLoad.getStatus().equals("waiting_restaurant"))) {
                    available = false;
                    break;
                }
            }
            if (available) {
                Noti.sendRunnerNoti(curRunner + String.valueOf(i));
                return curRunner + String.valueOf(i);
            }
        }
        return "NO RUNNER LEFT";
    }
}
