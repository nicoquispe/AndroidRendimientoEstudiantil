package pe.edu.utp.rendimientoestudiantil.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.adapters.CourseAdapter;
import pe.edu.utp.rendimientoestudiantil.adapters.InstitutionAdapter;
import pe.edu.utp.rendimientoestudiantil.fragments.AddCourseDiaglogFragment;
import pe.edu.utp.rendimientoestudiantil.fragments.AddInstitutionDialogFragment;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Institution;
import pe.edu.utp.rendimientoestudiantil.models.Teacher;

public class CoursesActivity extends BaseActivity implements AddCourseDiaglogFragment.AddCourseDialogListener {
    Long idInstitution;
    Institution institution;

    List<Course> courses;

    RecyclerView mCourseRecyclerView;
    RecyclerView.Adapter mCourseAdapter;
    RecyclerView.LayoutManager mCourseLayoutManager;
    private Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        settingBackToolbar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null){

            idInstitution = extras.getLong("idInstitution");
            institution = Institution.findById(Institution.class, idInstitution);

            courses = Course.find(Course.class, "institution = ?", institution.getId().toString() );

            teacher = Teacher.findById(Teacher.class, idTeacher);
            FloatingActionButton addCourse = (FloatingActionButton) findViewById(R.id.addCourse);
            assert addCourse != null;
            addCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    showNoticeDialog();
                    /*
                    Intent intent = new Intent(view.getContext(), AddCourseActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putLong("idInstitution", institution.getId());
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                    */
                }
            });

            this.setTitle(institution.getName());

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

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new AddCourseDiaglogFragment();
        dialog.show( getSupportFragmentManager() , "Course");
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

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

        EditText cicloeEditText;
        EditText seccionEditText;
        Spinner spinner;
        AutoCompleteTextView name;
        name = (AutoCompleteTextView) dialog.getDialog().findViewById( R.id.courseAutoCompleteTextView );

        spinner = (Spinner) dialog.getDialog().findViewById(R.id.spinner);
        cicloeEditText = (EditText) dialog.getDialog().findViewById(R.id.cicloEditText);
        seccionEditText = (EditText) dialog.getDialog().findViewById(R.id.seccionEditText);

        Course newCourse = new Course(
                name.getText().toString(),
                Integer.parseInt( cicloeEditText.getText().toString() ),
                spinner.getSelectedItem().toString(),
                Integer.parseInt( seccionEditText.getText().toString() ),
                institution,
                teacher );
        newCourse.save();
        courses.add(newCourse);
        mCourseRecyclerView.swapAdapter( new CourseAdapter( courses  ), false);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
