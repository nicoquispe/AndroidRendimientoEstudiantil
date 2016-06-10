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

import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.adapters.EvaluationAdapter;
import pe.edu.utp.rendimientoestudiantil.adapters.StudentAdapter;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Evaluation;

public class EvaluationActivity extends AppCompatActivity {

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
            this.setTitle(course.getName());

            FloatingActionButton addEvaluation = (FloatingActionButton) findViewById(R.id.addEvaluation);
            if (addEvaluation != null) {
                addEvaluation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(view.getContext(), AddEvaluationActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putLong("idCourse", course.getId());
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 0);
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

}
