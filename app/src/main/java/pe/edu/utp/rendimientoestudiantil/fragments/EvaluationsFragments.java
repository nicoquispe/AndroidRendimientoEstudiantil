package pe.edu.utp.rendimientoestudiantil.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.activities.AddEvaluationActivity;
import pe.edu.utp.rendimientoestudiantil.adapters.EvaluationAdapter;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Evaluation;

/**
 * Created by nico on 10/06/16.
 */
public class EvaluationsFragments extends Fragment {
    private View myFragmentView;
    private long idCourse;
    private Course course;
    private List<Evaluation> evaluations;
    private RecyclerView mEvaluationRecyclerView;
    private LinearLayoutManager mEvaluationLayoutManager;
    private EvaluationAdapter mEvaluationAdapter;

    public EvaluationsFragments() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragmentView =  inflater.inflate(R.layout.fragment_evaluations, container, false);


        Bundle extras = getActivity().getIntent().getExtras();
        idCourse = extras.getLong("idCourse");
        course = Course.findById(Course.class, idCourse);

        FloatingActionButton addEvaluation = (FloatingActionButton) myFragmentView.findViewById(R.id.addEvaluation);
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
        mEvaluationRecyclerView = (RecyclerView) myFragmentView.findViewById(R.id.EvaluationsRecyclerView);
        mEvaluationRecyclerView.setHasFixedSize(true);
        mEvaluationLayoutManager = new LinearLayoutManager(getActivity());
        mEvaluationRecyclerView.setLayoutManager(mEvaluationLayoutManager);

        mEvaluationAdapter = new EvaluationAdapter( evaluations, course );
        mEvaluationRecyclerView.setAdapter(mEvaluationAdapter);

        return myFragmentView;
    }
}
