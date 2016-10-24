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

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.badge.TestUtils.resource;

public class ResourceFactoryTest {

    @Test
    public void Can_read_a_resource() throws IOException {

        // Given
        final String resource = "test.txt";

        // When
        final String actual = IOUtils.toString(new ResourceFactory().find(resource), UTF_8);

        // Then
        assertThat(actual, equalTo(resource(resource)));
    }
}