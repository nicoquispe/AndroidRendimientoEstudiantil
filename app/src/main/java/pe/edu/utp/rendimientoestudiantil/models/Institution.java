package pe.edu.utp.rendimientoestudiantil.models;

import com.orm.SugarRecord;

/**
 * Created by nico on 25/05/16.
 */

public class Institution extends SugarRecord {
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
}
