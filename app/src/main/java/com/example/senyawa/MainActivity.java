package com.example.senyawa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView Unsur,Kode,Unsur2,Kode2,Unsur3,Kode3,Unsur4,Kode4;
    private ImageView Img1,Img2,Img3;
    private ApiInterface apiInterface;
    private Unsur unsur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Unsur=(TextView)findViewById(R.id.unsur);
        Kode=(TextView)findViewById(R.id.kode);
        Unsur2=(TextView)findViewById(R.id.unsur2);
        Kode2=(TextView)findViewById(R.id.kode2);
        Unsur3=(TextView)findViewById(R.id.unsur3);
        Kode3=(TextView)findViewById(R.id.kode3);
        Unsur4=(TextView)findViewById(R.id.unsur4);
        Kode4=(TextView)findViewById(R.id.kode4);
        Img1=(ImageView)findViewById(R.id.imageView);
        Img2=(ImageView)findViewById(R.id.imageView2);
        Img3=(ImageView)findViewById(R.id.imageView3);

        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        Call<Unsur>call=apiInterface.GetUnsur();
        call.enqueue(new Callback<com.example.senyawa.Unsur>() {
            @Override
            public void onResponse(Call<com.example.senyawa.Unsur> call, Response<com.example.senyawa.Unsur> response) {
                unsur=response.body();
                Unsur.setText(unsur.getUnsur());
                Kode.setText(unsur.getKode());

                List<Senyawa>senyawas=unsur.getSenyawa();
                Unsur2.setText(senyawas.get(0).getNama());
                Kode2.setText(senyawas.get(0).getKode());
                Picasso.with(MainActivity.this).load(senyawas.get(0).getImage()).into(Img1);

                Unsur3.setText(senyawas.get(1).getNama());
                Kode3.setText(senyawas.get(1).getKode());
                Picasso.with(MainActivity.this).load(senyawas.get(1).getImage()).into(Img2);

                Unsur4.setText(senyawas.get(2).getNama());
                Kode4.setText(senyawas.get(2).getKode());
                Picasso.with(MainActivity.this).load(senyawas.get(2).getImage()).into(Img3);



            }



            @Override
            public void onFailure(Call<com.example.senyawa.Unsur> call, Throwable t) {

            }
        });

    }
}
