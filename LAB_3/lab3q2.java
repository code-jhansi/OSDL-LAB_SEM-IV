/*Design and implement a Java application to simulate an Online Order 
Processing System where multiple customer orders are processed simultaneously 
using multithreading.
1.In an e-commerce platform, several operations such as order validation, payment 
processing, and order shipment must be handled concurrently for different customers. 
To improve system performance and responsiveness, each order processing task should 
be executed in a separate thread.
2.Create individual threads for handling different customer orders or different stages of 
order processing. Each thread should simulate processing by displaying status messages 
and using the sleep() method to represent time-consuming operations.
3.The main program should start multiple threads at the same time and demonstrate 
concurrent execution of order-related tasks.*/
class OrderTask implements Runnable{
    private String taskName;
    public OrderTask(String taskName){
        this.taskName=taskName;
    }
    public void run(){
        try{
            System.out.println(taskName + " started...");
            //simulating process steps
            for(int i=1;i<=3;i++){
                System.out.println(taskName + " processing step " + i);
                Thread.sleep(5000);//simulate time delay
            }
            System.out.println(taskName + " completed.");
            }catch(InterruptedException e){
                System.out.println(taskName + " interrupted.");
            }
        }
}


public class lab3q2 {
    public static void main(String[] args){
        //Creating threads for different order-related tasks
        Thread validation = new Thread(new OrderTask("Order Validation"));
        Thread payment = new Thread(new OrderTask("Payment Processing"));
        Thread shipment = new Thread(new OrderTask("order Shipment"));

        //start all threads concurrently
        validation.start();
        payment.start();
        shipment.start();
        System.out.println("All order tasks are being processed concurrently...");
    }
}
