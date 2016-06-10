package pe.edu.utp.rendimientoestudiantil.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.adapters.StudentAdapter;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Evaluation;
import pe.edu.utp.rendimientoestudiantil.models.Student;

public class StudentsActivity extends BaseActivity {

    Long idCourse;
    List<Student> students;

    RecyclerView mStudentRecyclerView;
    RecyclerView.Adapter mStudentAdapter;
    RecyclerView.LayoutManager mStudentLayoutManager;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            idCourse = extras.getLong("idCourse");
            course = Course.findById(Course.class, idCourse);



            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), AddStudentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putLong("idCourse", idCourse);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }
            });

            students = course.findStudentsByCourse( );

            this.setTitle(course.getName());

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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (menu.findItem(R.id.action_show_compare) != null)
            menu.findItem(R.id.action_show_compare).setVisible(true);
        if (menu.findItem(R.id.action_register_notes) != null)
            menu.findItem(R.id.action_register_notes).setVisible(true);
        if (menu.findItem(R.id.action_edit_student) != null)
            menu.findItem(R.id.action_edit_student).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_register_notes) {

            Intent intent = new Intent(StudentsActivity.this, EvaluationActivity.class);
            Bundle bundle = new Bundle();
            bundle.putLong("idCourse", idCourse);
            intent.putExtras(bundle);
            startActivity( intent );
        }

        return super.onOptionsItemSelected(item);
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
        }
    }

}
