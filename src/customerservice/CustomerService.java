/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customerservice;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author Brainchild Software
 */
public class CustomerService {
    
   
    private static final int PCusIndex = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        
        //init PriorityQueue
        PriorityQueue PQueue = new PriorityQueue();
        
        // Add some PriorityCustomer
        PQueue.insert(new PriorityCustomer());
        PQueue.insert(new PriorityCustomer());
        PQueue.insert(new PriorityCustomer());
        PQueue.insert(new PriorityCustomer());
        
        // Loop it for 60 times coz 60 minutes of activity at this store 
        //and Each iteration is one min 
        for(int i=0; i <60; i++){
            PQueue.serviceCustomer();   // call service customer
        }
        
    }
    
}
