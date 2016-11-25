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

import shiver.me.timbers.badge.template.BadgeTemplateParser;

/**
 * This class holds all thr data that will be used by the {@link BadgeTemplateParser} to generate the SVG XML.
 *
 * @author Karl Bennett
 */
public class CommonBadgeData {

    private final String subject;
    private final String status;
    private final int width;
    private final int height;
    private final String fontFamily;
    private final int fontSize;
    private final int subjectWidth;
    private final int statusWidth;
    private final int subjectX;
    private final int statusX;
    private final int textShadowY;
    private final int textY;

    public CommonBadgeData(
        String subject,
        String status,
        int width,
        int height,
        String fontFamily,
        int fontSize,
        int subjectWidth,
        int statusWidth,
        int subjectX,
        int statusX,
        int textShadowY,
        int textY
    ) {
        this.subject = subject;
        this.status = status;
        this.width = width;
        this.height = height;
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.subjectWidth = subjectWidth;
        this.statusWidth = statusWidth;
        this.subjectX = subjectX;
        this.statusX = statusX;
        this.textShadowY = textShadowY;
        this.textY = textY;
    }

    public String getSubject() {
        return subject;
    }

    public String getStatus() {
        return status;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }

    public int getSubjectWidth() {
        return subjectWidth;
    }

    public int getStatusWidth() {
        return statusWidth;
    }

    public int getSubjectX() {
        return subjectX;
    }

    public int getStatusX() {
        return statusX;
    }

    public int getTextShadowY() {
        return textShadowY;
    }

    public int getTextY() {
        return textY;
    }
}
