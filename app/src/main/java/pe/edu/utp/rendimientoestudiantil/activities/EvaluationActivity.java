package pe.edu.utp.rendimientoestudiantil.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.adapters.EvaluationAdapter;
import pe.edu.utp.rendimientoestudiantil.fragments.AddEvaluationDialogFragment;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Evaluation;

public class EvaluationActivity extends BaseActivity implements AddEvaluationDialogFragment.AddEvaluationDialogListener{

    Long idCourse;
    private Course course;
    private List<Evaluation> evaluations;
    private RecyclerView mEvaluationRecyclerView;
    private LinearLayoutManager mEvaluationLayoutManager;
    private EvaluationAdapter mEvaluationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        if (extras != null){
            idCourse = extras.getLong("idCourse");
            course = Course.findById(Course.class, idCourse);
            this.setTitle(course.getName() +" - Evaluaciones");


            FloatingActionButton addEvaluation = (FloatingActionButton) findViewById(R.id.addEvaluation);
            if (addEvaluation != null) {
                addEvaluation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        showNoticeDialog();
                        /*
                        Intent intent = new Intent(view.getContext(), AddEvaluationActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putLong("idCourse", course.getId());
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 0);
                        */
                    }

                    
                });
            }

            evaluations = course.findEvaluationsByCourse( );
            mEvaluationRecyclerView = (RecyclerView) findViewById(R.id.EvaluationsRecyclerView);
            mEvaluationRecyclerView.setHasFixedSize(true);
            mEvaluationLayoutManager = new LinearLayoutManager(this);
            mEvaluationRecyclerView.setLayoutManager(mEvaluationLayoutManager);

            mEvaluationAdapter = new EvaluationAdapter( evaluations, course );
            mEvaluationRecyclerView.setAdapter(mEvaluationAdapter);

            return;
        }
        finish();

    }

    private void showNoticeDialog() {
        DialogFragment dialog = new AddEvaluationDialogFragment();
        dialog.show( getSupportFragmentManager(), "Evaluation" );
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Evaluation evaluation;
        EditText nameEditText = (EditText) dialog.getDialog().findViewById(R.id.nameEditText);
        EditText porcentageEditText = (EditText) dialog.getDialog().findViewById(R.id.porcentageEditText);

        evaluation = new Evaluation(nameEditText.getText().toString().trim(), Integer.parseInt( porcentageEditText.getText().toString().trim() ), course );
        evaluation.save();
        evaluations.add(evaluation);
        mEvaluationRecyclerView.swapAdapter( new EvaluationAdapter( evaluations, course  ), false);

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
