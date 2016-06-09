package pe.edu.utp.rendimientoestudiantil.models;

import com.orm.SugarRecord;

/**
 * Created by elbuenpixel on 07/06/16.
 */
public class CourseStudent extends SugarRecord {
    Course course;
    Student student;
    public CourseStudent(Course course, Student student) {
        this.course = course;
        this.student = student;
    }
    public CourseStudent() {
    }
    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }
}
