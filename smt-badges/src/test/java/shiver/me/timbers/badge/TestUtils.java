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

import com.github.mustachejava.DefaultMustacheFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import shiver.me.timbers.badge.data.BadgeData;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.singletonMap;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaNumericString;

public class TestUtils {

    public static int textWidth(Font font, String text) {
        final AffineTransform affinetransform = new AffineTransform();
        final FontRenderContext context = new FontRenderContext(affinetransform, true, false);
        return (int) (font.getStringBounds(text, context).getWidth());
    }

    public static int textShadowY(int height, int padding) {
        return height - padding;
    }

    public static int textY(int height, int padding) {
        return textShadowY(height, padding) - 1;
    }

    public static String resource(String resource) throws IOException {
        return IOUtils.toString(Thread.currentThread().getContextClassLoader().getResourceAsStream(resource), UTF_8);
    }

    public static final String[] FONTS = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    static void The_mustache_template_works_correctly(String template) throws IOException {

        // Given
        final String subject = someAlphaNumericString(8);
        final String status = someAlphaNumericString(13);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final OutputStreamWriter writer = new OutputStreamWriter(out);
        final BadgeData data = new BadgeData(subject, status, null, 0, 0, null, 0, 0, 0, 0, 0, 0, 0);

        // When
        new DefaultMustacheFactory().compile(template).execute(writer, singletonMap("badge", data));
        writer.flush();
        final String actual = out.toString(UTF_8);

        // Then
        assertThat(actual, containsString(subject));
        assertThat(actual, containsString(status));
    }
}
