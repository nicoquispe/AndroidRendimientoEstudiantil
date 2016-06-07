package pe.edu.utp.rendimientoestudiantil.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

import pe.edu.utp.rendimientoestudiantil.db.Db;

/**
 * Created by nico on 06/06/16.
 */
public class CousesEntity extends BaseEntity {

    private  static String DEFAULT_QUERY = "SELECT * FROM " + Db.TABLE_Course;

    public static void insertCource( Course course, int idInstitution, int teacherId) {
        ContentValues values = new ContentValues();
        values.put("name", course.getName());
        values.put("cycle",course.getCycle());
        values.put("section_number",course.getSection_number());
        values.put("turn",course.getTurn());
        values.put("teacher_id", teacherId);
        values.put("institution_id", idInstitution );
        database.insert("Course", null, values);
    }



    public static long createCourse_Student(long course_id, long student_id) {

        ContentValues values = new ContentValues();
        values.put("course_id", course_id);
        values.put("student_id", student_id);
        long id = database.insert(Db.TABLE_Student_Course, null, values);
        return id;
    }

    public ArrayList<Course> getCourses( ) {
        ArrayList<Course> courses = new ArrayList<>();
        Cursor cursor = database.rawQuery( DEFAULT_QUERY , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Course course = new Course();
            course.setId(cursor.getInt(0));
            course.setName(cursor.getString(1));
            course.setCycle( cursor.getInt(2) );
            course.setTurn(cursor.getString(3));
            course.setSection_number( cursor.getInt(4) );
            course.setInstitution( InstitutionsEntity.findInstitutionById( cursor.getInt(5) ) );
            course.setTeacher( TeachersEntity.findTeacherById( cursor .getInt(6) ) );

            courses.add(course);
            cursor.moveToNext();
        }
        cursor.close();
        return courses;
    }

    public static ArrayList<Course> findCoursesByInstitucion(int institutionId, int teacher_id) {

        ArrayList<Course> courses = new ArrayList<>();

        String selectQuery = "SELECT  tc.id, tc.name as 'course_name', tc.cycle, tc.turn, tc.section_number, tc.institution_id , tc.teacher_id  FROM " + Db.TABLE_Course + " tc, " + Db.TABLE_Teacher + " tt, " + Db.TABLE_Institution+ " ti  WHERE tc.teacher_id = tt.id and ti.id= tc.institution_id and tc.institution_id = " + institutionId + " and tc.teacher_id = "+ teacher_id;
        Cursor cursor = database.rawQuery(selectQuery , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Course course = new Course();
            course.setId( cursor.getInt(0) );
            course.setName(cursor.getString(1));
            course.setCycle( cursor.getInt(2) );
            course.setTurn(cursor.getString(3));
            course.setSection_number( cursor.getInt(4) );
            course.setInstitution( InstitutionsEntity.findInstitutionById( cursor.getInt(5) ) );
            course.setTeacher( TeachersEntity.findTeacherById( cursor .getInt(6) ) );
            courses.add(course);
            cursor.moveToNext();
        }
        cursor.close();
        return courses;
    }

    public static Course findCourseById( Integer course_id  ){

        try {
            Cursor cursor = database.rawQuery(DEFAULT_QUERY + " WHERE id = " + Integer.toString( course_id ) , null);
            if (cursor.moveToFirst()){
                Course course = new Course();
                course.setId( cursor.getInt(0) );
                course.setName(cursor.getString(1));
                course.setCycle( cursor.getInt(2) );
                course.setTurn(cursor.getString(3));
                course.setSection_number( cursor.getInt(4) );
                course.setInstitution( InstitutionsEntity.findInstitutionById( cursor.getInt(5) ) );
                course.setTeacher( TeachersEntity.findTeacherById( cursor .getInt(6) ) );
                return course;
            }
        }
        catch (SQLiteException e) {
            new SQLiteException("Error al Consultar");
        }
        catch (Exception e) {
            new Exception("Error en el metodo Consultar");
        }
        return null;
    }
}
