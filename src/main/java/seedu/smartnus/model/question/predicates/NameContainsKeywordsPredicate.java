package seedu.smartnus.model.question.predicates;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.smartnus.commons.util.StringUtil;
import seedu.smartnus.model.question.Question;

/**
 * Tests that a {@code Question}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Question> {
    private final List<String> keywords;

    /**
     * Constructs the NameContainsKeywordsPredicate.
     * @param keywords The keywords for the question name to be matched against.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        requireNonNull(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Question question) {
        if (keywords.isEmpty()) {
            return false;
        }
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsIgnoreCaseString(question.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(keywords);
    }
}
