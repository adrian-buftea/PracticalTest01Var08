package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Practicaltest01Var08PlayActivity extends AppCompatActivity {

    private Button checkButton, backButton;
    private TextView riddleSecond, rightAnswer;
    private EditText attemptEditText;

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
    }
}
