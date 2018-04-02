package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Practicaltest01Var08PlayActivity extends AppCompatActivity {

    private Button checkButton, backButton;
    private TextView riddleSecond, rightAnswer;
    private EditText attemptEditText;
    private int serviceStatus = 0;
    private IntentFilter intentFilter = new IntentFilter();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicaltest01_var08_play);

        checkButton = findViewById(R.id.checkButton);
        backButton = findViewById(R.id.backButton);
        riddleSecond = findViewById(R.id.riddleSecond);
        rightAnswer = findViewById(R.id.rightAnswer);
        attemptEditText = findViewById(R.id.attemptEditText);

        rightAnswer.setText("11");

        intentFilter.addAction("stars");

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("riddle")) {
            riddleSecond.setText(intent.getStringExtra("riddle"));
        }
        if (intent != null && intent.getExtras().containsKey("answer")) {
            attemptEditText.setText(intent.getStringExtra("answer"));
        }

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attemptEditText.getText().toString().length() != 0) {
                    String correct = rightAnswer.getText().toString();
                    String answer = attemptEditText.getText().toString();
                    if (correct.equals(answer)) {
                        Toast.makeText(getApplicationContext(), "Correct" ,
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong" ,
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Answer should not be empty" ,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK, null);
                finish();
            }
        });

        if (serviceStatus == 0) {
            Intent newIntent = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
            newIntent.putExtra("answer", attemptEditText.getText().toString());
            getApplicationContext().startService(newIntent);
            serviceStatus = 1;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var08Service.class);
        stopService(intent);
        super.onDestroy();
    }
}
