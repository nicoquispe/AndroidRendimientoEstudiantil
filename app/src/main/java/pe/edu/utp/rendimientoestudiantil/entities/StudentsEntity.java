package pe.edu.utp.rendimientoestudiantil.entities;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import pe.edu.utp.rendimientoestudiantil.db.Db;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Student;

/**
 * Created by nico on 06/06/16.
 */
public class StudentsEntity extends BaseEntity {
    private  static String DEFAULT_QUERY = "SELECT * FROM " + Db.TABLE_Student;


    public void insertStudent(Student student, int course_id) {
        ContentValues values = new ContentValues();
        //values.put("first_name", student.getFirst_name());
        //values.put("last_name", student.getLast_name());
        long idStudent = database.insert("Student", null, values);
        CousesEntity.createCourse_Student( course_id, idStudent );
    }


    public ArrayList<Student> findAll( ) {
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = database.rawQuery( DEFAULT_QUERY, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Student student = new Student();
            student.setId(cursor.getLong(0));
            //student.setFirst_name(cursor.getString(1));
            //student.setLast_name(cursor.getString(2));
            students.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        return students;
    }


    public ArrayList<Student> findStudentsByCourse( Course course){
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Student WHERE course = " + course.getId() , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Student student = new Student();
            student.setId(cursor.getLong(0));
            //student.setFirst_name(cursor.getString(1));
            //student.setLast_name(cursor.getString(2));
            students.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        return students;
    }

    public ArrayList<Student> findStudentsByCourse( Integer courseId){
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = database.rawQuery("select ts.id, ts.first_name, ts.last_name from " + Db.TABLE_Student +" ts, " + Db.TABLE_Course + " tc, " + Db.TABLE_Student_Course + " tsc where tc.id=tsc.course_id and ts.id = tsc.student_id and tc.id= " + courseId, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Student student = new Student();
            student.setId(cursor.getLong(0));
            //student.setFirst_name(cursor.getString(1));
            //student.setLast_name(cursor.getString(2));
            students.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        return students;
    }
}
