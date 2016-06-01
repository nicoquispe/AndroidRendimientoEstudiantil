package pe.edu.utp.rendimientoestudiantil.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by nico on 31/05/16.
 */
public class BaseLogin extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int teacherId;
        teacherId= getCurrentProfesorID();

        if( teacherId != 0 ){
            Intent intent = new Intent( this, MainActivity.class);
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
