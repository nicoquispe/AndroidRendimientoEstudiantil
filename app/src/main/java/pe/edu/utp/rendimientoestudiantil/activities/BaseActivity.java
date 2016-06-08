package pe.edu.utp.rendimientoestudiantil.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import pe.edu.utp.rendimientoestudiantil.SessionManager;
import pe.edu.utp.rendimientoestudiantil.db.DatabaseAccess;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Institution;
import pe.edu.utp.rendimientoestudiantil.entities.InstitutionsEntity;
import pe.edu.utp.rendimientoestudiantil.models.Student;
import pe.edu.utp.rendimientoestudiantil.models.Teacher;

/**
 * Created by nico on 30/05/16.
 */
public class BaseActivity extends AppCompatActivity {
    public int teacherId;
    private DatabaseAccess databaseAccess;
    protected InstitutionsEntity ie;
    private SessionManager session;
    public long idTeacher;
    private String emailTeacher;

    public void logoutUser (){
        session.logoutUser();
        finish();
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        if( session.isLoggedIn() ){
            Teacher teacher = session.getUserDetails();
            idTeacher = teacher.getId();
            emailTeacher = teacher.getEmail();
            Log.e("aa", idTeacher + " " +   emailTeacher);
        }
        else{
            finish();
        }
    }
}
