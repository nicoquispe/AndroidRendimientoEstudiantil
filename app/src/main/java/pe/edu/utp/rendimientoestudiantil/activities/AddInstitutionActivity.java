package pe.edu.utp.rendimientoestudiantil.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.db.DatabaseAccess;
import pe.edu.utp.rendimientoestudiantil.models.Institution;
import pe.edu.utp.rendimientoestudiantil.models.Teacher;
import pe.edu.utp.rendimientoestudiantil.models.TeacherInstitution;

public class AddInstitutionActivity extends BaseActivity {

    private AutoCompleteTextView auto;
    private ArrayAdapter<String> arrayAdapter;
    List<TeacherInstitution> teacherInstituciones;
    Teacher teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_institution);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText nameEditText = (EditText) findViewById(R.id.nameEditText);


        teacher = Teacher.findById(Teacher.class, 1);

        teacherInstituciones = teacher.getInstitutions();
        Log.e("Eo", teacherInstituciones.toString());



        Button addInstitution = (Button) findViewById(R.id.addInstitution);

        addInstitution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Institution institution = new Institution(nameEditText.getText().toString() );
                institution.save();

                TeacherInstitution teacherInstitution = new TeacherInstitution( teacher, institution);
                teacherInstitution.save();
                setResult(RESULT_OK);
                finish();
            }
        });

    }

}
