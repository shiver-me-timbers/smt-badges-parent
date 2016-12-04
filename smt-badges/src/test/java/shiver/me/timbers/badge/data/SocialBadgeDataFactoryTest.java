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

package shiver.me.timbers.badge.data;

import org.junit.Test;
import shiver.me.timbers.badge.SocialBadgeData;
import shiver.me.timbers.badge.SocialBadgeOptions;
import shiver.me.timbers.badge.font.FontMetrics;

import java.math.BigDecimal;

import static java.lang.String.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.badge.TestUtils.textShadowY;
import static shiver.me.timbers.badge.TestUtils.textY;
import static shiver.me.timbers.data.random.RandomDoubles.someDoubleBetween;
import static shiver.me.timbers.data.random.RandomIntegers.someInteger;
import static shiver.me.timbers.data.random.RandomIntegers.someIntegerBetween;
import static shiver.me.timbers.data.random.RandomStrings.someString;
import static shiver.me.timbers.matchers.Matchers.hasField;

public class SocialBadgeDataFactoryTest {

    @Test
    public void Can_create_a_social_badge_data() {

        final int height = someInteger();
        final int padding = someIntegerBetween(1, 1000);
        final int fontSize = someInteger();
        final FontMetrics fontMetrics = mock(FontMetrics.class);
        final SocialBadgeOptions badgeOptions = mock(SocialBadgeOptions.class);

        final String fontFamily = someString();
        final double subjectWidth = someDoubleBetween(1D, 1000D);
        final double statusWidth = someDoubleBetween(1D, 1000D);
        final String subject = someString();
        final String status = someString();
        final String subjectLink = someString();
        final String statusLink = someString();

        // Given
        given(fontMetrics.fontFamily()).willReturn(fontFamily);
        given(fontMetrics.calculateWidth(subject)).willReturn(subjectWidth);
        given(fontMetrics.calculateWidth(status)).willReturn(statusWidth);
        given(badgeOptions.getSubject()).willReturn(subject);
        given(badgeOptions.getStatus()).willReturn(status);
        given(badgeOptions.getSubjectLink()).willReturn(subjectLink);
        given(badgeOptions.getStatusLink()).willReturn(statusLink);

        // When
        final SocialBadgeData actual = new SocialBadgeDataFactory()
            .create(height, padding, fontSize, fontMetrics, badgeOptions);

        // Then
        final int subjectContainerWidth = width(padding, subjectWidth);
        final int statusContainerWidth = width(padding, statusWidth);
        final int statusTriangleBaseX = subjectContainerWidth + 6;
        final double statusRectangleX = statusTriangleBaseX + 0.5;
        final int subjectX = subjectContainerWidth / 2;
        final int statusX = ((int) statusRectangleX) + (statusContainerWidth / 2);
        assertThat(actual, hasField("subject", subject));
        assertThat(actual, hasField("status", status));
        assertThat(actual, hasField("width", width(padding, subjectWidth, statusWidth)));
        assertThat(actual, hasField("height", height));
        assertThat(actual, hasField("fontFamily", fontFamily));
        assertThat(actual, hasField("fontSize", fontSize));
        assertThat(actual, hasField("subjectWidth", subjectContainerWidth));
        assertThat(actual, hasField("statusWidth", statusContainerWidth));
        assertThat(actual, hasField("subjectX", subjectX));
        assertThat(actual, hasField("statusX", statusX));
        assertThat(actual, hasField("textShadowY", textShadowY(height, padding)));
        assertThat(actual, hasField("textY", textY(height, padding)));
        assertThat(actual, hasField("rectangleHeight", height - 1));
        assertThat(actual, hasField("subjectLink", subjectLink));
        assertThat(actual, hasField("statusLink", statusLink));
        assertThat(actual, hasField("statusRectangleX", statusRectangleX));
        assertThat(actual, hasField("statusTriangleBaseX", statusTriangleBaseX));
    }

    private int width(int padding, double text) {
        return (int) (text + (padding * 2));
    }

    private int width(int padding, double subjectWidth, double statusWidth) {
        return (int) new BigDecimal(valueOf(width(padding, subjectWidth) + 6.5 + width(padding, statusWidth)))
            .setScale(0, HALF_UP).doubleValue();
    }
}