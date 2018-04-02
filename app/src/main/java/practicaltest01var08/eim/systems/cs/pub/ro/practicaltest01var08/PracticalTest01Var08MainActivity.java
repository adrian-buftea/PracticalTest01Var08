package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {


    private Button playButton;
    private EditText riddle, answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        playButton = findViewById(R.id.playButton);
        riddle = findViewById(R.id.riddle);
        answer = findViewById(R.id.answer);

        riddle.setText("1 + 10 = ?");

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer.getText().toString().length() != 0) {
                    String theRiddle = riddle.getText().toString();
                    String theAnswer = answer.getText().toString();

                    Intent intent = new Intent(getApplicationContext(), Practicaltest01Var08PlayActivity.class);

                    intent.putExtra("riddle", theRiddle);
                    intent.putExtra("answer", theAnswer);
                    startActivityForResult(intent, 101);
                } else {
                    Toast.makeText(getApplicationContext(), "Answer should not be empty" ,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
//            Toast.makeText(getApplicationContext(), "The activity returned with result " + resultCode,
//                    Toast.LENGTH_LONG).show();
        }
    }
}
