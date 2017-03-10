package com.example.nur.derplist.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.nur.derplist.R;
import com.example.nur.derplist.handler.DatabaseHandler;

public class DetailActivity extends AppCompatActivity {

    private int detailId;

    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = new DatabaseHandler(this);

        Bundle extras = getIntent().getBundleExtra("BUNDLE_EXTRAS");

        ((TextView) findViewById(R.id.tv_detail_content)).setText(extras.getString("EXTRA_CONTENT"));
        ((TextView) findViewById(R.id.tv_detail_name)).setText(extras.getString("EXTRA_NAME"));

        this.detailId = extras.getInt("EXTRA_ID");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteInspiration();
                break;
        }
        return true;
    }

    private void deleteInspiration() {
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Deleting...");
        pDialog.setCancelable(false);
        pDialog.show();
        db.deleteInspiration(this.detailId);
        pDialog.hide();
        // Go back
        startActivity(new Intent(this, ListActivity.class));
    }
}
