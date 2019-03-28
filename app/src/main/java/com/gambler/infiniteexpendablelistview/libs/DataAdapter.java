package com.gambler.infiniteexpendablelistview.libs;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gambler.infiniteexpendablelistview.R;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<DataItem> mItems;

    public DataAdapter(List<DataItem> items) {
        mItems = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemViewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemViewType, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        DataItem dataItem = mItems.get(pos);
        viewHolder.post(dataItem);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item;
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
