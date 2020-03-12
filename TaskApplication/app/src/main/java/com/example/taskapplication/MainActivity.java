package com.example.taskapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskapplication.items.Items;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    private EditText searchEt;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Items> itemsOrginalList = new ArrayList<>();
    private ArrayList<Items> itemsAdapterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //our own methods
        initViews();
        registerListners();
        prepareListItems();

    }

    private void prepareListItems() {

        itemsOrginalList.add(new Items("Apple", 15, 100, 1));
        itemsOrginalList.add(new Items("Shampoo", 35, 50, 2));
        itemsOrginalList.add(new Items("Laptop", 35000, 10, 3));
        itemsOrginalList.add(new Items("Watch", 1590, 10, 4));
//        itemsOrginalList.add( new Items("Apple",15,20,5));
        itemsOrginalList.add(new Items("Sanatizer", 15, 30, 6));
        itemsOrginalList.add(new Items("Key Board", 15, 40, 7));
        itemsOrginalList.add(new Items("Mouse", 15, 50, 8));
        itemsOrginalList.add(new Items("Spects", 15, 60, 9));
        itemsOrginalList.add(new Items("Fit Bit", 15, 25, 10));
        itemsOrginalList.add(new Items("Books", 15, 30, 11));
        itemsOrginalList.add(new Items("Paper Pin", 15, 90, 12));
        itemsOrginalList.add(new Items("Usb Cable", 15, 80, 13));
        itemsOrginalList.add(new Items("Shirts", 15, 110, 14));
        itemsOrginalList.add(new Items("Snickers", 15, 150, 15));
        itemsOrginalList.add(new Items("Toys", 15, 95, 16));



        /*itemNameList.add("");
        itemNameList.add("");
        itemNameList.add("");
        itemNameList.add("");*/
    }

    private void registerListners() {
        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && event.getAction() == KeyEvent.ACTION_DOWN &&
                        (event.getKeyCode() == KeyEvent.KEYCODE_ENTER || event.getKeyCode() == KeyEvent.KEYCODE_NUMPAD_ENTER)) ||
                        actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {

                    String searchText = searchEt.getText().toString().trim();
                    if (searchText.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please Enter item name", Toast.LENGTH_LONG).show();
                        return false;
                    } else {
                        setItemsToRecyclerViewAdapter(searchText);
                    }
                }
                return false;
            }
        });

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()) {
                    recyclerViewAdapter.clearItems();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setItemsToRecyclerViewAdapter(String searchText) {
        itemsAdapterList.clear();
        boolean isItemPresent = false;
        for (int i = 0; i < itemsOrginalList.size(); i++) {
            if (itemsOrginalList.get(i).getItemName().toLowerCase().contains(searchText.toLowerCase())) {

                itemsAdapterList.add(itemsOrginalList.get(i));
                isItemPresent = true;
            }
        }
        if (!isItemPresent) {
            Toast.makeText(getApplicationContext(), "No Items found", LENGTH_LONG).show();
        }
        recyclerViewAdapter.setItemsList(itemsAdapterList);
    }


    public void initViews() {
        searchEt = (EditText) findViewById(R.id.search_et);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerViewAdapter.setCallBack(callBack);
        recyclerViewAdapter.setItemsList(itemsAdapterList);
        recyclerView.setAdapter(recyclerViewAdapter);


    }


    public RecyclerViewAdapter.RecyclerViewCallBack callBack = new RecyclerViewAdapter.RecyclerViewCallBack() {

        @Override
        public void onItemClick(Items items) {
            Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("BUNDLE_DATA", items);
            intent.putExtras(bundle);
            startActivity(intent,bundle);
        }
    };
}
