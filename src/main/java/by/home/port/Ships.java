package by.home.port;

import java.util.List;

public class Ships {
    private List<Ship> ships;

    public Ships() {
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    @Override
    public String toString() {
        return "Ships{" +
                "ships=" + ships +
                '}';
    }
}
