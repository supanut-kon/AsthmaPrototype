package cnmi.it.asthmaprototype.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cnmi.it.asthmaprototype.Models.InhalerModel;
import cnmi.it.asthmaprototype.R;

public class YellowCardAdapter extends RecyclerView.Adapter<YellowCardAdapter.YellowHolder> {
    private Context context;
    private ArrayList<InhalerModel> Inhaler;
    int[] resources = {R.drawable.flixotide_evohaler,
            R.drawable._0_aeronide,
            R.drawable.easyhaler_budesoniude,
            R.drawable.easyhaler_salbutamolsq,
            R.drawable.seretideevo,
            R.drawable.seretideaccuhaler,
            R.drawable.ventolin_inhaler,
            R.drawable.add_user2};

    public YellowCardAdapter(Context context, ArrayList<InhalerModel> inhaler){
        this.context = context;
        this.Inhaler = inhaler;
    }
    @NonNull
    @Override
    public YellowCardAdapter.YellowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.yellow_dashboard_card, parent, false);

        return new YellowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YellowCardAdapter.YellowHolder holder, int position) {
        InhalerModel inhaler = Inhaler.get(position);
        int imgid = inhaler.getDid();
        String times = inhaler.getTimes();

        holder.inhalerimg.setImageResource(resources[imgid]);
        int timestoint = Integer.parseInt(times);
        holder.desc.setText(String.format("ครั้งละ %d สูด วันละ %s ครั้ง", timestoint+timestoint, inhaler.getInaday()));

    }

    @Override
    public int getItemCount() {
        return Inhaler.size();
    }

    public class YellowHolder extends RecyclerView.ViewHolder{
        private ImageView inhalerimg;
        private TextView desc;

        public YellowHolder(@NonNull View itemView){
            super(itemView);
            inhalerimg = itemView.findViewById(R.id.inhalerYellowCard);
            desc = itemView.findViewById(R.id.yellowCardDesc);

        }
    }
}