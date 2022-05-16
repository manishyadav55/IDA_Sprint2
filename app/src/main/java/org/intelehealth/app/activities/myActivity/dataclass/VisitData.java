package org.intelehealth.app.activities.myActivity.dataclass;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class VisitData {

        @SerializedName("results" )
        public
        ArrayList<Results> results;
        @SerializedName("links" )
        ArrayList<Links> links;

        public VisitData(ArrayList<Results> results, ArrayList<Links> links) {

                this.results = results;
                this.links = links;
        }
}