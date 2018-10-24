package com.apps.amazings.julias.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

//Test Kommentar
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etBillamount)
    EditText etBillamount;
    @BindView(R.id.tvTipPercentage)
    TextView tvTipPercentage;
    @BindView(R.id.tvTipTotal)
    TextView tvTipTotal;
    @BindView(R.id.tvResult)
    TextView tvResult;

    float TIP_PERCENTAGE_DEFAULT = 15;
    float TIP_PERCENTAGE_LOW = 10;
    float TIP_PERCENTAGE_HIGH = 20;

    float tipPercentage = TIP_PERCENTAGE_DEFAULT;
    float tipTotal = 0;
    float finalBillTotal = 0;
    float billAmount = 0;
    @BindView(R.id.ibRatingBad)
    ImageButton ibRatingBad;
    @BindView(R.id.ibRatingOk)
    ImageButton ibRatingOk;
    @BindView(R.id.ibRatingGood)
    ImageButton ibRatingGood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setFieldValues();
        ibRatingBad.setActivated(false);
        ibRatingGood.setActivated(false);
        ibRatingOk.setActivated(true);
    }

    private void setFieldValues() {
        tvTipPercentage.setText(getString(R.string.main_msg_tipPercentage, tipPercentage));
        tvTipTotal.setText(getString(R.string.main_msg_tipTotal, tipTotal));
        tvResult.setText(getString(R.string.main_msg_billTotalResult, finalBillTotal));
    }

    @OnClick({R.id.ibRatingBad, R.id.ibRatingOk, R.id.ibRatingGood})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibRatingBad:
                tipPercentage = TIP_PERCENTAGE_LOW;
                ibRatingOk.setActivated(false);
                ibRatingGood.setActivated(false);
                break;
            case R.id.ibRatingOk:
                tipPercentage = TIP_PERCENTAGE_DEFAULT;
                ibRatingBad.setActivated(false);
                ibRatingGood.setActivated(false);
                break;
            case R.id.ibRatingGood:
                tipPercentage = TIP_PERCENTAGE_HIGH;
                ibRatingOk.setActivated(false);
                ibRatingBad.setActivated(false);
                break;
        }
        view.setActivated(true);
        calculateFinalBill();
        setFieldValues();
    }

    @OnTextChanged(R.id.etBillamount)
    public void onTextChanged() {
        calculateFinalBill();
        setFieldValues();
    }

    private void calculateFinalBill() {
        if (!etBillamount.getText().toString().equals("") && !etBillamount.getText().toString().equals(".")) {
            billAmount = Float.valueOf(etBillamount.getText().toString());
        } else {
            billAmount = 0;
        }
        tipTotal = billAmount * tipPercentage / 100;
        finalBillTotal = billAmount + tipTotal;
    }
}
