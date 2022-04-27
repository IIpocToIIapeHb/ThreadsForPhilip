package by.home.port;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private final static int DOCKS_NUMBER = 3;
    List<Dock> docks;
    private Lock lock = new ReentrantLock();
    private Semaphore semaphore = new Semaphore(DOCKS_NUMBER);
    private static volatile Port port;

    public void setDocks(List<Dock> docks) {
        this.docks = docks;
    }

    public static Port getPort() {
        Port localInstance = port;
        if (localInstance == null) {
            synchronized (Port.class) {
                localInstance = port;
                if (localInstance == null) {
                    port = localInstance = new Port();
                }
            }
        }
        return localInstance;
    }

    void process(Ship ship) {

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            lock.unlock();
        }
    }
}
