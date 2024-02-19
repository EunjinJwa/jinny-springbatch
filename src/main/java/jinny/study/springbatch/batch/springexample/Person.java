package jinny.study.springbatch.batch.springexample;

public class Person {

    private String lastName;
    private String firstName;

    public Person() {
    }

    public Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "{" +
            " lastName='" + getLastName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            "}";
    }

}
