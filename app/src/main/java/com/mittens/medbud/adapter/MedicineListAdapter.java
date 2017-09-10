package com.mittens.medbud.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.TextView;

import com.mittens.medbud.R;
import com.mittens.medbud.data.Mapper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mit on 9/10/17.
 */

public class MedicineListAdapter extends RecyclerView.Adapter<MedicineListAdapter.ViewHolder> {
    private static final String TAG = "CaseFileAdapter";
    private ArrayList<Mapper> medications;
    private Context context;


    public MedicineListAdapter(ArrayList<Mapper> medications, Context context) {
        this.medications = medications;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewTag = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.medication_card, parent, false);
        return new ViewHolder((viewTag));
    }

    @Override
    public void onBindViewHolder(MedicineListAdapter.ViewHolder holder, int position) {
        final Mapper medication = medications.get(position);

        if (medication == null) {
            Log.e(TAG, "fuck");
        } else {


        }

        holder.dosage.setText("some");
        holder.name.setText("Homer");


    }

    @Override
    public int getItemCount() {
        return medications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.dosage)
        TextView dosage;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.quantity)
        TextView quantity;
        @Bind(R.id.taken_today)
        ImageSwitcher taken;
        @Bind(R.id.name)
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
