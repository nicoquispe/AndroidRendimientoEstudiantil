package pe.edu.utp.rendimientoestudiantil.models;

public class Student extends Person {
    protected Course course;

    public Student(int id, String first_name, String last_name, Course course) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.course = course;
    }

    public Student() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getFullName () {
        return this.first_name + ", " + this.last_name;
    }
}
