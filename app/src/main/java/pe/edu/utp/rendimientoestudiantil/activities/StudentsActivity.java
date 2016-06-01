package pe.edu.utp.rendimientoestudiantil.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.adapters.CourseAdapter;
import pe.edu.utp.rendimientoestudiantil.adapters.StudentAdapter;
import pe.edu.utp.rendimientoestudiantil.db.DatabaseAccess;
import pe.edu.utp.rendimientoestudiantil.models.Student;

public class StudentsActivity extends BaseActivity {

    Integer idCourse;
    String nameCourse;
    ArrayList<Student> students;

    RecyclerView mStudentRecyclerView;
    RecyclerView.Adapter mStudentAdapter;
    RecyclerView.LayoutManager mStudentLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            idCourse = extras.getInt("id");
            nameCourse = extras.getString("name");


            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), AddStudentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("idCourse", idCourse);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }
            });

            this.setTitle(nameCourse);


            mStudentRecyclerView = (RecyclerView) findViewById(R.id.StudentsRecyclerView);
            mStudentRecyclerView.setHasFixedSize(true);
            mStudentLayoutManager = new LinearLayoutManager(this);
            mStudentRecyclerView.setLayoutManager(mStudentLayoutManager);

            students = getStudents( idCourse );
            mStudentAdapter = new StudentAdapter( students );
            mStudentRecyclerView.setAdapter(mStudentAdapter);
        }
        else {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            // Si es así mostramos mensaje de cancelado por pantalla.
            Toast.makeText(this, "Registro cancelado", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(this, "Alumno asignado al curso", Toast.LENGTH_SHORT).show();
            students = getStudents( idCourse );
            mStudentAdapter = new StudentAdapter( students );
            mStudentRecyclerView.setAdapter(mStudentAdapter);
        }
    }

}
