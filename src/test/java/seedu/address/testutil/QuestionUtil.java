package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditQuestionDescriptor;
import seedu.address.model.question.Question;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Question.
 */
public class QuestionUtil {

    /**
     * Returns an add command string for adding the {@code question}.
     */
    public static String getAddCommand(Question question) {
        return AddCommand.COMMAND_WORD + " " + getQuestionDetails(question);
    }

    /**
     * Returns the part of command string for the given {@code question}'s details.
     */
    public static String getQuestionDetails(Question question) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + question.getName().fullName + " ");
        sb.append(PREFIX_IMPORTANCE + question.getImportance().value + " ");
        question.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditQuestionDescriptor}'s details.
     */
    public static String getEditQuestionDescriptorDetails(EditQuestionDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getImportance().ifPresent(importance -> sb.append(PREFIX_IMPORTANCE).append(importance.value)
                .append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}