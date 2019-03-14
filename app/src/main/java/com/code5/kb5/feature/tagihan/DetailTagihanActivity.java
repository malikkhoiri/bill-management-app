package com.code5.kb5.feature.tagihan;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.code5.kb5.R;
import com.code5.kb5.model.bill.BillData;
import com.code5.kb5.utils.ThousandSeparator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTagihanActivity extends AppCompatActivity {
    @BindView(R.id.tv_bill_d) TextView tvBill;
    @BindView(R.id.tv_amount_d) TextView tvAmount;
    @BindView(R.id.thumbnail) ImageView ivThumbnail;
    @BindView(R.id.btn_payment_verification) Button btnPaymentVerification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tagihan);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bill Detail");
        ButterKnife.bind(this);

        BillData billData = (BillData) getIntent().getSerializableExtra("bill_data");

        tvBill.setText(billData.getNamaTag());
        tvAmount.setText(("Rp. " + ThousandSeparator.createCurrency(String.valueOf(billData.getTotalTag()))));

        Glide.with(getApplicationContext())
                .load("https://cdn.pixabay.com/photo/2017/03/30/13/33/html-2188441_1280.png")
                .apply(RequestOptions.circleCropTransform())
                .into(ivThumbnail);

        btnPaymentVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PaymentVerificationActivity.class));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
