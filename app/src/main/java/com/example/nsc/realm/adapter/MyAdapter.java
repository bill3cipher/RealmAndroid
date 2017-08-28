package com.example.nsc.realm.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nsc.realm.Model.Student;
import com.example.nsc.realm.R;

import java.util.ArrayList;

/**
 * Created by NSC on 8/21/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context mContext;
    private CardView cvItem;

    private ArrayList<Student> studentArrayList = new ArrayList<>();

    public MyAdapter(Context context, ArrayList<Student> studentArrayList) {
        this.mContext = context;
        this.studentArrayList = studentArrayList;
    }


    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position) {
        holder.tvName.setText(String.valueOf(studentArrayList.get(position).getStudentName()));
        holder.tvScore.setText(String.valueOf(studentArrayList.get(position).getStudentScore()));

    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvScore;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvScore = (TextView) itemView.findViewById(R.id.tvScore);
        }
    }

    public void setStudentArrayList(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
        notifyDataSetChanged();
    }
}
