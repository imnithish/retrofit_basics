package com.imnstudios.retrofitsc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.TodoHolder> {

    private List<Todo> todoArrayList;

    public RvAdapter(List<Todo> todoArrayList) {
        this.todoArrayList = todoArrayList;
    }

    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new TodoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.TodoHolder holder, int position) {
        String id = String.valueOf(todoArrayList.get(position).getId());
        String  userId = String.valueOf(todoArrayList.get(position).getUserId());
        holder.userId.setText(id);
        holder.id.setText(userId);
        holder.title.setText(todoArrayList.get(position).getTitle());

        String isCompleted;
        if (todoArrayList.get(position).isCompleted()) {
            isCompleted = "completed";
        } else {
            isCompleted = "not completed";
        }
        holder.completed.setText(isCompleted);
    }

    @Override
    public int getItemCount() {
        return todoArrayList.size();
    }

    static class TodoHolder extends RecyclerView.ViewHolder {
        TextView userId;
        TextView id;
        TextView title;
        TextView completed;

        TodoHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.userId);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            completed = itemView.findViewById(R.id.completed);

        }
    }
}
