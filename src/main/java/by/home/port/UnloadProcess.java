package by.home.port;

public class UnloadProcess implements Process{
    @Override
    public void activate(Ship ship, Dock dock) {
        System.out.println(ship.toString() + " has been unloading in the dock: " + dock.getId());
        Sleep.sleep(1000);
        ship.setLoaded(false);
    }
}
