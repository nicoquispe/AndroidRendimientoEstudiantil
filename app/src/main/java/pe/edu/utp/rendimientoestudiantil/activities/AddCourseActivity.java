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
import pe.edu.utp.rendimientoestudiantil.models.Teacher;

public class AddCourseActivity extends BaseActivity {

    Long idInstitution;
    Institution institution;
    Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        if (extras != null){

            idInstitution = extras.getLong("idInstitution");
            institution = Institution.findById(Institution.class, idInstitution);

            teacher = Teacher.findById(Teacher.class, 1);

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
                    Course newCourse = new Course(nameEditText.getText().toString(), Integer.parseInt( cicloeEditText.getText().toString() ), spinner.getSelectedItem().toString(), Integer.parseInt( seccionEditText.getText().toString() ) , institution, teacher );
                    newCourse.save();


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
