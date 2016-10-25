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

import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static java.awt.Font.PLAIN;
import static java.awt.Font.TRUETYPE_FONT;
import static java.awt.Font.createFont;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.badge.Badge.FONT;
import static shiver.me.timbers.badge.Badge.FONT_SIZE;
import static shiver.me.timbers.badge.Badge.HEIGHT;
import static shiver.me.timbers.badge.Badge.PADDING;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaNumericString;

public class ITBadge {

    private static final XPath XPATH = XPathFactory.newInstance().newXPath();
    private static Font DEFAULT_FONT;

    @BeforeClass
    public static void setUp() throws IOException, FontFormatException {
        DEFAULT_FONT = createFont(
            TRUETYPE_FONT,
            Thread.currentThread().getContextClassLoader().getResourceAsStream("DejaVuSans-webfont.ttf")
        );
    }

    @Test
    public void Can_create_a_badge()
        throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        // Given
        final String subject = someAlphaNumericString(8);
        final String status = someAlphaNumericString(13);
        final Colour colour = someEnum(Colour.class);

        // When
        final Document actual = toDocument(new Badge(subject, status, colour));

        // Then
        final Element svg = findElementById(actual, "smt-svg");
        final String badgeWidth = badgeWidth(subject, status);
        final String badgeHeight = valueOf(HEIGHT);
        final Element rectangle = findElementById(actual, "smt-badge-subject-rectangle");
        final Element statusPath = findElementById(actual, "smt-badge-status-path");
        final Element textContainer = findElementById(actual, "smt-badge-text-container");
        final Element subjectText = findElementById(actual, "smt-badge-subject");
        final Element statusText = findElementById(actual, "smt-badge-status");
        assertThat(svg.getAttribute("width"), equalTo(badgeWidth));
        assertThat(svg.getAttribute("height"), equalTo(badgeHeight));
        assertThat(rectangle.getAttribute("width"), equalTo(badgeWidth));
        assertThat(rectangle.getAttribute("height"), equalTo(badgeHeight));
        assertThat(
            findElementById(actual, "smt-badge-subject-path").getAttribute("d"),
            equalTo(subjectPathDirections(subject, badgeHeight))
        );
        assertThat(statusPath.getAttribute("d"), equalTo(statusPathDirections(subject, status, badgeHeight)));
        assertThat(statusPath.getAttribute("fill"), equalTo(colour.toString()));
        assertThat(
            findElementById(actual, "smt-badge-gradient-path").getAttribute("d"),
            equalTo(gradientPathDirections(badgeWidth, badgeHeight))
        );
        assertThat(textContainer.getAttribute("font-family"), startsWith(FONT));
        assertThat(textContainer.getAttribute("font-size"), equalTo(valueOf(FONT_SIZE)));
        assertThat(subjectText.getAttribute("x"), equalTo(valueOf(PADDING)));
        assertThat(subjectText.getAttribute("y"), equalTo(textY()));
        assertThat(subjectText.getTextContent(), equalTo(subject));
        assertThat(statusText.getAttribute("x"), equalTo(statusX(subject)));
        assertThat(statusText.getAttribute("y"), equalTo(textY()));
        assertThat(statusText.getTextContent(), equalTo(status));
    }

    private static Document toDocument(Badge badge) throws ParserConfigurationException, IOException, SAXException {
        final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return dBuilder.parse(new ByteArrayInputStream(badge.toString().getBytes(UTF_8)));
    }

    private Element findElementById(Document document, String id) throws XPathExpressionException {
        return (Element) XPATH.evaluate(format("//*[@id='%s']", id), document, XPathConstants.NODE);
    }

    private static String badgeWidth(String subject, String status) {
        return valueOf(textWidth(subject) + textWidth(status));
    }

    private static String subjectPathDirections(String subject, String badgeHeight) {
        return pathDirections(0, 0, textWidth(subject), Integer.valueOf(badgeHeight), 0);
    }

    private static String pathDirections(int x, int y, int h, int v, int H) {
        return format("M%s %sh%sv%sH%sz", x, y, h, v, H);
    }

    private static String statusPathDirections(String subject, String status, String badgeHeight) {
        final int subjectWidth = textWidth(subject);
        return pathDirections(subjectWidth, 0, textWidth(status), Integer.valueOf(badgeHeight), subjectWidth);
    }

    private static int textWidth(String text) {
        return TestUtils.textWidth(DEFAULT_FONT.deriveFont(PLAIN, FONT_SIZE), text) + (PADDING * 2);
    }

    private static String gradientPathDirections(String badgeWidth, String badgeHeight) {
        return pathDirections(0, 0, Integer.valueOf(badgeWidth), Integer.valueOf(badgeHeight), 0);
    }

    private static String textY() {
        return valueOf(TestUtils.textY(HEIGHT, PADDING));
    }

    private static String statusX(String subject) {
        return valueOf(textWidth(subject) + PADDING);
    }
}