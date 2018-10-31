package com.iteso.pdm18_scrollabletabs;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.tools.CategoryControl;
import com.iteso.pdm18_scrollabletabs.tools.Constant;
import com.iteso.pdm18_scrollabletabs.tools.DatabaseHandler;
import com.iteso.pdm18_scrollabletabs.tools.ItemProductControl;
import com.iteso.pdm18_scrollabletabs.tools.StoreControl;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTechnology extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ItemProduct> products;
    AdapterProduct adapterProduct;

    public FragmentTechnology() {
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

        products = itemProductControl.getItemProductsByCategory(0, databaseHandler);

        if (products.size() == 0) {
            StoreControl storeControl = new StoreControl();
            Store store = storeControl.getStoreById(1, databaseHandler);
            Category category = new Category(0);

            // int code, String title, String description, Integer image, Store store, Category category) {
            itemProductControl.addItemProduct(
                    new ItemProduct(1, "Mac", "Lorem Ipsum",
                            Constant.TYPE_MAC,
                            store,
                            category),
                    databaseHandler);
            itemProductControl.addItemProduct(
                    new ItemProduct(2, "Alienware", "Lorem Ipsum",
                            Constant.TYPE_ALIENWARE,
                            store,
                            category),
                    databaseHandler);
            itemProductControl.addItemProduct(
                    new ItemProduct(3, "Lanix", "Lorem Ipsum",
                            Constant.TYPE_ALIENWARE,
                            store,
                            category),
                    databaseHandler);
            products = itemProductControl.getItemProductsByCategory(0, databaseHandler);
        }

        adapterProduct = new AdapterProduct(Constant.FRAGMENT_TECHNOLOGY, getActivity(), products);
        recyclerView.setAdapter(adapterProduct);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ItemProduct itemProduct = data.getParcelableExtra(Constant.EXTRA_PRODUCT);
        Iterator<ItemProduct> iterator = products.iterator();
        int position = 0;
        while (iterator.hasNext()) {
            ItemProduct item = iterator.next();
            if (item.getCode() == itemProduct.getCode()) {
                products.set(position, itemProduct);
                break;
            }
            position++;
        }
        adapterProduct.notifyDataSetChanged();

    }

}
