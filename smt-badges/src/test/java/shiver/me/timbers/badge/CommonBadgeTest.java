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

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class CommonBadgeTest {

    @Test
    @SuppressWarnings("unchecked")
    public void Can_generate_the_badge_SVG_XML() {

        final CommonBadgeOptions badgeOptions = mock(BadgeOptions.class);
        final CommonBadgeDataFactory<CommonBadgeOptions, CommonBadgeData> dataFactory =
            mock(CommonBadgeDataFactory.class);
        final BadgeTemplateParser<CommonBadgeData> templateParser = mock(BadgeTemplateParser.class);

        final CommonBadgeData data = mock(CommonBadgeData.class);

        final String expected = someString();

        // Given
        given(dataFactory.create(badgeOptions)).willReturn(data);
        given(templateParser.parse(data)).willReturn(expected);

        // When
        final String actual = new CommonBadge<>(badgeOptions, dataFactory, templateParser).toString();

        // Then
        assertThat(actual, is(expected));
    }
}