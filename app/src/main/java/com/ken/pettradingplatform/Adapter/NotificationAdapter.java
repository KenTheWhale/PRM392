package com.ken.pettradingplatform.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ken.pettradingplatform.R;
import com.ken.pettradingplatform.UpdatePostActivity;
import com.ken.pettradingplatform.configurations.APIClientConfig;
import com.ken.pettradingplatform.controllers.NotificationController;
import com.ken.pettradingplatform.controllers.ShopController;
import com.ken.pettradingplatform.reponses.CustomerPostListResponse;
import com.ken.pettradingplatform.reponses.NotificationResponse;
import com.ken.pettradingplatform.reponses.ShopPostListResponse;
import com.ken.pettradingplatform.requests.NotificationRequest;

import java.text.SimpleDateFormat;
import java.util.List;

public class NotificationAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<NotificationResponse.Notification> noti;
    private String parent;

    NotificationController notificationController;

    public NotificationAdapter(Context context, int layout, List<NotificationResponse.Notification> noti, String parent) {
        this.context = context;
        this.layout = layout;
        this.noti = noti;
        this.parent = parent;
    }
    @Override
    public int getCount() {
        return noti.size();
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
    public View getView(int position, View convertView, ViewGroup group) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);
        ImageView image = convertView.findViewById(R.id.img_Notification);
        TextView shopName = convertView.findViewById(R.id.ShopNameNoti);
        TextView postName = convertView.findViewById(R.id.PostNameNoti);
        TextView address = convertView.findViewById(R.id.AddressNoti);
        TextView money = convertView.findViewById(R.id.MoneyNoti);
        TextView sellerName = convertView.findViewById(R.id.SellerNameNoti);
        TextView sellerPhone = convertView.findViewById(R.id.SellerPhoneNoti);
        TextView meetDate = convertView.findViewById(R.id.MeetDateNoti);


        NotificationResponse.Notification noitfi = noti.get(position);

        image.setImageResource(R.drawable.dog);
        shopName.setText(noitfi.getPostName());
        postName.setText(noitfi.getPostName());
        address.setText(noitfi.getAddress());
        money.setText(noitfi.getMoney()+"");
        sellerName.setText(noitfi.getSellerName());
        sellerPhone.setText(noitfi.getSellerPhone());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(noitfi.getMeetDate());
        meetDate.setText(formattedDate);




        return convertView;
    }

}
