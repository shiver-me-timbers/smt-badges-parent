package shiver.me.timbers.badge.data;

import shiver.me.timbers.badge.SocialBadgeData;
import shiver.me.timbers.badge.SocialBadgeOptions;
import shiver.me.timbers.badge.font.FontMetrics;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;
import static shiver.me.timbers.badge.data.BadgeDataUtils.statusX;
import static shiver.me.timbers.badge.data.BadgeDataUtils.textShadowY;
import static shiver.me.timbers.badge.data.BadgeDataUtils.textWidth;
import static shiver.me.timbers.badge.data.BadgeDataUtils.textX;
import static shiver.me.timbers.badge.data.BadgeDataUtils.textY;

/**
 * @author Karl Bennett
 */
public class SocialBadgeDataFactory implements InternalBadgeDataFactory<SocialBadgeOptions, SocialBadgeData> {

    private static final double SPACING = 6;
    private static final double OFFSET = 0.5;

    @Override
    public SocialBadgeData create(
        int height,
        int padding,
        int fontSize,
        FontMetrics fontMetrics,
        SocialBadgeOptions options
    ) {
        final String subject = options.getSubject();
        final String status = options.getStatus();
        final int subjectWidth = textWidth(fontMetrics, padding, subject);
        final int statusWidth = textWidth(fontMetrics, padding, status);
        final int width = width(subjectWidth, statusWidth);
        final int rectangleHeight = height - 1;
        final int statusTriangleBaseX = (int) statusTriangleBaseX(subjectWidth);
        final double statusRectangleX = statusRectangleX(statusTriangleBaseX);
        final int subjectX = textX(subjectWidth);
        final int statusX = statusX((int) statusRectangleX, statusWidth);
        final int textShadowY = textShadowY(height, padding);
        final int textY = textY(height, padding);
        return new SocialBadgeData(
            subject,
            status,
            width,
            height,
            fontMetrics.fontFamily(),
            fontSize,
            subjectWidth,
            statusWidth,
            subjectX,
            statusX,
            textShadowY,
            textY,
            rectangleHeight,
            options.getSubjectLink(),
            options.getStatusLink(),
            statusRectangleX,
            statusTriangleBaseX
        );
    }

    private static int width(int subjectWidth, int statusWidth) {
        return (int) new BigDecimal(String.valueOf(subjectWidth + SPACING + OFFSET + statusWidth)).setScale(0, HALF_UP)
            .doubleValue();
    }

    private static double statusTriangleBaseX(int subjectWidth) {
        return subjectWidth + SPACING;
    }

    private double statusRectangleX(double statusTriangleBaseX) {
        return statusTriangleBaseX + OFFSET;
    }
}
