package pe.edu.utp.rendimientoestudiantil.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.adapters.InstitutionAdapter;
import pe.edu.utp.rendimientoestudiantil.db.DatabaseAccess;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Institution;
import pe.edu.utp.rendimientoestudiantil.models.Student;
import pe.edu.utp.rendimientoestudiantil.models.Teacher;

public class MainActivity extends AppCompatActivity {

    ArrayList<Course> courses;
    ArrayList<Institution> instituciones;
    ArrayList<Student> students;


    RecyclerView mInstitutionRecyclerView;
    RecyclerView.Adapter mInstitutionAdapter;
    RecyclerView.LayoutManager mInstitutionLayoutManager;

    private void inicializeData(  ){
        Teacher teacher;
        teacher = new Teacher( 1, "Ubaldo", "Lizardo Silva" );
        courses = new ArrayList<>();
        instituciones= new ArrayList<>();
        students= new ArrayList<>();

        instituciones.add( new Institution( 1, "UTP - UNIVERSIDAD TECNOLOGICA DEL PERU" ) );

        courses.add( new Course( 10, "CALCULO INTEGRAL", teacher, instituciones.get(2) ) );

        students.add( new Student( 1412974, "ABARCA SANTOS", "LUIS JESUS", courses.get(0) ) );

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


        mInstitutionRecyclerView = (RecyclerView) findViewById(R.id.InstitutionsRecyclerView);
        mInstitutionRecyclerView.setHasFixedSize(true);
        mInstitutionLayoutManager = new LinearLayoutManager(this);
        mInstitutionRecyclerView.setLayoutManager(mInstitutionLayoutManager);
        mInstitutionAdapter = new InstitutionAdapter( instituciones );
        mInstitutionRecyclerView.setAdapter(mInstitutionAdapter);

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
