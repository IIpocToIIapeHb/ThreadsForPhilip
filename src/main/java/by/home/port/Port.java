package by.home.port;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    List<Dock> docks;
    private Lock lock = new ReentrantLock();
    private Semaphore semaphore = new Semaphore(3);
    private static volatile Port instance;

    public List<Dock> getDocks() {
        return docks;
    }

    public void setDocks(List<Dock> docks) {
        this.docks = docks;
    }

    public static Port getInstance() {
        Port localInstance = instance;
        if (localInstance == null) {
            synchronized (Port.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Port();
                }
            }
        }
        return localInstance;
    }

    void process(Ship ship){
       // System.out.println( ship.toString() + " waiting....");
        try {
            semaphore.acquire();
            System.out.println(ship.toString() + " entered to the port");
          //  lock.lock();
            Thread.sleep(100);
            Dock dock = docks.remove(0);
            dock.process(ship);
            System.out.println(ship.toString() + " exit the port");
           docks.add(dock);
           semaphore.release();
        }  catch (InterruptedException e) {
            e.printStackTrace();
       } finally {
//            System.out.println(ship.toString() + " exit the dock");
//            docks.add(dock);
//            semaphore.release();


//            lock.unlock();
        }
    }
}
