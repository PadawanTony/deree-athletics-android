package com.example.nourhan.dereeathletics;

/**
 * Created by Nourhan on 11/25/16.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Show toast message if we return from request_program **/
        extras = getIntent().getExtras();
        if (extras.getString("message") == null) {
            Log.e("EXTRAS: ", "extras.getString(\"message\") is null");
        } else {
            Toast.makeText(MainActivity.this, extras.getString("message"), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void requestProgram(View view){
        Intent sendRequest = new Intent(this, Request_Program.class);
        sendRequest.putExtra("ID", extras.getString("ID"));
        startActivity(sendRequest);
    }

    public void viewProgram(View view){
        Intent sendRequest = new Intent(this, View_Program.class);
        sendRequest.putExtra("ID", extras.getString("ID"));
        startActivity(sendRequest);
    }

}

