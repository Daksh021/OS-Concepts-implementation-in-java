import java.util.*;

public class Main {

    public static void main(String[] args) {

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
        scheduler.fcfs(processes);

        System.out.println();

        scheduler.sjf(processes);

        System.out.println();

        scheduler.priority_scheduling(processes);

        System.out.println();
        scheduler.roundrobin(processes);




    }
}