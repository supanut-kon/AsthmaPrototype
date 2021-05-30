package cnmi.it.asthmaprototype.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cnmi.it.asthmaprototype.R;

public class CustomAdapter extends BaseAdapter {
    Context context;
    int resources[];
    String[] resourcesname;
    LayoutInflater inflater;

    public CustomAdapter(Context context, int[] resources, String[] textResourceId){
       this.context = context;
       this.resources = resources;
       this.resourcesname = textResourceId;
       inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return resources.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.custom_spinner_item, null);
        ImageView image = convertView.findViewById(R.id.spinnerImage);
        TextView text = convertView.findViewById(R.id.spinnerText);
        image.setImageResource(resources[position]);
        text.setText(resourcesname[position]);

        return convertView;
    }
}
