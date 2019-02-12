package com.kalpesh.krecyclerviewadapter;

import android.support.annotation.NonNull;

/**
 * Created by Kalpesh Talkar on 01/06/16.
 */
public interface KRecyclerViewItemClickListener {

    /**
     * KRecyclerView onItemClickListener call back.
     *
     * @param holder       The holder on which the user clicked.
     * @param itemObject   The object on the clicked position.
     * @param itemPosition The object on the clicked position.
     */
    void onRecyclerItemClicked(@NonNull KRecyclerViewHolder holder, @NonNull Object itemObject, int itemPosition);
}
