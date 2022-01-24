//package com.master.design.rashnanthi.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.master.design.rashnanthi.DataModel.NotificationDM;
//import com.master.design.rashnanthi.Models.DrawerMenu;
//import com.master.design.rashnanthi.R;
//
//import java.util.ArrayList;
//
//public class Adapter_Notificaion extends BaseAdapter {
//
//    private Context context;
//    private ArrayList<NotificationDM> notificationDMS;
//
//    public Adapter_Notificaion(Context context, ArrayList<NotificationDM> menus) {
//        this.context = context;
//        this.notificationDMS = notificationDMS;
//    }
//
//    @Override
//    public int getCount() {
//        return notificationDMS.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return notificationDMS.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView == null) {
//            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.notification_recycle_view_item, parent, false);
//            viewHolder = new ViewHolder(convertView);
//
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        setDetails(viewHolder, position);
//        return convertView;
//    }
//
//    private void setDetails(ViewHolder viewHolder, int position) {
//        NotificationDM NotificationDM = notificationDMS.get(position);
//        viewHolder.txt_title1.setText(NotificationDM.getText1());
//        viewHolder.txt_title2.setText(NotificationDM.getName());
//        viewHolder.txt_title3.setText(NotificationDM.getName());
//
//    }
//
//    private static class ViewHolder {
//        private TextView txt_title1,txt_title2,txt_title3;
//
//
//        private ViewHolder(View view) {
//            txt_title1 = (TextView) view.findViewById(R.id.notification_title_Txt);
//            txt_title2 = (TextView) view.findViewById(R.id.notification_time_Txt);
//            txt_title3 = (TextView) view.findViewById(R.id.notificatiom_messege_Txt);
//
//
//        }
//    }
//}
