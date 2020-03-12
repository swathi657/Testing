package com.example.taskapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.taskapplication.items.Items;

public class ItemDetailsActivity extends AppCompatActivity {
    private Items items;
    private TextView itemNameTv;
    private TextView availbleQty;
    private TextView itemCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);


        //our own methods
        initViews();
        getBundleData();

    }

    private void initViews() {
        itemNameTv = (TextView) findViewById(R.id.item_name);
        availbleQty = (TextView) findViewById(R.id.available_qty);
        itemCost = (TextView) findViewById(R.id.item_cost);

    }

    private void getBundleData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        items = (Items) bundle.getSerializable("BUNDLE_DATA");

        //setting ui data
        itemNameTv.setText(items.getItemName());
        itemCost.setText(String.valueOf(items.getCost()));
        availbleQty.setText(String.valueOf(items.getAvilQty()));
    }
}
