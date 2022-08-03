package frontend;

public class Utils {

    static void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ignored) {
            // should not happen...
            throw new RuntimeException(ignored);
        }
    }

}
