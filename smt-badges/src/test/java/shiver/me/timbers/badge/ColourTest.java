package shiver.me.timbers.badge;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.badge.Colour.blue;
import static shiver.me.timbers.badge.Colour.brightgreen;
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
        assertThat(green.toString(), equalTo("#97CA00"));
        assertThat(yellowgreen.toString(), equalTo("#a4a61d"));
        assertThat(yellow.toString(), equalTo("#dfb317"));
        assertThat(orange.toString(), equalTo("#fe7d37"));
        assertThat(red.toString(), equalTo("#e05d44"));
        assertThat(lightgrey.toString(), equalTo("#9f9f9f"));
        assertThat(blue.toString(), equalTo("#007ec6"));
        assertThat(pink.toString(), equalTo("#e05d44"));
    }
}