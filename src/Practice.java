import java.io.IOException;
import java.util.*;

public class Practice {

     class Proces{

        int id;
        int arrival_time;
        int completion_time;
        int burst_time;
        int turn_around_time;
        int waiting_time;
        int priority_time;

        public Proces(int id,int arrival_time,int burst_time,int priority_time){
            this.id = id;
            this.arrival_time = arrival_time;
            this.burst_time = burst_time;
            this.priority_time = priority_time;
        }

    }


    public static void fcfs(List<Proces> process){

         process.sort(Comparator.comparingInt(p1->p1.arrival_time));
         int time = 0;

         for(Proces proces : process){

             time = Math.max(time,proces.arrival_time);
             proces.completion_time = time + proces.burst_time;
             proces.turn_around_time = proces.completion_time - proces.arrival_time;
             proces.waiting_time = proces.turn_around_time - proces.burst_time;

             time = proces.completion_time;

         }

        System.out.println(time);




    }

    public static void sjf(List<Proces> proces){

         int time = 0;
         List<Proces> completed = new ArrayList<>();

         while(completed.size() < proces.size()){
             List<Proces> available = new ArrayList<>();
             for(Proces p : proces){

                 if(!completed.contains(p) && p.arrival_time <= time){
                     available.add(p);
                 }
             }

             if(available.isEmpty()){
                 time++;
                 continue;
             }

             available.sort(Comparator.comparingInt(p->p.burst_time));
             Proces current = available.get(0);

             current.completion_time = time + current.burst_time;
             current.turn_around_time = current.completion_time - current.arrival_time;
             current.waiting_time = current.turn_around_time - current.burst_time;

             completed.add(current);


         }

        System.out.println("Total time taken to finish the task = " + time);


    }


    public static void priority(List<Proces> proces){

         int time = 0;
         List<Proces> completed = new ArrayList<>();

         while(completed.size() < proces.size()){

             List<Proces> available = new ArrayList<>();
             for(Proces p : proces){

                 if(!completed.contains(p) && p.arrival_time <= time){
                     available.add(p);
                 }

             }

             if(completed.isEmpty()){
                 time++;
                 continue;
             }

             available.sort(Comparator.comparingInt(p->p.priority_time));
             Proces current = available.get(0);

             current.completion_time = time + current.burst_time;
             current.turn_around_time = current.completion_time - current.arrival_time;
             current.waiting_time = current.turn_around_time - current.burst_time;

             completed.add(current);





         }

        System.out.println(time);


    }


    public static void round_robin(List<Proces> process){
         int time = 0;
         int time_quantum = 2;
         Queue<Proces> readyQ = new LinkedList<>();
         int index = 0;
         HashMap<Integer,Integer> map = new HashMap<>();
         HashSet<Integer> arrived = new HashSet<>();

         process.sort(Comparator.comparingInt(p->p.arrival_time));

         for(Proces p:process){
             map.put(p.id,p.burst_time);
         }

         while(!readyQ.isEmpty() || index < process.size()){

             while(index < process.size() && process.get(index).arrival_time <= time){

                 readyQ.add(process.get(index));
                 arrived.add(process.get(index).id);
                 index++;
             }

             if(readyQ.isEmpty()){
                 time++;
                 continue;
             }

             Proces current = readyQ.poll();
             int remaining_burst_time = map.get(current.id);

             if(remaining_burst_time > time_quantum){
                 time += time_quantum;
                 map.put(current.id,remaining_burst_time - time_quantum);
             }

             else{

                 time += current.burst_time;
                 map.put(current.id,0);
                 current.completion_time = time;
                 current.turn_around_time = current.completion_time - current.arrival_time;
                 current.waiting_time = current.turn_around_time - current.burst_time;
             }


             while(index < process.size() && process.get(index).arrival_time <= time){

                 if(!arrived.contains(process.get(index).id) && process.get(index).arrival_time <= time){
                     readyQ.add(process.get(index));
                     arrived.add(process.get(index).id);


                 }

                 index++;
             }


             if(map.get(current.id) > 0){
                 readyQ.add(current);
             }

         }


        for (Proces p : process) {
            System.out.println("Process info : " + p.id + " \n"
                    + "Completion time : " + p.completion_time + "\n"
                    + "Turn around time : " + p.turn_around_time + "\n"
                    + "Waiting time : " + p.waiting_time + "\n"
                    + "Priority : " + p.priority_time + "\n");
        }




    }




    public static void main(String[] args){

        List<Processs> processes = new ArrayList<>();


        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of process");
        int n = sc.nextInt();


        for(int i = 0; i < n; i++){


            System.out.println("Enter the process id ");
            int id = sc.nextInt();

            System.out.println("Enter the arrival time ");
            int arrival_time = sc.nextInt();

            System.out.println("Enter the burst time :");
            int burst_time = sc.nextInt();

            System.out.println("Enter the the priority");
            int priority = sc.nextInt();

            processes.add(new Processs(id,arrival_time,burst_time,priority));

        }

        Scheduler scheduler = new Scheduler();
        scheduler.roundrobin(processes);


    }

}