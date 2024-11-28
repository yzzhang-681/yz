package com.example.ex_three;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    private ListView listView;
    private ActionMode actionMode;
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    private int selectedPosition = -1; // 用于记录选中的项位置

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (selectedPosition == -1) return false; // 确保有选中项

            if (item.getItemId() == R.id.action_edit) {
                showEditDialog(selectedPosition);
                mode.finish(); // 关闭ActionMode
                return true;
            } else if (item.getItemId() == R.id.action_delete) {
                deleteSelectedItem(selectedPosition);
                mode.finish(); // 关闭ActionMode
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            selectedPosition = -1; // 重置选中项位置
        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
        items.add("Item 4");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null) {
                    return false;
                }
                selectedPosition = position; // 保存选中项位置
                actionMode = startActionMode(actionModeCallback);
                view.setSelected(true);
                return true;
            }
        });
    }

    private void showEditDialog(int position) {
        // 实现编辑对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("编辑项目");

        final EditText input = new EditText(this);
        input.setText(items.get(position));
        builder.setView(input);

        builder.setPositiveButton("确定", (dialog, which) -> {
            String newItem = input.getText().toString();
            items.set(position, newItem); // 更新列表项
            adapter.notifyDataSetChanged(); // 刷新ListView
            Toast.makeText(this, "项目已更新", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("取消", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void deleteSelectedItem(int position) {
        items.remove(position); // 从列表中删除项目
        adapter.notifyDataSetChanged(); // 刷新ListView
        Toast.makeText(this, "项目已删除", Toast.LENGTH_SHORT).show();
    }
}
