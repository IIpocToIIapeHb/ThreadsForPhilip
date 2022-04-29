package by.home.port;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final String INPUT_FILE = "data\\input.json";

    public static void main(String[] args) throws IOException {

        Port port = Port.getInstance();
        List<Dock> docks = new ArrayList<>();
        for (int i = 0; i < port.getDocksNumber(); i++) {
            docks.add(new Dock(++i));
        }
        port.setDocks(docks);

        ObjectMapper mapper = new ObjectMapper();
        Ships shipsWrapper = mapper.readValue(new File(INPUT_FILE),Ships.class);
        List<Ship> ships = shipsWrapper.getShips();

        ExecutorService executor= Executors.newFixedThreadPool(ships.size());
        ships.stream().forEach(ship -> executor.submit(ship));
        executor.shutdown();
    }
}
