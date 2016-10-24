package shiver.me.timbers.badge;

/**
 * This class is used to generate the XML for an SVG badge that are usually used to show metrics on source code
 * repositories. The design of this badge has been copied from http://shields.io and attempts to follow their
 * specification: https://github.com/badges/shields/blob/master/spec/SPECIFICATION.md
 *
 * @author Karl Bennett
 */
public class Badge {

    static final int HEIGHT = 20;
    static final int PADDING = 5;
    static final String FONT = "DejaVu Sans";
    static final int FONT_SIZE = 11;
    private static final String JAVA_SCRIPT_FILE = "badge.js";
    static final String TEMPLATE = "badge.mustache";

    private final String svg;

    public Badge(String subject, String status, Colour colour) {
        this(
            subject,
            status, colour,
            new BadgeDataFactory(HEIGHT, PADDING, FONT, FONT_SIZE, JAVA_SCRIPT_FILE),
            new BadgeTemplateParser(TEMPLATE)
        );
    }

    public Badge(String subject, String status, Colour colour, BadgeDataFactory dataFactory, BadgeTemplateParser template) {
        this.svg = template.generate(dataFactory.create(subject, status, colour));
    }

    @Override
    public String toString() {
        return svg;
    }
}