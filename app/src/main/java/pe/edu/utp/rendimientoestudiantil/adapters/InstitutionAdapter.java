package pe.edu.utp.rendimientoestudiantil.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.edu.utp.rendimientoestudiantil.activities.CoursesActivity;
import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.activities.CoursesTabsActivity;
import pe.edu.utp.rendimientoestudiantil.models.Institution;

public class InstitutionAdapter extends RecyclerView.Adapter<InstitutionAdapter.ViewHolder>  {
    private List<Institution> instituciones;

    public InstitutionAdapter(List<Institution> instituciones) {
        this.instituciones = instituciones;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.institution_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, final int position) {
        holder.nameTextView.setText( instituciones.get(position).getName() );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itemIntent;
                itemIntent = new Intent(view.getContext(), CoursesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("idInstitution", instituciones.get(position).getId());
                    itemIntent.putExtras(bundle);
                view.getContext().startActivity(itemIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return instituciones.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder  extends RecyclerView.ViewHolder  {
        TextView nameTextView;
        CardView institutionCard;
        public ViewHolder(View itemView) {
            super(itemView);
            institutionCard = (CardView) itemView.findViewById(R.id.institutionCard);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
        }
    }
}
