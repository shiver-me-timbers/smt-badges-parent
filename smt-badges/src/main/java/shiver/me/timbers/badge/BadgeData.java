package shiver.me.timbers.badge;

/**
 * This class holds all thr data that will be used by the {@link BadgeTemplateParser} to generate the SVG XML.
 *
 * @author Karl Bennett
 */
public class BadgeData {

    private final String subject;
    private final String status;
    private final Colour colour;
    private final int width;
    private final int height;
    private final String font;
    private final int fontSize;
    private final int subjectWidth;
    private final int statusWidth;
    private final int subjectX;
    private final int subjectY;
    private final int statusX;
    private final int statusY;
    private final String javaScript;

    public BadgeData(
        String subject,
        String status,
        Colour colour,
        int width,
        int height,
        String font,
        int fontSize,
        int subjectWidth,
        int statusWidth,
        int subjectX,
        int subjectY,
        int statusX,
        int statusY,
        String javaScript
    ) {
        this.subject = subject;
        this.status = status;
        this.colour = colour;
        this.width = width;
        this.height = height;
        this.font = font;
        this.fontSize = fontSize;
        this.subjectWidth = subjectWidth;
        this.statusWidth = statusWidth;
        this.subjectX = subjectX;
        this.subjectY = subjectY;
        this.statusX = statusX;
        this.statusY = statusY;
        this.javaScript = javaScript;
    }

    public String getSubject() {
        return subject;
    }

    public String getStatus() {
        return status;
    }

    public Colour getColour() {
        return colour;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getFont() {
        return font;
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

    public int getSubjectY() {
        return subjectY;
    }

    public int getStatusX() {
        return statusX;
    }

    public int getStatusY() {
        return statusY;
    }

    public String getJavaScript() {
        return javaScript;
    }
}