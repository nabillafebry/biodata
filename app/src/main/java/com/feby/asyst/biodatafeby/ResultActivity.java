package com.feby.asyst.biodatafeby;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.feby.asyst.biodatafeby.utility.Constant;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvNama, tvTempatLahir, tvGender, tvAlamat, tvPendidikan, tvEmail, tvHobi, tvHobiLain, tvTglLahir;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvNama = findViewById(R.id.nama_textview);
        tvTempatLahir = findViewById(R.id.tempat_lahir_textview);
        tvTglLahir = findViewById(R.id.tgl_lahir_textview);
        tvGender= findViewById(R.id.gender_textview);
        tvAlamat= findViewById(R.id.alamat_textview);
        tvPendidikan = findViewById(R.id.pendidikan_textview);
        tvEmail = findViewById(R.id.email_textview);
        tvHobi = findViewById(R.id.hobi_textview);
        tvHobiLain = findViewById(R.id.hobi_lain_textview);
        btnBack = findViewById(R.id.back_button);

        btnBack.setOnClickListener(this);

        if (getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            tvNama.setText(bundle.getString(Constant.KEY_NAMA, ""));
            tvTempatLahir.setText(bundle.getString(Constant.KEY_TEMPAT_LAHIR, ""));
            tvTglLahir.setText(bundle.getString(Constant.KEY_TGL_LAHIR, ""));
            tvGender.setText(bundle.getString(Constant.KEY_GENDER, ""));
            tvAlamat.setText(bundle.getString(Constant.KEY_ALAMAT, ""));
            tvPendidikan.setText(bundle.getString(Constant.KEY_PENDIDIKAN, ""));
            tvEmail.setText(bundle.getString(Constant.KEY_EMAIL, ""));
            tvHobi.setText(bundle.getString(Constant.KEY_HOBI, ""));
            tvHobiLain.setText(bundle.getString(Constant.KEY_HOBI_LAIN, ""));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Confirmation").setCancelable(false).setMessage("Are You Sure? Your Data Will Disappear").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).setNegativeButton("No", null).show();

                break;

        }

        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirmation").setCancelable(false).setMessage("Do You Want To Edit?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                finish();
            }
        }).setNegativeButton("No", null).show();

        return super.onOptionsItemSelected(item);
    }




}
