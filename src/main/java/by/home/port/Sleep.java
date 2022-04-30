package by.home.port;

import org.apache.log4j.Logger;

public class Sleep {
    private final static Logger LOGGER = Logger.getLogger(Sleep.class);
    static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }
}
