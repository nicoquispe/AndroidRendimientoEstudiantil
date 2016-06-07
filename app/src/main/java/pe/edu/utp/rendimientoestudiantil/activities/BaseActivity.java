package pe.edu.utp.rendimientoestudiantil.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import pe.edu.utp.rendimientoestudiantil.adapters.InstitutionAdapter;
import pe.edu.utp.rendimientoestudiantil.db.DatabaseAccess;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Institution;
import pe.edu.utp.rendimientoestudiantil.models.Student;
import pe.edu.utp.rendimientoestudiantil.models.Teacher;

/**
 * Created by nico on 30/05/16.
 */
public class BaseActivity extends AppCompatActivity {
    public int teacherId;
    public DatabaseAccess databaseAccess;

    ArrayList<Institution>  getInstitutions ( ){
        databaseAccess = DatabaseAccess.getInstance( this );
        databaseAccess.open();
        ArrayList<Institution> instituciones = databaseAccess.getInstitutionsEntity().findInstitutionsByTeacher( teacherId );
        databaseAccess.close();
        return instituciones;
    }
    ArrayList<Course>  getCoursesByInstitutionId ( int idInstitution ){
        databaseAccess = DatabaseAccess.getInstance( this );
        databaseAccess.open();
        ArrayList<Course> courses = databaseAccess.getCousesEntity().findCoursesByInstitucion( idInstitution, teacherId );
        databaseAccess.close();
        return courses;
    }

    ArrayList<Student>  getStudents (int idCourse ){
        databaseAccess = DatabaseAccess.getInstance( this );
        databaseAccess.open();
        ArrayList<Student> students = databaseAccess.getStudentsEntity().findStudentsByCourse( idCourse );
        databaseAccess.close();
        return students;
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseAccess = DatabaseAccess.getInstance( this );
        teacherId= getCurrentProfesorID();
        if( teacherId == 0 ){
            Intent intent = new Intent( this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public int getCurrentProfesorID (){
        SharedPreferences prefs = getSharedPreferences("RendimientoEstudiantil", Context.MODE_PRIVATE);
        int teacherId = prefs.getInt("teacherId", 0);
        return teacherId;
    }
    public void setCurrentProfesorID ( int teacherId ){
        SharedPreferences prefs = getSharedPreferences("RendimientoEstudiantil", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("teacherId", teacherId);
        editor.commit();
    }

}
