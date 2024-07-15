package com.ken.pettradingplatform.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ken.pettradingplatform.R;
import com.ken.pettradingplatform.reponses.CustomerPostListResponse;

import java.util.List;

public class CustomerPostAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<CustomerPostListResponse> responses;

    public CustomerPostAdapter(Context context, int layout, List<CustomerPostListResponse> responses) {
        this.context = context;
        this.layout = layout;
        this.responses = responses;
    }

    @Override
    public int getCount() {
        return responses.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup group) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        ImageView image = view.findViewById(R.id.imageView);

        return null;
    }
}
