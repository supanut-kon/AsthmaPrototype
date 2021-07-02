package cnmi.it.asthmaprototype.Adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.InhalerModel;
import cnmi.it.asthmaprototype.R;

public class InhalerCardAdapter extends RecyclerView.Adapter<InhalerCardAdapter.ViewHolder> {
    private Context context;
    String tdesc;
    private ArrayList<InhalerModel> Inhaler;
    int[] resources = {R.drawable.flixotide_evohaler,
            R.drawable._0_aeronide,
            R.drawable.easyhaler_budesoniude,
            R.drawable.easyhaler_salbutamolsq,
            R.drawable.seretideevo,
            R.drawable.seretideaccuhaler,
            R.drawable.ventolin_inhaler,
            R.drawable.add_user2};

    public InhalerCardAdapter(Context context, ArrayList<InhalerModel> Inhaler) {
        this.context = context;
        this.Inhaler = Inhaler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inhaler_card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InhalerModel inhaler = Inhaler.get(position);
        int imgid = inhaler.getDid();
        String name = inhaler.getName();
        int type = inhaler.getType();
        int id = inhaler.getId();

        holder.img.setImageResource(resources[imgid]);
        holder.inhalername.setText(name);
        if (type == 1){
            holder.type.setText("ยาพ่นปกติ");
            holder.type.setTextColor(Color.parseColor("#59B55D"));
            tdesc = "ครั้งละ " + inhaler.getTimes() + " สูด วันละ " + inhaler.getInaday() + " ครั้ง";
        } else if(type==2){
            holder.type.setText("ยาพ่นบรรเทาอาการ");
            holder.type.setTextColor(Color.parseColor("#FFC107"));
            tdesc = "ครั้งละ " + inhaler.getTimes() + " สูด";
        }else if(type==3){
            holder.type.setText("ยาพ่นฉุกเฉิน");
            holder.type.setTextColor(Color.parseColor("#DC0000"));
            tdesc = "ครั้งละ " + inhaler.getTimes() + " สูด และทานยาสเตียรอยด์";
        }else if (type==4){
            holder.type.setText("ยาพ่นจากเหลืองไปเขียว");
            holder.type.setTextColor(Color.parseColor("#FF7400"));
            tdesc = "ครั้งละ " + inhaler.getTimes() + " สูด วันละ " + inhaler.getInaday() + " ครั้ง";
        }

        holder.desc.setText(tdesc);

        holder.delete.setOnClickListener(v -> {
            DatabaseHelper helper = new DatabaseHelper(context);
            SQLiteDatabase writedb = helper.getReadableDatabase();

            writedb.delete("asthma_inhaler", "id = "+ id, null);
            Inhaler.remove(position);
            for(int i = 0; i <Inhaler.size(); i++){
                Log.d("Inhaler", String.valueOf(Inhaler.get(i).getId()));
            }
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, Inhaler.size());
            writedb.close();
        });
    }

    @Override
    public int getItemCount() {
        return Inhaler.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView inhalername, type, desc;
        private ImageView edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.inhalerimg);
            inhalername = itemView.findViewById(R.id.inhalerName);
            type = itemView.findViewById(R.id.typeText);
            desc = itemView.findViewById(R.id.descText);
            edit = itemView.findViewById(R.id.editimg);
            delete = itemView.findViewById(R.id.deleteimg);
        }
    }


}
