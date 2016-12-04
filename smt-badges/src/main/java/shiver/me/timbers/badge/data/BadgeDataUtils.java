package shiver.me.timbers.badge.data;

import shiver.me.timbers.badge.font.FontMetrics;

/**
 * @author Karl Bennett
 */
public class BadgeDataUtils {

    static int textWidth(FontMetrics fontMetrics, int padding, String text) {
        return (int) (fontMetrics.calculateWidth(text) + (padding * 2));
    }

    static int statusX(int subjectWidth, int statusWidth) {
        return subjectWidth + textX(statusWidth);
    }

    static int textX(int subjectWidth) {
        return subjectWidth / 2;
    }

    static int textShadowY(int height, int padding) {
        return height - padding;
    }

    static int textY(int height, int padding) {
        return textShadowY(height, padding) - 1;
    }
}
