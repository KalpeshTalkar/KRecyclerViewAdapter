package com.kalpesh.krecyclerviewadapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

/**
 * Created by kalpeshtalkar on 02/07/16.
 */
public interface KRecyclerViewHolderViewTypeCallBack {

    /**
     * Returns the viewType used by the KRecyclerViewAdapter.
     *
     * @param position   Position of the data objec.
     * @param itemObject Data object.
     * @return An int value for your viewType used by KRecyclerViewAdapter.
     */
    int recyclerItemViewType(int position, @NonNull Object itemObject);

    /**
     * Returns the holder used by the KRecyclerViewAdapter.
     *
     * @param parent   Layout of the holder.
     * @param viewType View type.
     * @return An instance of KRecyclerViewHolder used by KRecyclerViewAdapter.
     */
    KRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    /**
     * Called when a holder is displayed.
     *
     * @param holder   KRecyclerViewHolder instance.
     * @param position Position of the holder.
     */
    void onHolderDisplayed(@NonNull KRecyclerViewHolder holder, int position);
}
