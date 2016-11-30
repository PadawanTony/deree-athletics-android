package com.example.nourhan.dereeathletics;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nourhan on 11/23/2016.
 */
public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText user, pass;
    String username, password;
    private Button mSubmit, mRegister;

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    // php login script location:

    // localhost :
    // testing on your device
    // put your local ip instead, on windows, run CMD > ipconfig
    // or in mac's terminal type ifconfig and look for the ip under en0 or en1
    // private static final String LOGIN_URL =
    // "http://xxx.xxx.x.x:1234/webservice/login.php";

    // testing on Emulator:
    // private static final String LOGIN_URL = "http://10.0.2.2:1234/webservice/login.php";

    // testing from a real server:
    private static final String LOGIN_URL ="https://athletics-deree.herokuapp.com/androidLogin";

    // JSON element ids from program of php script:
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // setup input fields
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);

        // setup buttons
        mSubmit = (Button) findViewById(R.id.login);
//        mRegister = (Button) findViewById(R.id.register);

        // register listeners
        mSubmit.setOnClickListener(this);
//        mRegister.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                username = user.getText().toString();
                password = pass.getText().toString();
                new AttemptLogin().execute();
                break;
//            case R.id.register:
//                Intent i = new Intent(this, Register.class);
//                startActivity(i);
//                Log.e("REGISTER:", "You clicked the register button!");
//                break;

            default:
                break;
        }
    }

    class AttemptLogin extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // Check for success tag
            int success;

            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));

                Log.d("request!", "starting");
                // getting product details by making HTTP request
                JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
                        params);

                // check your log for json program
                Log.d("Login attempt", json.toString());

                // json success tag
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Login Successful!", json.toString());
                    // save user data
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(Login.this);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("username", username);
                    edit.apply();

                    /** Get User's ID **/
                    JSONObject userLoggedIn = json.getJSONObject("user");
                    Log.e("userID", userLoggedIn.getString("id"));

                    Intent i = new Intent(Login.this, MainActivity.class);
                    i.putExtra("ID", userLoggedIn.getString("id"));
                    finish();
                    startActivity(i);
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null) {
                Toast.makeText(Login.this, file_url, Toast.LENGTH_LONG).show();
            }
        }

    }

}

