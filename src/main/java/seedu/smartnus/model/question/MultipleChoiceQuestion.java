package seedu.smartnus.model.question;

import java.util.ArrayList;
import java.util.Set;

import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.tag.Tag;

public class MultipleChoiceQuestion extends Question {
    public static final int NUMBER_OF_INCORRECT_CHOICES = 3;
    public static final String MESSAGE_INCORRECT_NUMBER_OF_CHOICES = "Must have only 3 incorrect options!";
    public static final String MESSAGE_DUPLICATE_CHOICES = "Options should not be duplicates!";

    public MultipleChoiceQuestion(Name name, Importance importance, Set<Tag> tags,
                                  Set<Choice> choices) {
        super(name, importance, tags, choices);
    }

    /**
     * Returns True if {@code MultipleChoiceQuestion} is valid, false otherwise.A {@code MultipleChoiceQuestion} is
     * valid if it has four choices and exactly one of the choices is correct.
     *
     * @return True if MultipleChoiceQuestion is valid, false otherwise.
     */
    @Override
    public boolean isValidQuestion() {
        int choiceCount = getChoices().size();
        int correctChoiceCount = 0;
        for (Choice choice : getChoices()) {
            if (choice.getIsCorrect()) {
                correctChoiceCount += 1;
            }
        }
        return choiceCount == (NUMBER_OF_INCORRECT_CHOICES + 1) && correctChoiceCount == 1;
    }

    @Override
    public String getQuestionAndOptions() {
        String title = this.getName().toString();
        ArrayList<Choice> choices = this.getOrderedChoices();
        String options = "\na. " + choices.get(0).getTitle()
                + "  b. " + choices.get(1).getTitle()
                + "  c. " + choices.get(2).getTitle()
                + "  d. " + choices.get(3).getTitle();
        return title + options;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MultipleChoiceQuestion)) {
            return false;
        }

        return super.equals(other);
    }
}