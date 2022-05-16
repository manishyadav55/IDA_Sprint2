package org.intelehealth.app.activities.myActivity.dataclass;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class Results {

  @SerializedName("uuid")
  String uuid;
  @SerializedName("patient")
  Patient patient;
  @SerializedName("location")
  Location location;
  @SerializedName("encounters")
  public
  ArrayList<Encounters> encounters;
  @SerializedName("attributes")
  ArrayList<Attributes> attributes;

  public Results(String uuid, org.intelehealth.app.activities.myActivity.dataclass.Patient patient, org.intelehealth.app.activities.myActivity.dataclass.Location location, ArrayList<org.intelehealth.app.activities.myActivity.dataclass.Encounters> encounters, ArrayList<org.intelehealth.app.activities.myActivity.dataclass.Attributes> attributes) {
    this.uuid = uuid;
    this.patient = patient;
    this.location = location;
    this.encounters = encounters;
    this.attributes = attributes;
  }
}