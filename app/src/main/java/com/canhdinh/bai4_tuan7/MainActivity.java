package com.canhdinh.bai4_tuan7;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static SQL db;

    int REQUEST_CODE = 1;
    EditText edtten, edtgia;
    Button btnthem,btndanhsach;
    ImageView imganh;


    ArrayList<SanPham> arraySanPham;
    ListView lvDanhsach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new SQL(this,"BanHang.sqlite",null,1);
        db.TruyVan("Create Table If not Exists SanPham(ID Integer Primary Key Autoincrement, TenSP Varchar, Gia Integer, HinhMh Blob)");
        
        addControl();
        addEvent();
    }

    private void addEvent() {

        imganh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.Insertsanpham(edtten.getText().toString(), Integer.parseInt(edtgia.getText().toString()),ConverttoArrayByte(imganh));
                Toast.makeText(MainActivity.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                edtgia.setText("");
                edtten.setText("");
                edtten.requestFocus();
            }
        });

        btndanhsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private byte[] ConverttoArrayByte(ImageView imganh) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imganh.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private void addControl() {
        edtgia = findViewById(R.id.edtGiatien);
        edtten = findViewById(R.id.edtTen);
        btnthem = findViewById(R.id.btnThemSP);
        btndanhsach = findViewById(R.id.btnXemDS);
        imganh = findViewById(R.id.imageView);


        lvDanhsach = (ListView) findViewById(R.id.listView);

        Cursor cursor = MainActivity.db.TruyVanTraVe("Select * from SanPham");
        arraySanPham = new ArrayList<>();
        while (cursor.moveToNext()) {
            arraySanPham.add(new SanPham(cursor.getString(1), cursor.getInt(2), cursor.getBlob(3)));
        }
        CustomAdapter adapter = new CustomAdapter(MainActivity.this, R.layout.row__listview, arraySanPham);
        lvDanhsach.setAdapter(adapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_CODE&&resultCode==RESULT_OK)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imganh.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
