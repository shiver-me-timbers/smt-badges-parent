package shiver.me.timbers.badge;

/**
 * This enum contains the valid colours that a badge can have.
 *
 * @author Karl Bennett
 */
public enum Colour {
    brightgreen("#4c1"),
    green("#97CA00"),
    yellowgreen("#a4a61d"),
    yellow("#dfb317"),
    orange("#fe7d37"),
    red("#e05d44"),
    lightgrey("#9f9f9f"),
    blue("#007ec6"),
    pink("#e05d44");

    private final String colour;

    Colour(String colour) {
        this.colour = colour;
    }


    @Override
    public String toString() {
        return colour;
    }
}
