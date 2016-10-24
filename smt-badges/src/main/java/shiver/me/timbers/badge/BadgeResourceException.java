package shiver.me.timbers.badge;

import static java.lang.String.format;

/**
 * @author Karl Bennett
 */
public class BadgeResourceException extends IllegalStateException {
    public BadgeResourceException(String resource, Throwable cause) {
        super(format("Could not load the resource (%s).", resource), cause);
    }
}
