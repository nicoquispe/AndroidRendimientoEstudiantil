package pe.edu.utp.rendimientoestudiantil.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.activities.ChartActivity;
import pe.edu.utp.rendimientoestudiantil.models.Student;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<Student> students;

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, final int position) {
        holder.nameTextView.setText( students.get(position).getId() + " - " +  students.get(position).toString() );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itemIntent;
                itemIntent = new Intent(view.getContext(), ChartActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", students.get(position).toString());
                bundle.putLong("id", students.get(position).getId());
                itemIntent.putExtras(bundle);
                view.getContext().startActivity(itemIntent);
            }
        });
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
        CardView studentCard;
        public ViewHolder(View itemView) {
            super(itemView);
            studentCard = (CardView) itemView.findViewById(R.id.studentCard);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
        }
    }
}
