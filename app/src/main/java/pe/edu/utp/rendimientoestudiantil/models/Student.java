package pe.edu.utp.rendimientoestudiantil.models;

import com.orm.SugarRecord;

public class Student extends SugarRecord {
    String firstName;
    String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Student(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return this.firstName + ", " + this.lastName;
    }
}
