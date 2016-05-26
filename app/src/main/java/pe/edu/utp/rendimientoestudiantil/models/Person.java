package pe.edu.utp.rendimientoestudiantil.models;

/**
 * Created by nico on 23/05/16.
 */
public class Person {
    protected int id;
    protected String first_name;
    protected String last_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
