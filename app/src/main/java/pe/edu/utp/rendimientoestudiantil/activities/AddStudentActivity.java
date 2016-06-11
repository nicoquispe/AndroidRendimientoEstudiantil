package pe.edu.utp.rendimientoestudiantil.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.CourseStudent;
import pe.edu.utp.rendimientoestudiantil.models.Student;

public class AddStudentActivity extends BaseActivity {

    private Long idCourse;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        if (extras != null){
            idCourse = extras.getLong("idCourse");

            course = Course.findById(Course.class, idCourse);

            final EditText firstName = (EditText) findViewById(R.id.firstNameEditText);
            final EditText lastName = (EditText) findViewById(R.id.lastNameEditText);
            final EditText hours = (EditText) findViewById(R.id.hoursEditText);
            Button addInstitution = (Button) findViewById(R.id.addStudent);
            assert addInstitution != null;
            addInstitution.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Student student;

                    List<Student> _students = Student.find(Student.class, "first_name = ? and last_name = ?", firstName.getText().toString().trim(), lastName.getText().toString().trim() );
                    if ( _students.size() > 0 ){
                        student = _students.get(0);
                    }
                    else{
                        student = new Student(firstName.getText().toString().trim(), lastName.getText().toString().trim() );
                        student.save();
                    }
                    CourseStudent courseStudent  = new CourseStudent( course, student, Integer.parseInt( hours.getText().toString().trim()) );
                    courseStudent.save();

                    setResult(RESULT_OK);
                    finish();

                }
            });
        }
        else{
            finish();
        }
    }

}
