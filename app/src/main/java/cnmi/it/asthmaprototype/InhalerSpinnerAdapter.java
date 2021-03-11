package cnmi.it.asthmaprototype;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class InhalerSpinnerAdapter extends ArrayAdapter<String> {
    Context ctx;
    String[] textArray ={};
    Integer[] imageArray ={};


    public InhalerSpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
