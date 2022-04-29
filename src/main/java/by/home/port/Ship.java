package by.home.port;

public class Ship implements Runnable{

    private int id;
    private boolean loaded;

    public Ship(int id, boolean loaded) {
        this.id = id;
        this.loaded = loaded;
    }

    public Ship(){}

    public int getId() {
        return id;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    @Override
    public void run() {
        Port port = Port.getInstance();
        port.process(this);

    }

    @Override
    public String toString() {
        return "Ship " +
                + id +
                ", loaded=" + loaded;
    }

}
