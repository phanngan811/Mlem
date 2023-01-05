package com.example.mlem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class DirectionAdapter extends ArrayAdapter<String> {
    Context context;
    String[] stepDirection;
    String[] desDirection;
    public DirectionAdapter(@NonNull Context context, String[] stepDirection, String[] desDirection) {
        super(context, R.layout.single_direction, R.id.textView6, stepDirection);
        this.context = context;
        this.stepDirection = stepDirection;
        this.desDirection = desDirection;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View singleItem = convertView;
        DirectionViewHolder holder = null;
        if(singleItem == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.single_direction, parent, false);
            holder = new DirectionViewHolder(singleItem);
            singleItem.setTag(holder);
        }else{
            holder = (DirectionViewHolder) singleItem.getTag();
        }
        holder.stepDirection.setText(stepDirection[position]);
        holder.desDirection.setText(desDirection[position]);
        return singleItem;
    }
}
