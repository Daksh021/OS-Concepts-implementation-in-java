

class MyThread extends Thread{

    public void run(){

        System.out.println(getName() + "Started");

        try{
            Thread.sleep(1000);
        }

        catch (InterruptedException e){
            System.out.println("Thread is interuppted");
        }

        System.out.println(getName() + "finished");

    }
}






public class ThreadLifeCycle {

    public static void main(String[] args) {

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            System.out.println("Main Interuppted");
        }

        t2.start();

    }

}
