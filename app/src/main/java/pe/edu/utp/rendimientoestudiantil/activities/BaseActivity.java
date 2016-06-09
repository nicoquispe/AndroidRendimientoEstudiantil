package pe.edu.utp.rendimientoestudiantil.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.utils.SessionManager;
import pe.edu.utp.rendimientoestudiantil.models.Teacher;

/**
 * Created by nico on 30/05/16.
 */
public class BaseActivity extends AppCompatActivity {
    private SessionManager session;
    public long idTeacher;
    private String emailTeacher;

    private static String TAG = "BASEACTIVITY";

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
        }
        else{
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logoutUser();
        }

        return super.onOptionsItemSelected(item);
    }
}
