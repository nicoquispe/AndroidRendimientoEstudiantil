package pe.edu.utp.rendimientoestudiantil.models;

import com.orm.SugarRecord;

public class Student extends SugarRecord {
    String firstname;
    String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Student(String firstname, String lastname) {

        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return this.firstname + ", " + this.lastname;
    }
}
