package com.example.rara.portalberita.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rara.portalberita.Helper.Server;
import com.example.rara.portalberita.Model.ModelBerita;
import com.example.rara.portalberita.R;

import java.util.ArrayList;

/**
 * Created by RaRa on 02/03/2017.
 */
public class AdapterBerita extends BaseAdapter{
    //buat array list model berita
    ArrayList<ModelBerita> data;
    Context c;
    //buat construktor
    //agar bisa di panggil oleh activity yang menggunakan adapter
    public AdapterBerita(ArrayList<ModelBerita> data, Context c) {
        this.data = data;
        this.c = c;
    }

    // fungsi untuk menghitung banyak data
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    // buatkan class untuk menampung widget yang kita gunakan
    public class ViewHolder{
        ImageView gambar;
        TextView judul;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        View v = convertView;
        //
        if (v == null){
            //panggil layout list_berita
            LayoutInflater layoutInflater = (LayoutInflater)c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.list_berita, null);
            holder = new ViewHolder();
            //kenalkan widget by id
            holder.gambar = (ImageView)v.findViewById(R.id.IvGambar);
            holder.judul = (TextView)v.findViewById(R.id.TxtJudul);
            // set v nya
            v.setTag(holder);
        }else {
            holder = (ViewHolder)v.getTag();
        }
        // set deta kedalam image view dan text view
        ModelBerita modelBerita = data.get(position);
        holder.judul.setText(modelBerita.getJudul());
        // tampilkan gambar ke dalam image view
        Glide.with(c).load(Server.BASE_IMG + modelBerita.getGambar()).
                placeholder((R.mipmap.ic_launcher)).into(holder.gambar);
        // http://192.168.4.123/berita/foto_berita/nama file dati database
        // format untuk menggunakan glide : context / url / widget
        return v;
    }
}
