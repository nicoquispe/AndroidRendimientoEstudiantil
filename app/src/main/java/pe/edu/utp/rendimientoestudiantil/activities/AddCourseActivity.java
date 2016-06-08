package pe.edu.utp.rendimientoestudiantil.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Institution;
import pe.edu.utp.rendimientoestudiantil.models.Teacher;

public class AddCourseActivity extends BaseActivity implements View.OnClickListener {

    Long idInstitution;
    Institution institution;
    Teacher teacher;
    private EditText nameEditText;
    private EditText cicloeEditText;
    private EditText seccionEditText;
    private Spinner spinner;

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

            spinner = (Spinner) findViewById(R.id.spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.turnos_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            nameEditText = (EditText) findViewById(R.id.nameEditText);
            cicloeEditText = (EditText) findViewById(R.id.cicloEditText);

            seccionEditText = (EditText) findViewById(R.id.seccionEditText);

            Button addInstitution = (Button) findViewById(R.id.addCurso);

            addInstitution.setOnClickListener( this );
        }
        else{
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        Course newCourse = new Course(
                nameEditText.getText().toString(),
                Integer.parseInt( cicloeEditText.getText().toString() ),
                spinner.getSelectedItem().toString(),
                Integer.parseInt( seccionEditText.getText().toString() ),
                institution,
                teacher );
        newCourse.save();

        setResult(RESULT_OK);
        finish();
    }
}
