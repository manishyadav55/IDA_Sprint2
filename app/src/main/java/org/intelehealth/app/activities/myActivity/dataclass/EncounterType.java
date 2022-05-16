package org.intelehealth.app.activities.myActivity.dataclass;

import com.google.gson.annotations.SerializedName;


public class EncounterType {

  @SerializedName("display")
  String display;

  public EncounterType(String display) {
    this.display = display;
  }
}