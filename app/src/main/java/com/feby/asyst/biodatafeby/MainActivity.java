package com.feby.asyst.biodatafeby;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.feby.asyst.biodatafeby.utility.Constant;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener,
        CompoundButton.OnCheckedChangeListener, DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener{

    EditText etNama, etTempatLahir, etAlamat, etEmail, etLain;
    ImageView ivDate;
    TextView tvTglLahir;
    RadioGroup rgGender;
    RadioButton rbLaki, rbPerempuan;
    Spinner spinnerPendidikan;
    CheckBox cbBerenang, cbTravelling, cbLain;
    Button btnSubmit;
    ArrayList<String> listHobi = new ArrayList<String>();
    ArrayList<String> listPendidikan = new ArrayList<String>();
    String selectedGender="Laki-laki", selectedPendidikan;
    String nama, tempat_lahir, alamat, email, lain, hobi="", tgl_lahir;
    DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = findViewById(R.id.nama_edittext);
        etTempatLahir = findViewById(R.id.tempat_lahir_edittext);
        etAlamat = findViewById(R.id.alamat_edittext);
        etEmail = findViewById(R.id.email_edittext);
        etLain = findViewById(R.id.lainnya_edittext);

        ivDate = findViewById(R.id.date_imageview);

        tvTglLahir = findViewById(R.id.tgl_lahir_textview);

        rgGender = findViewById(R.id.gender_radiogroup);

        rbLaki = findViewById(R.id.laki_radiobutton);
        rbPerempuan = findViewById(R.id.perempuan_radiobutton);

        cbBerenang = findViewById(R.id.berenang_checkbox);
        cbTravelling = findViewById(R.id.travelling_checkbox);
        cbLain = findViewById(R.id.lain_checkbox);


        btnSubmit = findViewById(R.id.submit_button);

        ivDate.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        spinnerPendidikan = findViewById(R.id.spinner_pendidikan);
        spinnerPendidikan.setOnItemSelectedListener(this);

        cbBerenang.setOnCheckedChangeListener(this);
        cbTravelling.setOnCheckedChangeListener(this);
        cbLain.setOnCheckedChangeListener(this);

        listPendidikan.add("SD");
        listPendidikan.add("SMP");
        listPendidikan.add("SMA/SMK");
        listPendidikan.add("D1");
        listPendidikan.add("D2");
        listPendidikan.add("D3");
        listPendidikan.add("S1");
        listPendidikan.add("S2");
        listPendidikan.add("S3");

        ArrayAdapter<String> pendidikanAdapter =new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listPendidikan);
        spinnerPendidikan.setAdapter(pendidikanAdapter);

        Calendar calendar = Calendar.getInstance();
        datePickerDialog = DatePickerDialog.newInstance(
                MainActivity.this,
                calendar.get(Calendar.YEAR), // Initial year selection
                calendar.get(Calendar.MONTH), // Initial month selection
                calendar.get(Calendar.DAY_OF_MONTH) // Inital day selection
        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.date_imageview:
                datePickerDialog.show(getFragmentManager(),"datePickerDialog");
                break;
            case R.id.submit_button:
                nama = etNama.getText().toString();
                tempat_lahir = etTempatLahir.getText().toString();
                alamat = etAlamat.getText().toString();
                email = etEmail.getText().toString();
                lain = etLain.getText().toString();
                tgl_lahir = tvTglLahir.getText().toString();



                if (TextUtils.isEmpty(etNama.getText().toString()) && TextUtils.isEmpty(etTempatLahir.getText().toString()) && TextUtils.isEmpty(etAlamat.getText().toString()) &&
                        TextUtils.isEmpty(etEmail.getText().toString()) && TextUtils.isEmpty(tvTglLahir.getText().toString())){

                    Toast.makeText(getApplicationContext(), "Harap Lengkapi Form!", Toast.LENGTH_SHORT).show();

                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(getApplicationContext(), "Invalid Email Address!", Toast.LENGTH_SHORT).show();
                }
                else {
                    hobi = "";
                    for (int i=0;i<listHobi.size();i++){
                        hobi = hobi+ " "+listHobi.get(i);

                    }

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("Confirmation").setCancelable(false).setMessage("Are You Sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                            intent.putExtra(Constant.KEY_NAMA, nama);
                            intent.putExtra(Constant.KEY_TEMPAT_LAHIR, tempat_lahir);
                            intent.putExtra(Constant.KEY_TGL_LAHIR, tgl_lahir);
                            intent.putExtra(Constant.KEY_GENDER, selectedGender);
                            intent.putExtra(Constant.KEY_ALAMAT, alamat);
                            intent.putExtra(Constant.KEY_PENDIDIKAN, selectedPendidikan);
                            intent.putExtra(Constant.KEY_EMAIL, email);
                            intent.putExtra(Constant.KEY_HOBI, hobi);
                            intent.putExtra(Constant.KEY_HOBI_LAIN, lain);
                            startActivity(intent);
                        }
                    }).setNegativeButton("No", null).show();
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.berenang_checkbox:
                if (isChecked){
                    listHobi.add("Berenang");

                }
                else {
                    listHobi.remove("Berenang");
                }
                break;
            case R.id.travelling_checkbox:
                if (isChecked){
                    listHobi.add("Travelling");

                }
                else {
                    listHobi.remove("Travelling");
                }
                break;
            case R.id.lain_checkbox:
                if (isChecked){
                    etLain.setEnabled(true);
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.laki_radiobutton:
                selectedGender = "Laki-laki";
                break;
            case R.id.perempuan_radiobutton:
                selectedGender = "Perempuan";
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedPendidikan = spinnerPendidikan.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        tvTglLahir.setText(date);
    }
}
