package droidmentor.onboardingdemo;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class SignInActivity extends AppCompatActivity {

    private Button mBtnBack;
    private ConstraintLayout mLayoutSignIn;
    private Button mBtnContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mLayoutSignIn = findViewById(R.id.relative_sign_in);

        mBtnContinue = findViewById(R.id.btn_continue);
        mBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });
        mBtnBack = findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInActivity.super.onBackPressed();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        mLayoutSignIn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyBoard(view);
                return false;
            }
        });
    }
    private void hideKeyBoard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
}
