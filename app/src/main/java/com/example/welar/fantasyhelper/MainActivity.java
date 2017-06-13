package com.example.welar.fantasyhelper;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.DataBaseHelper;

import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONException;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {
     public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
     private Cursor player;
     private DataBaseHelper db;
     private FantasyRestClientUsage fAPI;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            db = new DataBaseHelper(this);
            fAPI = new FantasyRestClientUsage();
        }

        /** Called when the user taps the Send button */
        public void sendId(View view) {
            Intent intent = new Intent(this, DisplayMessageActivity.class);
            EditText editText = (EditText) findViewById(R.id.editText);
            String player_name = editText.getText().toString();

            //find player in database
            //player = db.getPlayerId(player_name);

            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }

        public void showPlayersFound(View view) {
            EditText editText = (EditText) findViewById(R.id.editText);
            String player_name = editText.getText().toString();
            player_name = WordUtils.capitalize(player_name.toLowerCase());

            TextView textView = (TextView) findViewById(R.id.playerlist);
            textView.setText("Players Found:\n");

            //find player in database
            player = db.getPlayerId(player_name);
            if(player.getCount() == 0){
                //Log.d("MainActivity", "Cursor is empty");
                textView.setText("No player with that name exists. Please search for another player.");
            }
            else{
                while (player.isAfterLast() == false) {
                    textView.append("Team: " + player.getString(1) + "\n");
                    if(player.getCount()>1)
                        textView.append("First Name: " + player.getString(2) + "\n");
                    textView.append("Last Name: " + player.getString(3) + "\n");
                    textView.append("Fantasy ID: " + player.getString(4) + "\n");
                    textView.append("Position: " + player.getString(5) + "\n \n");
                    player.moveToNext();
                }
            }
        }

        public void callAPI(View view){
            EditText editText = (EditText) findViewById(R.id.editText);
            String player_name = editText.getText().toString();
            player_name = WordUtils.capitalize(player_name.toLowerCase());

            TextView textView = (TextView) findViewById(R.id.playerlist);
            textView.setText("Players Found:\n");

            //find player in database
            player = db.getPlayerId(player_name);
            if(player.getCount() == 0){
                //Log.d("MainActivity", "Cursor is empty");
                textView.setText("No player with that name exists. Please search for another player.");
            }
            else{
                String fantasyId = player.getString(4);
                try {
                    fAPI.getPlayer(fantasyId);

                }catch(JSONException e){
                }

            }

        }


    protected void onDestroy() {
        super.onDestroy();
        //player.close();
        db.close();
    }
}
