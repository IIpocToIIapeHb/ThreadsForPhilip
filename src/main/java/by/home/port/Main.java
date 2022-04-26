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

        List<Dock> docks = new ArrayList<>();
        docks.add(new Dock(1));
        docks.add(new Dock(2));
        docks.add(new Dock(3));
        Port port = Port.getInstance();
        port.setDocks(docks);
        ObjectMapper mapper = new ObjectMapper();
        Ships shipsWrapper = mapper.readValue(new File(INPUT_FILE),Ships.class);

        List<Ship> ships = shipsWrapper.getShips();
       // ships.stream().forEach(System.out::println);
        ExecutorService executor= Executors.newFixedThreadPool(ships.size());
        ships.stream().forEach(ship -> executor.submit(ship));

        executor.shutdown();
    }
}
