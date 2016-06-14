package pe.edu.utp.rendimientoestudiantil.activities;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.adapters.StudentAdapter;
import pe.edu.utp.rendimientoestudiantil.fragments.AddEvaluationDialogFragment;
import pe.edu.utp.rendimientoestudiantil.fragments.AddStudentDialogFragment;
import pe.edu.utp.rendimientoestudiantil.fragments.EvaluationsFragments;
import pe.edu.utp.rendimientoestudiantil.fragments.StudentsFragment;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.CourseStudent;
import pe.edu.utp.rendimientoestudiantil.models.Evaluation;
import pe.edu.utp.rendimientoestudiantil.models.Student;

public class StudentsTabsActivity extends BaseActivity implements AddStudentDialogFragment.AddStudentDialogListener, AddEvaluationDialogFragment.AddEvaluationDialogListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private long idCourse;
    private Course course;
    private TabLayout tabLayout;
    private StudentsFragment sf;
    private EvaluationsFragments ef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_tabs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        settingBackToolbar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            idCourse = extras.getLong("idCourse");
            course = Course.findById(Course.class, idCourse);
            this.setTitle(course.getName());

            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            mViewPager = (ViewPager) findViewById(R.id.container);
            sf = new StudentsFragment();
            ef = new EvaluationsFragments();
            mSectionsPagerAdapter.addFragment(sf, "Estudiantes");
            mSectionsPagerAdapter.addFragment(ef, "Evaluaciones");

            mViewPager.setAdapter(mSectionsPagerAdapter);

            tabLayout = (TabLayout) findViewById(R.id.tabs);
            assert tabLayout != null;
            tabLayout.setupWithViewPager(mViewPager);

            tabLayout.setOnTabSelectedListener( new TabLayout.ViewPagerOnTabSelectedListener( mViewPager ){
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    super.onTabSelected(tab);
                }
            });
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            assert fab != null;
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    switch ( tabLayout.getSelectedTabPosition() ){
                        case 0:
                            showAddStudentDialog();
                            break;
                        case 1:
                            showAddEvaluationDialog();
                            break;
                        default:
                            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            break;
                    }
                }
            });
            return;
        }
        finish();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (menu.findItem(R.id.action_register_notes) != null)
            menu.findItem(R.id.action_register_notes).setVisible(true);
        if (menu.findItem(R.id.action_select_students) != null)
            menu.findItem(R.id.action_select_students).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }
    private void showAddEvaluationDialog() {

        DialogFragment dialog = new AddEvaluationDialogFragment();
        dialog.show( getSupportFragmentManager(), "Evaluation" );
    }

    private void showAddStudentDialog() {
        DialogFragment dialog = new AddStudentDialogFragment();
        dialog.show( getSupportFragmentManager(), "Student" );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void addStudent( DialogFragment dialog ){

        Student student;
        EditText firstName = (EditText) dialog.getDialog().findViewById(R.id.firstNameEditText);
        EditText lastName = (EditText) dialog.getDialog().findViewById(R.id.lastNameEditText);
        EditText hours = (EditText) dialog.getDialog().findViewById(R.id.hoursEditText);

        List<Student> _students = Student.find(Student.class, "first_name = ? and last_name = ?", firstName.getText().toString().trim(), lastName.getText().toString().trim() );
        if ( _students.size() > 0 ){
            student = _students.get(0);
        }
        else{
            student = new Student(firstName.getText().toString().trim(), lastName.getText().toString().trim() );
            student.save();
            sf.addStudent( student );
            mViewPager.getAdapter().notifyDataSetChanged();

        }
        CourseStudent courseStudent  = new CourseStudent( course, student, Integer.parseInt( hours.getText().toString().trim()) );
        courseStudent.save();
    }
    private void addEvaluation( DialogFragment dialog ){

        Evaluation evaluation;
        EditText nameEditText = (EditText) dialog.getDialog().findViewById(R.id.nameEditText);
        EditText porcentageEditText = (EditText) dialog.getDialog().findViewById(R.id.porcentageEditText);

        evaluation = new Evaluation(nameEditText.getText().toString().trim(), Integer.parseInt( porcentageEditText.getText().toString().trim() ), course );
        evaluation.save();
        ef.addEvaluation( evaluation );
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        switch ( tabLayout.getSelectedTabPosition() ) {
            case 0:
                addStudent( dialog );
                break;
            case 1:
                addEvaluation( dialog );
                break;
            default:
                break;
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
