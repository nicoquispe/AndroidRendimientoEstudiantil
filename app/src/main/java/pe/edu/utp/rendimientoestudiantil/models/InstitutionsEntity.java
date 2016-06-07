package pe.edu.utp.rendimientoestudiantil.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

import pe.edu.utp.rendimientoestudiantil.db.Db;

/**
 * Created by nico on 06/06/16.
 */
public class InstitutionsEntity extends BaseEntity {

    private  static String DEFAULT_QUERY = "SELECT * FROM " + Db.TABLE_Institution;

    public void insertInstitution( Institution institution, int teacherId) {
        ContentValues values = new ContentValues();
        values.put("name", institution.getName());
        long institutionID = database.insert(Db.TABLE_Institution, null, values);
        createTeacherInstitution( teacherId, institutionID);
    }

    public long createTeacherInstitution(long teacher_id, long institution_id) {

        ContentValues values = new ContentValues();
        values.put("teacher_id", teacher_id);
        values.put("institution_id", institution_id);
        long id = database.insert(Db.TABLE_Teacher_Institution, null, values);
        return id;
    }


    public ArrayList<Institution> findInstitutionsByTeacher( int teacher_id ) {
        ArrayList<Institution> institutions = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT ti.id, ti.name FROM " + Db.TABLE_Institution + " ti, " + Db.TABLE_Teacher + " tt, " + Db.TABLE_Teacher_Institution + " tti where ti.id = tti.institution_id and tt.id = tti.teacher_id and tt.id = " + Integer.toString( teacher_id ), null);
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

    public static Institution findInstitutionById(int institution_id){

        Cursor cursor = database.rawQuery(DEFAULT_QUERY + " WHERE id = " + Integer.toString( institution_id ) , null);
        if (cursor.moveToFirst()){
            Institution institution = new Institution();
            institution.setId( cursor.getInt(0) );
            institution.setName(cursor.getString(1));
            cursor.close();
            return institution;
        }
        cursor.close();
        return null;
    }


}
