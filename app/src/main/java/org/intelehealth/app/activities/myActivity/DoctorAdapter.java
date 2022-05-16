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

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder>  {
    public Context context;
    public ArrayList<String> personNames;
   public ArrayList<String> noOfConsultation;
    public ArrayList<String> appreciationWords;


    public DoctorAdapter(Context context, ArrayList<String> personNames, ArrayList<String> noOfConsultation, ArrayList<String> appreciationWords) {
        this.context = context;
        this.personNames = personNames;
        this.noOfConsultation=noOfConsultation;
        this.appreciationWords=appreciationWords;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.doctor_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String Name="Name :"+personNames.get(position);
        String NOOfConsultation="No Of Consultation :"+noOfConsultation.get(position);
        String AppreciationWords=appreciationWords.get(position);
        holder.Setdata(Name,NOOfConsultation,AppreciationWords);
    }

    @Override
    public int getItemCount() {
        return personNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName, noOfConsultation,appreciationWords;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName=itemView.findViewById(R.id.doctorName);
            noOfConsultation=itemView.findViewById(R.id.consultation);
            appreciationWords=itemView.findViewById(R.id.appreciation);
        }

        public void Setdata(String name, String NOOfConsultation,String AppreciationWords) {
            textViewName.setText(name);
            noOfConsultation.setText(NOOfConsultation);
            appreciationWords.setText(AppreciationWords);
        }
    }
}
