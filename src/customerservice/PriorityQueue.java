/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customerservice;

import java.util.concurrent.TimeUnit;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Brainchild Software
 */
public class PriorityQueue {
    private static final int d= 2;
    private PriorityCustomer[] customers;   //customers Array that hold customer info
    private int heapSize;   // size of heap
     
    /**
     * This will initialize our customers with default size. 
     */
    public PriorityQueue(){
        heapSize = 0;
        this.customers = new PriorityCustomer[50];
    }
     
    /**
     *  This will check if the heap is empty or not
     *  Complexity: O(1)
     * @return 
     */
    public boolean isEmpty(){
        return heapSize==0;
    }
     
    /**
     *  This will check if the heap is full or not
     *  Complexity: O(1)
     * @return 
     */
    public boolean isFull(){
        return heapSize == this.customers.length;
    }
     
    /**
     *  This will return the parent index
     *  Complexity: O(1)
     * @return 
     */
    private int parent(int i){
        return (i-1)/d;
    }
    
    /**
     *  This will return the left and right Child index
     *  Complexity: O(1)
     * @return 
     */
    private int kthChild(int i,int k){
        return (d*i)+k;
    }
     
    /**
     *  This will insert new Customer in to heap
     *  Complexity: O(log N)
     *  As worst case scenario, we need to traverse till the root
     * @param cus
     */
    public void insert(PriorityCustomer cus){
       
        int index = heapSize++;
        this.customers[index] = cus;
        heapifyUp(heapSize-1);
    }
     
    /**
     *  This will delete Customer at index x
     *  Complexity: O(log N)
     * 
     * @param x
     */
    public void poll(int x){
        PriorityCustomer pCus = this.customers[x];
        this.customers[x] = this.customers[heapSize -1];
        heapSize--;
        heapifyDown(x);
    }
 
    /**
     *  This method used to maintain the heap property while inserting an Customer.
     *  
     */
    private void heapifyUp(int i) {
        PriorityCustomer temp = this.customers[i];
        while(i>0 && temp.getPriority() > this.customers[parent(i)].getPriority()){
            this.customers[i] = this.customers[parent(i)];
            i = parent(i);
        }
        this.customers[i] = temp;
    }
     
    /**
     *  This method used to maintain the heap property while deleting an Customer.
     *  
     */
    private void heapifyDown(int i){
        int child;
        PriorityCustomer temp = this.customers[i];
        while(kthChild(i, 1) < heapSize){
            child = maxChild(i);
            if(temp.getPriority() < this.customers[child].getPriority()){ this.customers[i] = this.customers[child]; }else break; i = child; } this.customers[i] = temp; } 
    
    
    private int maxChild(int i) { 
        int leftChild = kthChild(i, 1); 
        int rightChild = kthChild(i, 2); 
        return this.customers[leftChild].getPriority()>this.customers[rightChild].getPriority()?leftChild:rightChild;
    } 
    
     
    /**
     *  This method used to print all Customer priority and service Time of the heap
     *  
     */
    public void printHeap() {
        for (int i = 0; i < heapSize; i++)
            System.out.print(i +" - "+this.customers[i].getPriority()+" "+ this.customers[i].getServiceTime() +"\n ");
        System.out.println();
    }
    
    
    /**
     *  This method returns the max priority Customer or the Root Customer of the heap.
     *  complexity: O(1)
     * @return 
     */
     public PriorityCustomer findMax(){
         return this.customers[0];
    }
    
     /**
     *  This method service the customer and add new customer with 25% of probability.
     * if the customer service is done then remove from customers heap
     * @throws java.lang.InterruptedException
     */
     
    public void serviceCustomer() throws InterruptedException{
        PriorityCustomer pCustomer = this.findMax();    // get max PriorityCustomer 
        pCustomer.decServiceTime();     // decrice Priority Customer service time
        
        if(this.prob25() == 1){         // check probability of adding customer
            this.insert(new PriorityCustomer());    // add a new customer
            System.out.println("New customer added!  Queue length is now "+ this.heapSize); 
        }
        if(pCustomer.getServiceTime() == 0){    // check is service time is done
            this.poll(0);   // delete the customer after service
            System.out.println("Customer serviced and removed from the queue.  Quest length is now  "+ this.heapSize);
        }
        TimeUnit.SECONDS.sleep(5); //sleerp this fuction for 60 second
        System.out.println("---------------------------------------------------");
    }
    
    // Random Function to that returns 0 or 1 with 
    // equal probability 
    private int rand50(){  
        return (int) (10 * Math.random()) & 1;
    } 

    // Random Function to that returns 1 with 25% 
    private int prob25(){ 
        return this.rand50() & this.rand50(); 
    }
}
