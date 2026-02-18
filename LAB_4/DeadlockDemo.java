class DeadlockDemo{
    //Two shared resources (Objects used for locking)
    static final Object resource1=new Object();
    static final Object resource2=new Object();
    //Thread A class
    static class ThreadA extends Thread{
        public void run(){ //Code executed when thread starts
            synchronized(resource1){ //Thread A locks Resource 1
                System.out.println("Thread A locked Resource 1");
                try{
                    Thread.sleep(5000); //PAuse so Thread B can lock Resource2
                }catch(InterruptedException e){
                    System.out.println("Thread A interrupted");
                }
                System.out.println("Thread A waiting for Resource 2");
                synchronized(resource2){  //Try to lock Resource 2
                    //This may never execute due to deadlock
                    System.out.println("Thread A locked Resource 2");
                }
            }
        }
    }
    //Thread B class
    static class ThreadB extends Thread{
        public void run(){ //Code executed when thread starts
            synchronized(resource2){ //Thread B locks Resource 2 first
                System.out.println("Thread B locked Resource 2");
                try{
                    Thread.sleep(5000); //Pause so Thread A keeps resource1
                }catch(InterruptedException e){
                    System.out.println("Thread B interrupted");
                }
                System.out.println("Thread B waiting for Resource 1");
                synchronized(resource1){ //Try to lock Resource 1
                    //This may nevr happen due to deadlock
                    System.out.println("Thread B locked Resource 1");
                }
            }
        }
    }
    public static void main(String[] args){
        Thread t1=new ThreadA();//Create Thread A Object
        Thread t2=new ThreadB();//Create Thread B Object
        t1.start();//start Thread A execution
        t2.start();//start Thread B execution
    }
}



