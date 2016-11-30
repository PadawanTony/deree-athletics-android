package com.example.nourhan.dereeathletics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nourhan on 11/25/16.
 */
public class Request_Program extends AppCompatActivity {

    private EditText activitiesV, commentsV;
    private NumberPicker heightV, weightV;
    private CheckBox mondayV, tuesdayV, wednesdayV, thursdayV, fridayV, saturdayV, sundayV;
    private RadioButton pastExerciseYesV, pastExerciseNoV, currentlyExercisingYesV, currentlyExercisingNoV, currentExercisingIntensityLightV, currentExercisingIntensityModerateV, currentExercisingIntensityHeavyV;
    private RadioGroup pastExerciseV, currentlyExercisingV, currentExercisingIntensityV;

    JSONParser jsonParser = new JSONParser();
    private static String url_create_program = "https://athletics-deree.herokuapp.com/submitProgramAndroid";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private String activities, comments;
    private int height, weight;
    private boolean monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    private int mondayInt, tuesdayInt, wednesdayInt, thursdayInt, fridayInt, saturdayInt, sundayInt;
    private int pastExerciseRadioButtonID, currentlyExercisingRadioButtonID, currentExercisingIntensityRadioButtonID, indxPastExerciseRadioButton, indxcurrentlyExercisingRadioButton, indxCurrentExercisingIntensityRadioButton;


    public void initializeEditTexts() {
        activitiesV = (EditText) findViewById(R.id.activitiesV);
        commentsV = (EditText) findViewById(R.id.commentsV);
    }

    public void initializeNumberpickers() {
        heightV = (NumberPicker) findViewById(R.id.heightV);
        weightV = (NumberPicker) findViewById(R.id.weightV);
    }

    public void initializeCheckboxes() {
        mondayV = (CheckBox) findViewById(R.id.mondayV);
        tuesdayV = (CheckBox) findViewById(R.id.tuesdayV);
        wednesdayV = (CheckBox) findViewById(R.id.wednesdayV);
        thursdayV = (CheckBox) findViewById(R.id.thursdayV);
        fridayV = (CheckBox) findViewById(R.id.fridayV);
        saturdayV = (CheckBox) findViewById(R.id.saturdayV);
        sundayV = (CheckBox) findViewById(R.id.sundayV);
    }

    public void initializeRadioButtons() {
        pastExerciseYesV = (RadioButton) findViewById(R.id.pastExerciseYesV);
        pastExerciseNoV = (RadioButton) findViewById(R.id.pastExerciseNoV);
        currentlyExercisingYesV = (RadioButton) findViewById(R.id.currentlyExercisingYesV);
        currentlyExercisingNoV = (RadioButton) findViewById(R.id.currentlyExercisingNoV);
        currentExercisingIntensityLightV = (RadioButton) findViewById(R.id.currentExercisingIntensityLightV);
        currentExercisingIntensityModerateV = (RadioButton) findViewById(R.id.currentExercisingIntensityModerateV);
        currentExercisingIntensityHeavyV = (RadioButton) findViewById(R.id.currentExercisingIntensityHeavyV);
    }

    public void initializeRadioGroups() {
        pastExerciseV = (RadioGroup) findViewById(R.id.pastExerciseV);
        currentlyExercisingV = (RadioGroup) findViewById(R.id.currentlyExercisingV);
        currentExercisingIntensityV = (RadioGroup) findViewById(R.id.currentExercisingIntensityV);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_program);

        initializeEditTexts();
        initializeNumberpickers();
        initializeCheckboxes();
        initializeRadioButtons();
        initializeRadioGroups();

        /** Configure heightV NumberPicker **/
        heightV.setMinValue(100);
        heightV.setMaxValue(250);
        heightV.setValue(160);
        heightV.setWrapSelectorWheel(false);

        /** Configure weightV NumberPicker **/
        weightV.setMinValue(40);
        weightV.setMaxValue(200);
        weightV.setValue(70);
        weightV.setWrapSelectorWheel(false);
    }

    public void cancelRequest(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void submitRequest(View view) {

        /** Get Values of EditTexts **/
        activities = activitiesV.getText().toString(); //true or null
        comments = commentsV.getText().toString(); //true or null
        Log.e("activities", activities);
        Log.e("comments", comments);

        /** Get Values of NumberPickers **/
        height = heightV.getValue(); //default 160
        weight = weightV.getValue(); //default 70
        Log.e("heightV", String.valueOf(height));
        Log.e("weightV", String.valueOf(weight));

        /** Get Values of Checkboxes **/
        monday = mondayV.isChecked(); //true or false
        mondayInt = (monday) ? 1 : 0;
        tuesday = tuesdayV.isChecked(); //true or false
        tuesdayInt = (tuesday) ? 1 : 0;
        wednesday = wednesdayV.isChecked(); //true or false
        wednesdayInt = (wednesday) ? 1 : 0;
        thursday = thursdayV.isChecked(); //true or false
        thursdayInt = (thursday) ? 1 : 0;
        friday = fridayV.isChecked(); //true or false
        fridayInt = (friday) ? 1 : 0;
        saturday = saturdayV.isChecked(); //true or false
        saturdayInt = (saturday) ? 1 : 0;
        sunday = sundayV.isChecked(); //true or false
        sundayInt = (sunday) ? 1 : 0;
        Log.e("monday", String.valueOf(monday));
        Log.e("tuesday", String.valueOf(tuesday));
        Log.e("wednesday", String.valueOf(wednesday));
        Log.e("thursday", String.valueOf(thursday));
        Log.e("friday", String.valueOf(friday));
        Log.e("saturday", String.valueOf(saturday));
        Log.e("sunday", String.valueOf(sunday));
        Log.e("mondayInt", String.valueOf(mondayInt));
        Log.e("tuesdayInt", String.valueOf(tuesdayInt));
        Log.e("wednesdayInt", String.valueOf(wednesdayInt));
        Log.e("thursdayInt", String.valueOf(thursdayInt));
        Log.e("fridayInt", String.valueOf(fridayInt));
        Log.e("saturdayInt", String.valueOf(saturdayInt));
        Log.e("sundayInt", String.valueOf(sundayInt));

        /** Get Values of RadioButtons **/
        //pastExercise
        pastExerciseRadioButtonID = pastExerciseV.getCheckedRadioButtonId();
        View pastExerciseRadioButton = pastExerciseV.findViewById(pastExerciseRadioButtonID);
        indxPastExerciseRadioButton = pastExerciseV.indexOfChild(pastExerciseRadioButton);
        Log.e("indexPastExerciseRB", String.valueOf(indxPastExerciseRadioButton));

        //currentlyExercising
        currentlyExercisingRadioButtonID = currentlyExercisingV.getCheckedRadioButtonId();
        View currentlyExercisingRadioButton = currentlyExercisingV.findViewById(currentlyExercisingRadioButtonID);
        indxcurrentlyExercisingRadioButton = currentlyExercisingV.indexOfChild(currentlyExercisingRadioButton);
        Log.e("indexCurrentlyExRB", String.valueOf(indxcurrentlyExercisingRadioButton));

        //exerciseIntensity
        currentExercisingIntensityRadioButtonID = currentExercisingIntensityV.getCheckedRadioButtonId();
        View currentExercisingIntensityRadioButton = currentExercisingIntensityV.findViewById(currentExercisingIntensityRadioButtonID);
        indxCurrentExercisingIntensityRadioButton = currentExercisingIntensityV.indexOfChild(currentExercisingIntensityRadioButton);
        Log.e("indexExIntensityRB", String.valueOf(indxCurrentExercisingIntensityRadioButton));

        /** Request Program **/
        new RequestProgram().execute();
    }

    /**
     * Background Async Task to Request Program
     */
    class RequestProgram extends AsyncTask<Object, String, String> {

        private ProgressDialog pDialog;

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Request_Program.this);
            pDialog.setMessage("Sending Request..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating Request
         */
        protected String doInBackground(Object... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userID", Integer.toString(1)));
            params.add(new BasicNameValuePair("height", Integer.toString(height)));
            params.add(new BasicNameValuePair("weight", Integer.toString(weight)));
            params.add(new BasicNameValuePair("pastExercise", Integer.toString(indxPastExerciseRadioButton)));
            params.add(new BasicNameValuePair("currentlyExercising", Integer.toString(indxcurrentlyExercisingRadioButton)));
            params.add(new BasicNameValuePair("currentExercisingIntensity", Integer.toString(indxCurrentExercisingIntensityRadioButton)));
            params.add(new BasicNameValuePair("activities", activities));
            params.add(new BasicNameValuePair("monday", Integer.toString(mondayInt)));
            params.add(new BasicNameValuePair("tuesday", Integer.toString(tuesdayInt)));
            params.add(new BasicNameValuePair("wednesday", Integer.toString(wednesdayInt)));
            params.add(new BasicNameValuePair("thursday", Integer.toString(thursdayInt)));
            params.add(new BasicNameValuePair("friday", Integer.toString(fridayInt)));
            params.add(new BasicNameValuePair("saturday", Integer.toString(saturdayInt)));
            params.add(new BasicNameValuePair("sunday", Integer.toString(sundayInt)));
            params.add(new BasicNameValuePair("comments", comments));

            // getting JSON Object
            // Note that create-program url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_program,
                    "POST", params);

            // check log cat for response
            Log.d("JSON Response: ", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created request
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    // failed to create request
                    Log.v("ERROR: ", "Failure to insert whatever");
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
            // dismiss the dialog once done
            pDialog.dismiss();
        }
    }

}
