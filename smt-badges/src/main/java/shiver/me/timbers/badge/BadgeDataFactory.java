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
        this(height, padding, font, fontSize, javaScriptFile, new FontMetrics(), new BadgeResourceFactory());
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
        return (int) fontMetrics.calculateWidth(font, fontSize, text) + (padding * 2);
    }

    private int width(int subjectWidth, int statusWidth) {
        return subjectWidth + statusWidth;
    }

    private int textY() {
        return height - padding - 1;
    }
}
