package com.kalpesh.krecyclerviewadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Kalpesh Talkar on 03/07/16.
 */
public class AnotherHolder extends KRecyclerViewHolder {

    TextView titleLabel, descLabel;

    public AnotherHolder(View itemView) {
        super(itemView);
        titleLabel = (TextView) itemView.findViewById(R.id.titleLabel);
        descLabel = (TextView) itemView.findViewById(R.id.descLabel);
    }

    @Override
    protected void setData(@NonNull Context context, @NonNull Object itemObject) {
        super.setData(context, itemObject);
        if (itemObject instanceof MyObject) {
            MyObject myObject = (MyObject) itemObject;
            titleLabel.setText(myObject.title);
            descLabel.setText(myObject.description);
        }
    }

}
