package com.kalpesh.krecyclerviewadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Kalpesh Talkar on 02/07/16.
 */
public class SimpleHolder extends KRecyclerViewHolder {

    private TextView label;
    private CheckBox checkbox;

    SimpleHolder(View itemView) {
        super(itemView);
        label = itemView.findViewById(R.id.label);
        checkbox = itemView.findViewById(R.id.checkbox);
        checkbox.setFocusable(false);
        checkbox.setClickable(false);
        checkbox.setLongClickable(false);
    }

    @Override
    protected void setSelected(@NonNull Context context, boolean selected) {
        super.setSelected(context, selected);
        checkbox.setChecked(selected);
    }

    @Override
    protected void setData(@NonNull Context context, @NonNull Object itemObject) {
        super.setData(context, itemObject);
        if (itemObject instanceof String) {
            String object = (String)itemObject;
            label.setText(object);
        }
    }

}
