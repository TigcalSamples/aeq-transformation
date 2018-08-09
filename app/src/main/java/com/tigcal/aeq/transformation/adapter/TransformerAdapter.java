package com.tigcal.aeq.transformation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tigcal.aeq.transformation.R;
import com.tigcal.aeq.transformation.model.Transformer;

import java.util.List;

public class TransformerAdapter extends RecyclerView.Adapter<TransformerAdapter.ViewHolder> {
    private Context context;
    private List<Transformer> transformers;
    private OnClickListener clickListener;

    public TransformerAdapter(Context context, List<Transformer> transformers, OnClickListener clickListener) {
        this.context = context;
        this.transformers = transformers;
        this.clickListener = clickListener;
    }

    @Override
    public TransformerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_transformer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransformerAdapter.ViewHolder holder, final int position) {
        final Transformer transformer = transformers.get(position);

        if(transformer.isAutobot()) {
            holder.icon.setImageResource(R.drawable.ic_autobots);
        } else {
            holder.icon.setImageResource(R.drawable.ic_decepticons);
        }

        holder.nameText.setText(transformer.getName());

        //TODO improve stats display
        StringBuilder statsBuilder = new StringBuilder();
        statsBuilder.append("Strength: ");
        statsBuilder.append(transformer.getStrength());
        statsBuilder.append("; ");
        statsBuilder.append("Intelligence: ");
        statsBuilder.append(transformer.getIntelligence());
        statsBuilder.append("; ");
        statsBuilder.append("Speed: ");
        statsBuilder.append(transformer.getSpeed());
        statsBuilder.append("; ");
        statsBuilder.append("Endurance: ");
        statsBuilder.append(transformer.getEndurance());
        statsBuilder.append("; ");
        statsBuilder.append("Rank: ");
        statsBuilder.append(transformer.getRank());
        statsBuilder.append("; ");
        statsBuilder.append("Courage: ");
        statsBuilder.append(transformer.getCourage());
        statsBuilder.append("; ");
        statsBuilder.append("Firepower: ");
        statsBuilder.append(transformer.getFirepower());
        statsBuilder.append("; ");
        statsBuilder.append("Skill: ");
        statsBuilder.append(transformer.getStrength());
        holder.statText.setText(statsBuilder.toString());
    }

    @Override
    public int getItemCount() {
        return transformers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView nameText;
        TextView statText;
        ImageView removeImage;

        ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.transformer_icon);
            nameText = itemView.findViewById(R.id.transformer_name_text);
            statText = itemView.findViewById(R.id.transformer_stat_text);
            removeImage = itemView.findViewById(R.id.remove_button);
            removeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(getAdapterPosition());
                }
            });
        }
    }

    public interface OnClickListener {
        void onClick(int index);
    }

}