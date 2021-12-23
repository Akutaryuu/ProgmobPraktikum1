package com.example.modul1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.modul1.R;
import com.example.modul1.anggotaEntity;

public class anggotaAdapter extends RecyclerView.Adapter<com.example.modul1.anggotaAdapter.ViewAdapter>  {

    private List<anggotaEntity> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }
    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public anggotaAdapter(Context context, List<anggotaEntity> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.fullname.setText(list.get(position).fullname);
        holder.notelp.setText(""+list.get(position).notelp);
        holder.email.setText(list.get(position).email);
        holder.alasan.setText(list.get(position).alasan);
        holder.gender.setText(list.get(position).jeniskelamin);
        holder.hobi.setText(list.get(position).hobi);
        holder.usia.setText(""+list.get(position).usia);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView fullname,notelp,email,alasan,gender,hobi,usia;

        public  ViewAdapter(@NonNull View itemView){
            super(itemView);
            fullname = itemView.findViewById(R.id.nama);
            notelp = itemView.findViewById(R.id.telepon);
            email = itemView.findViewById(R.id.email);
            alasan = itemView.findViewById(R.id.alasan);
            gender = itemView.findViewById(R.id.gender);
            hobi = itemView.findViewById(R.id.hobi);
            usia =itemView.findViewById(R.id.usia);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });

        }

    }

}
