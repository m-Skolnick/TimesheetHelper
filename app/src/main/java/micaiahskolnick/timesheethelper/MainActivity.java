package micaiahskolnick.timesheethelper;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferenceEditor;
    private static final int PREFERENCE_MODE_PRIVATE = 0;
    private String testNumber;



    private EditText goalBox;
    private EditText goal2Box;
    private EditText currBox;
    private EditText clockInTimeBox;
    private TextView testSaveName;
    private TextView timeNeededHoursBox;
    private TextView timeNeededMinutesBox;
    private TextView timeNeededHours2Box;
    private TextView timeNeededMinutes2Box;
    private TextView timeNeededHeadingBox;
    private TextView hoursHeading;
    private TextView minutesHeading;


    private double goal;

    private double current;
    private double needed;
    private double neededHours;
    private double neededMinutes;
    private int neededHoursInt;
    private int neededMinInt;
    private String goalStr;
    private String timeNeededHoursStr;
    private String timeNeededMinutesStr;

    public void updateDisplay(){
        getNumbers();
        calculate();
        setText();

        getNumbers2();
        calculate();
        set2Text();
    }

    public void getNumbers(){

        try {
            goal = Double.parseDouble(goalBox.getText().toString());
            current = Double.parseDouble(currBox.getText().toString());

        }
        catch (Exception e){

        }
    }

    public void getNumbers2(){

        try {
            goal = Double.parseDouble(goal2Box.getText().toString());
            current = Double.parseDouble(currBox.getText().toString());

        }
        catch (Exception e){

        }
    }

    public void calculate(){
        needed = goal - current;
        neededMinutes = needed*60d;
        neededHours = (int) needed;
        neededMinutes =  neededMinutes - neededHours*60;
        neededHoursInt = (int) neededHours;
        neededMinInt = (int) neededMinutes;
    }

    public void setText(){

        timeNeededHoursStr = Integer.toString(neededHoursInt);
        timeNeededMinutesStr = Integer.toString(neededMinInt);
            // Set text in boxes
        timeNeededHoursBox.setText(timeNeededHoursStr);
        timeNeededMinutesBox.setText(timeNeededMinutesStr);
    }

    public void set2Text(){

        timeNeededHoursStr = Integer.toString(neededHoursInt);
        timeNeededMinutesStr = Integer.toString(neededMinInt);
        // Set text in boxes
        timeNeededHours2Box.setText(timeNeededHoursStr);
        timeNeededMinutes2Box.setText(timeNeededMinutesStr);
    }

    public void clearBox(EditText thisBox){
        thisBox.setText("");
        updateDisplay();
    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first

        String goalStr = Double.toString(goal);
        String currStr = Double.toString(current);

        preferenceEditor.putString("savedGoal",goalStr);
        preferenceEditor.putString("savedCurr",currStr);

        // Apply the edits!
        preferenceEditor.apply();
    }
    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

            String savedGoal = preferenceSettings.getString("savedGoal","");
            String savedCurr = preferenceSettings.getString("savedCurr","");
            goalBox.setText(savedGoal);
            currBox.setText(savedCurr);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        preferenceSettings = getPreferences(PREFERENCE_MODE_PRIVATE);
        preferenceEditor = preferenceSettings.edit();

        currBox = (EditText) findViewById(R.id.curr_Box);
        goalBox = (EditText) findViewById(R.id.goal_Box);
        goal2Box = (EditText) findViewById(R.id.goal2_box);

        timeNeededHoursBox = (TextView) findViewById(R.id.time_needed_hours);
        timeNeededMinutesBox = (TextView) findViewById(R.id.time_needed_minutes);
        timeNeededHours2Box = (TextView) findViewById(R.id.hours2_view);
        timeNeededMinutes2Box = (TextView) findViewById(R.id.minutes2_view);
       // clockInTimeBox = (EditText) findViewById(R.id.clock_in_time_box);

        currBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBox(currBox);
            }
        });
        goalBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBox(goalBox);
            }
        });



        // Add a listener to update values when text in input box is changed.
        currBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {

                updateDisplay();
            }

            @Override
            public void afterTextChanged(final Editable s) {

            }
        });
        goalBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {

                updateDisplay();
            }

            @Override
            public void afterTextChanged(final Editable s) {

            }
        });

    }
}
