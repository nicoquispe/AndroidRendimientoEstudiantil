package pe.edu.utp.rendimientoestudiantil.models;

import com.orm.SugarRecord;

/**
 * Created by elbuenpixel on 07/06/16.
 */
public class CourseStudent extends SugarRecord {
    Course course;
    Student student;
    int hours;
    public CourseStudent(Course course, Student student, int hours) {
        this.course = course;
        this.student = student;
        this.hours = hours;
    }
    public CourseStudent() {
    }

    public int getHours() {
        return hours;
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }
}
