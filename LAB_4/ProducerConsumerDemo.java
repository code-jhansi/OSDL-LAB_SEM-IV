class Buffer{
    private int data;
    private boolean available=false;
    synchronized void produce(int value){
        while(available){
            try{
                wait();//wait if buffer is full
            }catch(InterruptedException e){
                System.out.println("Producer interrupted");
            }
        }
        data=value;
        available=true;
        System.out.println("Produced:" +data);
        notify();//notify consumer
    }
    synchronized int consume(){
        while(!available){
         try{
            wait();//wait if buffer is empty
        }catch(InterruptedException e){
            System.out.println("Consumer interruptrd");
        }
    
    }
    available=false;
    System.out.println("Consumed:" + data);
    notify();//Notify producer
    return data;
    }

}
class Producer extends Thread{
    private Buffer buffer;
    Producer(Buffer buffer){
        this.buffer=buffer;
    }
    public void run(){
        for(int i=1;i<=3;i++){
            buffer.produce(i);
            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){
                System.out.println("Producer sleep Interrupted");
            }
        }
    }
}
class Consumer extends Thread{
    private Buffer buffer;
    Consumer(Buffer buffer){
        this.buffer=buffer;
    }
    public void run(){
        for(int i=1;i<=3;i++){
            buffer.consume();
            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){
                System.out.println("Consumer sleep interrpted");
            }
        }
    }
}
public class ProducerConsumerDemo {
    public static void main(String[] args){
        Buffer buffer=new Buffer();
        Producer producer=new Producer(buffer);
        Consumer consumer=new Consumer(buffer);
        producer.start();
        consumer.start();
    }    
}
