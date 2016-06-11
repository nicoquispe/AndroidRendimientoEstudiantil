package pe.edu.utp.rendimientoestudiantil.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.activities.ChartActivity;
import pe.edu.utp.rendimientoestudiantil.models.Evaluation;
import pe.edu.utp.rendimientoestudiantil.models.Student;

/**
 * Created by nico on 10/06/16.
 */
public class EvaluaionStudentAdapter extends RecyclerView.Adapter<EvaluaionStudentAdapter.ViewHolder> {
    private static String TAG = "Test";
    private List<Student> students;
    private List<Student> studentsSeleted;
    private Evaluation evaluation;

    public EvaluaionStudentAdapter(List<Student> students, Evaluation evaluation) {
        this.students = students;
        this.evaluation = evaluation;
        this.studentsSeleted = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_card, parent, false);
        return new ViewHolder(view);
    }

    public  List<Student> getStudentsSeleted (){
        return this.studentsSeleted;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.nameTextView.setText( students.get(position).toString() );
        holder.noteEditText.setVisibility( View.VISIBLE );
        holder.noteEditText.setText( String.valueOf( students.get(position).getNote( evaluation ) ) );
        holder.noteEditText.setTag( students.get(position) );
        holder.chkStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    studentsSeleted.add( students.get(position) );
                }
                else{
                    studentsSeleted.remove( students.get(position) );
                }
            }
        });

        holder.noteEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String qtyString = s.toString().trim();
                int note = qtyString.equals("") ? 0:Integer.valueOf(qtyString);
                Student student = (Student) holder.noteEditText.getTag();
                student.setNote(note, evaluation);
                student.save();
            }
        });
    }
    private void updateListItem(int position) {

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder  extends RecyclerView.ViewHolder  {
        TextView nameTextView;
        EditText noteEditText;
        CardView studentCard;
        CheckBox chkStudent;
        public ViewHolder(View itemView) {
            super(itemView);
            studentCard = (CardView) itemView.findViewById(R.id.studentCard);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            noteEditText = (EditText) itemView.findViewById(R.id.noteEditText);
            chkStudent = (CheckBox) itemView.findViewById(R.id.chkStudent);

        }
    }
}
