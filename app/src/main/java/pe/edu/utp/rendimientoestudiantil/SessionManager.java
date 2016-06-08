package pe.edu.utp.rendimientoestudiantil;

/**
 * Created by nico on 07/06/16.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

import pe.edu.utp.rendimientoestudiantil.activities.LoginActivity;
import pe.edu.utp.rendimientoestudiantil.models.Teacher;

public class SessionManager {
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;private static final String PREF_NAME = "RendimientoEstudiantil";

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_ID = "id";

    public static final String KEY_EMAIL = "email";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void createLoginSession(Long id, String email){

        editor.putBoolean(IS_LOGIN, true);
        editor.putLong(KEY_ID, id);
        editor.putString(KEY_EMAIL, email);

        editor.commit();
    }
    public Teacher getUserDetails(){
        Teacher teacher = new Teacher();
        teacher.setId( pref.getLong(KEY_ID, 0 ) );
        teacher.setEmail( pref.getString(KEY_EMAIL, null) );
        return teacher;
    }
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            _context.startActivity(i);
        }

    }
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
