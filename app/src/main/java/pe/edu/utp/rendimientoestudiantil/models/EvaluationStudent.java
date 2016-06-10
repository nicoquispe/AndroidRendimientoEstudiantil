package pe.edu.utp.rendimientoestudiantil.models;

import com.orm.SugarRecord;

/**
 * Created by nico on 09/06/16.
 */
public class EvaluationStudent extends SugarRecord {
    Evaluation evaluation;
    Student student;
    int note;

    public EvaluationStudent() {
    }

    public EvaluationStudent(Evaluation evaluation, Student student, int note) {
        this.evaluation = evaluation;
        this.student = student;
        this.note = note;
    }
}
