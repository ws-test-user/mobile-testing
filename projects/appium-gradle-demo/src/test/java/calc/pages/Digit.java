package calc.pages;

/**
 * Calculator operations enum.
 */
public enum Digit {
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    TREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9");

    private final String text;

    /**
     * Init digit.
     *
     * @param text operation text.
     */
    Digit(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}