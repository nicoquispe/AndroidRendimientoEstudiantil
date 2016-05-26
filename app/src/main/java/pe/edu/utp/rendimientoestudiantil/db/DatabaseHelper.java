package pe.edu.utp.rendimientoestudiantil.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pe.edu.utp.rendimientoestudiantil.models.Course;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "rendimiento.db";
    private static final int DB_VERSION = 1;


    private static final String TABLE_Teacher = "Teacher";
    private static final String TABLE_Institution = "Institution";
    private static final String TABLE_Course = "Course";
    private static final String TABLE_Student = "Student";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_Teacher + " ( id INTEGER PRIMARY KEY, first_name TEXT, last_name TEXT );");
        db.execSQL("CREATE TABLE " + TABLE_Institution  + " ( id INTEGER PRIMARY KEY, name TEXT );");
        db.execSQL("CREATE TABLE " + TABLE_Course  + " ( id INTEGER PRIMARY KEY, name TEXT, institution INTEGER, teacher INTEGER );");
        db.execSQL("CREATE TABLE " + TABLE_Student  + " ( id INTEGER PRIMARY KEY, first_name TEXT, last_name TEXT, course INTEGER );");


        db.execSQL("insert into " + TABLE_Teacher + "(id, first_name,last_name ) values(1, 'Ubaldo', 'Lizardo Silva' )");

        db.execSQL("insert into " + TABLE_Institution + " ( id, name ) values( 1, 'UTP - UNIVERSIDAD TECNOLOGICA DEL PERU' ) " );
        db.execSQL("insert into " + TABLE_Institution + " ( id, name ) values( 2, 'PUCP - PONTIFICIA UNIVERSIDAD CATOLICA DEL PERU' ) " );
        db.execSQL("insert into " + TABLE_Institution + " ( id, name ) values( 3, 'UPC - UNIVERSIDAD PERUANA DE CIENCIAS APLICADAS' ) " );


        db.execSQL("insert into " + TABLE_Course + " ( id, name, institution, teacher) values( 1, 'ESTADISTICA INFERENCIAL', 1, 1 ) " );
        db.execSQL("insert into " + TABLE_Course + " ( id, name, institution, teacher) values( 2, 'DESARROLLO WEB INTEGRADO', 1, 1 ) " );
        db.execSQL("insert into " + TABLE_Course + " ( id, name, institution, teacher) values( 3, 'DESARROLLO DE APLICACIONES EMPRESARIALES CON ANDROID', 1, 1 ) " );
        db.execSQL("insert into " + TABLE_Course + " ( id, name, institution, teacher) values( 4, 'CURSO INTEGRADOR I: INGENIERIA DE SISTEMAS-SOFTWARE', 1, 2 ) " );
        db.execSQL("insert into " + TABLE_Course + " ( id, name, institution, teacher) values( 5, 'PROGRAMACION LOGICA Y FUNCIONAL', 1, 2 ) " );
        db.execSQL("insert into " + TABLE_Course + " ( id, name, institution, teacher) values( 6, 'PROCESOS PARA INGENIERIA', 1, 2 ) " );
        db.execSQL("insert into " + TABLE_Course + " ( id, name, institution, teacher) values( 7, 'FISICA GENERAL', 1, 2 ) " );
        db.execSQL("insert into " + TABLE_Course + " ( id, name, institution, teacher) values( 8, 'CALCULO DIFERENCIAL', 1, 3 ) " );
        db.execSQL("insert into " + TABLE_Course + " ( id, name, institution, teacher) values( 9, 'INDIVIDUO Y MEDIO AMBIENTE', 1, 3 ) " );
        db.execSQL("insert into " + TABLE_Course + " ( id, name, institution, teacher) values( 10, 'CALCULO INTEGRAL', 1, 3 ) " );

        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1412974, 'ABARCA SANTOS', 'LUIS JESUS', 1 ) "  );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1311998, 'AGUIRRE GUTIERREZ', 'ALEXIS', 1 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1220921, 'ARCE GUTIERREZ', 'YORKA YEIVIN', 1 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1421505, 'CABRERA SANCHEZ', 'JUNIOR JESUS', 1 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1331417, 'CAMPUSMANA CALDERON', 'JULIO SANTIAGO', 1 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1511398, 'CHOQUE LEAÑO', 'CESAR', 2 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1420112, 'CONDORI CARRILLO', 'ELEN CATHERINE', 2 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1412981, 'CONZA ROJAS', 'YOEL ANTONIO', 2 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1410313, 'CURAY CORNEJO', 'XIMENA DE LOM', 2 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1320317, 'CUTIPA LOPEZ', 'JULIO CESAR', 2 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1523042, 'CUZCANO ORTIZ', 'GERALD ROGELIO', 3 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1310898, 'ESPINO ALVARADO', 'PABLO CESAR', 3 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1321128, 'FUENTES GUILLEN', 'JULEYSI JAZMIN', 3 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1410454, 'GARCIA PUMA', 'EDWIN JESUS', 3 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1211397, 'GERONIMO LEON', 'YELTHSIN GHENRY', 3 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1412341, 'GOMEZ QUESQUEN', 'YURI KENNY', 4 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1410301, 'HUAYANAY RURUSH', 'RENZON', 4 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1910370, 'INGA QUISPE', 'EDWAR DANNY', 4 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1411094, 'IPANAQUE ALAYA', 'EVERT LUIS', 4 ) " );
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1220802, 'ALAMA VISITACION', 'GLORIA MARIA', 4 ) " ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1330554, 'ALVAREZ HANCCO', 'KARINA ROCIO', 5 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1310113, 'APAESTEGUI ORTEGA', 'ROGER', 5 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1011545, 'BAZAN CHACA', 'KEVIN EDGAR', 5 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1312429, 'CALISAYA APAZA', 'IVAN EMILIO', 5 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1420051, 'CALLAÑAUPA BARBOZA', 'ALBERT ROGGER', 5 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1420652, 'CASTILLO QUISPE', 'SHAROON DENISSE', 6 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1111764, 'FUENTES GARNIQUE', 'EDGARD MARTIN', 6 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1112015, 'GARCIA OCHOA', 'ELVIS LENON', 6 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1220430, 'GASPAR SANCHEZ', 'EDWAR ALEXANDER', 6 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1411881, 'GUARDIA DURAND', 'ALEX JESUS', 6 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1411069, 'GUTIERREZ TELLES', 'WALBERTH FELIPE', 7 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1330532, 'HANCO FLORES', 'BEATRIZ VANESSA', 7 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1310952, 'HUACCHO AVILA', 'MIGUEL DANTE', 7 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1910497, 'LAUREANO LAZARO', 'KEVIN PABLO', 7 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1012280, 'LEONARDO PAREDES', 'JULIO CESAR', 7 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1212600, 'MEZA AVENDAÑO', 'CAROLINA', 8 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1310258, 'MIGUEL DIAZ', 'HANS ALEJANDRO', 8 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1122039, 'MILLA TARAZONA', 'MARITZA YANINA', 8 ) "  ) ;
        db.execSQL("insert into " + TABLE_Student + " ( id, first_name, last_name, course) values( 1413294, 'MORALES SEGOVIA', 'LUCHO GREGORIO', 8 ) "  ) ;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Teacher);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Institution );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Course );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Student );

        this.onCreate(db);
    }
}
