package Task4_OnlineExamination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Reads all console input on a single dedicated background thread and
 * makes it available through a blocking queue. This avoids the unsafe
 * pattern of interrupting a thread that is blocked inside Scanner/BufferedReader,
 * which can corrupt the reader's internal state.
 */
public class InputReader {

    private static final BlockingQueue<String> inputQueue = new LinkedBlockingQueue<>();
    private static boolean started = false;

    public static synchronized void start() {
        if (started) {
            return;
        }
        started = true;

        Thread readerThread = new Thread(() -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    inputQueue.put(line);
                }
            } catch (Exception e) {
                // Input stream closed; stop reading
            }
        });
        readerThread.setDaemon(true);
        readerThread.start();
    }

    /** Blocks until a line is available. */
    public static String readLine() {
        try {
            return inputQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "";
        }
    }

    /** Waits up to timeoutMillis for a line. Returns null if time runs out. */
    public static String readLineWithTimeout(long timeoutMillis) {
        try {
            return inputQueue.poll(timeoutMillis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }
}