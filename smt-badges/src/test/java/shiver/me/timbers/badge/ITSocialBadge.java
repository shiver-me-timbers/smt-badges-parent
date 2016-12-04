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
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.math.BigDecimal;

import static java.lang.String.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static shiver.me.timbers.badge.BadgeTestUtils.assertLeftLinkRectangle;
import static shiver.me.timbers.badge.BadgeTestUtils.assertSocialGradient;
import static shiver.me.timbers.badge.BadgeTestUtils.assertSocialStatusWithShadow;
import static shiver.me.timbers.badge.BadgeTestUtils.assertSocialSubjectWithShadow;
import static shiver.me.timbers.badge.BadgeTestUtils.assertStatusRectangle;
import static shiver.me.timbers.badge.BadgeTestUtils.assertSubjectRectangle;
import static shiver.me.timbers.badge.BadgeTestUtils.assertSvg;
import static shiver.me.timbers.badge.BadgeTestUtils.assertTextContainer;
import static shiver.me.timbers.badge.BadgeTestUtils.assertTriangleBasePath;
import static shiver.me.timbers.badge.BadgeTestUtils.assertTrianglePath;
import static shiver.me.timbers.badge.BadgeTestUtils.textWidth;
import static shiver.me.timbers.badge.BadgeTestUtils.toDocument;
import static shiver.me.timbers.badge.FlatBadge.HEIGHT;
import static shiver.me.timbers.badge.PlasticBadge.PLASTIC_TEMPLATE;
import static shiver.me.timbers.badge.TestUtils.The_mustache_template_works_correctly;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaNumericString;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class ITSocialBadge {

    @Test
    public void Can_create_a_badge()
        throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        // Given
        final String subject = someAlphaNumericString(8);
        final String status = someAlphaNumericString(13);
        final String subjectLink = someString();
        final String statusLink = someString();

        // When
        final Document actual = toDocument(new SocialBadge(subject, status, subjectLink, statusLink));

        // Then
        final String badgeWidth = valueOf(roundUp(textWidth(subject) + 6.5 + textWidth(status)));
        final String badgeHeight = valueOf(HEIGHT);
        final String rectangleHeight = valueOf(HEIGHT - 1);
        final String statusRectangleX = valueOf(6.5 + textWidth(subject));
        assertSvg(actual, badgeWidth, badgeHeight);
        assertSocialGradient(actual);
        assertSubjectRectangle(actual, valueOf(textWidth(subject)), rectangleHeight);
        assertStatusRectangle(actual, statusRectangleX, status, rectangleHeight);
        assertTriangleBasePath(actual, valueOf(6 + textWidth(subject)));
        assertTrianglePath(actual, statusRectangleX);
        assertTextContainer(actual, 1, "Helvetica Neue");
        assertSocialSubjectWithShadow(actual, subject);
        assertSocialStatusWithShadow(actual, subject, status);
        assertLeftLinkRectangle(actual, subject, rectangleHeight);
    }

    private int roundUp(double value) {
        return (int) new BigDecimal(String.valueOf(value)).setScale(0, HALF_UP).doubleValue();
    }

    @Test
    public void The_social_mustache_template_works_correctly() throws IOException {

        The_mustache_template_works_correctly(PLASTIC_TEMPLATE);
    }
}