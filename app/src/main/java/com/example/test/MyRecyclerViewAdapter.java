package com.example.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>
{
    private ArrayList<Employee> mEmployeesList = new ArrayList<>();

    public MyRecyclerViewAdapter(ArrayList<Employee> mList)
    {
        this.mEmployeesList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row1, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        String name = "Name: " + mEmployeesList.get(position).getName();
        holder.nameTextView.setText(name);

        String gender = "Gender: " + mEmployeesList.get(position).getPrice();
        holder.genderTextView.setText(gender);

        String age = "Age: " + mEmployeesList.get(position).getMaxprice();
        holder.ageTextView.setText(age);
    }

    @Override
    public int getItemCount()
    {
        return mEmployeesList.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView nameTextView;
        private TextView genderTextView;
        private TextView ageTextView;

        public MyViewHolder(View view)
        {
            super(view);

            nameTextView = view.findViewById(R.id.textViewName);
            genderTextView = view.findViewById(R.id.textViewGender);
            ageTextView = view.findViewById(R.id.textViewAge);
        }
    }
}
