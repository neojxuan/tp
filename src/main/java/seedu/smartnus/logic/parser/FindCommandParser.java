package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.smartnus.logic.commands.FindCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.predicates.HasImportancePredicate;
import seedu.smartnus.model.question.predicates.NameContainsKeywordsPredicate;
import seedu.smartnus.model.question.predicates.TagsContainKeywordsPredicate;
import seedu.smartnus.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * or if user does not provide any parameters to search by
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_IMPORTANCE, PREFIX_TAG);

        ArrayList<Predicate<Question>> predicates = new ArrayList<>();

        setNamePredicate(argMultimap, predicates);
        setImportancePredicate(argMultimap, predicates);
        setTagsPredicate(argMultimap, predicates);

        if (predicates.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_NO_FIELDS_SPECIFIED));
        }

        return new FindCommand(predicates);
    }

    private void setTagsPredicate(ArgumentMultimap argMultimap, ArrayList<Predicate<Question>> predicates)
            throws ParseException {
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        if (!tagList.isEmpty()) {
            List<String> tagKeywords = new ArrayList<>();
            tagList.stream().forEach(tag -> tagKeywords.add(tag.getTagName()));
            predicates.add(new TagsContainKeywordsPredicate(tagKeywords));
        }
    }

    private void setImportancePredicate(ArgumentMultimap argMultimap, ArrayList<Predicate<Question>> predicates)
            throws ParseException {
        Optional<String> importanceKeyword = argMultimap.getValue(PREFIX_IMPORTANCE);
        if (importanceKeyword.isPresent()) {
            Importance importance = ParserUtil.parseImportance(importanceKeyword.get());
            predicates.add(new HasImportancePredicate(importance));
        }
    }

    private void setNamePredicate(ArgumentMultimap argMultimap, ArrayList<Predicate<Question>> predicates) {
        String nameInput = argMultimap.getPreamble();
        if (!nameInput.isBlank()) {
            List<String> parsedNameKeywords = Stream.of(nameInput.trim().split("\\s+"))
                    .filter(keyword -> !keyword.isEmpty()).collect(Collectors.toList());
            predicates.add(new NameContainsKeywordsPredicate(parsedNameKeywords));
        }
    }

}
