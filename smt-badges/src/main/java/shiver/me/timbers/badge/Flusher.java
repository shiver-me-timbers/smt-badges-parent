package shiver.me.timbers.badge;

import java.io.Flushable;
import java.io.IOException;

/**
 * @author Karl Bennett
 */
public class Flusher {

    public void flush(Flushable flushable) throws IOException {
        flushable.flush();
    }
}
