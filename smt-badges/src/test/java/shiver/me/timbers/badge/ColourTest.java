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

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.badge.Colour.blue;
import static shiver.me.timbers.badge.Colour.brightgreen;
import static shiver.me.timbers.badge.Colour.darkgrey;
import static shiver.me.timbers.badge.Colour.green;
import static shiver.me.timbers.badge.Colour.lightgrey;
import static shiver.me.timbers.badge.Colour.orange;
import static shiver.me.timbers.badge.Colour.pink;
import static shiver.me.timbers.badge.Colour.red;
import static shiver.me.timbers.badge.Colour.yellow;
import static shiver.me.timbers.badge.Colour.yellowgreen;

public class ColourTest {

    @Test
    public void Can_produce_the_right_colours() {

        assertThat(brightgreen.toString(), equalTo("#4c1"));
        assertThat(green.toString(), equalTo("#97ca00"));
        assertThat(yellowgreen.toString(), equalTo("#a4a61d"));
        assertThat(yellow.toString(), equalTo("#dfb317"));
        assertThat(orange.toString(), equalTo("#fe7d37"));
        assertThat(red.toString(), equalTo("#e05d44"));
        assertThat(darkgrey.toString(), equalTo("#555"));
        assertThat(lightgrey.toString(), equalTo("#9f9f9f"));
        assertThat(blue.toString(), equalTo("#007ec6"));
        assertThat(pink.toString(), equalTo("#e05d44"));
    }
}