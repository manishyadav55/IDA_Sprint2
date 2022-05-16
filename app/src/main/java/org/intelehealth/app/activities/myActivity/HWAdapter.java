package org.intelehealth.app.activities.myActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.intelehealth.app.R;

import java.util.ArrayList;

public class HWAdapter extends RecyclerView.Adapter<HWAdapter.ViewHolder> {
    public Context context;
    public ArrayList<String> personNames;
    public ArrayList<String> noOfConsultation;
    int[] images;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView hwName;
        TextView consultation;
        ImageView rowImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hwName=itemView.findViewById(R.id.healthworkerName);
            consultation=itemView.findViewById(R.id.consultation);
            //rowImage=itemView.findViewById(R.id.imageRanking);

        }
    }
    public HWAdapter(Context context, ArrayList<String> personNames, ArrayList<String> noOfConsultation, int[] images ) {
        this.context = context;
        this.personNames = personNames;
        this.noOfConsultation=noOfConsultation;
        this.images=images;
    }
    @NonNull
    @Override
    public HWAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.nurse_list,parent,false);
        /*
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.nurse_list,parent,false);
        */

        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HWAdapter.ViewHolder holder, int position) {
        holder.hwName.setText(personNames.get(position));
        holder.consultation.setText(noOfConsultation.get(position));
        holder.rowImage.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return personNames.size();
    }
}
