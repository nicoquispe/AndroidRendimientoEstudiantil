package pe.edu.utp.rendimientoestudiantil.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.CousesEntity;
import pe.edu.utp.rendimientoestudiantil.models.Institution;
import pe.edu.utp.rendimientoestudiantil.models.InstitutionsEntity;
import pe.edu.utp.rendimientoestudiantil.models.Student;
import pe.edu.utp.rendimientoestudiantil.models.StudentsEntity;
import pe.edu.utp.rendimientoestudiantil.models.Teacher;
import pe.edu.utp.rendimientoestudiantil.models.TeachersEntity;
import pe.edu.utp.rendimientoestudiantil.utils.FileUtils;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    private InstitutionsEntity institutionsEntity;
    private StudentsEntity studentsEntity;
    private TeachersEntity teachersEntity;
    private CousesEntity cousesEntity;


    private static final String TABLE_Teacher = "Teacher";
    private static final String TABLE_Institution = "Institution";
    private static final String TABLE_Teacher_Institution = "Teacher_Institution";
    private static final String TABLE_Course = "Course";
    private static final String TABLE_Course_Institution = "Course_Institution";
    private static final String TABLE_Teacher_Course = "Teacher_Course";
    private static final String TABLE_Student = "Student";
    private static final String TABLE_Student_Course = "Student_Course";
    private static final int Teacher_id = 1;


    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
            this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null && database.isOpen() ) {

            this.database.close();
        }
    }


    /* INSERT */
    public void insertTeacher( Teacher teacher) {
        ContentValues values = new ContentValues();
        values.put("id", teacher.getId());
        values.put("first_name", teacher.getFirst_name());
        values.put("last_name", teacher.getLast_name());
        database.insert("Teacher", null, values);
    }


    public InstitutionsEntity getInstitutionsEntity(){
        if (institutionsEntity == null){
            institutionsEntity = new InstitutionsEntity();
            institutionsEntity.setDataBase(database);
        }
        return institutionsEntity;
    }
    public CousesEntity getCousesEntity(){
        if (cousesEntity == null){
            cousesEntity = new CousesEntity();
            cousesEntity.setDataBase(database);
        }
        return cousesEntity;
    }
    public StudentsEntity getStudentsEntity(){
        if (studentsEntity == null){
            studentsEntity = new StudentsEntity();
            studentsEntity.setDataBase(database);
        }
        return studentsEntity;
    }
    public TeachersEntity getTeachersEntity(){
        if (teachersEntity == null){
            teachersEntity = new TeachersEntity();
            teachersEntity.setDataBase(database);
        }
        return teachersEntity;
    }



    /* GET ALL */
    public ArrayList<Teacher> getTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Teacher", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Teacher teacher = new Teacher();
            teacher.setId(cursor.getInt(0));
            teacher.setFirst_name(cursor.getString(1));
            teacher.setLast_name(cursor.getString(2));
            teachers.add(teacher);
            cursor.moveToNext();
        }
        cursor.close();
        return teachers;
    }





    /* UPDATE */


    /* DELETE */

    public void deleteInstitution(Institution institution) {
        database.delete("Institution", "id = ?", new String[]{ String.valueOf( institution.getId() ) });
        database.close();
    }

    public void deleteCource(Course course) {
        database.delete("Teacher", "id = ?", new String[]{ String.valueOf( course.getId() ) });
        database.close();
    }




}
