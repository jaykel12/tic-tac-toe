package tictactoe.torres.com.tic_tac_toe;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences.Editor;

public class MainActivity extends Activity implements View.OnClickListener {

    //defining variables for widgets
    private TextView statusView;
    private Button gameGrid[][] = new Button[3][3];
    private Button newGameButton;

    //variables for ticTacToe
    private boolean playerX = true;
    private boolean winner = false;
    private int fullBoard = 9;

    //SharedPreferences object
    private SharedPreferences savedValues;

    //define instance variables that should be saved
    private String button1 = "";
    private String button2 = "";
    private String button3 = "";
    private String button4 = "";
    private String button5 = "";
    private String button6 = "";
    private String button7 = "";
    private String button8 = "";
    private String button9 = "";
    private String status = "";
    private int saveFullBoard = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //references to widgets
        statusView = (TextView) findViewById(R.id.statusView);
        newGameButton = (Button) findViewById(R.id.newGameButton);
        gameGrid[0][0] = (Button) findViewById(R.id.button1);
        gameGrid[0][1] = (Button) findViewById(R.id.button2);
        gameGrid[0][2] = (Button) findViewById(R.id.button3);
        gameGrid[1][0] = (Button) findViewById(R.id.button4);
        gameGrid[1][1] = (Button) findViewById(R.id.button5);
        gameGrid[1][2] = (Button) findViewById(R.id.button6);
        gameGrid[2][0] = (Button) findViewById(R.id.button7);
        gameGrid[2][1] = (Button) findViewById(R.id.button8);
        gameGrid[2][2] = (Button) findViewById(R.id.button9);

        //set listeners
        newGameButton.setOnClickListener(this);
        gameGrid[0][0].setOnClickListener(this);
        gameGrid[0][1].setOnClickListener(this);
        gameGrid[0][2].setOnClickListener(this);
        gameGrid[1][0].setOnClickListener(this);
        gameGrid[1][1].setOnClickListener(this);
        gameGrid[1][2].setOnClickListener(this);
        gameGrid[2][0].setOnClickListener(this);
        gameGrid[2][1].setOnClickListener(this);
        gameGrid[2][2].setOnClickListener(this);

        //getSharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    @Override
    public void onPause(){
        //save the variables
        Editor editor = savedValues.edit();
        editor.putString("button1", button1);
        editor.putString("button2", button2);
        editor.putString("button3", button3);
        editor.putString("button4", button4);
        editor.putString("button5", button5);
        editor.putString("button6", button6);
        editor.putString("button7", button7);
        editor.putString("button8", button8);
        editor.putString("button9", button9);
        editor.putString("status", status);
        editor.putInt("saveFullBoard", saveFullBoard);
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();

        //get instance variables
        button1 = savedValues.getString("button1", button1);
        button2 = savedValues.getString("button2", button2);
        button3 = savedValues.getString("button3", button3);
        button4 = savedValues.getString("button4", button4);
        button5 = savedValues.getString("button5", button5);
        button6 = savedValues.getString("button6", button6);
        button7 = savedValues.getString("button7", button7);
        button8 = savedValues.getString("button8", button8);
        button9 = savedValues.getString("button9", button9);
        status = savedValues.getString("status", status);
        saveFullBoard = savedValues.getInt("saveFullBoard", saveFullBoard);

        //set X or O on widget
        gameGrid[0][0].setText(button1);
        gameGrid[0][1].setText(button2);
        gameGrid[0][2].setText(button3);
        gameGrid[1][0].setText(button4);
        gameGrid[1][1].setText(button5);
        gameGrid[1][2].setText(button6);
        gameGrid[2][0].setText(button7);
        gameGrid[2][1].setText(button8);
        gameGrid[2][2].setText(button9);
        statusView.setText(status);
        fullBoard = saveFullBoard;
        //
    }

    public void clearGrid(){
        gameGrid[0][0].setText("");
        gameGrid[0][1].setText("");
        gameGrid[0][2].setText("");
        gameGrid[1][0].setText("");
        gameGrid[1][1].setText("");
        gameGrid[1][2].setText("");
        gameGrid[2][0].setText("");
        gameGrid[2][1].setText("");
        gameGrid[2][2].setText("");

        button1 = "";
        button2 = "";
        button3 = "";
        button4 = "";
        button5 = "";
        button6 = "";
        button7 = "";
        button8 = "";
        button9 = "";
    }

    public void startNewGame(){
        statusView.setText("Player X's Turn");
        playerX = true;
        winner = false;
        fullBoard = 9;
    }

    public void ticTacToe(){
        fullBoard--;
        saveFullBoard = fullBoard;
        //switch players
        if(!playerX){
            statusView.setText("Player X's Turn");
            status = statusView.getText().toString();
            playerX = true;
        }else if(playerX){
            statusView.setText("Player O's Turn");
            status = statusView.getText().toString();
            playerX = false;
        }

        if(gameGrid[0][0].getText().equals("X") && gameGrid[0][1].getText().equals("X") && gameGrid[0][2].getText().equals("X") ||
                gameGrid[1][0].getText().equals("X") && gameGrid[1][1].getText().equals("X") && gameGrid[1][2].getText().equals("X") ||
                gameGrid[2][0].getText().equals("X") && gameGrid[2][1].getText().equals("X") && gameGrid[2][2].getText().equals("X") ||
                gameGrid[0][0].getText().equals("X") && gameGrid[1][0].getText().equals("X") && gameGrid[2][0].getText().equals("X") ||
                gameGrid[0][1].getText().equals("X") && gameGrid[1][1].getText().equals("X") && gameGrid[2][1].getText().equals("X") ||
                gameGrid[0][2].getText().equals("X") && gameGrid[1][2].getText().equals("X") && gameGrid[2][2].getText().equals("X") ||
                gameGrid[0][0].getText().equals("X") && gameGrid[1][1].getText().equals("X") && gameGrid[2][2].getText().equals("X") ||
                gameGrid[0][2].getText().equals("X") && gameGrid[1][1].getText().equals("X") && gameGrid[2][0].getText().equals("X")){
            statusView.setText("X Wins!");
            status = statusView.getText().toString();
            winner = true;
        }else if(gameGrid[0][0].getText().equals("O") && gameGrid[0][1].getText().equals("O") && gameGrid[0][2].getText().equals("O") ||
                gameGrid[1][0].getText().equals("O") && gameGrid[1][1].getText().equals("O") && gameGrid[1][2].getText().equals("O") ||
                gameGrid[2][0].getText().equals("O") && gameGrid[2][1].getText().equals("O") && gameGrid[2][2].getText().equals("O") ||
                gameGrid[0][0].getText().equals("O") && gameGrid[1][0].getText().equals("O") && gameGrid[2][0].getText().equals("O") ||
                gameGrid[0][1].getText().equals("O") && gameGrid[1][1].getText().equals("O") && gameGrid[2][1].getText().equals("O") ||
                gameGrid[0][2].getText().equals("O") && gameGrid[1][2].getText().equals("O") && gameGrid[2][2].getText().equals("O") ||
                gameGrid[0][0].getText().equals("O") && gameGrid[1][1].getText().equals("O") && gameGrid[2][2].getText().equals("O") ||
                gameGrid[0][2].getText().equals("O") && gameGrid[1][1].getText().equals("O") && gameGrid[2][0].getText().equals("O")){
            statusView.setText("O Wins!");
            status = statusView.getText().toString();
            winner = true;
        }

        if(fullBoard == 0 && !winner){
            statusView.setText("Tie!");
            status = statusView.getText().toString();
        }
    }

    @Override
    public void onClick(View v) {
        if (playerX && !winner) {
            switch (v.getId()) {
                case R.id.button1:
                    if(gameGrid[0][0].getText().equals("")){
                        gameGrid[0][0].setText("X");
                        button1 = "X";
                        ticTacToe();
                    }
                    break;
                case R.id.button2:
                    if(gameGrid[0][1].getText().equals("")) {
                        gameGrid[0][1].setText("X");
                        button2 = "X";
                        ticTacToe();
                    }
                    break;
                case R.id.button3:
                    if(gameGrid[0][2].getText().equals("")) {
                        gameGrid[0][2].setText("X");
                        button3 = "X";
                        ticTacToe();
                    }
                    break;
                case R.id.button4:
                    if(gameGrid[1][0].getText().equals("")) {
                        gameGrid[1][0].setText("X");
                        button4 = "X";
                        ticTacToe();
                    }
                    break;
                case R.id.button5:
                    if(gameGrid[1][1].getText().equals("")) {
                        gameGrid[1][1].setText("X");
                        button5 = "X";
                        ticTacToe();
                    }
                    break;
                case R.id.button6:
                    if(gameGrid[1][2].getText().equals("")) {
                        gameGrid[1][2].setText("X");
                        button6 = "X";
                        ticTacToe();
                    }
                    break;
                case R.id.button7:
                    if(gameGrid[2][0].getText().equals("")) {
                        gameGrid[2][0].setText("X");
                        button7 = "X";
                        ticTacToe();
                    }
                    break;
                case R.id.button8:
                    if(gameGrid[2][1].getText().equals("")) {
                        gameGrid[2][1].setText("X");
                        button8 = "X";
                        ticTacToe();
                    }
                    break;
                case R.id.button9:
                    if(gameGrid[2][2].getText().equals("")) {
                        gameGrid[2][2].setText("X");
                        button9 = "X";
                        ticTacToe();
                    }
                    break;
            }
        }else if(!playerX && !winner) {
            switch (v.getId()) {
                case R.id.button1:
                    if(gameGrid[0][0].getText().equals("")){
                        gameGrid[0][0].setText("O");
                        button1 = "O";
                        ticTacToe();
                    }
                    break;
                case R.id.button2:
                    if(gameGrid[0][1].getText().equals("")) {
                        gameGrid[0][1].setText("O");
                        button2 = "O";
                        ticTacToe();
                    }
                    break;
                case R.id.button3:
                    if(gameGrid[0][2].getText().equals("")) {
                        gameGrid[0][2].setText("O");
                        button3 = "O";
                        ticTacToe();
                    }
                    break;
                case R.id.button4:
                    if(gameGrid[1][0].getText().equals("")) {
                        gameGrid[1][0].setText("O");
                        button4 = "O";
                        ticTacToe();
                    }
                    break;
                case R.id.button5:
                    if(gameGrid[1][1].getText().equals("")) {
                        gameGrid[1][1].setText("O");
                        button5 = "O";
                        ticTacToe();
                    }
                    break;
                case R.id.button6:
                    if(gameGrid[1][2].getText().equals("")) {
                        gameGrid[1][2].setText("O");
                        button6 = "O";
                        ticTacToe();
                    }
                    break;
                case R.id.button7:
                    if(gameGrid[2][0].getText().equals("")) {
                        gameGrid[2][0].setText("O");
                        button7 = "O";
                        ticTacToe();
                    }
                    break;
                case R.id.button8:
                    if(gameGrid[2][1].getText().equals("")) {
                        gameGrid[2][1].setText("O");
                        button8 = "O";
                        ticTacToe();
                    }
                    break;
                case R.id.button9:
                    if(gameGrid[2][2].getText().equals("")) {
                        gameGrid[2][2].setText("O");
                        button9 = "O";
                        ticTacToe();
                    }
                    break;
            }
        }
        if(v.getId() == newGameButton.getId()){
        clearGrid();
        startNewGame();
        }
    }
}
