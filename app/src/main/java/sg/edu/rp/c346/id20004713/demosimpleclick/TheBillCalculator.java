package sg.edu.rp.c346.id20004713.demosimpleclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class TheBillCalculator extends AppCompatActivity {
    EditText etAmount;
    EditText etPax;
    ToggleButton tbSVC;
    ToggleButton tbGST;
    EditText etDiscount;
    RadioGroup rgPayment;
    Button btnSplit;
    Button btnReset;
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_bill_calculator);

        etAmount = findViewById(R.id.etAmount);
        etPax = findViewById(R.id.etNumOfPeople);
        tbSVC = findViewById(R.id.tbSVS);
        tbGST = findViewById(R.id.tbGST);
        etDiscount = findViewById(R.id.etDiscount);
        rgPayment = findViewById(R.id.rgPaymentMethod);
        btnSplit = findViewById(R.id.btnSplit);
        btnReset = findViewById(R.id.btnReset);
        tvOutput = findViewById(R.id.tvOutput);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmount.setText("");
                etPax.setText("");
                etDiscount.setText("");
                tbSVC.setChecked(false);
                tbGST.setChecked(false);
                rgPayment.clearCheck();
            }
        });

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amountOut = 0.0;
                double amountEntered = Double.parseDouble(etAmount.getText().toString());
                int paymentMethodId = rgPayment.getCheckedRadioButtonId();
                double numOfPeople = Double.parseDouble(etPax.getText().toString());
                double discount = Double.parseDouble((etDiscount.getText().toString()));

                if(amountEntered > 0 && numOfPeople > 0){
                    if (tbSVC.isChecked() && tbGST.isChecked()){
                        amountOut = amountEntered * 1.17;
                    } else if (tbSVC.isChecked() && ! tbGST.isChecked()){
                        amountOut = amountEntered * 1.1;
                    } else if (! tbSVC.isChecked() && tbGST.isChecked()){
                        amountOut = amountEntered*1.07;
                    } else {
                        amountOut = amountEntered;
                    }


                    if (etDiscount.getText().toString().trim().length() > 0) {
                        amountOut = amountOut * ((100 - discount) / 100);
                    }

                    double paymentPerPerson = amountOut / numOfPeople;

                    if (paymentMethodId == R.id.rbCash){
                        tvOutput.setText("Total Bill: $" + amountOut + "\nEach Pays: $" + paymentPerPerson
                                + " in Cash");
                    } else {
                        tvOutput.setText("Total Bill: $" + amountOut + "\nEach Pays: $" + paymentPerPerson
                                + " via PayNow to 91234567");
                    }
                } else {
                    Toast.makeText(TheBillCalculator.this, "Invalid Input",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}