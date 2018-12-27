package com.thedevstack.jb.mathrewards;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {

    /*
      Class Scope Containers
    */

    /* Store answers */
    protected ArrayList<Integer> answers = new ArrayList<>();

    /* Container for all golden star ID's */
    protected int[] starOnIdContainer = new int[]{
            R.id.star_on0, R.id.star_on1, R.id.star_on2,
            R.id.star_on3, R.id.star_on4
    };

    /* Container for all grey star ID's */
    protected int[] starOffIdContainer = new int[]{
            R.id.star_off0, R.id.star_off1, R.id.star_off2,
            R.id.star_off3, R.id.star_off4
    };

    /*
      The points contained in the index that is equal to the user
      level is awarded when a equation is answered correctly
    */
    protected int[] pointsAwardedContainer = new int[]{
            1, 3, 5, 8, 10, 12, 15, 20
    };

    /*
      Class Scope Variables
    */

    /* The correct answers location */
    protected int locationOfCorrectAnswer;

    /*
      starbarCounter
      The counter is incremented or decremented depending if
      a equation was answered correctly or incorrectly
      The value contained in the starbarCounter is used to
      turn golden stars visible or invisible
      The starbarCounter is also used to determine if a user level
      should be incremented or decremented based on the amount of
      equations answered correctly or incorrectly
    */
    protected int starbarCounter;

    /* START ONCREATE METHOD */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* Generates the initial equation when app launches */
        generateEquation();
    } /* END ONCREATE METHOD */

    /*
      buttonPressed
      Get tag of button pressed
      Convert integer to string
      Test against locationOfCorrectAnswer
    */
    public void buttonPressed(View view) {
        /* Check if button selected contains the correct answer */
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            /* Make golden star visible */
            starOnController(starbarCounter);
            /* Increment the counter */
            this.starbarCounter++;
            /* Add to current user points */
            pointsController();
            /* Reset starbarCounter if equal to five */
            if (starbarCounter == 5) {
                this.starbarCounter = 0;
                starbarResetController();
                /* Check the amount of golden stars */
                levelUpController();
            }
            /* Generate next equation */
        } else {
            /* Get ID for user current level */
            TextView currentLevel = findViewById(R.id.current_level);
            /* Parse the integer out of the string */
            int currentLevelNum = Integer.parseInt(currentLevel.getText().toString());

            /*
              Check for a integer before subtracting
              user level to avoid exception
            */
            if (starbarCounter == 0) {
                if (currentLevelNum > 1) {
                    levelDownController();
                } // End if
            } // End if

            /*
              Check for a integer before decrementing
              counter to avoid exception
            */
            if (starbarCounter >= 1) {
                this.starbarCounter--;
                /* Make grey star visible */
                starOffController(starbarCounter);
            } // End if
        } // End if else
        /* Always generate a new equation */
        generateEquation();
    } // End method

    /*
      starOnController
      Set golden star to visible
      Set grey star to invisible
   */
    public void starOnController(int count) {
        /* Set star ID's */
        View starOn = findViewById(starOnIdContainer[count]);
        View starOff = findViewById(starOffIdContainer[count]);
        /* Set star visibility */
        starOn.setVisibility(View.VISIBLE);
        starOff.setVisibility(View.INVISIBLE);
    } // End method

    /*
      starOffController
      Set grey star to visible
      Set golden star to invisible
    */
    public void starOffController(int count) {
        /* Set star ID's */
        View starOn = findViewById(starOnIdContainer[count]);
        View starOff = findViewById(starOffIdContainer[count]);
        /* Set star visibility */
        starOn.setVisibility(View.INVISIBLE);
        starOff.setVisibility(View.VISIBLE);
    } // End method

    /*
      pointsController
      Increase current user points for each correct answer
    */
    public void pointsController() {
        /* Set current level ID */
        TextView currentLevel = findViewById(R.id.current_level);
        /* Set current level */
        int currentLevelNum = Integer.parseInt(currentLevel.getText().toString());
        /* Set current points ID */
        TextView currentPoints = findViewById(R.id.current_points);
        /* Set current amount of user points */
        int currentPointsNum = Integer.parseInt(currentPoints.getText().toString());
        for (int i = 0; i < currentLevelNum; i++) {

            /*
              Points per answer is based on user level and is
              added to the current amount of user points
            */
            int points = currentPointsNum + pointsAwardedContainer[i];
            /* Update the points value with the added points */
            currentPoints.setText(Integer.toString(points));
        } // End for
    } // End method

    /*
      The starbarResetController
      Reset the starbar
      All golden stars are made invisible
      All grey stars are made visible
    */
    public void starbarResetController() {
        /* Reset stars in starbar */
        int i = 0;
        while (i < starOnIdContainer.length) {
            /* Set star ID's */
            View starOn = findViewById(starOnIdContainer[i]);
            View starOff = findViewById(starOffIdContainer[i]);
            /* Set visibility */
            starOn.setVisibility(View.INVISIBLE);
            starOff.setVisibility(View.VISIBLE);
            i++; /* Increment i */
        } // End while
    } // End method

    /*
      levelUpController
      Add to the current user level for a correct answer
    */
    public void levelUpController() {
        /* Set current level ID */
        TextView currentLevel = findViewById(R.id.current_level);
        /* Set current level */
        int currentLevelNum = Integer.parseInt(currentLevel.getText().toString());
        /* Add one from current user level */
        int newLevel = currentLevelNum + 1;
        /* Set new level */
        currentLevel.setText(Integer.toString(newLevel));
    } // End method

    /*
      levelDownController
      Subtract from the current user level for a incorrect answer
    */
    public void levelDownController() {
        /* Set current level ID */
        TextView currentLevel = findViewById(R.id.current_level);
        /* Set current level */
        int currentLevelNum = Integer.parseInt(currentLevel.getText().toString());
        /* Subtract one from current user level */
        int newLevel = currentLevelNum - 1;
        /* Set new level */
        currentLevel.setText(Integer.toString(newLevel));
    } // End method

    /*
      generateEquation
      Generates a new equation
    */
    public void generateEquation() {
        /* Clear ArrayList */
        answers.clear();
        /* Button ID's */
        Button mainButton0 = findViewById(R.id.main_button0);
        Button mainButton1 = findViewById(R.id.main_button1);
        Button mainButton2 = findViewById(R.id.main_button2);
        Button mainButton3 = findViewById(R.id.main_button3);
        /* Set current equation ID */
        TextView currentEquation = findViewById(R.id.current_equation);
        /* Generate random number */
        Random rand = new Random();

        /* Generate random number in range 1-100 */
        int num1 = rand.nextInt(101);
        int num2 = rand.nextInt(101);
        /* Add to get correct answer */
        int correctAnswer = num1 + num2;
        /* Generate random number for location of the correct answer */
        locationOfCorrectAnswer = rand.nextInt(4);
        /* Set equation */
        currentEquation.setText(Integer.toString(num1) + " + " + Integer.toString(num2));
        /* Set correct answer to a random button */
        int incorrectAnswer;
        for (int i = 0; i < 4; i++) {
            /* Check location */
            if (i == locationOfCorrectAnswer) {
                answers.add(correctAnswer);
            } else {
                incorrectAnswer = rand.nextInt(202);

                /*
                  Check if incorrect answer is equal to the correct
                  answer generate a new incorrect answer
                */
                while (incorrectAnswer == correctAnswer) {
                    incorrectAnswer = rand.nextInt(202);
                } // End while
                answers.add(incorrectAnswer);
            } // End if else
        } // End for

        /*
          Set button text from array index
        */
        mainButton0.setText(Integer.toString(answers.get(0)));
        mainButton1.setText(Integer.toString(answers.get(1)));
        mainButton2.setText(Integer.toString(answers.get(2)));
        mainButton3.setText(Integer.toString(answers.get(3)));
    } // End method
} // End class