package shiver.me.timbers.badge.data;

/**
 * @author Karl Bennett
 */
public class SocialBadgeData extends CommonBadgeData {

    private int rectangleHeight;
    private final String subjectLink;
    private final String statusLink;
    private final double statusRectangleX;
    private final int statusTriangleBaseX;

    public SocialBadgeData(
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
        int textY,
        int rectangleHeight,
        String subjectLink,
        String statusLink,
        double statusRectangleX,
        int statusTriangleBaseX
    ) {
        super(
            subject,
            status,
            width,
            height,
            fontFamily,
            fontSize,
            subjectWidth,
            statusWidth,
            subjectX,
            statusX,
            textShadowY,
            textY
        );
        this.rectangleHeight = rectangleHeight;
        this.subjectLink = subjectLink;
        this.statusLink = statusLink;
        this.statusRectangleX = statusRectangleX;
        this.statusTriangleBaseX = statusTriangleBaseX;
    }

    public int getRectangleHeight() {
        return rectangleHeight;
    }

    public String getSubjectLink() {
        return subjectLink;
    }

    public String getStatusLink() {
        return statusLink;
    }

    public double getStatusRectangleX() {
        return statusRectangleX;
    }

    public int getStatusTriangleBaseX() {
        return statusTriangleBaseX;
    }
}
