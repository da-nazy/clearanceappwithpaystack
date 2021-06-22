package com.example.clearanceapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;

public class transcript_checkout extends AppCompatActivity {
    private TextInputLayout mCardNumber;
    private TextInputLayout mCardExpiry;
    private TextInputLayout mCardCVV;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcript_checkout);

        initializePaystack();
        initializeFormVariables();

        db = new DatabaseHelper(this);
    }

    private void initializePaystack() {
        PaystackSdk.initialize(getApplicationContext());
        PaystackSdk.setPublicKey("pk_test_b18f4786bac7e1d247635ab928ab837411723a3a");
        //PaystackSdk.setPublicKey(BuildConfig.PSTK_PUBLIC_KEY);
    }


    private void initializeFormVariables() {
        mCardNumber = findViewById(R.id.til_card_number);
        mCardExpiry = findViewById(R.id.til_card_expiry);
        mCardCVV = findViewById(R.id.til_card_cvv);

        // this is used to add a forward slash (/) between the cards expiry month
        // and year (11/21). After the month is entered, a forward slash is added
        // before the year

        Objects.requireNonNull(mCardExpiry.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 2 && !s.toString().contains("/")) {
                    s.append("/");
                }
            }
        });

        Button button = findViewById(R.id.btn_make_payment);

        // save reference number of user
        // create room in db for refernce number
        // show prompt if refrence number is gottne {use this to get your transcript from the department okay}
        // check if network exits before perform charge
        button.setOnClickListener(v -> performCharge());
    }

    private void performCharge() {
        Intent intent = getIntent();

        String cardNumber = mCardNumber.getEditText().getText().toString();
        String cardExpiry = mCardExpiry.getEditText().getText().toString();
        String cvv = mCardCVV.getEditText().getText().toString();

        String[] cardExpiryArray = cardExpiry.split("/");
        int expiryMonth = Integer.parseInt(cardExpiryArray[0]);
        int expiryYear = Integer.parseInt(cardExpiryArray[1]);
        int amount = intent.getIntExtra("transcript", 0);
        amount *= 100;

        Card card = new Card(cardNumber, expiryMonth, expiryYear, cvv);

        Charge charge = new Charge();
        charge.setAmount(amount);
        charge.setEmail("David.mbah@esut.edu.ng");
        charge.setCard(card);

        PaystackSdk.chargeCard(this, charge, new Paystack.TransactionCallback() {
            @Override
            public void onSuccess(Transaction transaction) {
                parseResponse(transaction.getReference());
            }

            @Override
            public void beforeValidate(Transaction transaction) {
                Log.d("Main Activity", "beforeValidate: " + transaction.getReference());
            }

            @Override
            public void onError(Throwable error, Transaction transaction) {
                Log.d("Main Activity", "onError: " + error.getLocalizedMessage());
                Log.d("Main Activity", "onError: " + error);
            }

        });
    }

    private void parseResponse(String transactionReference) {
        String message = "Payment Successful - " + transactionReference;
        paymentSuccess(transactionReference);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void paymentSuccess(String msg) {
        ViewGroup viewGroup = findViewById(android.R.id.content);

        View payment = LayoutInflater.from(this).inflate(R.layout.payment_success, viewGroup, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(payment);
        Button menu;
        TextView text;
        text = payment.findViewById(R.id.transcript_txt);
        menu = payment.findViewById(R.id.cmp_transcript);
        text.setText("Transcript payment of  #8000 was successful with reference number " + msg + ". \\n Come to the department with your reference number.");

        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.show();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To save the reference number in db
                if (db.addUserReference(getMatric(), msg)) {
                    Toast.makeText(getApplicationContext(), "User reference added", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    Intent intent=new Intent(transcript_checkout.this,searchMatricNumber.class);
                    startActivity(intent);
                }else{
                    System.out.println("Error");
                }

            }
        });

    }

    public String getMatric() {
        SharedPreferences sp = getSharedPreferences("userdetails", MODE_PRIVATE);
        return sp.getString("id", " ");
    }

}