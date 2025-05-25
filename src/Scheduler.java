import java.util.*;

public class Scheduler {

    public void fcfs(List<Processs> processes) {
        // Sort by arrival time
        processes.sort(Comparator.comparingInt(p -> p.arrival_time));
        int time = 0;

        for (Processs p : processes) {
            // If the CPU is idle, move time forward
            time = Math.max(time, p.arrival_time);

            // Process starts execution
            p.completion_time = time + p.burst_time;
            p.turn_around_time = p.completion_time - p.arrival_time;
            p.waiting_time = p.turn_around_time - p.burst_time;

            // Update the current time
            time = p.completion_time;

            System.out.println("Process info : " + p.id + " \n"
                    + "Process Completion time : " + p.completion_time + "\n"
                    + "Process turn around time : " + p.turn_around_time + "\n"
                    + "Process waiting time :  " + p.waiting_time + "\n"
            );
        }

        System.out.println("Total time taken to finish the task = " + time);
    }


    public void sjf(List<Processs> processes) {
        int time = 0;
        List<Processs> completed = new ArrayList<>();

        while (completed.size() < processes.size()) {
            // Get all processes that have arrived and are not completed
            List<Processs> available = new ArrayList<>();
            for (Processs p : processes) {
                if (!completed.contains(p) && p.arrival_time <= time) {
                    available.add(p);
                }
            }

            // If no process has arrived, advance time
            if (available.isEmpty()) {
                time++;
                continue;
            }

            // Select process with the shortest burst time among available
            available.sort(Comparator.comparingInt(p -> p.burst_time));
            Processs current = available.get(0);

            current.completion_time = time + current.burst_time;
            current.turn_around_time = current.completion_time - current.arrival_time;
            current.waiting_time = current.turn_around_time - current.burst_time;
            time = current.completion_time;

            completed.add(current);

            System.out.println("Process info : " + current.id + " \n"
                    + "Process Completion time : " + current.completion_time + "\n"
                    + "Process turn around time : " + current.turn_around_time + "\n"
                    + "Process waiting time :  " + current.waiting_time + "\n");
        }

        System.out.println("Total time taken to finish the task = " + time);
    }


    public void priority_scheduling(List<Processs> processes) {
        int time = 0;
        List<Processs> completed = new ArrayList<>();

        while (completed.size() < processes.size()) {
            // Get all processes that have arrived and are not completed
            List<Processs> available = new ArrayList<>();
            for (Processs p : processes) {
                if (!completed.contains(p) && p.arrival_time <= time) {
                    available.add(p);
                }
            }

            // If no process has arrived, advance time
            if (available.isEmpty()) {
                time++;
                continue;
            }

            // Select process with the highest priority among available (lower priority value = higher priority)
            available.sort(Comparator.comparingInt(p -> p.priority));
            Processs current = available.get(0);

            current.completion_time = time + current.burst_time;
            current.turn_around_time = current.completion_time - current.arrival_time;
            current.waiting_time = current.turn_around_time - current.burst_time;
            time = current.completion_time;

            completed.add(current);

            System.out.println("Process info : " + current.id + " \n"
                    + "Process Completion time : " + current.completion_time + "\n"
                    + "Process turn around time : " + current.turn_around_time + "\n"
                    + "Process waiting time :  " + current.waiting_time + "\n"
                    + "Process Priority :" + current.priority + "\n");
        }

        System.out.println("Total time taken to finish the task = " + time);
    }

    public void roundrobin(List<Processs> processes) {
        int time_quantum = 2;
        int time = 0;
        Queue<Processs> readyQueue = new LinkedList<>();
        Map<Integer, Integer> remaining_bt = new HashMap<>();
        Set<Integer> arrived = new HashSet<>();

        // Sort by arrival time to make checking easier
        processes.sort(Comparator.comparingInt(p -> p.arrival_time));

        for (Processs p : processes) {
            remaining_bt.put(p.id, p.burst_time);
        }

        int index = 0; // Tracks next process to arrive

        while (!readyQueue.isEmpty() || index < processes.size()) {

            // Add newly arrived processes to the queue
            while (index < processes.size() && processes.get(index).arrival_time <= time) {
                readyQueue.add(processes.get(index));
                arrived.add(processes.get(index).id);
                index++;
            }

            if (readyQueue.isEmpty()) {
                time++; // CPU idle
                continue;
            }

            Processs current = readyQueue.poll();
            int rem_bt = remaining_bt.get(current.id);

            if (rem_bt > time_quantum) {
                time += time_quantum;
                remaining_bt.put(current.id, rem_bt - time_quantum);
            } else {
                time += rem_bt;
                remaining_bt.put(current.id, 0);
                current.completion_time = time;
                current.turn_around_time = current.completion_time - current.arrival_time;
                current.waiting_time = current.turn_around_time - current.burst_time;
            }

            // After execution slice, check again for newly arrived processes
            while (index < processes.size() && processes.get(index).arrival_time <= time) {
                if (!arrived.contains(processes.get(index).id)) {
                    readyQueue.add(processes.get(index));
                    arrived.add(processes.get(index).id);
                }
                index++;
            }

            // If not finished yet, add current process back to queue
            if (remaining_bt.get(current.id) > 0) {
                readyQueue.add(current);
            }
        }

        // Output result
        for (Processs p : processes) {
            System.out.println("Process info : " + p.id + " \n"
                    + "Completion time : " + p.completion_time + "\n"
                    + "Turn around time : " + p.turn_around_time + "\n"
                    + "Waiting time : " + p.waiting_time + "\n"
                    + "Priority : " + p.priority + "\n");
        }
    }
}
