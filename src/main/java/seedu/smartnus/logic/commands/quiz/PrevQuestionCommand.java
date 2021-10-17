package seedu.smartnus.logic.commands.quiz;

import static java.util.Objects.requireNonNull;

import seedu.smartnus.logic.commands.Command;
import seedu.smartnus.logic.commands.CommandResult;
import seedu.smartnus.logic.commands.ExitCommand;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.model.quiz.exceptions.QuizOutOfBoundException;


public class PrevQuestionCommand extends Command {
    public static final String COMMAND_WORD = "prev";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Quizzes the previous question, takes no arguments";
    public static final String MESSAGE_SUCCESS = "Got Previous Quiz!";
    public static final String MESSAGE_START_OF_QUIZ = "You have reached the beginning of the quiz, enter '"
            + ExitCommand.COMMAND_WORD + "' to exit the quiz.";

    private final QuizManager quizManager;

    public PrevQuestionCommand(QuizManager quizManager) {
        this.quizManager = quizManager;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        try {
            quizManager.prevQuestion();
        } catch (QuizOutOfBoundException e) {
            return new CommandResult(MESSAGE_START_OF_QUIZ);
        }

        // TODO: Change the displayed message once UI is done.
        return new CommandResult(quizManager.currQuestion().getQuestionAndOptions());
    }

    @Override
    public boolean equals(Object other) {
        // TODO: In future, check if the attributes (if any) for the QuizCommand are the same
        return other == this // short circuit if same object
                || other instanceof PrevQuestionCommand; // instanceof handles nulls
    }
}