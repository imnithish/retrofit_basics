package com.imnstudios.retrofitsc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    List<Todo> todoArray;
    Button b1;

    public static final String TAG = "RETROFIT_RESPONSE";

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        b1 = findViewById(R.id.b1);
    }

    public void getTodos(View view) {
        b1.setEnabled(false);
        Call<List<Todo>> call = apiInterface.getTodos();

        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                todoArray = response.body();


                Log.i(TAG, "onResponse " + response.body());

                b1.setEnabled(true);
                if (response.isSuccessful())
                    setAdapter();
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.i(TAG, "onFailure " + t.getLocalizedMessage());

                b1.setEnabled(true);
            }

        });


    }

    private void setAdapter() {
        recyclerView.setAdapter(new RvAdapter(todoArray));
    }

    public void getTodoUsingRouteParameter(View view) {
        Call<Todo> call = apiInterface.getTodo(2);
        call.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                Log.i(TAG, "onResponse " + response.body());
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                Log.i(TAG, "onFailure " + t.getLocalizedMessage());
            }
        });
    }

    public void getTodosUsingQuery(View view) {

        Call<List<Todo>> call = apiInterface.getTodosUsingQuery(2, false);
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                Log.i(TAG, "onResponse " + response.body());
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.i(TAG, "onFailure " + t.getLocalizedMessage());
            }
        });

    }

    public void postTodo(View view) {
        Todo todo = new Todo(3, "hey babes", false);

        Call<Todo> call = apiInterface.postTodo(todo);
        call.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                Log.i(TAG, "onResponse " + response.body());
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                Log.i(TAG, "onFailure " + t.getLocalizedMessage());
            }
        });
    }
}
