package com.muaythai.app._old;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pi19124 on 13.06.2017.
 */

public class StableArrayAdapter extends ArrayAdapter<String> {

    public StableArrayAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1, new ArrayList<String>());
    }

}
