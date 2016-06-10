package pe.edu.utp.rendimientoestudiantil.models;

import com.orm.SugarRecord;

import java.util.List;

public class Student extends SugarRecord {
    String firstName;
    String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Student(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getNote( Evaluation evaluation ){
        List<EvaluationStudent> evaluationStudents = EvaluationStudent.find(EvaluationStudent.class, "evaluation = ? and student = ?", evaluation.getId().toString(), this.getId().toString() );
        if ( evaluationStudents!= null ){
            if(evaluationStudents.size() > 0){
                EvaluationStudent evaluationStudent =  evaluationStudents.get(0);
                return evaluationStudent.note;
            }
        }
        return 0;
    }

    public void setNote( int note, Evaluation evaluation ){
        List<EvaluationStudent> evaluationStudents = EvaluationStudent.find(EvaluationStudent.class, "evaluation = ? and student = ?", evaluation.getId().toString(), this.getId().toString() );
        if ( evaluationStudents!= null ){
            if(evaluationStudents.size() > 0){
                EvaluationStudent evaluationStudent =  evaluationStudents.get(0);
                evaluationStudent.note = note;
                evaluationStudent.save();
            }
            else{
                EvaluationStudent evaluationStudent = new EvaluationStudent( evaluation, this , note );
                evaluationStudent.save();
            }
        }
        return;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return this.firstName + ", " + this.lastName;
    }
}
