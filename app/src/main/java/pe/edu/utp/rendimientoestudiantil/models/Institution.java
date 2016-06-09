package pe.edu.utp.rendimientoestudiantil.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by nico on 25/05/16.
 */

public class Institution extends SugarRecord {
    @Unique
    String name;

    public Institution( String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Institution() {

    }

    @Override
    public String toString() {
        return name;
    }
}
