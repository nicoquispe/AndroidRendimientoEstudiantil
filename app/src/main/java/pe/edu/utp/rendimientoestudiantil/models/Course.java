package pe.edu.utp.rendimientoestudiantil.models;

public class Course {
    protected int id;
    protected String name;
    protected int cycle;
    protected String turn;
    protected int section_number;
    protected Institution institution;
    protected Teacher teacher;

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

    public Course(int id, String name, int cycle, String turn, int section_number, Institution institution, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.cycle = cycle;
        this.turn = turn;
        this.section_number = section_number;
        this.institution = institution;
        this.teacher = teacher;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public int getSection_number() {
        return section_number;
    }

    public void setSection_number(int section_number) {
        this.section_number = section_number;
    }

    public Course() {

    }
}
