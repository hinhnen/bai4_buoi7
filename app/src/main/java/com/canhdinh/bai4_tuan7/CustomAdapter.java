package com.canhdinh.bai4_tuan7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<SanPham> {
    CustomAdapter(Context context, int resource, List<SanPham> item) {
        super(context, resource, item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if (view ==null)
        {
            LayoutInflater inflater=LayoutInflater.from(getContext());
            view=inflater.inflate(R.layout.row__listview, null);
        }
        SanPham sanPham=getItem(position);
        if (sanPham!=null)
        {
            ImageView imgHinhDaidien= (ImageView) view.findViewById(R.id.imgAnhDaiDien);
            TextView txtTenSP= (TextView) view.findViewById(R.id.txtTen);

            Bitmap bitmap= BitmapFactory.decodeByteArray(sanPham.hinhanh, 0, sanPham.hinhanh.length);
            imgHinhDaidien.setImageBitmap(bitmap);
            txtTenSP.setText(sanPham.tensp);
        }
        return view;
    }
}
