package sbu.cs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskScheduler
{
    public static class Task implements Runnable
    {
        /*
            ------------------------- You don't need to modify this part of the code -------------------------
         */
        String taskName;
        int processingTime;

        public Task(String taskName, int processingTime) {
            this.taskName = taskName;
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
        }
    }

    public static ArrayList<String> doTasks(ArrayList<Task> tasks)
    {
        ArrayList<String> finishedTasks = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i=0; i<tasks.size(); i++) {
            Thread thread = new Thread(tasks.get(i));
            threads.set(i, thread);
        }
        for (int i=0; i<threads.size(); i++) {
            threads.get(i).start();
        }
        for (int i=0; i<threads.size(); i++) {
            try {
                threads.get(i).join();
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        /*List times = new ArrayList<>();
        for (Task task : tasks) {
            times.add(task.processingTime);
        }*/


        for (int i=0; i<tasks.size(); i++) {
            for (int j=0; j<tasks.size(); j++) {
                if (tasks.get(i).processingTime > tasks.get(j).processingTime) {
                    Task temp = tasks.get(i);
                    //countries.get(i) = countries.get(j);
                    tasks.set(i, tasks.get(j));
                    tasks.set(j, temp);
                }
            }
        }
        for (int i=0; i<tasks.size(); i++) {
            finishedTasks.set(i, tasks.get(i).taskName);
        }
        /*
        TODO
            Create a thread for each given task, And then start them based on which task has the highest priority
            (highest priority belongs to the tasks that take more time to be completed).
            You have to wait for each task to get done and then start the next task.
            Don't forget to add each task's name to the finishedTasks after it's completely finished.
         */

        return finishedTasks;
    }

    public static void main(String[] args) {
        // Test your code here
    }
}
