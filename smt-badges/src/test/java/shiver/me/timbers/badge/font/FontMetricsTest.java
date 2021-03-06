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

package shiver.me.timbers.badge.font;

import org.junit.Test;

import java.awt.*;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.badge.TestUtils.FONTS;
import static shiver.me.timbers.badge.TestUtils.textWidth;
import static shiver.me.timbers.data.random.RandomIntegers.someIntegerBetween;
import static shiver.me.timbers.data.random.RandomStrings.someString;
import static shiver.me.timbers.data.random.RandomThings.someThing;

public class FontMetricsTest {

    @Test
    public void Can_find_the_width_of_some_text_within_a_certain_font() {

        final FontFactory fontFactory = mock(FontFactory.class);
        final int fontSize = someIntegerBetween(1, 32);
        final String text = someString();

        final Font font = new Font(someThing(FONTS), Font.PLAIN, fontSize);

        // Given
        given(fontFactory.create(fontSize)).willReturn(font);

        // When
        final double actual = new shiver.me.timbers.badge.font.FontMetrics(fontFactory, fontSize).calculateWidth(text);

        // Then
        assertThat(actual, closeTo(textWidth(font, text), 0.001));
    }

    @Test
    public void Can_get_the_font_family() {

        final FontFactory fontFactory = mock(FontFactory.class);
        final int fontSize = someIntegerBetween(1, 32);

        final Font font = mock(Font.class);
        final String fontFamily = someString();

        // Given
        given(fontFactory.create(fontSize)).willReturn(font);
        given(font.getFamily()).willReturn(fontFamily);

        // When
        final String actual = new shiver.me.timbers.badge.font.FontMetrics(fontFactory, fontSize).fontFamily();

        // Then
        assertThat(actual, is(fontFamily));
    }
}