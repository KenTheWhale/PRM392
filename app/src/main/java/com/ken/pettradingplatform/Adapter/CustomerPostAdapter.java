package com.ken.pettradingplatform.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ken.pettradingplatform.R;
import com.ken.pettradingplatform.ViewPostDetailActivity;
import com.ken.pettradingplatform.reponses.CustomerPostListResponse;

import java.util.List;

public class CustomerPostAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<CustomerPostListResponse.Post> posts;
    private String parent;

    public CustomerPostAdapter(Context context, int layout, List<CustomerPostListResponse.Post> posts, String parent) {
        this.context = context;
        this.layout = layout;
        this.posts = posts;
        this.parent = parent;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int i, View view, ViewGroup group) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        ImageView image = view.findViewById(R.id.imageView);
        TextView name = view.findViewById(R.id.nameTextView);
        TextView price = view.findViewById(R.id.priceTextView);
        ImageButton btnDetail = view.findViewById(R.id.imageButton);

        CustomerPostListResponse.Post post = posts.get(i);

        image.setImageResource(R.drawable.dog);
        name.setText(post.getTypeName());
        price.setText(post.getPrice() + "");
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewPostDetailActivity.class);
                intent.putExtra("postId", post.getId());
                intent.putExtra("parent", parent);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
