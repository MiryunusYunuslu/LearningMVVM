package com.example.retromvvm.Fragment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.retromvvm.R;

import org.json.JSONStringer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Objects;

import javax.xml.transform.Result;
public class ProfileFragment extends Fragment {
    private TextView textView, myName;
    static String name1 = "";
    private EditText edittedName;
    private ImageView imageView, profileImage;
    private static final int request_code_image = 101;
    private Uri uri;
    private SQLiteDatabase sqLiteDatabase;
    private byte[] b;
    public ProfileFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    private void checkDataBase() {
        sqLiteDatabase=getActivity().openOrCreateDatabase("profile",Context.MODE_PRIVATE,null);
        @SuppressLint("Recycle") Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM profile",null);
        int nameIx=cursor.getColumnIndex("name");
        int imageIx=cursor.getColumnIndex("image");
        while(cursor.moveToNext()){
            String name=cursor.getString(nameIx);
            byte [] bytes=cursor.getBlob(imageIx);
            myName.setText(name);
            Bitmap bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            profileImage.setImageBitmap(bitmap);
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.updateProfile);
        myName = view.findViewById(R.id.myname);
        profileImage = view.findViewById(R.id.myProfileImage);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        sqLiteDatabase=getActivity().openOrCreateDatabase("profile",Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE  IF NOT EXISTS profile(name VARCHAR,image BLOB)");
        checkDataBase();
    }
    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_layout, null);
        edittedName = view.findViewById(R.id.typeName);
        @SuppressLint("Recycle") Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM profile",null);
        int nameIx=cursor.getColumnIndex("name");
        while(cursor.moveToNext()) {
            String nameInMemory = cursor.getString(nameIx);
            if(nameInMemory!=null){
                edittedName.setText(nameInMemory);
            }
        }
        imageView = view.findViewById(R.id.updateimage);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickFromGallery();
            }
        });
        builder.setView(view).setTitle("Update Profile").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Done!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name="";
                if (!TextUtils.isEmpty(edittedName.getText())) {
                    String name2 = edittedName.getText().toString();
                    if(b==null){
                        sqLiteDatabase=getActivity().openOrCreateDatabase("profile",Context.MODE_PRIVATE,null);
                        Cursor cursor2=sqLiteDatabase.rawQuery("SELECT image FROM profile",null);
                        int imageIndex=cursor2.getColumnIndex("image");
                        while(cursor2.moveToNext()){
                            b=cursor2.getBlob(imageIndex);
                        }
                    }
                    createDatabase(name2);
                }
                /*
                else{
                    Cursor cursor=sqLiteDatabase.rawQuery("SELECT name FROM profile",null);
                    int Nameix=cursor.getColumnIndex("name");
                    while(cursor.moveToNext()){
                        name=cursor.getString(Nameix);
                    }
                    createDatabase(name);
                }

                 */
            }
        }).create().show();
    }
    private void createDatabase(String name1) {
        sqLiteDatabase=getActivity().openOrCreateDatabase("profile",Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE  IF NOT EXISTS profile(name VARCHAR,image BLOB)");
        String sqlInsert="INSERT INTO profile(name,image) VALUES(?,?)";
        SQLiteStatement sqLiteStatement=sqLiteDatabase.compileStatement(sqlInsert);
        sqLiteStatement.bindString(1,name1);
        sqLiteStatement.bindBlob(2,b);
        sqLiteStatement.execute();
        checkDataBase();
    }
    private void imagePickFromGallery() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Select Picture"), request_code_image);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request_code_image && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                uri = data.getData();
            }
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
                BitmapToByte(bitmap);
            } catch (Exception ignored) {
                System.out.println(ignored.getLocalizedMessage());
            }
        }
    }
    private void BitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos);
        b = baos.toByteArray();
    }
    }