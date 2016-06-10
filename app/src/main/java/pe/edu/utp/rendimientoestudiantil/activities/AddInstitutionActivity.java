package pe.edu.utp.rendimientoestudiantil.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.models.Institution;
import pe.edu.utp.rendimientoestudiantil.models.Teacher;
import pe.edu.utp.rendimientoestudiantil.models.TeacherInstitution;

public class AddInstitutionActivity extends BaseActivity {

    private AutoCompleteTextView auto;
    private ArrayAdapter<String> arrayAdapter;
    List<TeacherInstitution> teacherInstituciones;
    Teacher teacher;
    private AutoCompleteTextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_institution);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (AutoCompleteTextView) findViewById(R.id.institutionAutoCompleteTextView);
        name.setThreshold(3);
        List<Institution> instituciones = Institution.listAll(Institution.class);

        ArrayAdapter<Institution> adapter = new ArrayAdapter<Institution>(
                this, android.R.layout.simple_dropdown_item_1line, instituciones);
        name.setAdapter(adapter);

        teacher = Teacher.findById(Teacher.class, idTeacher);

        Button addInstitution = (Button) findViewById(R.id.addInstitution);

        addInstitution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Institution> _instituciones = Institution.find(Institution.class, "name = ?", name.getText().toString().trim());
                Institution institution;
                if ( _instituciones.size() > 0 ){
                    institution = _instituciones.get(0);
                }
                else{
                    institution = new Institution(name.getText().toString().trim() );
                    institution.save();
                }
                TeacherInstitution teacherInstitution = new TeacherInstitution( teacher, institution);
                teacherInstitution.save();
                setResult(RESULT_OK);
                finish();
            }
        });

    }

}
