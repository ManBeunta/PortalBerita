package com.example.rara.portalberita;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.rara.portalberita.Adapter.AdapterBerita;
import com.example.rara.portalberita.Helper.Server;
import com.example.rara.portalberita.Model.ModelBerita;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ModelBerita> data;
    AQuery aQuery;
    ListView LvBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // methode untuk mengenalkan
        Berita();
        // methode untuk mrnampilkan list berita
        getBerita();
        LvBerita.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ModelBerita b = data.get(position);
                Intent a = new Intent(getApplicationContext(), DetailBerita.class);
                a.putExtra("judul", b.getJudul());
                a.putExtra("gambar", b.getGambar());
                a.putExtra("isi", b.getIsi());
                startActivity(a);
            }
        });
    }

    private void getBerita() {
        // buatkan String URL JSON getBerita.php
        String url = Server.BASE_URL + "getBerita.php";
        // buat progress dialog
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Informasi");
        pd.setMessage("Sedang Mengambil Data");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        // kirimkan url ke server
        try {
            aQuery.progress(pd).ajax(url,String.class, new AjaxCallback<String>(){
               // ambil nilai pengembalian dari server menggunakan methode callback

                @Override
                public void callback(String url, String object, AjaxStatus status) {
                    // kita cek apakah ada nilai pengembalian atau tidak??

                    if (object != null){
                    // ambil nilai pengembalian jika ada
                        try {
                            JSONObject json = new JSONObject(object);
                            // tampung object json ke dalam string
                            String sts = json.getString("status");
                            String msg = json.getString("msg");
                            if (sts.equalsIgnoreCase("1")){
                                // ambil object yang ada pada array data
                                JSONArray jsonArray = json.getJSONArray("data");
                                // lakukan perulangan data
                                for (int a = 0; a <jsonArray.length(); a++){
                                    //ambil object yang ada dalam array object data
                                    JSONObject jsonObject = jsonArray.getJSONObject(a);
                                    //masukan data ke dalam model berita
                                    ModelBerita b = new ModelBerita();
                                    b.setJudul(jsonObject.getString("judul"));
                                    b.setGambar(jsonObject.getString("gambar"));
                                    b.setIsi(jsonObject.getString("isi_berita"));
                                    // masukan ke dalam array list
                                    data.add(b);
                                    // setelah itu panggil dadapter berita
                                    AdapterBerita adapterBerita = new AdapterBerita(data, MainActivity.this);
                                    // kemudian tampilkan kedalam listview
                                    LvBerita.setAdapter(adapterBerita);

                                }
                            }
                        }catch (Exception e){

                        }
                    }
                }
            });
        }catch (Exception e){

        }
    }

    private void Berita() {
        data = new ArrayList<>();
        aQuery = new AQuery(this);
        LvBerita = (ListView)findViewById(R.id.LvBerita);

    }
}
