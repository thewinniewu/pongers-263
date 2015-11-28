package com.www.pongers_263;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by winniewu on 11/27/15.
 */
public class MaliciousActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malicious);

        EditText password = (EditText) this.findViewById(R.id.password);
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Toast.makeText(v.getContext(), "l0lz pwn3d",
                            Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });

    }

}
