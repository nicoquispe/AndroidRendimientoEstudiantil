package pe.edu.utp.rendimientoestudiantil.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.adapters.CourseAdapter;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Institution;

public class CoursesActivity extends BaseActivity {
    Long idInstitution;
    String nameInstitution;
    Institution institution;

    List<Course> courses;

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

            idInstitution = extras.getLong("id");
            institution = Institution.findById(Institution.class, idInstitution);

            courses = Course.find(Course.class, "institution = ?", institution.getId().toString() );

            nameInstitution = extras.getString("name");

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), AddCourseActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putLong("idInstitution", institution.getId());
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }
            });

            this.setTitle(nameInstitution);

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            // Si es así mostramos mensaje de cancelado por pantalla.
            Toast.makeText(this, "Registro cancelado", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(this, "Curso creado", Toast.LENGTH_SHORT).show();
        }
    }

}
