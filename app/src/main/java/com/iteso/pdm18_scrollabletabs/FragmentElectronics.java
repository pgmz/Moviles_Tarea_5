package com.iteso.pdm18_scrollabletabs;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.tools.Constant;
import com.iteso.pdm18_scrollabletabs.tools.DatabaseHandler;
import com.iteso.pdm18_scrollabletabs.tools.ItemProductControl;
import com.iteso.pdm18_scrollabletabs.tools.StoreControl;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentElectronics extends Fragment {


    RecyclerView recyclerView;

    public FragmentElectronics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_technology, container, false);
        recyclerView = rootView.findViewById(R.id.fragment_recycler);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        DatabaseHandler databaseHandler = DatabaseHandler.getInstance(this.getContext());
        ItemProductControl itemProductControl = new ItemProductControl();
        ArrayList<ItemProduct> products = itemProductControl.getItemProductsByCategory(2, databaseHandler);

        if (products.size() == 0) {
            StoreControl storeControl = new StoreControl();
            Store store = storeControl.getStoreById(3, databaseHandler);
            Category category = new Category(2);

            itemProductControl.addItemProduct(
                    new ItemProduct(6, "Refrigerador", "Lorem Ipsum",
                            Constant.TYPE_REFRIGERATOR,
                            store,
                            category),
                    databaseHandler);
            itemProductControl.addItemProduct(
                    new ItemProduct(7, "Micro", "Lorem Ipsum",
                            Constant.TYPE_MICRO,
                            store,
                            category),
                    databaseHandler);

        }

        products = itemProductControl.getItemProductsByCategory(2, databaseHandler);

        AdapterProduct adapterProduct = new AdapterProduct(Constant.FRAGMENT_ELECTRONICS, getActivity(), products);
        recyclerView.setAdapter(adapterProduct);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
