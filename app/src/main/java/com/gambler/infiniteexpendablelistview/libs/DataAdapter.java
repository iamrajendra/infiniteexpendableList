package com.gambler.infiniteexpendablelistview.libs;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.gambler.infiniteexpendablelistview.R;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {
    private List<DataItem> mItems;
    private List<DataItem> mItemsSearch;

    public DataAdapter(List<DataItem> items) {
        mItems = items;
        mItemsSearch = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemViewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemViewType, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        DataItem dataItem = mItemsSearch.get(pos);
        viewHolder.post(dataItem);

    }

    @Override
    public int getItemCount() {
        return mItemsSearch.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item;
    }

    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                List<DataItem> filteredList = new ArrayList<>();
                if (charString.isEmpty()) {
                    mItemsSearch = mItems;
                } else {
                    for (DataItem item : getAllData(mItems)
                    ) {

                        if (item.getTitle().toLowerCase().trim().contains(charString.toLowerCase().trim())) {
                            filteredList.add(item);
                        }


                    }
                    mItemsSearch = filteredList;
                }


                FilterResults filterResults = new FilterResults();
                filterResults.values = mItemsSearch;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mItemsSearch = (ArrayList<DataItem>) filterResults.values;
                notifyDataSetChanged();
            }
        }

                ;
    }

    private List<DataItem> getAllData(List<DataItem> items) {
        List<DataItem> dataItems = new ArrayList<>();

        for (DataItem data :
                items) {
            if (data.hasChild()) {

                dataItems.addAll(data.getChild());


            }
        }


//        dataItems.addAll(items);
        return dataItems;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextView;
        private DataItem mDataItem;
        private RecyclerView mRecyclerView;
        private  FlipImageView mFlipImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.title_tv);
            mRecyclerView = itemView.findViewById(R.id.list_item);
            mFlipImageView = itemView.findViewById(R.id.flip);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            itemView.setOnClickListener(this);
        }

        public void post(DataItem dataItem) {
            mDataItem = dataItem;
            mTextView.setText(dataItem.getTitle());
if (dataItem.hasChild()){
    mFlipImageView.setVisibility(View.VISIBLE);
}

        }

        @Override
        public void onClick(View v) {
            if (mDataItem.hasChild()) {
                if (mRecyclerView.getAdapter() == null) {
                    mRecyclerView.setAdapter(new DataAdapter(mDataItem.getChild()));
                    mFlipImageView.setStatus(true);
                } else {
                    if (mRecyclerView.getVisibility() == View.VISIBLE) {
                        mRecyclerView.setVisibility(View.GONE);
                        mFlipImageView.setStatus(false);
                    } else {
                        mRecyclerView.setVisibility(View.VISIBLE);
                        mFlipImageView.setStatus(true);
                    }
                }
            }
        }
    }
}
