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

    private final TemplateFactory templateFactory;
    private final MustacheFactory mustacheFactory;
    private final Flusher flusher;

    public BadgeTemplateParser(String flatPlasticTemplate, String flatSquareTemplate) {
        this(new TemplateFactory(flatPlasticTemplate, flatSquareTemplate), new DefaultMustacheFactory(), new Flusher());
    }

    public BadgeTemplateParser(TemplateFactory templateFactory, MustacheFactory mustacheFactory, Flusher flusher) {
        this.templateFactory = templateFactory;
        this.mustacheFactory = mustacheFactory;
        this.flusher = flusher;
    }

    public String generate(BadgeData data) {
        final String template = templateFactory.choose(data);
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
