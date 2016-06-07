package pe.edu.utp.rendimientoestudiantil.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.db.DatabaseAccess;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Student;

public class AddStudentActivity extends BaseActivity {

    private int idCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        if (extras != null){
            idCourse = extras.getInt("idCourse");

            final EditText firstName = (EditText) findViewById(R.id.firstNameEditText);
            final EditText lastName = (EditText) findViewById(R.id.lastNameEditText);


            Button addInstitution = (Button) findViewById(R.id.addStudent);
            addInstitution.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databaseAccess.open();
                    Student newStudent = new Student();
                    newStudent.setFirst_name( firstName.getText().toString() );
                    newStudent.setLast_name( lastName.getText().toString() );
                    databaseAccess.getStudentsEntity().insertStudent( newStudent, idCourse );
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
