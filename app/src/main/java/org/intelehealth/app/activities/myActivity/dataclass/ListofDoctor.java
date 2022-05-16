package org.intelehealth.app.activities.myActivity.dataclass;


import com.google.gson.annotations.SerializedName;

public class ListofDoctor {

    @SerializedName("name")
    String name;
    @SerializedName("cases")
    String cases;
    public ListofDoctor(){

    }

    public ListofDoctor(String name,String cases) {
        this.name = name;
        this.cases = cases;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }
}
