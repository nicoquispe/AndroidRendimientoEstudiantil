package pe.edu.utp.rendimientoestudiantil.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.activities.ChartActivity;
import pe.edu.utp.rendimientoestudiantil.models.Evaluation;
import pe.edu.utp.rendimientoestudiantil.models.Student;

/**
 * Created by nico on 10/06/16.
 */
public class EvaluaionStudentAdapter extends RecyclerView.Adapter<EvaluaionStudentAdapter.ViewHolder> {

    private List<Student> students;
    private Evaluation evaluation;
    public EvaluaionStudentAdapter(List<Student> students, Evaluation evaluation) {
        this.students = students;
        this.evaluation = evaluation;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.nameTextView.setText( students.get(position).toString() );
        holder.noteEditText.setVisibility( View.VISIBLE );
        holder.noteEditText.setText( String.valueOf( students.get(position).getNote( evaluation ) ) );
        holder.noteEditText.setTag( students.get(position) );
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
        public ViewHolder(View itemView) {
            super(itemView);
            studentCard = (CardView) itemView.findViewById(R.id.studentCard);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            noteEditText = (EditText) itemView.findViewById(R.id.noteEditText);
        }
    }
}
