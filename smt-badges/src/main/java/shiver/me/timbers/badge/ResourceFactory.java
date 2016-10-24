package shiver.me.timbers.badge;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Karl Bennett
 */
public class ResourceFactory {

    public InputStream find(String resource) throws IOException {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
    }
}
