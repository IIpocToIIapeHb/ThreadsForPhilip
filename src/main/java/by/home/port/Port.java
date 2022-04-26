package by.home.port;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    List<Dock> docks;
    private Lock lock = new ReentrantLock();
    Semaphore semaphore = new Semaphore(docks.size());

    private static volatile Port instance;

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
        try {
            semaphore.acquire();
            lock.lock();
           // Dock dock = docks.get();
           // dock.process(ship);
        }  catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            lock.unlock();
        }
    }
}
