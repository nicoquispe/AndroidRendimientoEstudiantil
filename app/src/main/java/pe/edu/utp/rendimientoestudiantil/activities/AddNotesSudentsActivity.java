package pe.edu.utp.rendimientoestudiantil.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.adapters.EvaluaionStudentAdapter;
import pe.edu.utp.rendimientoestudiantil.adapters.StudentAdapter;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Evaluation;
import pe.edu.utp.rendimientoestudiantil.models.Student;

public class AddNotesSudentsActivity extends AppCompatActivity {

    Long idCourse;
    Long idEvaluation;
    List<Student> students;

    RecyclerView mStudentRecyclerView;
    RecyclerView.Adapter mStudentAdapter;
    RecyclerView.LayoutManager mStudentLayoutManager;
    private Course course;
    private Evaluation evaluation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes_students);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            idCourse = extras.getLong("idCourse");
            idEvaluation = extras.getLong("idEvaluation");
            course = Course.findById(Course.class, idCourse);
            evaluation = Evaluation.findById(Evaluation.class, idEvaluation);

            students = course.findStudentsByCourse( );
            this.setTitle(course.getName());


            mStudentRecyclerView = (RecyclerView) findViewById(R.id.AddNotesStudentsRecyclerView);
            mStudentRecyclerView.setHasFixedSize(true);
            mStudentLayoutManager = new LinearLayoutManager(this);
            mStudentRecyclerView.setLayoutManager(mStudentLayoutManager);

            mStudentAdapter = new EvaluaionStudentAdapter( students, evaluation );
            mStudentRecyclerView.setAdapter(mStudentAdapter);
        }

    }

}
