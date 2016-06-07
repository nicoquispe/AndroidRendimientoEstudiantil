package pe.edu.utp.rendimientoestudiantil.models;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.content.SharedPreferences;
import android.util.Log;

import pe.edu.utp.rendimientoestudiantil.db.Db;

/**
 * Created by nico on 06/06/16.
 */
public class TeachersEntity extends BaseEntity {

    private  static String DEFAULT_QUERY = "SELECT * FROM " + Db.TABLE_Teacher;

    public String getProfesor(String email) {

        String[] selectionArgs = {email};
        Cursor cursor=database.query( Db.TABLE_Teacher, null, "email = ?", selectionArgs, null, null, null);
        if( cursor.getCount() < 1 ) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("password"));
        cursor.close();
        return password;
    }

    
    public void updateTeacher(Teacher oldTeacher, Teacher newTeacher) {
        ContentValues values = new ContentValues();

        values.put("first_name", newTeacher.getFirst_name());
        values.put("last_name", newTeacher.getLast_name());

        database.update( Db.TABLE_Teacher , values, "id = ?", new String[]{ String.valueOf( oldTeacher.getId() ) });
    }

    public void deleteTeacher(int teacher_id) {
        database.delete( Db.TABLE_Teacher , "id = ?", new String[]{ String.valueOf( teacher_id ) });
        database.close();
    }

    public static Teacher findTeacherById(Integer teacher_id){

        try {
            Cursor cursor = database.rawQuery(DEFAULT_QUERY + " WHERE id = " + Integer.toString( teacher_id ) , null);
            if (cursor.moveToFirst()){
                Teacher teacher = new Teacher();
                teacher.setId(cursor.getInt(0));
                teacher.setFirst_name(cursor.getString(1));
                teacher.setLast_name(cursor.getString(2));
                return teacher;
            }
        }
        catch (SQLiteException e) {
            new SQLiteException("Error al Consultar");
        }
        catch (Exception e) {
            new Exception("Error en el metodo Consultar");
        }
        return null;
    }
}
