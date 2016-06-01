package pe.edu.utp.rendimientoestudiantil.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.db.DatabaseAccess;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Institution;

public class AddCourseActivity extends BaseActivity {

    private int idInstitution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        if (extras != null){

            idInstitution = extras.getInt("idInstitution");
            final Spinner spinner = (Spinner) findViewById(R.id.spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.turnos_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            final EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
            final EditText cicloeEditText = (EditText) findViewById(R.id.cicloEditText);

            final EditText seccionEditText = (EditText) findViewById(R.id.seccionEditText);
            final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

            Button addInstitution = (Button) findViewById(R.id.addCurso);
            addInstitution.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databaseAccess.open();
                    Course newCourse = new Course();
                    newCourse.setName( nameEditText.getText().toString() );
                    newCourse.setCycle( Integer.parseInt( cicloeEditText.getText().toString() ) );
                    newCourse.setSection_number( Integer.parseInt( seccionEditText.getText().toString() ) );
                    newCourse.setTurn( spinner.getSelectedItem().toString() );
                    databaseAccess.insertCource( newCourse, idInstitution, teacherId );
                    databaseAccess.close();
                    setResult(RESULT_OK);
                    finish();
                }
            });
        }
        else{
            finish();
        }
    }

}
