package cnmi.it.asthmaprototype.Adapters;

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

public class InhalerCardAdapter extends RecyclerView.Adapter<InhalerCardAdapter.AddHolder>{
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

    String[] resourcesname = {"Flixotide Evohaler", "Aeronide", "Easyhaler Budesonide", "Easyhaler Salbutamol", "Seretide Evohaler", "Seretide Accuhaler", "Ventolin"};

    public InhalerCardAdapter(Context context, ArrayList<InhalerModel> arraylist){
        this.context = context;
        this.Inhaler = arraylist;
    }

    @NonNull
    @Override
    public InhalerCardAdapter.AddHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inhaler_card_layout, parent, false);
        return new AddHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InhalerCardAdapter.AddHolder holder, int position) {
        InhalerModel inhaler = Inhaler.get(position);
        holder.setDetails(inhaler, position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AddHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView inhalername, isemergnecy, desc;
        public AddHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.inhalerimg);
            inhalername = itemView.findViewById(R.id.inhalerName);
            isemergnecy = itemView.findViewById(R.id.isemergencyText);
            desc = itemView.findViewById(R.id.descText);
        }

        void setDetails(InhalerModel added, int pos){
            int imgid = added.getDid();
            String name = added.getName();
            int isem = added.getEmergency();
            String tdesc = "ครั้งละ "+added.getTimes()+" สูด วันละ "+added.getInaday()+" ครั้ง";

            int resourceId;
            switch(imgid){
                case 0:
                    resourceId = resources[0];
                    break;
                case 1:
                    resourceId = resources[1];
                    break;
                case 2:
                    resourceId = resources[2];
                    break;
                case 3:
                    resourceId = resources[3];
                    break;
                case 4:
                    resourceId = resources[4];
                    break;
                case 5:
                    resourceId = resources[5];
                    break;
                case 6:
                    resourceId = resources[6];
                    break;
                default:
                    resourceId = resources[7];
                    break;
            }
            img.setImageResource(resourceId);
            inhalername.setText(name);
            if(isem == 0){
                isemergnecy.setText("ยาพ่นปกติ");
            }else isemergnecy.setText("ยาพ่นฉุกเฉิน");

            desc.setText(tdesc);
        }
    }
}
