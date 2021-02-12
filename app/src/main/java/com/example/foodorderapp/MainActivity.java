package com.example.foodorderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodorderapp.Adapters.MainAdapter;
import com.example.foodorderapp.Adapters.OrdersAdapter;
import com.example.foodorderapp.Models.MainModel;
import com.example.foodorderapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.grilledtikka,"Paneer Tikka", "34","Delicious tandoori snack"));
        list.add(new MainModel(R.drawable.pizza2,"Pizza", "99","Extra Cheese with saucy"));
        list.add(new MainModel(R.drawable.samosa,"Samosa", "15","Flaky and tender fried samosa "));
        list.add(new MainModel(R.drawable.vegcutlet,"Veg Cutlet", "35","Vegetable cutlet or veg patties delicious snack "));
        list.add(new MainModel(R.drawable.harabharakabab,"Hara Bhara Kabab", "25","Hara Bhara Kabab Pan-fried spiced "));
        list.add(new MainModel(R.drawable.burger,"Burger", "34","Extra Cheese with saucy"));
        list.add(new MainModel(R.drawable.cheeseballs,"Cheese Balls", "34","Cheese Balls tasty snack"));
        list.add(new MainModel(R.drawable.breadpakora,"Bread Pakoda", "34","Bread Pakora is a popular and tasty snack"));
        list.add(new MainModel(R.drawable.springrolls,"Spring Rolls", "50","Spring Rolls Indo Chinese snack"));
        list.add(new MainModel(R.drawable.meduvada,"Medu Vada", "99","Medu Vada crispy,fluffy snack "));
        list.add(new MainModel(R.drawable.dhokla,"Dhokla", "40","Khaman dhokla savory snack"));

        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
            startActivity(new Intent(MainActivity.this, OrderActivity.class));
            break;

        }
        return super.onOptionsItemSelected(item);
    }
}