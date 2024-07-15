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
import com.ken.pettradingplatform.UpdatePostActivity;
import com.ken.pettradingplatform.ViewPostDetailActivity;
import com.ken.pettradingplatform.configurations.APIClientConfig;
import com.ken.pettradingplatform.controllers.ShopController;
import com.ken.pettradingplatform.reponses.CustomerPostListResponse;
import com.ken.pettradingplatform.reponses.ShopPostListResponse;

import java.util.List;

public class ShopPostAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ShopPostListResponse.Post> posts;
    private String parent;

    ShopController shopController;

    public ShopPostAdapter(Context context, int layout, List<ShopPostListResponse.Post> posts, String parent) {
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

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup group) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);
        ImageView image = convertView.findViewById(R.id.img_postShop);
        TextView name = convertView.findViewById(R.id.txt_NamePostShop);
        TextView price = convertView.findViewById(R.id.txt_pricePostShop);
        ImageButton btnDeletePost = image.findViewById(R.id.btn_deletePost);
        ImageButton btnUpdatePost = image.findViewById(R.id.btn_updatePost);

        ShopPostListResponse.Post post = posts.get(position);

        image.setImageResource(R.drawable.dog);
        name.setText(post.getTypeName());
        price.setText(post.getPrice() + "");

        shopController = APIClientConfig.getClient().create(ShopController.class);
        btnDeletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopController.cancelPost();
            }
        });


        btnUpdatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdatePostActivity.class);
                intent.putExtra("postId", post.getId());
                intent.putExtra("parent", parent);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
