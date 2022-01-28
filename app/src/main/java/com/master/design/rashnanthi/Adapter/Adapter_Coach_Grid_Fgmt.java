//package com.master.design.rashnanthi.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.master.design.rashnanthi.DataModel.CoachDM;
//import com.master.design.rashnanthi.DataModel.CoachGridDM;
//import com.master.design.rashnanthi.Helper.User;
//import com.master.design.rashnanthi.R;
//
//import java.util.ArrayList;
//
//public class Adapter_Coach_Grid_Fgmt extends RecyclerView.Adapter<Adapter_Coach_Grid_Fgmt.ViewHolder> {
//    private Context context;
//    private ArrayList<CoachGridDM> coachGridDMArrayList;
//     User user;
//
//
//    int selectedPosition = 0;
//
//    public Adapter_Coach_Grid_Fgmt(Context context, ArrayList<CoachGridDM> coachGridDMArrayList) {
//        this.context = context;
//        this.coachGridDMArrayList = coachGridDMArrayList;
//        user = new User(context);
//
//    }
//
//
//    @NonNull
//    @Override
//    public Adapter_Coach_Grid_Fgmt.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coach_grid_account_recycle_view_item, parent, false);
//        // set the view's size, margins, paddings and layout parameters
//        Adapter_Coach_Grid_Fgmt.ViewHolder vh = new Adapter_Coach_Grid_Fgmt.ViewHolder(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Adapter_Coach_Grid_Fgmt.ViewHolder holder, int position) {
//        setDetails(holder, position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public int getItemCount() {
//        return coachGridDMArrayList.size();
//    }
//
//
//    private void setDetails(Adapter_Coach_Grid_Fgmt.ViewHolder viewHolder, int position) {
//
//        viewHolder.imageView2.setImageResource(R.drawable.toggle_like_red_black);
//        viewHolder.imgview1.setImageResource(R.drawable.ic_my_account);
//
////        viewHolder.imgview1.setImageResource(coachGridDMArrayList.get(position).getCoach_Image());
////        viewHolder.imageView2.setImageResource(coachGridDMArrayList.get(position).getLike_img());
//
//        viewHolder.imgview1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        viewHolder.imageView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//
//    }
//
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//         ImageView imgview1, imageView2;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//
//            imgview1 = itemView.findViewById(R.id.coach_grid_img);
//
//            imageView2 = itemView.findViewById(R.id.like_coach_grid);
//
//        }
//    }
//}
