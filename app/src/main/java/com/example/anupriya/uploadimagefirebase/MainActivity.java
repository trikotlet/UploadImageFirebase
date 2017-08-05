package com.example.anupriya.uploadimagefirebase;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button browse;
    ImageView uploadedImage;
    Button add;
    Button viewData;

    EditText imageName;
    EditText imagePrice;
    EditText imageDescription;
    ArrayList<String> arrayList_mainAct;



    private DatabaseReference databaseProducts;
    private StorageReference mStorageRef;

    private static int REQUEST_CODE = 1234;

    private ProgressDialog mProgressDialog;

    private Uri uri;

    public static final String  FB_DATABASE_PATH = "image";
    public static final String  FB_STORAGE_PATH = "image/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList_mainAct = new ArrayList<>();

        browse = (Button) findViewById(R.id.browseBtn);
        uploadedImage = (ImageView) findViewById(R.id.up_image);
        add = (Button) findViewById(R.id.addbtn);
        viewData = (Button) findViewById(R.id.viewBtn);

        imageName = (EditText) findViewById(R.id.imageName);
        imagePrice = (EditText) findViewById(R.id.imagePrice);
        imageDescription = (EditText) findViewById(R.id.productDescription);



        mStorageRef = FirebaseStorage.getInstance().getReference();
        databaseProducts = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        mProgressDialog = new ProgressDialog(this);

       viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, ImageListActivity.class);
                startActivity(i);
            }
        });

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

                intent.setType("image/*");

                startActivityForResult(Intent.createChooser(intent,"Select Image"),REQUEST_CODE);

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(uri != null){


                    mProgressDialog.setMessage("Uploading");
                    mProgressDialog.show();

                    //storage reference
                    StorageReference filepath = mStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis() + "." + getImageExt(uri));


                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            mProgressDialog.dismiss();

                            Toast.makeText(getApplicationContext(), "Upload Done", Toast.LENGTH_SHORT).show();

                            databaseProducts = FirebaseDatabase.getInstance().getReference("Products");

                            String image_url = taskSnapshot.getDownloadUrl().toString();
                            String image_name = imageName.getText().toString().trim();
                            String image_price = imagePrice.getText().toString().trim();
                            String image_description = imageDescription.getText().toString().trim();


                            String id = databaseProducts.push().getKey();
                            Toast.makeText(getApplicationContext(),"Id from database Products"+id,Toast.LENGTH_SHORT).show();

                            ImageUpload product = new ImageUpload(image_url, image_name, image_price, image_description);

                            databaseProducts.child(id).setValue(product);
                            arrayList_mainAct.add(String.valueOf(product));

                        }

                    });

                }

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() !=null){

            uri = data.getData();

            try{
                Bitmap im = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                uploadedImage.setImageBitmap(im);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public String getImageExt(Uri uri){
        ContentResolver contentResolver =  getContentResolver();
        MimeTypeMap mimeTypemap = MimeTypeMap.getSingleton();

        return mimeTypemap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}
