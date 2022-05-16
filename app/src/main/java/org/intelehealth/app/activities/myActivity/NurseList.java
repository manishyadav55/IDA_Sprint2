package org.intelehealth.app.activities.myActivity;

import static java.util.stream.Collectors.toList;

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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NurseList extends AppCompatActivity {
    public ArrayList<String> NurseList = new ArrayList<String>();
    public ArrayList<String> noOfConsultation = new ArrayList<String>();
    public ArrayList<String> ranking = new ArrayList<String>();
    public Context context = this;
    public List<String> NurseNames = new ArrayList<>();
    public ArrayList<ListofDoctor> aList = new ArrayList<ListofDoctor>();
    public HashMap<String, Long> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_list);
        ranking.add("1");
        ranking.add("2");
        ranking.add("3");
        ranking.add("4");
        ranking.add("5");
        ranking.add("6");
        ranking.add("7");
        ranking.add("8");
        ranking.add("9");
        ranking.add("10");

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

                                    if (results.get(i).encounters.get(j).encounterProviders.get(k).encounterRole.display.contains("Nurse") == true) {
                                        if (results.get(i).encounters.get(j).encounterProviders.get(k).provider.display.contains("Doctor") != true) {
                                            NurseNames.add(results.get(i).encounters.get(j).encounterProviders.get(k).provider.display);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //grouping
                    Map<String, Long> grouping = NurseNames.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                    //Descending order sorted
                    Map<String, Long> finalMapDescendingOrder = new LinkedHashMap<>();
                    grouping.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                            .forEachOrdered(e -> finalMapDescendingOrder.put(e.getKey(), e.getValue()));

                    System.out.println("Descending order sorted - " + finalMapDescendingOrder);

                    String sorteddata = finalMapDescendingOrder.toString().substring(1, result.toString().length() - 1);
                    String[] sorted = sorteddata.split(",");

                    int k = sorted.length;
                    for (int i = 0; i < k; i++) {
                        String[] name = sorted[i].split("=");
                        NurseList.add(name[0]);
                        noOfConsultation.add(name[1]);
                    }

                    RecyclerView rc_doctor = findViewById(R.id.drLists);
                    NurseAdapter doctorAdapter = new NurseAdapter(context, NurseList, noOfConsultation, ranking);
                    rc_doctor.setAdapter(doctorAdapter);
                    rc_doctor.setLayoutManager(new LinearLayoutManager(context));
                }
                Log.d("list", NurseList.toString());

            }

            @Override
            public void onFailure(Call<VisitData> call, Throwable t) {
                Log.e("TAG", "response Error");

            }

        });
    }
}