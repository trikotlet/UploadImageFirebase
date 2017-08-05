package com.example.anupriya.uploadimagefirebase;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ImageListActivity extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ArrayList<ImageUpload> imgList;
    private ListView lv;
    private ImageListAdapter adapter;

    //private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        lv = (ListView) findViewById(R.id.ListViewImage);


        //databaseProducts = FirebaseDatabase.getInstance().getReference();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


        // Progress during product list is loading
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Please Wait Loading");
//        progressDialog.show();

        //databaseProducts = FirebaseDatabase.getInstance().getReference(MainActivity.FB_DATABASE_PATH);


        //fetch_details();

        imgList = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        imgList.add(new ImageUpload(
                                snapshot1.child("id").getValue().toString(),
                                snapshot1.child("p_name").getValue().toString(),
                                snapshot1.child("p_price").getValue().toString(),
                                snapshot1.child("p_description").getValue().toString()

                        ));

                        Log.e("Image Url",snapshot1.child("id").getValue().toString());
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        // Init adapter

        adapter = new ImageListAdapter(ImageListActivity.this, R.layout.list_layout, imgList);

        //set adapter for list

        lv.setAdapter(adapter);
    }


    /*private void fetch_details() {

        imgList = new ArrayList<>();
             databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Inside onDataChange",Toast.LENGTH_SHORT).show();

                //Fetch Image Data

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    //  ImageUpload img = snapshot.getValue(ImageUpload.class);

                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                        imgList.add(new ImageUpload(
                                snapshot1.child("id").getValue().toString(),
                                snapshot1.child("p_name").getValue().toString(),
                                snapshot1.child("p_price").getValue().toString(),
                                snapshot1.child("p_description").getValue().toString()
                        ));
                        Log.d("DataSnap", snapshot1.child("id").getValue().toString());
                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

//        fetch_details();
    } */
}



/*


package com.example.anupriya.testfirebase;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    CustomList adapter;
    ArrayList<BeanAdap> arrayList;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        get_user_details();
        //show_list();
        adapter = new CustomList(this,R.layout.customlist,arrayList);
        listView.setAdapter(adapter);

       // startActivity(new Intent(this,UploadData.class));


    }





    private void get_user_details(){
        arrayList = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("DATA : ",dataSnapshot.toString());

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Log.d("Children in first",snapshot.getKey().toString());

                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        Log.d("Children in second",snapshot1.getValue().toString());

                        arrayList.add(new BeanAdap(
                                snapshot1.child("p_price").getValue().toString(),
                                snapshot1.child("p_description").getValue().toString(),
                                snapshot1.child("p_name").getValue().toString(),
                                snapshot1.child("id").getValue().toString()
                        ));
                        Log.e("NAMES : ", snapshot1.child("p_price").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}



 */