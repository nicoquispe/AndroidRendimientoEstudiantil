package pe.edu.utp.rendimientoestudiantil.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

import pe.edu.utp.rendimientoestudiantil.models.Course;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "rendimiento.db";
    private static final int DB_VERSION = 30;


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

    public long createTeacherInstitution(SQLiteDatabase db, long teacher_id, long institution_id) {

        ContentValues values = new ContentValues();
        values.put("teacher_id", teacher_id);
        values.put("institution_id", institution_id);
        long id = db.insert(TABLE_Teacher_Institution, null, values);
        return id;
    }
    public long createCourse_Institution(SQLiteDatabase db, long course_id, long institution_id) {

        ContentValues values = new ContentValues();
        values.put("course_id", course_id);
        values.put("institution_id", institution_id);
        long id = db.insert(TABLE_Course_Institution, null, values);
        return id;
    }
    public long createCourse_Teacher(SQLiteDatabase db, long course_id, long teacher_id) {

        ContentValues values = new ContentValues();
        values.put("course_id", course_id);
        values.put("teacher_id", teacher_id);
        long id = db.insert(TABLE_Teacher_Course, null, values);
        return id;
    }
    public long createCourse_Student(SQLiteDatabase db, long course_id, long student_id) {

        ContentValues values = new ContentValues();
        values.put("course_id", course_id);
        values.put("student_id", student_id);
        long id = db.insert(TABLE_Student_Course, null, values);
        return id;
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
        /*
        valTeacher.put("first_name", "Nicanor");
        valTeacher.put("last_name", "Quispe Cuadros");
        teachersIds[1] = db.insert(TABLE_Teacher, null, valTeacher);
        */
        String[] turns = { "Mañana", "Tarde", "Noche" };
        String[] institutions = { "UTP - UNIVERSIDAD TECNOLOGICA DEL PERU", "PUCP - PONTIFICIA UNIVERSIDAD CATOLICA DEL PERU", "UNIVERSIDAD PERUANA DE CIENCIAS APLICADAS"};

        long[] institutionsIds = new long[3];
        long[] teacherinstitutionsIds = new long[3];
        long[] coursesIds = new long[12];
        long[] studentsIds = new long[40];
        Random r = new Random();
        Integer index = 0;
        for ( String institution :institutions  ){
            ContentValues valIntitution = new ContentValues();
            valIntitution.put("name", institution);
            long idInstitution = db.insert(TABLE_Institution, null, valIntitution);
            institutionsIds[index] = idInstitution;
            teacherinstitutionsIds[index] = createTeacherInstitution(db, teachersIds[r.nextInt(teachersIds.length)], idInstitution);
            index++;
        }
        String[] courses = { "MATEMÁTICA BÁSICA I", "MATEMÁTICA BÁSICA II", "CÁLCULO DIFERENCIAL", "CÁLCULO INTEGRAL", "CÁLCULO DE ECUACIONES DIFERENCIALES", "CÁLCULO DE ECUACIONES DE MULTIPLE VARIABLES", "ESTADISTICA INFERENCIAL", "PROGRAMACION LOGICA Y FUNCIONAL", "FISICA GENERAL", "MECÁNICA", "TEORÍA DE LENGUAJES DE PROGRAMACIÓN Y MÉTODOS TRADUCCIÓN I", "TEORÍA DE LENGUAJES DE PROGRAMACIÓN Y MÉTODOS TRADUCCIÓN II" };
        index = 0;
        for ( String course :courses  ){
            ContentValues valCourse = new ContentValues();
            valCourse.put("name", course);
            valCourse.put("cycle", r.nextInt(8-1) + 1);
            valCourse.put("turn", turns[r.nextInt(turns.length)]);
            valCourse.put("section_number", r.nextInt(9-1) + 1);
            valCourse.put("teacher_id", teachersIds[r.nextInt(teachersIds.length)]);
            valCourse.put("institution_id", institutionsIds[r.nextInt(institutionsIds.length)]);

            long idCourse = db.insert(TABLE_Course, null, valCourse);
            coursesIds[index] = idCourse;
            index++;
            //createCourse_Teacher(db, idCourse, teachersIds[r.nextInt(teachersIds.length)] );
            //createCourse_Institution(db, idCourse, institutionsIds[r.nextInt(institutionsIds.length)]);
        }

        index = 0;
        String[] students = {"ABARCA SANTOS, LUIS ", "AGUIRRE , GUTIERREZ", "ARCE GUTIERREZ, YORKA ", "CABRERA SANCHEZ, JUNIOR ", "CAMPUSMANA CALDERON, JULIO ", "CHOQUE , LEAÑO", "CONDORI CARRILLO, ELEN ", "CONZA ROJAS, YOEL ", "CURAY CORNEJO, XIMENA DE ", "CUTIPA LOPEZ, JULIO ", "CUZCANO ORTIZ, GERALD ", "ESPINO ALVARADO, PABLO ", "FUENTES GUILLEN, JULEYSI ", "GARCIA PUMA, EDWIN ", "GERONIMO LEON, YELTHSIN ", "GOMEZ QUESQUEN, YURI ", "HUAYANAY , RURUSH", "INGA QUISPE, EDWAR ", "IPANAQUE ALAYA, EVERT ", "ALAMA VISITACION, GLORIA MARIA", "ALVAREZ HANCCO, KARINA ROCIO", "APAESTEGUI ORTEGA, ROGER", "BAZAN CHACA, KEVIN EDGAR", "CALISAYA APAZA, IVAN EMILIO", "CALLAÑAUPA BARBOZA, ALBERT ROGGER", "CASTILLO QUISPE, SHAROON DENISSE", "FUENTES GARNIQUE, EDGARD MARTIN", "GARCIA OCHOA, ELVIS LENON", "GASPAR SANCHEZ, EDWAR ALEXANDER", "GUARDIA DURAND, ALEX JESUS", "GUTIERREZ TELLES, WALBERTH FELIPE", "HANCO FLORES, BEATRIZ VANESSA", "HUACCHO AVILA, MIGUEL DANTE", "LAUREANO LAZARO, KEVIN PABLO", "LEONARDO PAREDES, JULIO CESAR", "MEZA AVENDAÑO, CAROLINA", "MIGUEL DIAZ, HANS ALEJANDRO", "MILLA TARAZONA, MARITZA YANINA", "MORALES SEGOVIA, LUCHO GREGORIO"};
        for ( String student :students  ){
            String[] array = student.split("\\,", -1);
            ContentValues valStudent = new ContentValues();
            valStudent.put("first_name", array[0]);
            valStudent.put("last_name", array[1]);
            long idStudent = db.insert(TABLE_Student, null, valStudent);
            studentsIds[index] = idStudent;
            index++;
            createCourse_Student(db, coursesIds[r.nextInt(coursesIds.length)], idStudent );
        }
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
