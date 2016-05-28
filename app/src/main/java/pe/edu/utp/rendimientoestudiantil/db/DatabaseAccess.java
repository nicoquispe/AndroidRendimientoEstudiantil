package pe.edu.utp.rendimientoestudiantil.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Institution;
import pe.edu.utp.rendimientoestudiantil.models.Student;
import pe.edu.utp.rendimientoestudiantil.models.Teacher;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

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

    public void insertInstitution( Institution institution) {
        ContentValues values = new ContentValues();
        values.put("id", institution.getId());
        values.put("name", institution.getName());
        database.insert("Institution", null, values);
    }

    public void insertStudent( Student student) {
        ContentValues values = new ContentValues();
        values.put("id", student.getId());
        values.put("first_name", student.getFirst_name());
        values.put("last_name", student.getLast_name());
        values.put("course", student.getCourse().getId() );
        database.insert("Student", null, values);
    }

    public void insertCource( Course course) {
        ContentValues values = new ContentValues();
        values.put("id", course.getId());
        values.put("name", course.getName());
        values.put("institution", course.getInstitution().getId());
        values.put("teacher", course.getTeacher().getId() );
        database.insert("Course", null, values);
    }


    /* GET BY MODEL */
    public ArrayList<Student> getStudentsByCourse(Integer courseId){
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Student WHERE course = " + courseId , null);
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
    public ArrayList<Course> getCoursesByInstitucion(Integer institutionId) {
        //ArrayList<Course> courses = this.getCourses();
        ArrayList<Course> courses = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Course WHERE teacher = " + institutionId , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Course course = new Course();
            course.setId( cursor.getInt(0) );
            course.setName( cursor.getString(1) );
            course.setInstitution( getInstitutionById( cursor.getInt(2) ) );
            course.setTeacher( getTeacherById( cursor .getInt(3) ) );
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
            course.setName( cursor.getString(1) );
            course.setInstitution( getInstitutionById( cursor.getInt(2) ) );
            course.setTeacher( getTeacherById( cursor .getInt(3) ) );
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
            course.setInstitution( getInstitutionById( cursor.getInt(2) ) );
            course.setTeacher( getTeacherById( cursor .getInt(3) ) );
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
        Cursor cursor = database.rawQuery("SELECT * FROM Institution", null);
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
