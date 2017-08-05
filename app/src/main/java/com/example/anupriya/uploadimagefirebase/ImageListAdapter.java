package com.example.anupriya.uploadimagefirebase;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anupriya on 2017-08-02.
 */

public class ImageListAdapter extends ArrayAdapter<ImageUpload> {


    private ArrayList<ImageUpload> ImgUploadarrayList;
    LayoutInflater inflater;
    ImageUpload imageUploadobj;

    ImageView proImage;
    TextView proName;
    TextView proPrice;
    TextView proDesc;


    public ImageListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<ImageUpload> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        ImgUploadarrayList = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


       // inflater = context.getLayoutInflater();

        View v = inflater.inflate(R.layout.list_layout, parent, false);

        proImage = (ImageView) v.findViewById(R.id.productImg);
        proName = (TextView) v.findViewById(R.id.proName);
        proPrice = (TextView) v.findViewById(R.id.proPrice);
        proDesc = (TextView) v.findViewById(R.id.proDesc);

        imageUploadobj = ImgUploadarrayList.get(position);

        proName.setText(imageUploadobj.getP_name());
        proPrice.setText(imageUploadobj.getP_price());
        proDesc.setText(imageUploadobj.getP_description());



        Log.e("Image Name","........ " +proName);

       // Glide.with(this).load(imageUploadobj.getID()).into(proImage);

      //  Glide.with(this.getContext()).load(imageUploadobj.getID()).into(proImage);

        return  v;

        }
    }

