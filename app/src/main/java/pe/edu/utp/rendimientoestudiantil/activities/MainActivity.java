package pe.edu.utp.rendimientoestudiantil.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.db.DatabaseAccess;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Institution;
import pe.edu.utp.rendimientoestudiantil.models.Student;
import pe.edu.utp.rendimientoestudiantil.models.Teacher;

public class MainActivity extends AppCompatActivity {

    ArrayList<Course> courses;
    ArrayList<Institution> instituciones;
    ArrayList<Student> students;

    private void inicializeData(  ){
        Teacher teacher;
        teacher = new Teacher( 1, "Ubaldo", "Lizardo Silva" );
        courses = new ArrayList<>();
        instituciones= new ArrayList<>();
        students= new ArrayList<>();

        instituciones.add( new Institution( 1, "UTP - UNIVERSIDAD TECNOLOGICA DEL PERU" ) );
        instituciones.add( new Institution( 2, "PUCP - PONTIFICIA UNIVERSIDAD CATOLICA DEL PERU" ) );
        instituciones.add( new Institution( 3, "UPC - UNIVERSIDAD PERUANA DE CIENCIAS APLICADAS" ) );

        courses.add( new Course( 1, "ESTADISTICA INFERENCIAL", teacher, instituciones.get(0) ) );
        courses.add( new Course( 2, "DESARROLLO WEB INTEGRADO", teacher, instituciones.get(0) ) );
        courses.add( new Course( 3, "DESARROLLO DE APLICACIONES EMPRESARIALES CON ANDROID", teacher, instituciones.get(0) ) );
        courses.add( new Course( 4, "CURSO INTEGRADOR I: INGENIERIA DE SISTEMAS-SOFTWARE", teacher, instituciones.get(1) ) );
        courses.add( new Course( 5, "PROGRAMACION LOGICA Y FUNCIONAL", teacher, instituciones.get(1) ) );
        courses.add( new Course( 6, "PROCESOS PARA INGENIERIA", teacher, instituciones.get(1) ) );
        courses.add( new Course( 7, "FISICA GENERAL", teacher, instituciones.get(1) ) );
        courses.add( new Course( 8, "CALCULO DIFERENCIAL", teacher, instituciones.get(2) ) );
        courses.add( new Course( 9, "INDIVIDUO Y MEDIO AMBIENTE", teacher, instituciones.get(2) ) );
        courses.add( new Course( 10, "CALCULO INTEGRAL", teacher, instituciones.get(2) ) );

        students.add( new Student( 1412974, "ABARCA SANTOS", "LUIS JESUS", courses.get(0) ) );
        students.add( new Student( 1311998, "AGUIRRE GUTIERREZ", "ALEXIS", courses.get(0)) );
        students.add( new Student( 1220921, "ARCE GUTIERREZ", "YORKA YEIVIN", courses.get(0)) );
        students.add( new Student( 1421505, "CABRERA SANCHEZ", "JUNIOR JESUS", courses.get(0)) );
        students.add( new Student( 1331417, "CAMPUSMANA CALDERON", "JULIO SANTIAGO", courses.get(0)) );
        students.add( new Student( 1511398, "CHOQUE LEAÑO", "CESAR", courses.get(1)) );
        students.add( new Student( 1420112, "CONDORI CARRILLO", "ELEN CATHERINE", courses.get(1)) );
        students.add( new Student( 1412981, "CONZA ROJAS", "YOEL ANTONIO", courses.get(1)) );
        students.add( new Student( 1410313, "CURAY CORNEJO", "XIMENA DE LOS MILAGROS", courses.get(1)) );
        students.add( new Student( 1320317, "CUTIPA LOPEZ", "JULIO CESAR", courses.get(1)) );
        students.add( new Student( 1523042, "CUZCANO ORTIZ", "GERALD ROGELIO", courses.get(2)) );
        students.add( new Student( 1310898, "ESPINO ALVARADO", "PABLO CESAR", courses.get(2)) );
        students.add( new Student( 1321128, "FUENTES GUILLEN", "JULEYSI JAZMIN", courses.get(2)) );
        students.add( new Student( 1410454, "GARCIA PUMA", "EDWIN JESUS", courses.get(2)) );
        students.add( new Student( 1211397, "GERONIMO LEON", "YELTHSIN GHENRY", courses.get(2)) );
        students.add( new Student( 1412341, "GOMEZ QUESQUEN", "YURI KENNY", courses.get(3)) );
        students.add( new Student( 1410301, "HUAYANAY RURUSH", "RENZON",  courses.get(3)) );
        students.add( new Student( 1910370, "INGA QUISPE", "EDWAR DANNY", courses.get(3)) );
        students.add( new Student( 1411094, "IPANAQUE ALAYA", "EVERT LUIS", courses.get(3)) );
        students.add( new Student( 1220802, "ALAMA VISITACION", "GLORIA MARIA DE LOS MILAGROS", courses.get(3) ) ) ;
        students.add( new Student( 1330554, "ALVAREZ HANCCO", "KARINA ROCIO", courses.get(4) ) ) ;
        students.add( new Student( 1310113, "APAESTEGUI ORTEGA", "ROGER", courses.get(4) ) ) ;
        students.add( new Student( 1011545, "BAZAN CHACA", "KEVIN EDGAR", courses.get(4) ) ) ;
        students.add( new Student( 1312429, "CALISAYA APAZA", "IVAN EMILIO", courses.get(4) ) ) ;
        students.add( new Student( 1420051, "CALLAÑAUPA BARBOZA", "ALBERT ROGGER", courses.get(4) ) ) ;
        students.add( new Student( 1420652, "CASTILLO QUISPE", "SHAROON DENISSE", courses.get(5) ) ) ;
        students.add( new Student( 1111764, "FUENTES GARNIQUE", "EDGARD MARTIN", courses.get(5) ) ) ;
        students.add( new Student( 1112015, "GARCIA OCHOA", "ELVIS LENON", courses.get(5) ) ) ;
        students.add( new Student( 1220430, "GASPAR SANCHEZ", "EDWAR ALEXANDER", courses.get(5) ) ) ;
        students.add( new Student( 1411881, "GUARDIA DURAND", "ALEX JESUS", courses.get(5) ) ) ;
        students.add( new Student( 1411069, "GUTIERREZ TELLES", "WALBERTH FELIPE", courses.get(6) ) ) ;
        students.add( new Student( 1330532, "HANCO FLORES", "BEATRIZ VANESSA", courses.get(6) ) ) ;
        students.add( new Student( 1310952, "HUACCHO AVILA", "MIGUEL DANTE", courses.get(6) ) ) ;
        students.add( new Student( 1910497, "LAUREANO LAZARO", "KEVIN PABLO", courses.get(6) ) ) ;
        students.add( new Student( 1012280, "LEONARDO PAREDES", "JULIO CESAR", courses.get(6) ) ) ;
        students.add( new Student( 1212600, "MEZA AVENDAÑO", "CAROLINA", courses.get(7) ) ) ;
        students.add( new Student( 1310258, "MIGUEL DIAZ", "HANS ALEJANDRO", courses.get(7) ) ) ;
        students.add( new Student( 1122039, "MILLA TARAZONA", "MARITZA YANINA", courses.get(7) ) ) ;
        students.add( new Student( 1413294, "MORALES SEGOVIA", "LUCHO GREGORIO", courses.get(7) ) ) ;



        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        databaseAccess.insertTeacher( teacher );

        for ( Institution institution : instituciones ) {
            databaseAccess.insertInstitution( institution );
        }
        for ( Course course: courses ) {
            databaseAccess.insertCource( course );
        }
        for ( Student student: students ) {
            databaseAccess.insertStudent( student );
        }
        instituciones = databaseAccess.getInstitutions();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        instituciones = databaseAccess.getInstitutions();

        databaseAccess.close();

        /*
        if ( instituciones.size() == 0 ){
            this.inicializeData();
            instituciones = databaseAccess.getInstitutions();
        }
        */

        for ( Institution institution : instituciones ) {
            Log.d( "DATABASE", institution.getName() );
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
