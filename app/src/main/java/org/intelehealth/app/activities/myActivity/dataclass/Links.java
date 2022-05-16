package org.intelehealth.app.activities.myActivity.dataclass;
import com.google.gson.annotations.SerializedName;


public class Links {
  @SerializedName("rel" )
  String rel;
  @SerializedName("uri" )
  String uri;

    public Links(String rel, String uri) {
        this.rel = rel;
        this.uri = uri;
    }
}