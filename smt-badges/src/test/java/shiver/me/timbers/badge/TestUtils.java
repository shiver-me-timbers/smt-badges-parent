package shiver.me.timbers.badge;

import org.apache.commons.io.IOUtils;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TestUtils {

    public static int textWidth(String font, int fontSize, String text) {
        final AffineTransform affinetransform = new AffineTransform();
        final FontRenderContext context = new FontRenderContext(affinetransform, true, true);
        return (int) new Font(font, Font.PLAIN, fontSize).getStringBounds(text, context).getWidth();
    }

    public static int textY(int height, int padding) {
        return height - padding - 1;
    }

    public static String resource(String resource) throws IOException {
        return IOUtils.toString(Thread.currentThread().getContextClassLoader().getResourceAsStream(resource), UTF_8);
    }
}
