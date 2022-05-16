package org.intelehealth.app.activities.myActivity;

import static java.lang.String.valueOf;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import org.intelehealth.app.R;
import org.intelehealth.app.activities.myActivity.Auth.BasicAuthClient;
import org.intelehealth.app.activities.myActivity.dataclass.EncounterProviders;
import org.intelehealth.app.activities.myActivity.dataclass.Encounters;
import org.intelehealth.app.activities.myActivity.dataclass.ListofDoctor;
import org.intelehealth.app.activities.myActivity.dataclass.Results;
import org.intelehealth.app.activities.myActivity.dataclass.VisitData;
import org.intelehealth.app.networkApiCalls.ApiInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorList extends AppCompatActivity {
    ArrayList<ListofDoctor> alist = new ArrayList<ListofDoctor>();
    public ArrayList<String> DoctorList = new ArrayList<String>();
    public ArrayList<String> noOfConsultation = new ArrayList<String>();
    public ArrayList<String> appreciationWords = new ArrayList<String>();
    public Context context = this;
    public ArrayList<String> DoctorNames = new ArrayList<String>();
    public Map<String, Long> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        appreciationWords.add("Be proud of the fact that you have the power to rise above any situation and deliver the best results no matter the circumstances. Excellent work!");
        appreciationWords.add("To be honest, I don’t know how you manage to do such a good job every single time. Very well done!");
        appreciationWords.add("We are fortunate to be able to witness and work amongst an industry expert such as you!");
        appreciationWords.add("Your level of quality work remains unprecedented in our organization!");
        appreciationWords.add("They say that the Devil works hard. Everyone’s wrong. You work harder. We’re so proud of you!");
        appreciationWords.add("You manage to go above and beyond for every piece of job that you do. Great work!");
        appreciationWords.add("Please take a breather from working so hard. You have already done such excellent work!");
        appreciationWords.add("For being the first person to come in and the last person to leave, we commend your dedication and hard work!");
        appreciationWords.add("Your hard work has not gone unnoticed. I and the entire senior management would like to congratulate you on doing a great job!");
        appreciationWords.add("Your great work has resulted in tangible, beneficial results to us. You’re a force to be reckoned!");

        loadProfile();
    }

    private void loadProfile() {
        Call call = (Call) new BasicAuthClient().createService(ApiInterface.class).getAllData();
        call.enqueue(new Callback<VisitData>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<VisitData> call, Response<VisitData> response) {
                if (response.isSuccessful()) {
                    String details = new Gson().toJson(response.body().results);
                    ArrayList<Results> results = response.body().results;
//                    Log.d("results", new Gson().toJson(results));
                    Log.d("details", results.toString());
                    if (details != null) {
                        for (int i = 0; i < results.size(); i++) {
                            ArrayList<Encounters> encounters = results.get(i).encounters;
                            for (int j = 0; j < encounters.size(); j++) {
                                ArrayList<EncounterProviders> encounterProviders = encounters.get(j).encounterProviders;
                                for (int k = 0; k < encounterProviders.size(); k++) {
                                    Log.d("role", results.get(i).encounters.get(j).encounterProviders.get(k).encounterRole.display);
                                    Log.d("doctorname", results.get(i).encounters.get(j).encounterProviders.get(k).provider.display);

                                    if (results.get(i).encounters.get(j).encounterProviders.get(k).encounterRole.display.contains("Doctor") == true) {
                                        if (results.get(i).encounters.get(j).encounterProviders.get(k).provider.display.contains("nurse") != true) {
                                           /* Log.d("role", results.get(i).encounters.get(j).encounterProviders.get(k).encounterRole.display);
                                            Log.d("doctorname", results.get(i).encounters.get(j).encounterProviders.get(k).provider.display);*/
                                            DoctorNames.add(results.get(i).encounters.get(j).encounterProviders.get(k).provider.display);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    result = DoctorNames.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                    System.out.println(result);
                    String sorteddata = result.toString().substring(1, result.toString().length() - 1);
                    System.out.println(sorteddata);
                    String[] sorted = sorteddata.split(",");

                    int k = sorted.length;
                    for (int i = 0; i < k; i++) {
                        String[] name = sorted[i].split("=");
                        DoctorList.add(name[0]);
                        noOfConsultation.add(name[1]);

                        /*
                        Log.d("name0", name[0].toString());
                        String names=name[0];
                        String cases=name[1];
                        ListofDoctor dList = new ListofDoctor();
                        dList.setName(names);
                        dList.setCases(cases);
                        alist.add(dList);

                        //alist.add(new ListofDoctor(names,cases));
                       *//* personNames.add(name[0]);
                        cases.add(name[1]);*/
                    }

                    Log.d("sslist", alist.toString());
                    Log.d("list", result.toString());

                    System.out.println(result);
                    RecyclerView rc_doctor = findViewById(R.id.drList);
                    DoctorAdapter doctorAdapter = new DoctorAdapter(context, DoctorList, noOfConsultation, appreciationWords);
                    rc_doctor.setAdapter(doctorAdapter);
                    rc_doctor.setLayoutManager(new LinearLayoutManager(context));
                }
                Log.d("list", DoctorNames.toString());

            }

            @Override
            public void onFailure(Call<VisitData> call, Throwable t) {
                Log.e("TAG", "response Error");

            }

        });
    }

}