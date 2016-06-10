package pe.edu.utp.rendimientoestudiantil.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.CourseStudent;
import pe.edu.utp.rendimientoestudiantil.models.Evaluation;
import pe.edu.utp.rendimientoestudiantil.models.Student;

public class AddEvaluationActivity extends AppCompatActivity {

    private long idCourse;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evaluation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null){

            idCourse = extras.getLong("idCourse");

            course = Course.findById(Course.class, idCourse);
            final EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
            final EditText porcentageEditText = (EditText) findViewById(R.id.porcentageEditText);
            Button addEvaluation = (Button) findViewById(R.id.addEvaluation);

            if (addEvaluation != null) {
                addEvaluation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Evaluation evaluation;

                        evaluation = new Evaluation(nameEditText.getText().toString().trim(), Integer.parseInt( porcentageEditText.getText().toString().trim() ), course );
                        evaluation.save();

                        setResult(RESULT_OK);
                        finish();

                    }
                });
            }
            return;
        }
        finish();
    }

}
