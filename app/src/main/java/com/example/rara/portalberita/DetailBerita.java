package com.example.rara.portalberita;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rara.portalberita.Helper.Server;

public class DetailBerita extends AppCompatActivity {
    ImageView IvDitailGambar;
    WebView WvIsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Set();
        // ambil data dari intent
        String judul,isi,gambar;
        judul = getIntent().getStringExtra("judul");
        isi = getIntent().getStringExtra("isi");
        gambar = getIntent().getStringExtra("gambar");
        // tampilkan data
        Glide.with(this).load(Server.BASE_IMG + gambar).placeholder(R.mipmap.ic_launcher).into(IvDitailGambar);
        // load data ke dalam web view
        WvIsi.loadData(isi,"text/html", "utf-8");
        // tampilkan judul berita
        ActionBar title = getSupportActionBar();
        title.setTitle(judul);
    }

    private void Set() {
        IvDitailGambar = (ImageView)findViewById(R.id.IvDetailGambar);
        WvIsi = (WebView)findViewById(R.id.WvDetailIsi);
    }
}
