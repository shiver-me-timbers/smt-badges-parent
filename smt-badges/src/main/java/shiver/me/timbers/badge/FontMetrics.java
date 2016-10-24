/*
 * Copyright 2016 Karl Bennett
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package shiver.me.timbers.badge;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.IOException;

/**
 * This class provides methods for getting metrics on string that have been rendered with a certain font.
 *
 * @author Karl Bennett
 */
public class FontMetrics {

    static {
        try {
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(
                Font.createFont(
                    Font.TRUETYPE_FONT,
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("DejaVuSans.ttf")
                )
            );
        } catch (FontFormatException | IOException e) {
            throw new UnsupportedOperationException();
        }
    }

    public double calculateWidth(String font, int fontSize, String text) {
        final AffineTransform affinetransform = new AffineTransform();
        final FontRenderContext context = new FontRenderContext(affinetransform, true, true);
        return (int) new Font(font, Font.PLAIN, fontSize).getStringBounds(text, context).getWidth();
    }
}
