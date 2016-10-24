package shiver.me.timbers.badge;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.badge.TestUtils.textY;
import static shiver.me.timbers.data.random.RandomDoubles.someDouble;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomIntegers.someInteger;
import static shiver.me.timbers.data.random.RandomStrings.someString;
import static shiver.me.timbers.matchers.Matchers.hasField;

public class BadgeDataFactoryTest {

    @Test
    public void Can_create_a_badge_data() {

        final String font = someString();
        final int fontSize = someInteger();
        final String javaScriptFile = someString();
        final int height = someInteger();
        final int padding = someInteger();
        final FontMetrics fontMetrics = mock(FontMetrics.class);
        final BadgeResourceFactory resourceFactory = mock(BadgeResourceFactory.class);
        final String subject = someString();
        final String status = someString();
        final Colour colour = someEnum(Colour.class);

        final double subjectWidth = someDouble();
        final double statusWidth = someDouble();
        final String javaScript = someString();

        // Given
        given(fontMetrics.calculateWidth(font, fontSize, subject)).willReturn(subjectWidth);
        given(fontMetrics.calculateWidth(font, fontSize, status)).willReturn(statusWidth);
        given(resourceFactory.create(javaScriptFile)).willReturn(javaScript);

        // When
        final BadgeData actual = new BadgeDataFactory(height, padding, font, fontSize, javaScriptFile, fontMetrics, resourceFactory)
            .create(subject, status, colour);

        // Then
        final int subjectContainerWidth = width(padding, subjectWidth);
        assertThat(actual, hasField("subject", subject));
        assertThat(actual, hasField("status", status));
        assertThat(actual, hasField("colour", colour));
        assertThat(actual, hasField("width", width(padding, subjectWidth, statusWidth)));
        assertThat(actual, hasField("height", height));
        assertThat(actual, hasField("subjectWidth", subjectContainerWidth));
        assertThat(actual, hasField("statusWidth", width(padding, statusWidth)));
        assertThat(actual, hasField("font", font));
        assertThat(actual, hasField("fontSize", fontSize));
        assertThat(actual, hasField("subjectX", padding));
        assertThat(actual, hasField("subjectY", textY(height, padding)));
        assertThat(actual, hasField("statusX", subjectContainerWidth + padding));
        assertThat(actual, hasField("statusY", textY(height, padding)));
    }

    private int width(int padding, double text) {
        return ((int) text + (padding * 2));
    }

    private int width(int padding, double subjectWidth, double statusWidth) {
        return width(padding, subjectWidth) + width(padding, statusWidth);
    }
}