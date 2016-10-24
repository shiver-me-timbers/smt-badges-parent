package shiver.me.timbers.badge;

import org.junit.Test;

import java.awt.*;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.badge.TestUtils.textWidth;
import static shiver.me.timbers.data.random.RandomIntegers.someIntegerBetween;
import static shiver.me.timbers.data.random.RandomStrings.someString;
import static shiver.me.timbers.data.random.RandomThings.someThing;

public class FontMetricsTest {

    private static final String[] FONTS = GraphicsEnvironment.getLocalGraphicsEnvironment()
        .getAvailableFontFamilyNames();

    @Test
    public void Can_find_the_width_of_some_text_within_a_certain_font() {

        // Given
        final String font = someThing(FONTS);
        final int fontSize = someIntegerBetween(1, 32);
        final String text = someString();

        // When
        final double actual = new FontMetrics().calculateWidth(font, fontSize, text);

        // Then
        assertThat(actual, closeTo(textWidth(font, fontSize, text), 0.001));
    }
}