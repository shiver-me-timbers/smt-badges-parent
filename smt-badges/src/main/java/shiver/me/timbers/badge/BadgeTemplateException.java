package shiver.me.timbers.badge;

import static java.lang.String.format;

/**
 * @author Karl Bennett
 */
public class BadgeTemplateException extends IllegalStateException {
    public BadgeTemplateException(String template, Throwable cause) {
        super(format("Could not load the template (%s).", template), cause);
    }
}
