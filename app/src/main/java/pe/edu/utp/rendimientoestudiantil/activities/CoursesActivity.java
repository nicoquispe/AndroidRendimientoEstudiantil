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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.adapters.CourseAdapter;
import pe.edu.utp.rendimientoestudiantil.adapters.InstitutionAdapter;
import pe.edu.utp.rendimientoestudiantil.db.DatabaseAccess;
import pe.edu.utp.rendimientoestudiantil.models.Course;

public class CoursesActivity extends BaseActivity {
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


        Bundle extras = getIntent().getExtras();
        if (extras != null){

            idInstitution = extras.getInt("id");
            nameInstitution = extras.getString("name");

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), AddCourseActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("idInstitution", idInstitution);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }
            });

            this.setTitle(nameInstitution);

            mCourseRecyclerView = (RecyclerView) findViewById(R.id.CoursesRecyclerView);
            mCourseRecyclerView.setHasFixedSize(true);
            mCourseLayoutManager = new LinearLayoutManager(this);
            mCourseRecyclerView.setLayoutManager(mCourseLayoutManager);

            courses = getCoursesInstitutions(idInstitution);
            mCourseAdapter = new CourseAdapter( courses );
            mCourseRecyclerView.setAdapter(mCourseAdapter);
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
            Toast.makeText(this, "Curso creado", Toast.LENGTH_SHORT).show();
            courses = getCoursesInstitutions(idInstitution);
            mCourseAdapter = new CourseAdapter( courses );
            mCourseRecyclerView.setAdapter(mCourseAdapter);
        }
    }

}
