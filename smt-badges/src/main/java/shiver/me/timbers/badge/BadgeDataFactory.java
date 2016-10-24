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

/**
 * @author Karl Bennett
 */
public class BadgeDataFactory {


    private final int height;
    private final int padding;
    private final String font;
    private final int fontSize;
    private final String javaScriptFile;
    private final FontMetrics fontMetrics;
    private final BadgeResourceFactory resourceFactory;

    public BadgeDataFactory(int height, int padding, String font, int fontSize, String javaScriptFile) {
        this(
            height,
            padding,
            font,
            fontSize,
            javaScriptFile,
            new FontMetrics(font, fontSize),
            new BadgeResourceFactory()
        );
    }

    public BadgeDataFactory(
        int height,
        int padding,
        String font,
        int fontSize,
        String javaScriptFile,
        FontMetrics fontMetrics,
        BadgeResourceFactory resourceFactory
    ) {
        this.height = height;
        this.padding = padding;
        this.font = font;
        this.fontSize = fontSize;
        this.javaScriptFile = javaScriptFile;
        this.fontMetrics = fontMetrics;
        this.resourceFactory = resourceFactory;
    }

    public BadgeData create(String subject, String status, Colour colour) {
        final int subjectWidth = textWidth(subject);
        final int statusWidth = textWidth(status);
        final int width = width(subjectWidth, statusWidth);
        final int subjectX = padding;
        final int subjectY = textY();
        final int statusX = padding + subjectWidth;
        final int statusY = textY();
        final String javaScript = resourceFactory.create(javaScriptFile);
        return new BadgeData(
            subject,
            status,
            colour,
            width,
            height,
            font,
            fontSize,
            subjectWidth,
            statusWidth,
            subjectX,
            subjectY,
            statusX,
            statusY,
            javaScript
        );
    }

    private int textWidth(String text) {
        return (int) fontMetrics.calculateWidth(text) + (padding * 2);
    }

    private int width(int subjectWidth, int statusWidth) {
        return subjectWidth + statusWidth;
    }

    private int textY() {
        return height - padding - 1;
    }
}
