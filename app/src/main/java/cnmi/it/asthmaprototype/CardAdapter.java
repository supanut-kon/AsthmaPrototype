package cnmi.it.asthmaprototype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import cnmi.it.asthmaprototype.Models.FlowModel;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.SumHolder> {

    private Context context;
    private ArrayList<FlowModel> Flows;

    public CardAdapter(Context context, ArrayList<FlowModel> flows) {
        this.context = context;
        this.Flows = flows;
    }

    @NonNull
    @Override
    public SumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dashboardflows, parent, false);
        return new SumHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SumHolder holder, int position) {
        FlowModel flows = Flows.get(position);
        holder.setDetails(flows ,position);
    }

    @Override
    public int getItemCount() {
        return Flows.size();
    }

    public class SumHolder extends RecyclerView.ViewHolder {
        private TextView datetext, flowstext, timetext;
        public SumHolder(@NonNull View itemView) {
            super(itemView);
            flowstext = itemView.findViewById(R.id.peakflow);
            datetext = itemView.findViewById(R.id.date);
            timetext = itemView.findViewById(R.id.time);

        }

        void setDetails(FlowModel flows, int pos){


            int flowsstring = flows.getPef();
            String dateString = flows.getDate();
            String timeString = flows.getTime();

            flowstext.setText(String.format("%s", flowsstring));
            datetext.setText(String.format("%s", dateString));
            timetext.setText(String.format("%s", timeString));
        }
    }
}
