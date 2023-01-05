package com.example.mlem;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DirectionViewHolder {
    TextView stepDirection;
    TextView desDirection;

    DirectionViewHolder(View v){
        stepDirection = v.findViewById(R.id.textView6);
        desDirection = v.findViewById(R.id.textView13);
    }
}
