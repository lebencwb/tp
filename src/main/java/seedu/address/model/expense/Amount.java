package seedu.address.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Expense's amount in the expense book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount(String)}
 */
public class Amount {


    public static final String MESSAGE_CONSTRAINTS =
            "Expense amount should only contain numbers and 1 '.', and should be in the {dollars}.{cents} format."
                    + "{.cents} is optional but {dollars} should contain at least 1 digit.";
    public static final String VALIDATION_REGEX = "(?<dollars>\\d+)(.(?<cents>\\d{1,2}))?";
    public final Integer value;

    /**
     * Constructs a {@code Amount}.
     *
     * @param amount A valid amount.
     */
    public Amount(String amount) {
        requireNonNull(amount);
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);

        float f = Float.parseFloat(amount);
        value = Math.round(f * 100);
    }

    /**
     * Returns true if a given string is a valid amount.
     */
    public static boolean isValidAmount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a new {@code Amount} as the sum of the current {@code Amount} and the specified {@code Amount}.
     */
    public Amount add(Amount amount) {
        requireNonNull(amount);
        Integer total = value + amount.value;
        return new Amount(String.format("%.02f", total / 100.0));
    }

    public Double asDouble() {
        return value / 100.0;
    }

    @Override
    public String toString() {
        return asDouble().toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Amount // instanceof handles nulls
                && value.equals(((Amount) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}