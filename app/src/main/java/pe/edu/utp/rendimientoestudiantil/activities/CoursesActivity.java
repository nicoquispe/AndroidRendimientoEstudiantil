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

import java.util.ArrayList;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.adapters.CourseAdapter;
import pe.edu.utp.rendimientoestudiantil.adapters.InstitutionAdapter;
import pe.edu.utp.rendimientoestudiantil.db.DatabaseAccess;
import pe.edu.utp.rendimientoestudiantil.models.Course;

public class CoursesActivity extends AppCompatActivity {
    Integer idInstitution;
    String nameInstitution;
    ArrayList<Course> courses;

    RecyclerView mCourseRecyclerView;
    RecyclerView.Adapter mCourseAdapter;
    RecyclerView.LayoutManager mCourseLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            idInstitution = extras.getInt("id");
            nameInstitution = extras.getString("name");

            this.setTitle(nameInstitution);

            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
            databaseAccess.open();
            courses = databaseAccess.getCoursesByInstitucion( idInstitution );
            databaseAccess.close();


            mCourseRecyclerView = (RecyclerView) findViewById(R.id.CoursesRecyclerView);
            mCourseRecyclerView.setHasFixedSize(true);
            mCourseLayoutManager = new LinearLayoutManager(this);
            mCourseRecyclerView.setLayoutManager(mCourseLayoutManager);
            mCourseAdapter = new CourseAdapter( courses );
            mCourseRecyclerView.setAdapter(mCourseAdapter);
        }
        else {
            finish();
        }
    }

}
