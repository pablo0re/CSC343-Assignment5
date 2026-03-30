import java.util.*;

public class SchedulingHomework {

    static class Process {
        String id;
        int burst;
        int priority;
        int arrival;
        int waiting;
        int turnaround;

        Process(String id, int burst, int priority, int arrival) {
            this.id = id;
            this.burst = burst;
            this.priority = priority;
            this.arrival = arrival;
        }
    }

    static List<Process> copyList(List<Process> original) {
        List<Process> copy = new ArrayList<>();
        for (Process p : original) {
            copy.add(new Process(p.id, p.burst, p.priority, p.arrival));
        }
        return copy;
    }

    static void runFCFS(List<Process> processes) {
        int currentTime = 0;
        double totalWaiting = 0;

        for (Process p : processes) {
            p.waiting = currentTime - p.arrival;
            p.turnaround = p.waiting + p.burst;
            currentTime += p.burst;
            totalWaiting += p.waiting;
        }

        printResults("FCFS", processes, totalWaiting / processes.size());
    }

    static void runSJF(List<Process> processes) {
        processes.sort(Comparator.comparingInt((Process p) -> p.burst)
                .thenComparing(p -> p.id));

        int currentTime = 0;
        double totalWaiting = 0;

        for (Process p : processes) {
            p.waiting = currentTime - p.arrival;
            p.turnaround = p.waiting + p.burst;
            currentTime += p.burst;
            totalWaiting += p.waiting;
        }

        printResults("SJF", processes, totalWaiting / processes.size());
    }

    static void printResults(String title, List<Process> processes, double avgWaiting) {
        System.out.println("--------------- " + title + " ---------------");
        System.out.println("Process ID | Waiting Time | Turnaround Time");

        for (Process p : processes) {
            System.out.printf("%-10s | %-12d | %-15d%n", p.id, p.waiting, p.turnaround);
        }

        System.out.printf("Average Waiting Time = %.2f ms%n%n", avgWaiting);
    }

    public static void main(String[] args) {
        List<Process> base = new ArrayList<>();

        base.add(new Process("P1", 2, 2, 0));
        base.add(new Process("P2", 1, 1, 0));
        base.add(new Process("P3", 8, 4, 0));
        base.add(new Process("P4", 4, 2, 0));
        base.add(new Process("P5", 5, 3, 0));

        runFCFS(copyList(base));
        runSJF(copyList(base));
    }
}
