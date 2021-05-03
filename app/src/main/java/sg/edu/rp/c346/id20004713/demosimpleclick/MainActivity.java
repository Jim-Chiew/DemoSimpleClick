package sg.edu.rp.c346.id20004713.demosimpleclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    Button btnClick;
    EditText editInput;
    TextView tvOut;
    ToggleButton tgbEnable;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClick = findViewById(R.id.btnDisplay);
        editInput = findViewById(R.id.textBox);
        tvOut = findViewById(R.id.textViewDisplay);
        tgbEnable = findViewById(R.id.toggleButtonEnable);
        rgGender = findViewById(R.id.radioGroup);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "The button has been clicked",
                        Toast.LENGTH_SHORT).show();

                String userIn = editInput.getText().toString();
                String out;
                int checkedRadioId = rgGender.getCheckedRadioButtonId();
                if(checkedRadioId == R.id.radioButtonMale){
                    // Write the code when male selected
                    out = "He says " + userIn;
                }
                else{
                    // Write the code when female selected
                    out = "She says " + userIn;
                }

                tvOut.setText(out);
            }
        });

        tgbEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tgbEnable.isChecked()){
                    editInput.setEnabled(true);
                } else {
                    editInput.setEnabled(false);
                }
            }
        });
    }
}