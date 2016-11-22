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
import static shiver.me.timbers.badge.Style.flat;
import static shiver.me.timbers.badge.Style.plastic;

public class BadgeDataTest {

    @Test
    public void Can_check_if_the_style_has_been_set_to_flat() {

        // When
        final BadgeData actual = new BadgeData(null, null, null, flat, 0, 0, null, 0, 0, 0, 0, 0, 0, 0);

        // Then
        assertThat(actual.isFlat(), is(true));
        assertThat(actual.isPlastic(), is(false));
    }

    @Test
    public void Can_check_if_the_no_style_has_been_set_then_the_style_is_flat() {

        // When
        final BadgeData actual = new BadgeData(null, null, null, null, 0, 0, null, 0, 0, 0, 0, 0, 0, 0);

        // Then
        assertThat(actual.isFlat(), is(true));
        assertThat(actual.isPlastic(), is(false));
    }

    @Test
    public void Can_check_if_the_style_has_been_set_to_plastic() {

        // When
        final BadgeData actual = new BadgeData(null, null, null, plastic, 0, 0, null, 0, 0, 0, 0, 0, 0, 0);

        // Then
        assertThat(actual.isFlat(), is(false));
        assertThat(actual.isPlastic(), is(true));
    }
}