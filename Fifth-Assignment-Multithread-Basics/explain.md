1-
* Thread was interrupted!
* Thread will be finished here!!!

At first we start the thread and the run function will be running and while it is sleeping we interrupt the thread and we will get an exception.
2-
* Runnig in : main

In this code the run() method directly on a Runnable object in Java will not start a new thread. Instead, the run() method will execute in the current thread, just like any other method call. This means that the code within the run() method will run sequentially in the calling thread, and no multithreading behavior will occur.
The run() method is called directly, so the message will be printed from the main thread.


3-
* Running in: Thread-0
  Back to: main


The join method allows one thread to wait for the completion of another. If t is a Thread object whose thread is currently executing, t. join(); causes the current thread to pause execution until t 's thread terminates.
