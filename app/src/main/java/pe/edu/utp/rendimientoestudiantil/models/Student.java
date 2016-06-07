package pe.edu.utp.rendimientoestudiantil.models;

import com.orm.SugarRecord;

public class Student extends SugarRecord {
    String first_name;
    String last_name;
    public Student( String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Student() {
    }
    public String getFullName () {
        return this.first_name + ", " + this.last_name;
    }
}
