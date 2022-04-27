package by.home.port;

public class LoadProcess implements Process{
    @Override
    public void activate(Ship ship, Dock dock) {
        System.out.println(ship.toString() + " has been loading in the dock: " + dock.getId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ship.setLoaded(true);
    }
}
