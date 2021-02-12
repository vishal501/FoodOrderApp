package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderapp.databinding.ActivityDetailBinding;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    TextView quantity;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        quantity = (TextView) findViewById(R.id.quantity);

        final DBHelper helper = new DBHelper(this);


        if(getIntent().getIntExtra("type",0) == 1) {

            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", price));
            binding.foodName.setText(name);
            binding.detailDescription.setText(description);


            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean isInserted = helper.insertOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            name,
                            description,
                            Integer.parseInt(binding.quantity.getText().toString())

                    );

                    if (isInserted)
                        Toast.makeText(DetailActivity.this, "Data Success.", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Error.", Toast.LENGTH_SHORT).show();

                }
            });
        } else {

            /*
    id = 0
    name = 1
    phone = 2
    price = 3
    image = 4
    description = 5
    foodname = 6
    quantity = 7
     */

            final int id = getIntent().getIntExtra("id",0);
            Cursor cursor = helper.getOrderById(id);
            final int image = cursor.getInt(4);
            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", cursor.getInt(3)));
            binding.foodName.setText(cursor.getString(5));
            binding.detailDescription.setText(cursor.getString(6));

            binding.quantity.setText(String.format("%d", cursor.getInt(7)));

            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertBtn.setText("Update Now");
            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUpdated = helper.updateOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.priceLbl.getText().toString()),
                            image,
                            binding.detailDescription.getText().toString(),
                            binding.foodName.getText().toString(),
                            Integer.parseInt(binding.quantity.getText().toString()),
                            id
                            );
                    if (isUpdated)
                        Toast.makeText(DetailActivity.this,"Updated.",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this,"Failed.",Toast.LENGTH_SHORT).show();

                }
            });
        }

    }
    public void increment(View v){
        if (count >= 6) count = 6;
        else count++;
        quantity.setText("" + count);
    }
    public void decrement(View v){
        if (count <= 0) count = 0;
        else count--;

        quantity.setText("" + count);
    }


}