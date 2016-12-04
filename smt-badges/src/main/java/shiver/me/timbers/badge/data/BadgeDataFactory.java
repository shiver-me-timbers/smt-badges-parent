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

import shiver.me.timbers.badge.font.FontMetrics;
import shiver.me.timbers.badge.options.BadgeOptions;

import static shiver.me.timbers.badge.data.BadgeDataUtils.statusX;
import static shiver.me.timbers.badge.data.BadgeDataUtils.textShadowY;
import static shiver.me.timbers.badge.data.BadgeDataUtils.textWidth;
import static shiver.me.timbers.badge.data.BadgeDataUtils.textX;
import static shiver.me.timbers.badge.data.BadgeDataUtils.textY;

/**
 * @author Karl Bennett
 */
public class BadgeDataFactory implements InternalBadgeDataFactory<BadgeOptions, BadgeData> {

    @Override
    public BadgeData create(int height, int padding, int fontSize, FontMetrics fontMetrics, BadgeOptions options) {
        final String subject = options.getSubject();
        final String status = options.getStatus();
        final int subjectWidth = textWidth(fontMetrics, padding, subject);
        final int statusWidth = textWidth(fontMetrics, padding, status);
        final int width = width(subjectWidth, statusWidth);
        final int subjectX = textX(subjectWidth);
        final int statusX = statusX(subjectWidth, statusWidth);
        final int textShadowY = textShadowY(height, padding);
        final int textY = textY(height, padding);
        return new BadgeData(
            subject,
            status,
            options.getColour(),
            width,
            height,
            fontMetrics.fontFamily(),
            fontSize,
            subjectWidth,
            statusWidth,
            subjectX,
            statusX,
            textShadowY,
            textY
        );
    }

    private static int width(int subjectWidth, int statusWidth) {
        return subjectWidth + statusWidth;
    }

}
