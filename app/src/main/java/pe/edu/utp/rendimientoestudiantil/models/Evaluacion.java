package pe.edu.utp.rendimientoestudiantil.models;

public class Evaluacion {
    private int id;
    private String nombre;
    private int peso;
    private Course course;

    public Evaluacion(int id, String nombre, int peso, Course course) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

}
