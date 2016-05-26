package pe.edu.utp.rendimientoestudiantil.models;

public class Course {
    protected int id;
    protected String name;
    protected Teacher teacher;
    protected Institution institution;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Course(int id, String name, Teacher teacher, Institution institution) {

        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.institution = institution;
    }

    public Course() {

    }
}
