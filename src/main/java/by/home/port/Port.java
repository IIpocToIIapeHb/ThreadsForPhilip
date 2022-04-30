package by.home.port;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {

    private final static int DOCKS_NUMBER = 3;
    private List<Dock> docks;
    private Lock lock = new ReentrantLock();
    private Semaphore semaphore = new Semaphore(DOCKS_NUMBER);
    private static volatile Port instance;
    private final static Logger LOGGER = Logger.getLogger(Port.class);

    public void setDocks(List<Dock> docks) {
        this.docks = docks;
    }

    public int getDocksNumber() {
        return DOCKS_NUMBER;
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


    public void process(Ship ship) {

        try {
            semaphore.acquire();
            System.out.println(ship.toString() + " entered to the port");
            Thread.sleep(100);

            lock.lock();
            Dock dock = docks.remove(0);
            lock.unlock();

            dock.process(ship);

            lock.lock();
            docks.add(dock);
            lock.unlock();

            System.out.println(ship.toString() + " exit the port");
            semaphore.release();

        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }
}
