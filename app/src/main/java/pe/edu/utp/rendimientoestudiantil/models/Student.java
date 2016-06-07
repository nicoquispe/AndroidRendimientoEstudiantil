package pe.edu.utp.rendimientoestudiantil.models;

public class Student extends Person {
    public Student(int id, String first_name, String last_name) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Student() {
    }

    public String getFullName () {
        return this.first_name + ", " + this.last_name;
    }
}
