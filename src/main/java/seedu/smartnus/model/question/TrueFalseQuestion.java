package seedu.smartnus.model.question;

import static seedu.smartnus.model.choice.Choice.FALSE_CHOICE_TITLE;
import static seedu.smartnus.model.choice.Choice.TRUE_CHOICE_TITLE;

import java.util.ArrayList;
import java.util.Set;

import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.tag.Tag;

public class TrueFalseQuestion extends Question {

    public static final String MESSAGE_ANSWER_INVALID = "Only T or F is allowed as an answer!";
    private static final int NUMBER_OF_CHOICES = 2;

    public TrueFalseQuestion(Name name, Importance importance, Set<Tag> tags,
                             Set<Choice> choices, Statistic statistic) {
        super(name, importance, tags, choices, statistic);
    }

    public TrueFalseQuestion(Name name, Importance importance, Set<Tag> tags, Set<Choice> choices) {
        super(name, importance, tags, choices);
    }

    /**
     * Returns True if {@code TrueFalseQuestion} is valid, false otherwise.A {@code TrueFalseQuestion} is
     * valid if it has two choices (True and False) and only one of them is correct.
     *
     * @return True if TrueFalseQuestion is valid, false otherwise.
     */
    @Override
    public boolean isValidQuestion() {
        int validChoices = 0;
        int correctChoices = 0;
        for (Choice choice : getChoices()) {
            if (choice.getIsCorrect()) {
                correctChoices += 1;
            }
            if (isValidTrueFalseChoice(choice)) {
                validChoices += 1;
            }
        }
        return validChoices == NUMBER_OF_CHOICES && correctChoices == 1;
    }

    public static boolean isValidTrueFalseChoice(Choice choice) {
        return choice.getTitle().equals(TRUE_CHOICE_TITLE) || choice.getTitle().equals(FALSE_CHOICE_TITLE);
    }

    @Override
    public String getQuestionAndOptions() {
        String title = this.getName().toString();
        ArrayList<Choice> choices = this.getOrderedChoices();
        String options = "\n " + "1. " + choices.get(0).getTitle() + " 2. " + choices.get(1).getTitle();
        return title + options;
    }

    @Override
    public int getQuestionType() {
        return TF_QUESTION_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TrueFalseQuestion)) {
            return false;
        }

        return super.equals(other);
    }
}