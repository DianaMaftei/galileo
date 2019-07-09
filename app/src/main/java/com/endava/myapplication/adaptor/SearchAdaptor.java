package com.endava.myapplication.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.endava.myapplication.R;
import com.endava.myapplication.model.Employee;

import java.util.List;

class SearchViewHolder extends RecyclerView.ViewHolder {

    public TextView name, floor, project;


    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.name);
        floor = (TextView)itemView.findViewById(R.id.floor);
        project = (TextView)itemView.findViewById(R.id.project);
    }
}
public class SearchAdaptor extends RecyclerView.Adapter<SearchViewHolder> {

    private Context context;
    private List<Employee> employees;

    public SearchAdaptor(Context context, List<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.app_search_employee, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.name.setText(employees.get(position).getName());
        holder.floor.setText(employees.get(position).getFloor());
        holder.project.setText(employees.get(position).getProject());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }
}
