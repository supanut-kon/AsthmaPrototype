package cnmi.it.asthmaprototype.Adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.tmp_inhalerModel;
import cnmi.it.asthmaprototype.R;

public class InhalerCardAdapter extends RecyclerView.Adapter<InhalerCardAdapter.ViewHolder>{
    private Context context;
    private ArrayList<tmp_inhalerModel> Inhaler;
    int[] resources = {R.drawable.flixotide_evohaler,
            R.drawable._0_aeronide,
            R.drawable.easyhaler_budesoniude,
            R.drawable.easyhaler_salbutamolsq,
            R.drawable.seretideevo,
            R.drawable.seretideaccuhaler,
            R.drawable.ventolin_inhaler,
    R.drawable.add_user2};

    public InhalerCardAdapter(Context context, ArrayList<tmp_inhalerModel> Inhaler){
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
        tmp_inhalerModel inhaler = Inhaler.get(position);
        //holder.setDetails(inhaler, position);
        int imgid = inhaler.getDid();
        String name = inhaler.getName();
        int type = inhaler.getType();
        String tdesc;


        holder.img.setImageResource(resources[imgid]);
        holder.inhalername.setText(name);
        if(type == 0){
            holder.type.setText("ยาพ่นปกติ");
            holder.type.setTextColor(Color.parseColor("#59B55D"));
            tdesc = "ครั้งละ "+inhaler.getTimes()+" สูด วันละ "+inhaler.getInaday()+" ครั้ง";
        }else {
            holder.type.setText("ยาพ่นฉุกเฉิน");
            holder.type.setTextColor(Color.parseColor("#D3E91E1E"));
            tdesc = "ครั้งละ "+inhaler.getTimes()+" สูด";
        }
        holder.desc.setText(tdesc);

        holder.delete.setOnClickListener(v -> {
            DatabaseHelper helper = new DatabaseHelper(context);
            SQLiteDatabase writedb = helper.getWritableDatabase();
            int aposition = holder.getAdapterPosition();
            Inhaler.remove(aposition);
            notifyItemRemoved(aposition);
            notifyItemRangeChanged(aposition, Inhaler.size());
            writedb.delete("tmp_inhaler", "id = " + aposition, null);
            writedb.close();
        });
    }

    @Override
    public int getItemCount() {
        return Inhaler.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
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

//        void setDetails(InhalerModel added, int pos){
//
//            int imgid = added.getDid();
//            String name = added.getName();
//            int isem = added.getEmergency();
//            String tdesc = "ครั้งละ "+added.getTimes()+" สูด วันละ "+added.getInaday()+" ครั้ง";
//
//            img.setImageResource(resources[imgid]);
//            inhalername.setText(name);
//            if(isem == 0){
//                isemergnecy.setText("ยาพ่นปกติ");
//            }else isemergnecy.setText("ยาพ่นฉุกเฉิน");
//
//            desc.setText(tdesc);
//        }
    }


}
