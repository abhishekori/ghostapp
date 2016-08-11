package com.google.engedu.ghost;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;


public class GhostActivity extends ActionBarActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    TextView ghostText;
    TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);
        ghostText= (TextView) findViewById(R.id.ghostText);
        label= (TextView) findViewById(R.id.gameStatus);
        onStart(null);

        try {
            dictionary=new SimpleDictionary(getAssets().open("words.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
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

    private void computerTurn() {
       // label = (TextView) findViewById(R.id.gameStatus);
        // Do computer turn stuff then make it the user's turn again
        String currStr=ghostText.getText().toString();
        if(currStr.length()==4 && dictionary.isWord(currStr)){
            label.setText("computer won");

        }else{
            if(dictionary.getAnyWordStartingWith(currStr)!=null)
            {
                Log.d("gLong",dictionary.getAnyWordStartingWith(currStr));

            }else{
                Log.d("gLong","null");
            }

        }
        userTurn = true;
        label.setText(USER_TURN);
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        userTurn = random.nextBoolean();
        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        if (userTurn) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {


        char getChar=(char)event.getUnicodeChar();
        if(Character.isDigit(getChar))
        {
            return super.onKeyUp(keyCode, event);

        }else{

            String finalString;
            finalString=ghostText.getText().toString()+getChar;
            ghostText.setText(finalString);
            computerTurn();
        }

        return true;
    }
}
