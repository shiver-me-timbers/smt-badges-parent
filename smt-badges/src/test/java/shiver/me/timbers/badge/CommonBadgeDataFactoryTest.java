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
import static shiver.me.timbers.data.random.RandomIntegers.someInteger;

public class CommonBadgeDataFactoryTest {

    @Test
    public void Can_create_a_common_badge_data() {

        final FontMetrics fontMetrics = mock(FontMetrics.class);
        final Integer fontSize = someInteger();
        final Integer height = someInteger();
        final Integer padding = someInteger();
        @SuppressWarnings("unchecked")
        final InternalBadgeDataFactory<CommonBadgeOptions, CommonBadgeData> factory =
            mock(InternalBadgeDataFactory.class);
        final CommonBadgeOptions options = mock(CommonBadgeOptions.class);

        final CommonBadgeData expected = mock(CommonBadgeData.class);

        // Given
        given(factory.create(height, padding, fontSize, fontMetrics, options)).willReturn(expected);

        // When
        final CommonBadgeData actual = new CommonBadgeDataFactory<>(fontMetrics, fontSize, height, padding, factory)
            .create(options);

        // Then
        assertThat(actual, is(expected));
    }

}