package shiver.me.timbers.badge;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static java.util.Collections.singletonMap;

/**
 * This class uses the {@link com.github.mustachejava.MustacheFactory} to generate the badges SVG XML.
 *
 * @author Karl Bennett
 */
public class BadgeTemplateParser {

    private final String template;
    private final MustacheFactory mustacheFactory;
    private final Flusher flusher;

    public BadgeTemplateParser(String template) {
        this(template, new DefaultMustacheFactory(), new Flusher());
    }

    public BadgeTemplateParser(String template, MustacheFactory mustacheFactory, Flusher flusher) {
        this.mustacheFactory = mustacheFactory;
        this.template = template;
        this.flusher = flusher;
    }

    public String generate(BadgeData data) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final OutputStreamWriter writer = new OutputStreamWriter(out);
        mustacheFactory.compile(template).execute(writer, singletonMap("badge", data));
        try {
            flusher.flush(writer);
        } catch (IOException e) {
            throw new BadgeTemplateException(template, e);
        }
        return out.toString();
    }
}
