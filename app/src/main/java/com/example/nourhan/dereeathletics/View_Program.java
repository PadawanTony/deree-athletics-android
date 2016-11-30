package com.example.nourhan.dereeathletics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nourhan on 1/25/2016.
 */
public class View_Program extends AppCompatActivity {

    Bundle extras;

    String trainerComments, trainerName, ID;

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_REQUEST = "program";
    private static final String TAG_ID = "ID";
    private static final String TAG_TRAINER_COMMENTS = "program";
    private static final String TAG_TRAINER_NAME = "trainer";

    // requests JSONArray
    String program = null;

    // Progress Dialog
    private ProgressDialog pDialog;
    private ProgressDialog prDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    // url to get all requests list
    //ToDO: Change the URL
    private static String url_get_request = "https://athletics-deree.herokuapp.com/androidViewProgram";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_progran);

        if (savedInstanceState == null) {
            extras = getIntent().getExtras();
            if (extras == null) {
                Log.e("EXTRAS: ", "extras in null");
            } else {
                ID = extras.getString("ID");
            }
        } else {
            ID = "7";
        }

        //Load Additional Details
        new LoadSingleRequest().execute();
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

    /**
     * Background Async Task to Load Single Request by making HTTP Request
     */
    class LoadSingleRequest extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prDialog = new ProgressDialog(View_Program.this);
            prDialog.setMessage("Loading request. Please wait...");
            prDialog.setIndeterminate(false);
            prDialog.setCancelable(false);
            prDialog.show();
        }

        /**
         * getting request details from url
         */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ID", ID));

            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_get_request, "POST", params);

            // Check your log cat for JSON program
            Log.e("JSON response: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1)  // program found
                {
                    program = json.getString(TAG_TRAINER_COMMENTS);
                    Log.e("Program: ", program);
                    trainerName = json.getString(TAG_TRAINER_NAME);
                    Log.e("Program: ", trainerName);
                }
                else
                {
                    Toast.makeText(View_Program.this, "No program found!", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {

            //Set Values To TextViews
            TextView trainerCommentsTV = (TextView) findViewById(R.id.trainerComments);
            trainerCommentsTV.setText(program);
            TextView trainerNameTV = (TextView) findViewById(R.id.trainerName);
            trainerNameTV.setText(trainerName);
            Log.e("Response: ", "ID: " + ID + " trainerComments " + program + " trainerName" + trainerName);

            // dismiss the dialog after getting all requests
            prDialog.dismiss();
        }

    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("ID", extras.getString("ID"));
        startActivity(intent);
    }

}
