package by.home.port;

import java.util.concurrent.atomic.AtomicBoolean;

public class Dock {

    private int id;
    private Process process;

    public Dock(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void process(Ship ship) {
        if (ship.isLoaded()){
            process = new UnloadProcess();
        }else {
            process = new LoadProcess();
        }
        process.activate(ship, this);
    }
}
