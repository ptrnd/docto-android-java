package com.putrandabgs.docto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.putrandabgs.docto.model.Booking;
import com.putrandabgs.docto.model.Dokter;
import com.putrandabgs.docto.services.BookingService;
import com.putrandabgs.docto.services.DokterService;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmActivity extends AppCompatActivity {

    private Call<List<Dokter>> listDokter;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    TextView tvIdDokter;
    TextView namaDokter;
    TextView spesialis;
    TextView alamat;
    TextView telepon;
    TextView tanggalBooking;
    Button datePickerBtn;
    Button kembaliBtn;
    Button confirmBtn;

    String id_dokter;
    String idUserSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        tvIdDokter = findViewById(R.id.idDokterConfirm);
        namaDokter = findViewById(R.id.namaDokterConfirm);
        spesialis = findViewById(R.id.spesialisConfirm);
        alamat = findViewById(R.id.alamatConfirm);
        telepon = findViewById(R.id.teleponConfirm);
        tanggalBooking = findViewById(R.id.inputTanggal);
        confirmBtn = findViewById(R.id.confirmBtn);
        kembaliBtn = findViewById(R.id.kembaliBtn);
        datePickerBtn = findViewById(R.id.datePickerBtn);

        //mengambil data id user dari hasil login (sharedpreferences)
        SharedPreferences sp1 = this.getSharedPreferences("Login", MODE_PRIVATE);
        idUserSP = sp1.getString("ID_USER", null);

        //mengambil data dokter (id dokter) dari kiriman button sebelumnya
        Intent intent = getIntent();
        id_dokter = intent.getStringExtra("id_dokter");

        //menentukan tanggal
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        datePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        //mengambil data dokter
        DokterService dokterService = ApiClient.getClient().create(DokterService.class);

        listDokter = dokterService.getDokterById(id_dokter);

        listDokter.enqueue(new Callback<List<Dokter>>() {
            @Override
            public void onResponse(Call<List<Dokter>> call, Response<List<Dokter>> response) {
                generateData(response.body());
            }

            @Override
            public void onFailure(Call<List<Dokter>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });

        kembaliBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer getiduser = Integer.parseInt(idUserSP);
                Integer getiddokter = Integer.parseInt(tvIdDokter.getText().toString());

                //awalnya dd-MM-yyyy (Hari-Bulan-Tahun)
                String getTanggal = tanggalBooking.getText().toString();
                String getTanggalFlipped = null;

                //dijadikan yyyy-MM-dd (Tahun-Bulan-Hari) mengikuti format api
                Date date1 = null;
                try {
                    date1 = new SimpleDateFormat("dd-MM-yyyy").parse(getTanggal);
                    System.out.println(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                getTanggalFlipped = simpleDateFormat.format(date1);
                System.out.println("Flipped Date = " + getTanggalFlipped);

                if (validasi()){
                    kirimData(getiduser, getiddokter, getTanggalFlipped);
                } else {
                    Toast.makeText(ConfirmActivity.this, "tentukan tanggalnya terlebih dahulu.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void generateData(List<Dokter> dokter){
        tvIdDokter.setText(dokter.get(0).getIdDokter());
        namaDokter.setText(dokter.get(0).getNamaDokter());
        spesialis.setText(dokter.get(0).getSpesialisasi());
        alamat.setText(dokter.get(0).getAlamat());
        telepon.setText(dokter.get(0).getTelp());
    }

    public boolean validasi(){
        String getTanggal = tanggalBooking.getText().toString();
        if (getTanggal.equals("") || getTanggal.equals("Belum Ditentukan")){
            return false;
        }
        return true;
    }

    public void kirimData(Integer idUser, Integer idDokter, String tanggal){
        BookingService bookingService = ApiClient.getClient().create(BookingService.class);
//        Call<Booking> bookingList = bookingService.tambahBooking(new Booking(idUser, idDokter, tanggal));
        Call<Booking> bookingList = bookingService.tambahBooking(idUser, idDokter, tanggal);

        bookingList.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                if (response.isSuccessful()){
                    Toast.makeText(ConfirmActivity.this, "Booking tersimpan", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                } else {
                    Log.e("Error", response.message());
                    Log.e("Error", String.valueOf(response.body()));

                }
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    public void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                tanggalBooking.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}