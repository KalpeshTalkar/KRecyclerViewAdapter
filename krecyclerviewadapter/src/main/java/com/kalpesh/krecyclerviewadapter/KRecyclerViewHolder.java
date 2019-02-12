package com.kalpesh.krecyclerviewadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Kalpesh Talkar on 01/06/16.
 */
public class KRecyclerViewHolder extends RecyclerView.ViewHolder {

    /**
     * Initialise holder with view.
     *
     * @param itemView - row to be displayed in recycler view.
     */
    public KRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    /**
     * Initialise holder with view and adapter reference.
     *
     * @param itemView - row displayed in recycler view.
     * @param adapter  - adapter reference.
     */
    public KRecyclerViewHolder(@NonNull View itemView, @NonNull KRecyclerViewAdapter adapter) {
        super(itemView);
    }

    /**
     * Inherit your view holder from this KRecyclerViewHolder.
     * Override this method #setData in your viewHolder class.
     * <p>
     * Set your text and data in this method.
     * This method will be automatically called by the
     * KRecyclerViewAdapter in  {@link KRecyclerViewAdapter().#onCreateViewHolder() }
     *
     * @param context    current application context.
     * @param itemObject The object for the holder.
     *                   typecast this object into your model class object.
     */
    protected void setData(@NonNull Context context, @NonNull Object itemObject) {
    }

    /**
     * Called when user clicks on a row.
     * Override this method in your holder to get event when a holder is selected.
     *
     * @param context   - view reference.
     * @param selected- true if row/holder is selected.
     */
    protected void setSelected(@NonNull Context context, boolean selected) {
    }

}
