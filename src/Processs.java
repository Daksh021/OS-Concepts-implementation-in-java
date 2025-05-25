public class Processs {

    int id;
    int arrival_time;
    int completion_time;
    int burst_time;

    int turn_around_time;

    int waiting_time;

    int priority;

    Processs(int id,int arrival_time,int burst_time, int priority){
        this.id = id;
        this.arrival_time = arrival_time;
        this.burst_time = burst_time;
        this.priority = priority;
    }


}
