package pe.edu.utp.rendimientoestudiantil.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

import pe.edu.utp.rendimientoestudiantil.models.Course;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "_rendimiento_.db";
    private static final int DB_VERSION = 1;


    private static final String TABLE_Teacher = "Teacher";
    private static final String TABLE_Institution = "Institution";
    private static final String TABLE_Teacher_Institution = "Teacher_Institution";
    private static final String TABLE_Course = "Course";
    private static final String TABLE_Course_Institution = "Course_Institution";
    private static final String TABLE_Teacher_Course = "Teacher_Course";
    private static final String TABLE_Student = "Student";
    private static final String TABLE_Student_Course = "Student_Course";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_Teacher + " ( id INTEGER PRIMARY KEY AUTOINCREMENT, first_name TEXT, last_name TEXT, email TEXT, password TEXT );");
        db.execSQL("CREATE TABLE " + TABLE_Institution  + " ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT );");
        db.execSQL("CREATE TABLE " + TABLE_Teacher_Institution  + " ( id INTEGER PRIMARY KEY AUTOINCREMENT, teacher_id INTEGER, institution_id INTEGER );");
        db.execSQL("CREATE TABLE " + TABLE_Course  + " ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, cycle INTEGER, turn TEXT, section_number INTEGER, teacher_id INTEGER, institution_id INTEGER );");
        db.execSQL("CREATE TABLE " + TABLE_Student  + " ( id INTEGER PRIMARY KEY AUTOINCREMENT, first_name TEXT, last_name TEXT );");
        db.execSQL("CREATE TABLE " + TABLE_Student_Course  + " ( id INTEGER PRIMARY KEY AUTOINCREMENT, course_id INTEGER, student_id INTEGER );");

        ContentValues valTeacher = new ContentValues();
        valTeacher.put("first_name", "Ubaldo");
        valTeacher.put("last_name", "Lizardo Silva");
        valTeacher.put("email", "c0021@grupoutp.edu.pe");
        valTeacher.put("password", "123456");

        long[] teachersIds = new long[2];
        teachersIds[0] = db.insert(TABLE_Teacher, null, valTeacher);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Teacher );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Institution );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Teacher_Institution );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Course );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Course_Institution );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Teacher_Course );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Student );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Student_Course );

        this.onCreate(db);
    }
}
