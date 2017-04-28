package com.example.homework3;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements CalculationView {

    @InjectPresenter
    public CalculationPresenter calculationPresenter;

    EditText inputA;
    EditText inputB;
    TextView perimeterLabel;
    TextView squareLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputA = (EditText)findViewById(R.id.inputA);
        inputB = (EditText)findViewById(R.id.inputB);
        perimeterLabel = (TextView)findViewById(R.id.perimeterLabel);
        squareLabel = (TextView)findViewById(R.id.squareLabel);

        Button buttonCalculate = (Button) findViewById(R.id.buttonCalculate);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aValue = inputA.getText().toString();
                String bValue = inputB.getText().toString();

                if (aValue.isEmpty() || bValue.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Все поля должны быть заполнены",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                calculationPresenter.calculate(Double.valueOf(aValue), Double.valueOf(bValue));

                inputA.clearFocus();
                inputB.clearFocus();

                hideKeyboard();
            }
        });
    }

    @Override
    public void updateValues(CalculationResult result) {
        perimeterLabel.setText(String.valueOf(result.perimeter));
        squareLabel.setText(String.valueOf(result.square));
    }

    @Override
    public void clear() {
        perimeterLabel.setText("");
        squareLabel.setText("");
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
