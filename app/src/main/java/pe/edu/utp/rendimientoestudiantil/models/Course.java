package pe.edu.utp.rendimientoestudiantil.models;

import android.support.annotation.NonNull;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class Course extends SugarRecord {
    String name;
    int cycle;
    String turn;
    int section_number;
    Institution institution;
    Teacher teacher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Course(String name, int cycle, String turn, int section_number, Institution institution,  Teacher teacher) {
        this.name = name;
        this.cycle = cycle;
        this.turn = turn;
        this.section_number = section_number;
        this.institution = institution;
        this.teacher = teacher;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public List<Evaluation> findEvaluationsByCourse(){
        return Evaluation.find(Evaluation.class, "course = ?", this.getId().toString());
        /*
        List<Evaluation> evaluations = new ArrayList<>();
        if ( courseEvaluations.size()>0 ){
            for ( CourseEvaluation courseEvaluation : courseEvaluations ) {
                evaluations.add( courseEvaluation.getEvaluation() );
            }
        }
        return evaluations;
        */
    }

    public List<Student> findStudentsByCourse(){
        List<CourseStudent> courseStudents = CourseStudent.find(CourseStudent.class, "course = ?", this.getId().toString());
        List<Student> students = new ArrayList<>();
        if ( courseStudents.size()>0 ){
            for ( CourseStudent courseStudent : courseStudents ) {
                students.add( courseStudent.getStudent() );
            }
        }
        return students;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public int getSection_number() {
        return section_number;
    }

    public void setSection_number(int section_number) {
        this.section_number = section_number;
    }

    public Course() {

    }

    @Override
    public String toString() {
        return name;
    }
}
