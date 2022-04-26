package by.home.port;

import java.util.concurrent.atomic.AtomicBoolean;

public class Dock {

    private int id;

    public Dock(int id) {
        this.id = id;
    }

    public void process(Ship ship) {
        if (ship.isLoaded() == true ){
            unloadProcess(ship);
        }else {
            loadProcess(ship);
        }

    }

    private void  unloadProcess(Ship ship){
        System.out.println(ship.toString() + " has been unloading in the dock: " + id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ship.setLoaded(false);
    }

    private void  loadProcess(Ship ship){
        System.out.println(ship.toString() + " has been loading in the dock: " + id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ship.setLoaded(true);
    }
}
