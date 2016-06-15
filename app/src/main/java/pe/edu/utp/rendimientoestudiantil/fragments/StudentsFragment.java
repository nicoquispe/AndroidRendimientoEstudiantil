package pe.edu.utp.rendimientoestudiantil.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.activities.AddStudentActivity;
import pe.edu.utp.rendimientoestudiantil.activities.ChartActivity;
import pe.edu.utp.rendimientoestudiantil.activities.StudentsTabsActivity;
import pe.edu.utp.rendimientoestudiantil.adapters.DividerItemDecoration;
import pe.edu.utp.rendimientoestudiantil.adapters.StudentAdapter;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Student;

/**
 * Created by nico on 10/06/16.
 */
public class StudentsFragment extends Fragment implements RecyclerView.OnItemTouchListener,
        View.OnClickListener,
        ActionMode.Callback {
    private View myFragmentView;
    Long idCourse;
    List<Student> students;

    RecyclerView mStudentRecyclerView;
    StudentAdapter mStudentAdapter;
    RecyclerView.LayoutManager mStudentLayoutManager;
    private Course course;
    Context mContext;
    int itemCount;
    GestureDetectorCompat gestureDetector;
    ActionMode actionMode;


    public StudentsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void addStudent( Student student ){
        students.add( student );
        mStudentRecyclerView.swapAdapter( new StudentAdapter( students  ), false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragmentView =  inflater.inflate(R.layout.fragment_students, container, false);
        mContext = getContext();

        //myView = myFragmentView.findViewById(R.id.myIdTag);
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null){
            idCourse = extras.getLong("idCourse");
            course = Course.findById(Course.class, idCourse);



            FloatingActionButton fab = (FloatingActionButton) myFragmentView.findViewById(R.id.fab);
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

            mStudentRecyclerView = (RecyclerView) myFragmentView.findViewById(R.id.StudentsRecyclerView);
            mStudentRecyclerView.setHasFixedSize(true);
            mStudentLayoutManager = new LinearLayoutManager(getActivity());
            mStudentRecyclerView.setLayoutManager(mStudentLayoutManager);

            mStudentAdapter = new StudentAdapter( students );
            mStudentRecyclerView.setAdapter(mStudentAdapter);

            RecyclerView.ItemDecoration itemDecoration =
                    new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
            mStudentRecyclerView.addItemDecoration(itemDecoration);
            mStudentRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mStudentRecyclerView.addOnItemTouchListener(this);

            gestureDetector =
                    new GestureDetectorCompat(getActivity(), new RecyclerViewStudentOnGestureListener());

        }

        return myFragmentView;

    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.menu_student, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_delete_student:
                List<Integer> selectedItemPositions = mStudentAdapter.getSelectedItems();
                int currPos;
                for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
                    currPos = selectedItemPositions.get(i);
                    mStudentAdapter.removeData(currPos);
                }
                actionMode.finish();
                return true;
            case R.id.action_trending:

                List<Long> ids = new ArrayList<>();
                for ( int id : mStudentAdapter.getSelectedItems() ){
                    ids.add(  students.get( id ).getId() );
                }
                long[] longArray = new long[ids.size()];
                for (int i = 0; i < ids.size(); i++)
                    longArray[i] = ids.get(i);

                Intent itemIntent;
                itemIntent = new Intent(getActivity(), ChartActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("idCourse", course.getId());
                bundle.putLongArray( "idsStudents", longArray );
                itemIntent.putExtras(bundle);
                getActivity().startActivity(itemIntent);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        this.actionMode = null;
        mStudentAdapter.clearSelections();
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.studentCard) {

            int idx = mStudentRecyclerView.getChildPosition(view);
            if(actionMode != null) {
                myToggleSelection(idx);
                return;
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private void addItemToList() {
        Student student = new Student();
        itemCount++;
        int position = ((LinearLayoutManager) mStudentRecyclerView.getLayoutManager()).
                findFirstVisibleItemPosition();
        position++;
        //RecyclerViewDemoApp.addItemToList(student, position);
        mStudentAdapter.addData(student, position);
    }

    private void myToggleSelection(int idx) {
        mStudentAdapter.toggleSelection(idx);
        if( mStudentAdapter.getSelectedItemCount() == 0 ){
            actionMode.finish();
            return;
        }

        String title = getString(R.string.selected_count, mStudentAdapter.getSelectedItemCount());
        actionMode.setTitle(title);
    }

    private class RecyclerViewStudentOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = mStudentRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (actionMode != null) {
                onClick(view);
            }
            else{
                Intent itemIntent;
                itemIntent = new Intent(view.getContext(), ChartActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("idCourse", course.getId());
                List<Long> ids = new ArrayList<>();
                ids.add( students.get(mStudentRecyclerView.getChildPosition( view )).getId() );

                long[] longArray = new long[ids.size()];
                for (int i = 0; i < ids.size(); i++)
                    longArray[i] = ids.get(i);
                bundle.putLongArray( "idsStudents", longArray );
                itemIntent.putExtras(bundle);
                view.getContext().startActivity(itemIntent);
            }
            return super.onSingleTapConfirmed(e);
        }

        public void onLongPress(MotionEvent e) {
            View view = mStudentRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (actionMode != null) {
                return;
            }
            // Start the CAB using the ActionMode.Callback defined above
            actionMode = getActivity().startActionMode( StudentsFragment.this );
            int idx = mStudentRecyclerView.getChildPosition(view);
            myToggleSelection(idx);
            super.onLongPress(e);
        }
    }
}
