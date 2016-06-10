package pe.edu.utp.rendimientoestudiantil.models;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nico on 09/06/16.
 */
public class Evaluation extends SugarRecord {
    String name;
    int porcentage;
    Course course;

    public Evaluation() {
    }

    public Evaluation(String name, int porcentage, Course course) {
        this.name = name;
        this.porcentage = porcentage;
        this.course = course;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
