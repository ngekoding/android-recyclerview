package com.example.nur.derplist.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.nur.derplist.R;
import com.example.nur.derplist.adapter.InspirationAdapter;
import com.example.nur.derplist.handler.DatabaseHandler;
import com.example.nur.derplist.model.Inspiration;
import com.example.nur.derplist.model.InspirationData;

import java.util.List;

public class ListActivity extends AppCompatActivity implements InspirationAdapter.ItemClickCallback {

    private RecyclerView recyclerView;
    private InspirationAdapter adapter;
    private List<Inspiration> inspirationList;
    private DatabaseHandler db;

    public static final String LOG = "NOOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Initialize database
        db = new DatabaseHandler(this);
        // Dummy data
        db.addInspiration(new Inspiration("John Doe", "Lorem ipsum dolor sit amet"));
        db.addInspiration(new Inspiration("Anjana Vakil", "Lorem ipsum dolor sit amet"));
        // Get all data
        inspirationList = db.getAllInspirations();

        adapter = new InspirationAdapter(inspirationList);
        adapter.setItemClickCallback(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Inspiration inspiration = inspirationList.get(position);

        Intent intent = new Intent(ListActivity.this, DetailActivity.class);

        Bundle extras = new Bundle();
        extras.putInt("EXTRA_ID", inspiration.getId());
        extras.putString("EXTRA_NAME", inspiration.getName());
        extras.putString("EXTRA_CONTENT", inspiration.getContent());

        intent.putExtra("BUNDLE_EXTRAS", extras);

        startActivity(intent);
    }

    @Override
    public void ontBookmarkClick(int position) {
        Inspiration data = inspirationList.get(position);
        if (data.isBookmark()) {
            data.setBookmark(false);
        } else {
            data.setBookmark(true);
        }
        inspirationList.set(position, data);
        db.updateInspiration(data);
        adapter.notifyDataSetChanged();
    }
}
