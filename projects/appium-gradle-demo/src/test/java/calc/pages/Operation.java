package calc.pages;

/**
 * Calculator operations enum.
 */
public enum Operation {
    PLUS("plus"),
    MINUS("minus"),
    MULTIPLY("mul"),
    DIVIDE("div"),
    EQUALS("equal");

    private final String text;

    /**
     * Init operation.
     *
     * @param text operation text.
     */
    Operation(final String text) {
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
