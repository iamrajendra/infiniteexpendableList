package com.gambler.infiniteexpendablelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gambler.infiniteexpendablelistview.libs.DataAdapter;
import com.gambler.infiniteexpendablelistview.libs.DataItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<DataItem> mItems;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.data_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mItems = new ArrayList<>();

        List<DataItem> dataItems =  new ArrayList<>();

        dataItems.add(new DataItem("Nisha Verma"));
       DataItem jitendra = new DataItem("Jitendra Verma");
       jitendra.addChild(new DataItem("Alakh Verma"));
        dataItems.add(jitendra);
        dataItems.add(new DataItem("Rajendra Verma"));
        DataItem papaji  =  new DataItem("Rajaram Verma");
        papaji.addChildren(dataItems);

        mItems.add(papaji);
        DataItem mukeshJain =  new DataItem("MUkesh Jain");
        mukeshJain.addChild(new DataItem("Payal Jain"));
        mukeshJain.addChild(new DataItem("Shweta Jain"));
        mItems.add(mukeshJain);
        DataItem vikel = new DataItem("Vikal uncle");
        DataItem kuldeep  =  new DataItem("Kuldeep");
        DataItem surbhi = new DataItem("Surbhi");
        surbhi.addChild(new DataItem("Naina"));
        surbhi.addChild(new DataItem("Ramesh"));
        DataItem amit  =  new DataItem("Amit");
        amit.addChild(new DataItem("Amita"));
        surbhi.addChild(amit);
        kuldeep.addChild(surbhi);
        vikel.addChild(kuldeep);
        mItems.add(vikel);
        mRecyclerView.setAdapter(new DataAdapter(mItems));
    }
}
