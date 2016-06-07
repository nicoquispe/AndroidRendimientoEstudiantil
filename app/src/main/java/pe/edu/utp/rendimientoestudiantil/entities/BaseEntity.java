package pe.edu.utp.rendimientoestudiantil.entities;

import android.database.sqlite.SQLiteDatabase;

import pe.edu.utp.rendimientoestudiantil.db.DatabaseAccess;

/**
 * Created by nico on 06/06/16.
 */
public class BaseEntity {
    public static SQLiteDatabase database;

    public void setDataBase(SQLiteDatabase database) {
        this.database = database;
    }
    public SQLiteDatabase getDatabase() {
        return database;
    }

}
