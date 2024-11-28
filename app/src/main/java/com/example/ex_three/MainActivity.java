package com.example.ex_three;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);

        List<Map<String, Object>> data = new ArrayList<>();
        String[] name = new String[]{"cat", "dog", "elephant", "lion", "tiger", "monkey"};
        int[] picture = new int[]{R.drawable.cat, R.drawable.dog, R.drawable.elephant, R.drawable.lion, R.drawable.tiger, R.drawable.monkey};

        //将数据放入map再放入data
        for (int i = 0; i < name.length; i++) {
            Map<String, Object> map = new HashMap();
            map.put("name", name[i]);
            map.put("picture", picture[i]);
            data.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), data, R.layout.item_layout, new String[]{"name", "picture"}, new int[]{R.id.textView, R.id.imageView});

        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, map.get("name").toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}



//test
