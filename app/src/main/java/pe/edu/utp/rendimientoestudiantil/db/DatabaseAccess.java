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
import pe.edu.utp.rendimientoestudiantil.models.Institution;
import pe.edu.utp.rendimientoestudiantil.models.Student;
import pe.edu.utp.rendimientoestudiantil.models.Teacher;
import pe.edu.utp.rendimientoestudiantil.utils.FileUtils;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;


    private static final String TABLE_Teacher = "Teacher";
    private static final String TABLE_Institution = "Institution";
    private static final String TABLE_Teacher_Institution = "Teacher_Institution";
    private static final String TABLE_Course = "Course";
    private static final String TABLE_Course_Institution = "Course_Institution";
    private static final String TABLE_Teacher_Course = "Teacher_Course";
    private static final String TABLE_Student = "Student";
    private static final String TABLE_Student_Course = "Student_Course";
    private static final int Teacher_id = 1;
    public static String DB_FILEPATH = "/data/data/pe.edu.utp.rendimientoestudiantil/databases/rendimiento.db";



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
        if (database != null) {
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

    public long createTeacherInstitution(SQLiteDatabase db, long teacher_id, long institution_id) {

        ContentValues values = new ContentValues();
        values.put("teacher_id", teacher_id);
        values.put("institution_id", institution_id);
        long id = db.insert(TABLE_Teacher_Institution, null, values);
        return id;
    }



    public void insertInstitution( Institution institution, int teacherId) {
        ContentValues values = new ContentValues();
        values.put("name", institution.getName());
        long institutionID = database.insert("Institution", null, values);
        createTeacherInstitution( database, teacherId, institutionID);
    }

    public long createCourse_Student(long course_id, long student_id) {

        ContentValues values = new ContentValues();
        values.put("course_id", course_id);
        values.put("student_id", student_id);
        long id = database.insert(TABLE_Student_Course, null, values);
        return id;
    }

    public void insertStudent( Student student, int course_id) {
        ContentValues values = new ContentValues();
        values.put("first_name", student.getFirst_name());
        values.put("last_name", student.getLast_name());
        long idStudent = database.insert("Student", null, values);
        createCourse_Student( course_id, idStudent );
    }

    public void insertCource( Course course, int idInstitution, int teacherId) {
        ContentValues values = new ContentValues();
        values.put("name", course.getName());
        values.put("cycle",course.getCycle());
        values.put("section_number",course.getSection_number());
        values.put("turn",course.getTurn());
        values.put("teacher_id", teacherId);
        values.put("institution_id", idInstitution );
        database.insert("Course", null, values);
    }


    /* GET BY MODEL */
    public ArrayList<Student> getStudentsByCourse(Integer courseId){
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = database.rawQuery("select ts.id, ts.first_name, ts.last_name from " + TABLE_Student +" ts, " + TABLE_Course + " tc, " + TABLE_Student_Course + " tsc where tc.id=tsc.course_id and ts.id = tsc.student_id and tc.id= " + courseId, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Student student = new Student();
            student.setId(cursor.getInt(0));
            student.setFirst_name(cursor.getString(1));
            student.setLast_name(cursor.getString(2));
            //student.setCourse( getCursoById( cursor.getInt(3) ) );
            students.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        return students;
    }
    public ArrayList<Student> getStudentsByCourse(Course course){
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Student WHERE course = " + course.getId() , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Student student = new Student();
            student.setId(cursor.getInt(0));
            student.setFirst_name(cursor.getString(1));
            student.setLast_name(cursor.getString(2));
            student.setCourse( getCursoById( cursor.getInt(3) ) );
            students.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        return students;
    }


    public boolean importDatabase(String dbPath) throws IOException {

        // Close the SQLiteOpenHelper so it will commit the created empty
        // database to internal storage.
        close();
        File newDb = new File(dbPath);
        File oldDb = new File(DB_FILEPATH);
        if (newDb.exists()) {
            FileUtils.copyFile(new FileInputStream(newDb), new FileOutputStream(oldDb));
            // Access the copied database so SQLiteHelper will cache it and mark
            // it as created.
            database.close();
            return true;
        }
        return false;
    }

    public String getProfesor(String email) {

        String[] selectionArgs = {email};
        Cursor cursor=database.query(TABLE_Teacher, null, "email = ?", selectionArgs, null, null, null);
        if( cursor.getCount() < 1 ) {
            Log.e("password", email);
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("password"));
        cursor.close();
        return password;
    }
    public ArrayList<Course> getCoursesByInstitucion(Integer institutionId) {
        //ArrayList<Course> courses = this.getCourses();
        ArrayList<Course> courses = new ArrayList<>();

        String selectQuery = "SELECT  tc.id, tc.name as 'course_name', tc.cycle, tc.turn, tc.section_number, tc.institution_id , tc.teacher_id  FROM " + TABLE_Course + " tc, " + TABLE_Teacher + " tt, " + TABLE_Institution+ " ti  WHERE tc.teacher_id = tt.id and ti.id= tc.institution_id and tc.institution_id = " + institutionId + " and tc.teacher_id = "+ Teacher_id;
        Cursor cursor = database.rawQuery(selectQuery , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Course course = new Course();
            course.setId( cursor.getInt(0) );
            course.setName(cursor.getString(1));
            course.setCycle( cursor.getInt(2) );
            course.setTurn(cursor.getString(3));
            course.setSection_number( cursor.getInt(4) );
            course.setInstitution( getInstitutionById( cursor.getInt(5) ) );
            //course.setTeacher( getTeacherById( cursor .getInt(6) ) );
            courses.add(course);
            cursor.moveToNext();
        }
        cursor.close();
        return courses;
    }

    public ArrayList<Course> getCoursesByInstitucion(Institution institution) {
        //ArrayList<Course> courses = this.getCourses();
        ArrayList<Course> courses = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Course WHERE teacher = " + institution.getId() , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Course course = new Course();
            course.setId( cursor.getInt(0) );
            course.setName(cursor.getString(1));
            course.setCycle( cursor.getInt(2) );
            course.setTurn(cursor.getString(3));
            course.setSection_number( cursor.getInt(4) );
            course.setInstitution( getInstitutionById( cursor.getInt(5) ) );
            course.setTeacher( getTeacherById( cursor .getInt(6) ) );
            courses.add(course);
            cursor.moveToNext();
        }
        cursor.close();
        return courses;
    }



    /* GET BY ID */
    public Institution getInstitutionById(int id) {
        ArrayList<Institution> institutions = this.getInstitutions();
        for (Institution institution : institutions) {
            if ( institution.getId() == id ){
                return institution;
            }
        }
        return null;
    }

    public Teacher getTeacherById(int id) {
        ArrayList<Teacher> teachers = this.getTeachers();
        for (Teacher teacher : teachers) {
            if ( teacher.getId() == id ){
                return teacher;
            }
        }
        return null;
    }

    public Course getCursoById(int id) {
        ArrayList<Course> courses = this.getCourses();
        for (Course course : courses) {
            if ( course.getId() == id ){
                return course;
            }
        }
        return null;
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

    public ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Course", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Course course = new Course();
            course.setId(cursor.getInt(0));
            course.setName(cursor.getString(1));
            course.setCycle( cursor.getInt(2) );
            course.setTurn(cursor.getString(3));
            course.setSection_number( cursor.getInt(4) );
            course.setInstitution( getInstitutionById( cursor.getInt(5) ) );
            course.setTeacher( getTeacherById( cursor .getInt(6) ) );

            courses.add(course);
            cursor.moveToNext();
        }
        cursor.close();
        return courses;
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Student", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Student student = new Student();
            student.setId(cursor.getInt(0));
            student.setFirst_name(cursor.getString(1));
            student.setLast_name(cursor.getString(2));
            student.setCourse( getCursoById( cursor.getInt(3) ) );
            students.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        return students;
    }

    public ArrayList<Institution> getInstitutions() {
        ArrayList<Institution> institutions = new ArrayList<>();



        Cursor cursor = database.rawQuery("SELECT ti.id, ti.name FROM " + TABLE_Institution + " ti, " + TABLE_Teacher + " tt, " + TABLE_Teacher_Institution + " tti where ti.id = tti.institution_id and tt.id = tti.teacher_id and tt.id = " + Teacher_id , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Institution institution = new Institution();
            institution.setId(cursor.getInt(0));
            institution.setName(cursor.getString(1));
            institutions.add(institution);
            cursor.moveToNext();
        }
        cursor.close();
        return institutions;
    }



    /* UPDATE */
    public void updateTeacher(Teacher oldTeacher, Teacher newTeacher) {
        ContentValues values = new ContentValues();

        values.put("id", newTeacher.getId());
        values.put("first_name", newTeacher.getFirst_name());
        values.put("last_name", newTeacher.getLast_name());

        database.update("Teacher", values, "id = ?", new String[]{ String.valueOf( oldTeacher.getId() ) });
    }


    /* DELETE */
    public void deleteTeacher(Teacher teacher) {
        database.delete("Teacher", "id = ?", new String[]{ String.valueOf( teacher.getId() ) });
        database.close();
    }

    public void deleteInstitution(Institution institution) {
        database.delete("Institution", "id = ?", new String[]{ String.valueOf( institution.getId() ) });
        database.close();
    }

    public void deleteCource(Course course) {
        database.delete("Teacher", "id = ?", new String[]{ String.valueOf( course.getId() ) });
        database.close();
    }
}
