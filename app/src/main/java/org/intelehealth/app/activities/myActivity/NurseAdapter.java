package org.intelehealth.app.activities.myActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.intelehealth.app.R;

import java.util.ArrayList;

public class NurseAdapter extends RecyclerView.Adapter<NurseAdapter.ViewHolder>  {

    public Context context;
    public ArrayList<String> nurseNames;
    public ArrayList<String> cases;
    public ArrayList<String> rankings;

    public NurseAdapter(Context context, ArrayList<String> nurseNames, ArrayList<String> cases, ArrayList<String> rankings) {
        this.context = context;
        this.nurseNames = nurseNames;
        this.cases = cases;
        this.rankings = rankings;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nurse_lists,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String Name="Name:-"+nurseNames.get(position);
        String Cases="Cases:-"+cases.get(position);
        String Ranking=rankings.get(position);
        holder.Setdata(Name,Cases,Ranking);
    }

    @Override
    public int getItemCount() {
        return nurseNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName,textViewCases,textViewRanking;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName=itemView.findViewById(R.id.healthworkerName);
            textViewCases=itemView.findViewById(R.id.consultation);
            textViewRanking=itemView.findViewById(R.id.rankings);

        }

        public void Setdata(String name,String cases,String Ranking) {
            textViewName.setText(name);
            textViewCases.setText(cases);
            textViewRanking.setText(Ranking);

        }
    }

}
