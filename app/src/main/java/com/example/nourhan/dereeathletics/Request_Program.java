package com.example.nourhan.dereeathletics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;

/**
 * Created by Nourhan on 11/25/16.
 */
public class Request_Program extends AppCompatActivity {

    private EditText activitiesV, commentsV;
    private NumberPicker heightV, weightV;
    private CheckBox mondayV, tuesdayV, wednesdayV, thursdayV, fridayV, saturdayV, sundayV;
    private RadioButton pastExerciseYesV, pastExerciseNoV, currentlyExercisingYesV, currentlyExercisingNoV, currentExercisingIntensityLightV, currentExercisingIntensityModerateV, currentExercisingIntensityHeavyV;

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
    }
}
