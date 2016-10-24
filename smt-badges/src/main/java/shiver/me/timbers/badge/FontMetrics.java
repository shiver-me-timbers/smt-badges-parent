package shiver.me.timbers.badge;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

/**
 * This class provides methods for getting metrics on string that have been rendered with a certain font.
 *
 * @author Karl Bennett
 */
public class FontMetrics {

    public double calculateWidth(String font, int fontSize, String text) {
        final AffineTransform affinetransform = new AffineTransform();
        final FontRenderContext context = new FontRenderContext(affinetransform, true, true);
        return (int) new Font(font, Font.PLAIN, fontSize).getStringBounds(text, context).getWidth();
    }
}
