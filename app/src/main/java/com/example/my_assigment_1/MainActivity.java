package com.example.my_assigment_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUnitsUsed;
    private EditText editTextRebatePercentage;
    private Button buttonCalculate;
    private TextView textViewResult;
    private Button btnClear;

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.about :
                //Toast.makeText(this, "This is about", Toast.LENGTH_LONG).show();

                Intent intent = new Intent( this, AboutActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUnitsUsed = findViewById(R.id.editTextUnitsUsed);
        editTextRebatePercentage = findViewById(R.id.editTextRebatePercentage);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        btnClear = findViewById(R.id.btnClear);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    private void calculateBill() {
        String unitsUsedString = editTextUnitsUsed.getText().toString();
        String rebatePercentageString = editTextRebatePercentage.getText().toString();

        if (unitsUsedString.isEmpty() || rebatePercentageString.isEmpty()) {
            textViewResult.setText("Please enter valid values.");
            return;
        }

        double unitsUsed = Double.parseDouble(unitsUsedString);
        double rebatePercentage = Double.parseDouble(rebatePercentageString);

        double totalCharges = calculateCharges(unitsUsed);
        double finalCost = totalCharges - (totalCharges * rebatePercentage);

        String result = String.format("Final Cost: RM %.2f", finalCost);
        textViewResult.setText(result);
    }

    private double calculateCharges(double unitsUsed) {
        double totalCharges = 0.0;

        if (unitsUsed >= 1 && unitsUsed <= 200) {
            totalCharges = unitsUsed * 0.218;
        } else if (unitsUsed >= 201 && unitsUsed <= 300) {
            totalCharges = (200 * 0.218) + ((unitsUsed - 200) * 0.334);
        } else if (unitsUsed >= 301 && unitsUsed <= 600) {
            totalCharges = (200 * 0.218) + (100 * 0.334) + ((unitsUsed - 300) * 0.516);
        } else if (unitsUsed > 601 && unitsUsed <= 900) {
            totalCharges = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((unitsUsed - 600) * 0.546);
        }

        return totalCharges;
    }



    private void clearFields() {
        editTextUnitsUsed.setText("");
        editTextRebatePercentage.setText("");
        textViewResult.setText("");
    }


}
