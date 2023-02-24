package com.mblhcmute.startactivityforresult;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mblhcmute.startactivityforresult.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private EditText mEditTextNumber1;
    private EditText mEditTextNumber2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextViewResult = binding.textViewResult;
        mEditTextNumber1 = binding.editTextNumber1;
        mEditTextNumber2 = binding.editTextNumber2;

        Button buttonOpenActivity2 = binding.buttonOpenActivity2;
        buttonOpenActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditTextNumber1.getText().toString().equals("")
                        || mEditTextNumber2.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please insert numbers", Toast.LENGTH_SHORT).show();
                } else {

                    int number1 = Integer.parseInt(mEditTextNumber1.getText().toString());
                    int number2 = Integer.parseInt(mEditTextNumber2.getText().toString());

                    Intent intent = new Intent(MainActivity.this, Activity2.class);
                    intent.putExtra("number1", number1);
                    intent.putExtra("number2", number2);
                    startActivityForResult(intent, 1);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int result = data.getIntExtra("result", 0);
                mTextViewResult.setText("" + result);
            }
            if (resultCode == RESULT_CANCELED) {
                mTextViewResult.setText("Nothing selected");
            }
        }
    }
}