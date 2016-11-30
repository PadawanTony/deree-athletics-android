package com.example.nourhan.dereeathletics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by Nourhan on 11/25/16.
 */
public class Request_Program extends AppCompatActivity {

    private EditText activitiesV, commentsV;
    private NumberPicker heightV, weightV;
    private CheckBox mondayV, tuesdayV, wednesdayV, thursdayV, fridayV, saturdayV, sundayV;
    private RadioButton pastExerciseYesV, pastExerciseNoV, currentlyExercisingYesV, currentlyExercisingNoV, currentExercisingIntensityLightV, currentExercisingIntensityModerateV, currentExercisingIntensityHeavyV;

    JSONParser jsonParser = new JSONParser();
    private static String url_create_request = "http://ashoka.students.acg.edu/" +
            "ws_test/create_request.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_program);

        initializeEditTexts();
        initializeNumberpickers();
        initializeCheckboxes();
        initializeRadioButtons();

        /** Configure heightV NumberPicker **/
        heightV.setMinValue(100);
        heightV.setMaxValue(250);
        heightV.setValue(160);
        heightV.setWrapSelectorWheel(false);
        Log.e("heightV", String.valueOf(heightV.getValue()));

        /** Configure weightV NumberPicker **/
        weightV.setMinValue(40);
        weightV.setMaxValue(200);
        weightV.setValue(70);
        weightV.setWrapSelectorWheel(false);
        Log.e("weightV", String.valueOf(weightV.getValue()));
    }

    public void cancelRequest(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void submitRequest(View view) {
        office = officeText.getText().toString();
        Log.v("Antony", "office: " + office);

        formal = is_formal.isChecked();
        Log.d("HELLOOOOOO", String.valueOf(formal));
        if (formal) {
            dressCode = "formal";
        } else dressCode = "casual";

        ambNum = amb_num.getValue();


        event_startTime = "'" + start.getText().toString() + ":00'";


        event_endTime = "'" + end.getText().toString() + ":00'";

        amb_arrival = "'" + arrival.getText().toString() + ":00'";

        amb_dep = "'" + departure.getText().toString() + ":00'";


        eventLocation = eventLocText.getText().toString();

        meetingTime = "'" + meeting.getText().toString() + ":00'";


        name = nameText.getText().toString();
        description = descText.getText().toString();

//        String eventDate;
        event_date = "'" + date.getText().toString() + "'";


        pickup = "N/A";
        dropoff = "N/A";
        typeOfEvent = "tour";

        if (presentation.isChecked()) {
            typeOfEvent = "presentation";
        } else if (other.isChecked()) {
            typeOfEvent = "other";
        } else if (social.isChecked()) {
            typeOfEvent = "social";
        }

        if (tour.isChecked()) {
            pickup = pickupText.getText().toString();
            dropoff = dropoffText.getText().toString();
            typeOfEvent = "tour";
        }

        contactName = contactNameText.getText().toString();
        contactDetails = contactDetailsText.getText().toString();
        duties = dutiesText.getText().toString();

        if (formal) {
            dressCode = "formal";
        } else dressCode = "casual";

        if (meetAmbNo.isChecked()) {
            meeting_loc = "N/A";

        } else meeting_loc = meeting_locText.getText().toString();


        //CHECK FOR ALL FIELDS
        if (name.isEmpty()) {
            Log.d("NAME", name);
            Toast.makeText(this, "Please make sure the Event's Name field is filled...", Toast.LENGTH_LONG).show();
        } else if (office.isEmpty()) {
            Log.d("NAME", office);
            Toast.makeText(this, "Please make sure the Office's Name field is filled...", Toast.LENGTH_LONG).show();
        } else if (date.getText().toString().isEmpty()) {
            Log.d("NAME", date.getText().toString());
            Toast.makeText(this, "Please make sure you specified the event date...", Toast.LENGTH_LONG).show();
        } else if (description.isEmpty()) {
            Log.d("NAME", description);
            Toast.makeText(this, "Please make sure the Event's Description field is filled...", Toast.LENGTH_LONG).show();
        } else if (!tour.isChecked() && !social.isChecked() && !other.isChecked() && !presentation.isChecked()) {
            Toast.makeText(this, "Please make sure you picked an Event Type...", Toast.LENGTH_LONG).show();
        } else if (eventLocation.isEmpty()) {
            Log.d("NAME TYPE", eventLocation);
            Toast.makeText(this, "Please make sure you specified where the event will take place...", Toast.LENGTH_LONG).show();
        } else if (start.getText().toString().isEmpty()) {
            Log.d("NAME", start.getText().toString());
            Toast.makeText(this, "Please make sure you specified when the event starts...", Toast.LENGTH_LONG).show();
        } else if (end.getText().toString().isEmpty()) {
            Log.d("NAME", end.getText().toString());
            Toast.makeText(this, "Please make sure ou specified when the event ends...", Toast.LENGTH_LONG).show();
        } else if (arrival.getText().toString().isEmpty()) {
            Log.d("NAME", start.getText().toString());
            Toast.makeText(this, "Please make sure you specified when the ambassadors should arrive...", Toast.LENGTH_LONG).show();
        } else if (end.getText().toString().isEmpty()) {
            Log.d("NAME", end.getText().toString());
            Toast.makeText(this, "Please make sure ou specified when the ambassadors should leave...", Toast.LENGTH_LONG).show();
        } else if ((pickupText.getText().toString().isEmpty() || dropoffText.getText().toString().isEmpty()) && tour.isChecked()) {
            Log.d("NAME", pickupText.getText().toString());
            Toast.makeText(this, "Please make sure you specified both a pickup and dropoff location for the tour...", Toast.LENGTH_LONG).show();
        } else if (amb_num.getValue() == 0) {
            Log.d("NAME", String.valueOf(amb_num.getValue()));
            Toast.makeText(this, "Please make sure you specified the number of ambassadors assisting...", Toast.LENGTH_LONG).show();
        } else if (dutiesText.getText().toString().isEmpty()) {
            Log.d("NAME", dutiesText.getText().toString());
            Toast.makeText(this, "Please make sure you specified duties of the ambassadors assisting...", Toast.LENGTH_LONG).show();
        } else if (meeting_loc.isEmpty() && meetAmbYes.isChecked()) {
            Log.d("HELLOOOOOO", meeting_loc);
            Toast.makeText(this, "You chose to meet up with our Ambassadors before the event, \n Please make sure you entered a meeting place...", Toast.LENGTH_LONG).show();
        } else if (meeting.getText().toString().equals("00:00") && meetAmbYes.isChecked()) {
            Log.d("HELLOOOOOO", meetingTime);
            Toast.makeText(this, "You chose to meet up with our Ambassadors before the event, \n Please make sure you entered a meeting time...", Toast.LENGTH_LONG).show();
        } else if (contactDetails.isEmpty() || contactName.isEmpty()) {
            Toast.makeText(this, "Please make sure you entered your contact details...", Toast.LENGTH_LONG).show();
        } else {
            new CreateRequest().execute();
//            new CreateEvent().execute();
//            new CreateContact().execute();
        }
    }



}
