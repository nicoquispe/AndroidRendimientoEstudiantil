package pe.edu.utp.rendimientoestudiantil.models;

import com.orm.SugarRecord;

/**
 * Created by elbuenpixel on 07/06/16.
 */
public class TeacherInstitution extends SugarRecord {
    Teacher teacher;
    Institution institution;

    public TeacherInstitution(Teacher teacher, Institution institution) {
        this.teacher = teacher;
        this.institution = institution;
    }

    public TeacherInstitution() {
    }
}
