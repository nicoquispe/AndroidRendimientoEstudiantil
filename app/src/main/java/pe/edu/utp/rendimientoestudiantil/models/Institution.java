package pe.edu.utp.rendimientoestudiantil.models;

/**
 * Created by nico on 25/05/16.
 */
public class Institution {
    protected int id;
    protected String name;

    public Institution(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Institution() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }
}
