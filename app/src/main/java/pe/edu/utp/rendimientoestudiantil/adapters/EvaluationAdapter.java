package pe.edu.utp.rendimientoestudiantil.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.models.Evaluation;

/**
 * Created by nico on 09/06/16.
 */
public class EvaluationAdapter extends RecyclerView.Adapter<EvaluationAdapter.ViewHolder>  {
    private List<Evaluation> evaluations;


    public EvaluationAdapter(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.evaluation_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameTextView.setText( evaluations.get(position).toString() );

    }

    @Override
    public int getItemCount() {
        return evaluations.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder  extends RecyclerView.ViewHolder  {
        TextView nameTextView;
        CardView evaluationCard;
        public ViewHolder(View itemView) {
            super(itemView);
            evaluationCard = (CardView) itemView.findViewById(R.id.evaluationCard);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
        }
    }
}
