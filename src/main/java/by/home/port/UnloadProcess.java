package by.home.port;

public class UnloadProcess implements Process{
    @Override
    public void activate(Ship ship, Dock dock) {
        System.out.println(ship.toString() + " has been unloading in the dock: " + dock.getId());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ship.setLoaded(false);
    }
}
