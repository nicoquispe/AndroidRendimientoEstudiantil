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

import pe.edu.utp.rendimientoestudiantil.activities.CoursesTabsActivity;
import pe.edu.utp.rendimientoestudiantil.activities.StudentsActivity;
import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.models.Course;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>  {
    private List<Course> courses;

    public CourseAdapter(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }




    @Override
    public void onBindViewHolder( ViewHolder holder, final int position) {
        holder.nameTextView.setText( courses.get(position).getName() );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itemIntent;
                itemIntent = new Intent(view.getContext(), StudentsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("idCourse", courses.get(position).getId());
                itemIntent.putExtras(bundle);
                view.getContext().startActivity(itemIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder  extends RecyclerView.ViewHolder  {
        TextView nameTextView;
        CardView courseCard;
        public ViewHolder(View itemView) {
            super(itemView);
            courseCard = (CardView) itemView.findViewById(R.id.courseCard);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
        }


    }
}
