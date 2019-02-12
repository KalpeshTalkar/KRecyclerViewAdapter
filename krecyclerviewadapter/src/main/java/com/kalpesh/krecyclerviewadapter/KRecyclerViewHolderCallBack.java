package com.kalpesh.krecyclerviewadapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

/**
 * Created by Kalpesh Talkar on 02/06/16.
 */
public interface KRecyclerViewHolderCallBack {

    /**
     * Returns the holder used by the KRecyclerViewAdapter.
     *
     * @param parent Layout of the holder.
     * @return An instance of KRecyclerViewHolder used by KRecyclerViewAdapter.
     */
    KRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent);

    /**
     * Called when a holder is displayed.
     *
     * @param holder   KRecyclerViewHolder instance.
     * @param position Position of the holder.
     */
    void onHolderDisplayed(@NonNull KRecyclerViewHolder holder, int position);

}
