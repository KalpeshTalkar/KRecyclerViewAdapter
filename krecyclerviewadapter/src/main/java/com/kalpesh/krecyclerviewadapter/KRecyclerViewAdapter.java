package com.kalpesh.krecyclerviewadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kalpesh Talkar on 01/06/16.
 */
public class KRecyclerViewAdapter extends RecyclerView.Adapter<KRecyclerViewHolder> {

    @Nullable
    private KRecyclerViewItemClickListener recyclerItemClickListener;
    private KRecyclerViewHolderCallBack recyclerViewHolderCallBack;
    private KRecyclerViewHolderViewTypeCallBack recyclerViewHolderViewTypeCallBack;

    @NonNull
    private Context context;

    @NonNull
    private List<?> dataSource;
    @NonNull
    private List<Integer> selectedIndexList = new ArrayList<>();

    private boolean displaySingleRow;

    /**
     * Only single selection is allowed if set true.
     */
    public boolean allowsSingleSelection = false;

    /**
     * Allows multiple selection if set true.
     */
    public boolean allowsMultipleSelection = false;

    /**
     * If set true, will deselect if clicked on a selected item.
     */
    public boolean deselectItemOnClickIfSelected = true;

    /**
     * Initialise adapter for recycler view with multiple view holder.
     *
     * @param context                    - view reference.
     * @param dataSource                 - array list of data.
     * @param recyclerViewHolderCallBack - call back to return required holder.
     * @param recyclerItemClickListener  - get event when item is clicked.
     */
    public KRecyclerViewAdapter(@NonNull Context context, @NonNull ArrayList<?> dataSource, @NonNull KRecyclerViewHolderCallBack recyclerViewHolderCallBack, @Nullable KRecyclerViewItemClickListener recyclerItemClickListener) {
        this.context = context;
        this.dataSource = dataSource;
        this.recyclerViewHolderCallBack = recyclerViewHolderCallBack;
        this.recyclerItemClickListener = recyclerItemClickListener;
        displaySingleRow = true;
    }

    /**
     * Initialise adapter for recycler view with multiple view holder.
     *
     * @param context                            - view reference.
     * @param dataSource                         - array list of data.
     * @param recyclerViewHolderViewTypeCallBack - view type call back to display different holders.
     * @param recyclerItemClickListener          - to get event when item is clicked.
     */
    public KRecyclerViewAdapter(@NonNull Context context, @NonNull ArrayList<?> dataSource, @NonNull KRecyclerViewHolderViewTypeCallBack recyclerViewHolderViewTypeCallBack, @Nullable KRecyclerViewItemClickListener recyclerItemClickListener) {
        this.context = context;
        this.dataSource = dataSource;
        this.recyclerViewHolderViewTypeCallBack = recyclerViewHolderViewTypeCallBack;
        this.recyclerItemClickListener = recyclerItemClickListener;
        displaySingleRow = false;
    }

    @NonNull
    @Override
    public KRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (displaySingleRow) {
            return recyclerViewHolderCallBack.onCreateViewHolder(parent);
        } else {
            return recyclerViewHolderViewTypeCallBack.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final KRecyclerViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.setData(context, dataSource.get(position));
        final boolean selected = selectedIndexList.contains(position);
        holder.setSelected(context, selected);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allowsSingleSelection || allowsMultipleSelection) {
                    if (selectedIndexList.contains(position) && deselectItemOnClickIfSelected) {
                        int index = selectedIndexList.indexOf(position);
                        selectedIndexList.remove(index);
                        holder.setSelected(context, false);
                    } else {
                        if (allowsSingleSelection) {
                            if (selectedIndexList.size() > 0) {
                                int previousSelectedIndex = selectedIndexList.get(0);
                                Object parent = holder.itemView.getParent();
                                if (parent instanceof RecyclerView) {
                                    final RecyclerView recyclerView = (RecyclerView) parent;
                                    KRecyclerViewHolder previousHolder = (KRecyclerViewHolder) recyclerView.findViewHolderForAdapterPosition(previousSelectedIndex);
                                    if (previousHolder != null) {
                                        previousHolder.setSelected(context, false);
                                    }
                                }
                            }
                            selectedIndexList.clear();
                        }
                        selectedIndexList.add(position);
                        holder.setSelected(context, true);
                    }
                }
                if (recyclerItemClickListener != null)
                    recyclerItemClickListener.onRecyclerItemClicked(holder, dataSource.get(position), position);
            }
        });
        if (displaySingleRow) {
            if (recyclerViewHolderCallBack != null) {
                recyclerViewHolderCallBack.onHolderDisplayed(holder, position);
            }
        } else {
            if (recyclerViewHolderViewTypeCallBack != null) {
                recyclerViewHolderViewTypeCallBack.onHolderDisplayed(holder, position);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (displaySingleRow) {
            return 0;
        }
        return recyclerViewHolderViewTypeCallBack.recyclerItemViewType(position, dataSource.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSource.size() > 0 ? dataSource.size() : 0;
    }

    /**
     * Set selected positions of the recycler view.
     *
     * @param selectedIndexes - list of indexes to be set selected.
     */
    public void setSelectedIndexes(List<Integer> selectedIndexes) {
        selectedIndexList.clear();
        selectedIndexList.addAll(selectedIndexes);
        notifyDataSetChanged();
    }

    /**
     * Get list of selected indexes.
     *
     * @return - list of selected positions/indexes.
     */
    public List getSelectedIndexes() {
        return selectedIndexList;
    }

    /**
     * Delete item from recycler view.
     *
     * @param position - index of the item to be deleted.
     */
    public void deleteItem(int position) {
        dataSource.remove(position);
        notifyItemRemoved(position);
    }

}
