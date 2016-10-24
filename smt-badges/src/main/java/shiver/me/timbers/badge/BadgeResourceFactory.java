package shiver.me.timbers.badge;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This factory is used to read the badge template file into a {@link String} ready to be processed by the
 * {@link BadgeTemplateParser}.
 *
 * @author Karl Bennett
 */
public class BadgeResourceFactory {

    private final ResourceFactory resourceFactory;

    public BadgeResourceFactory() {
        this(new ResourceFactory());
    }

    public BadgeResourceFactory(ResourceFactory resourceFactory) {
        this.resourceFactory = resourceFactory;
    }

    public String create(String resource) {
        try {
            final InputStream inputStream = resourceFactory.find(resource);
            final ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString("UTF-8");
        } catch (IOException e) {
            throw new BadgeResourceException(resource, e);
        }
    }
}
