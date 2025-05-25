import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Semaphores {

    static Semaphore empty = new Semaphore(5);
    static Semaphore mutex = new Semaphore(1);
    static Semaphore full = new Semaphore(0);

    static LinkedList<Integer> buffer = new LinkedList<>();

    static class Producer extends Thread{

        public void run(){
            for(int i = 0; i <= 5; i++) {
                try {


                    empty.acquire();
                    mutex.acquire();
                    buffer.add(i);
                    System.out.println("Produced " + i);
                    mutex.release();
                    full.release();
                    Thread.sleep(500);
                }

                catch (InterruptedException e){
                    System.out.println(e);
                }

            }
        }
    }

    public static class Consumer extends Thread{

       public void run(){

           for(int i = 0; i <= 5; i++){

               try {

                   full.acquire();
                   mutex.acquire();
                   int item = buffer.remove();
                   System.out.println("Consumed " +item);
                   mutex.release();
                   empty.release();

                   Thread.sleep(1000);
               }

               catch (InterruptedException e){
                   System.out.println(e);
               }

           }

        }

    }


    public static void main(String[] args) {
        new Producer().start();
        new Consumer().start();
    }
}
