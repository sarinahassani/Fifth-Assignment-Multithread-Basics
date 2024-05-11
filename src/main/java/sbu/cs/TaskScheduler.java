package sbu.cs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskScheduler {
    public static class Task implements Runnable {
        /*
            ------------------------- You don't need to modify this part of the code -------------------------
         */
        String taskName;
        int    processingTime;

        public Task(String taskName, int processingTime) {
            this.taskName       = taskName;
            this.processingTime = processingTime;
        }
        /*
            ------------------------- You don't need to modify this part of the code -------------------------
         */

        @Override
        public void run() {
            /*
            TODO
                Simulate utilizing CPU by sleeping the thread for the specified processingTime
             */
            try {
                System.out.println(taskName + " is running");
                Thread.sleep(processingTime);
                System.out.println(taskName + " completed");
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ArrayList<String> doTasks(ArrayList<Task> tasks) {
        ArrayList<String> finishedTasks = new ArrayList<>();
        ArrayList<Thread> threads       = new ArrayList<>();

        tasks.sort((t1, t2) -> t2.processingTime - t1.processingTime);

        for (Task task : tasks) {
            finishedTasks.add(task.taskName);
            Thread thread = new Thread(task);
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
            try {
                thread.join();
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        return finishedTasks;
    }

    public static void main(String[] args) {
        ArrayList <Task> tasks = new ArrayList <> (); //create an array list of tasks

        //add tasks to the array list
        tasks.add (new Task ("First Task", 1000));
        tasks.add (new Task ("Second Task", 2000));
        tasks.add (new Task ("Third Task", 3000));
        tasks.add (new Task ("Fourth Task", 4000));
        tasks.add (new Task ("Fifth Task", 5000));

        ArrayList <String> finishedTasks = doTasks (tasks); //execute tasks and add their names to the array list
        System.out.println ("Finished tasks: " + finishedTasks); //print all the executed tasks names
    }
}
