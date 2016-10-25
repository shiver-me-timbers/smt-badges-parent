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

import org.junit.Test;

import java.awt.*;

import static org.hamcrest.Matchers.closeTo;
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
        final String font = someThing(FONTS);
        final int fontSize = someIntegerBetween(1, 32);
        final String text = someString();

        // Given
        given(fontFactory.create(font, fontSize)).willReturn(new Font(font, Font.PLAIN, fontSize));

        // When
        final double actual = new FontMetrics(fontFactory, font, fontSize).calculateWidth(text);

        // Then
        assertThat(actual, closeTo(textWidth(font, fontSize, text), 0.001));
    }
}