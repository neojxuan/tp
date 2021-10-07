package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.question.Question;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withQuestion("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Question} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withQuestion(Question question) {
        addressBook.addQuestion(question);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
