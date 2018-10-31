package com.chikeandroid.retrofittutorial2.data.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chikeandroid.retrofittutorial2.R;
import com.chikeandroid.retrofittutorial2.data.InjectorUtils;
import com.chikeandroid.retrofittutorial2.data.remote.TxnStatus;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mResponseTv;
    private SendPostViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText titleEt = (EditText) findViewById(R.id.et_title);
        final EditText bodyEt = (EditText) findViewById(R.id.et_body);
        Button submitBtn = (Button) findViewById(R.id.btn_submit);
        mResponseTv = (TextView) findViewById(R.id.tv_response);

        SendPostViewModelFactory factory = InjectorUtils.provideSendPostViewModelFactory(
                this.getApplicationContext());
        mViewModel = ViewModelProviders.of(this, factory).get(SendPostViewModel.class);

        mViewModel.getTxnStatus().observe(this, new Observer<TxnStatus>() {
            @Override
            public void onChanged(@Nullable TxnStatus txnStatus) {
                if(txnStatus == TxnStatus.TXN_SUCCESS) {
                    Toast.makeText(MainActivity.this, "POST success", Toast.LENGTH_SHORT).show();
                } else if(txnStatus == TxnStatus.TXN_FAILED) {
                    showErrorMessage();
                }
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEt.getText().toString().trim();
                String body = bodyEt.getText().toString().trim();
                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
                    mViewModel.sendPost(title, body);
                }
            }
        });
    }

    public void showErrorMessage() {
        Toast.makeText(this, R.string.mssg_error_submitting_post, Toast.LENGTH_SHORT).show();
    }

    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
            mResponseTv.setText(response);
        }
    }
}
