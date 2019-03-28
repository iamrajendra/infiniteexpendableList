package com.gambler.infiniteexpendablelistview.libs;

import java.util.ArrayList;
import java.util.List;

public class DataItem implements Node {
    private List<DataItem> mItems;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DataItem() {
        mItems = new ArrayList<>();

    }

    public DataItem(String title) {
        mItems = new ArrayList<>();
        this.title = title;

    }

    public DataItem(List<DataItem> items) {
        mItems = items;

    }

    public DataItem(DataItem item) {
        mItems = new ArrayList<>();
        mItems.add(item);


    }

    @Override
    public boolean hasChild() {
        if (mItems.size() > 0) {
            return true;
        }

        return false;
    }

    public void removeItem(DataItem item) {
        mItems.remove(item);

    }

    public void addChildren(List<DataItem> dataItems) {
        mItems.addAll(dataItems);
    }

    public void addChild(DataItem dataItems) {
        mItems.add(dataItems);
    }


    public List<DataItem> getChild() {
        return mItems;
    }
}
