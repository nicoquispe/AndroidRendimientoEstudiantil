package pe.edu.utp.rendimientoestudiantil.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.adapters.InstitutionAdapter;
import pe.edu.utp.rendimientoestudiantil.adapters.StudentAdapter;
import pe.edu.utp.rendimientoestudiantil.fragments.AddInstitutionDialogFragment;
import pe.edu.utp.rendimientoestudiantil.fragments.AddStudentDialogFragment;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.CourseStudent;
import pe.edu.utp.rendimientoestudiantil.models.Evaluation;
import pe.edu.utp.rendimientoestudiantil.models.Student;
import pe.edu.utp.rendimientoestudiantil.models.Teacher;

public class StudentsActivity extends BaseActivity implements AddStudentDialogFragment.AddStudentDialogListener{

    Long idCourse;
    List<Student> students;

    RecyclerView mStudentRecyclerView;
    RecyclerView.Adapter mStudentAdapter;
    RecyclerView.LayoutManager mStudentLayoutManager;
    private Course course;
    private Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        settingBackToolbar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            idCourse = extras.getLong("idCourse");
            course = Course.findById(Course.class, idCourse);



            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            assert fab != null;
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showNoticeDialog();
                    /*
                    Intent intent = new Intent(view.getContext(), AddStudentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putLong("idCourse", idCourse);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                    */
                }
            });
            teacher = Teacher.findById(Teacher.class, idTeacher);

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

        if (menu.findItem(R.id.action_register_notes) != null)
            menu.findItem(R.id.action_register_notes).setVisible(true);
        if (menu.findItem(R.id.action_select_students) != null)
            menu.findItem(R.id.action_select_students).setVisible(true);
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
        if ( id == R.id.action_select_students ){
            for (int position = 0; position < mStudentRecyclerView.getChildCount(); position++) {
                mStudentRecyclerView.getChildAt(position).findViewById(R.id.chkStudent).setVisibility(View.VISIBLE);
                //students.get(position).setCheckboxIsVisible(true);
                mStudentAdapter.notifyDataSetChanged();
            }
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
    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new AddStudentDialogFragment();
        dialog.show( getSupportFragmentManager(), "Student" );
    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Student student;


        final EditText firstName = (EditText) dialog.getDialog().findViewById(R.id.firstNameEditText);
        final EditText lastName = (EditText) dialog.getDialog().findViewById(R.id.lastNameEditText);
        final EditText hours = (EditText) dialog.getDialog().findViewById(R.id.hoursEditText);

        List<Student> _students = Student.find(Student.class, "first_name = ? and last_name = ?", firstName.getText().toString().trim(), lastName.getText().toString().trim() );
        if ( _students.size() > 0 ){
            student = _students.get(0);
        }
        else{
            student = new Student(firstName.getText().toString().trim(), lastName.getText().toString().trim() );
            student.save();
            students.add( student );

            mStudentRecyclerView.swapAdapter( new StudentAdapter( students  ), false);
        }
        CourseStudent courseStudent  = new CourseStudent( course, student, Integer.parseInt( hours.getText().toString().trim()) );
        courseStudent.save();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
