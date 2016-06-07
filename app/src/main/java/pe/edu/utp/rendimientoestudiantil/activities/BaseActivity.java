package pe.edu.utp.rendimientoestudiantil.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import pe.edu.utp.rendimientoestudiantil.db.DatabaseAccess;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Institution;
import pe.edu.utp.rendimientoestudiantil.entities.InstitutionsEntity;
import pe.edu.utp.rendimientoestudiantil.models.Student;

/**
 * Created by nico on 30/05/16.
 */
public class BaseActivity extends AppCompatActivity {
    public int teacherId;
    private DatabaseAccess databaseAccess;
    protected InstitutionsEntity ie;

    protected ArrayList<Institution>  getInstitutions ( ){
        //databaseAccess = DatabaseAccess.getInstance( this );
        //databaseAccess.open();
        //ArrayList<Institution> instituciones = databaseAccess.getInstitutionsEntity().findInstitutionsByTeacher( teacherId );
        //Log.e("ERROR", ie.getDatabase() +"" );
        //databaseAccess.close();
        //return instituciones;
        return null;
    }
    protected void insertInstitution( Institution newInstitution ) {
        //databaseAccess = DatabaseAccess.getInstance( this );
        //databaseAccess.open();
        //databaseAccess.getInstitutionsEntity().insertInstitution( newInstitution, teacherId );
        //databaseAccess.close();

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
        if( teacherId == 0 ){
            /*
            Intent intent = new Intent( this, LoginActivity.class);
            startActivity(intent);
            finish();*/
        }
        //databaseAccess = DatabaseAccess.getInstance( this );
        teacherId = getCurrentProfesorID();
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
