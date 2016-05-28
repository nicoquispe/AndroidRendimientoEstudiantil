package pe.edu.utp.rendimientoestudiantil.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.adapters.StudentAdapter;
import pe.edu.utp.rendimientoestudiantil.db.DatabaseAccess;
import pe.edu.utp.rendimientoestudiantil.models.Student;

public class StudentsActivity extends AppCompatActivity {

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });Bundle extras = getIntent().getExtras();
        if (extras != null){
            idCourse = extras.getInt("id");
            nameCourse = extras.getString("name");

            this.setTitle(nameCourse);

            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
            databaseAccess.open();
            students = databaseAccess.getStudentsByCourse( idCourse );
            databaseAccess.close();


            mStudentRecyclerView = (RecyclerView) findViewById(R.id.StudentsRecyclerView);
            mStudentRecyclerView.setHasFixedSize(true);
            mStudentLayoutManager = new LinearLayoutManager(this);
            mStudentRecyclerView.setLayoutManager(mStudentLayoutManager);
            mStudentAdapter = new StudentAdapter( students );
            mStudentRecyclerView.setAdapter(mStudentAdapter);
        }
        else {
            finish();
        }
    }

}
