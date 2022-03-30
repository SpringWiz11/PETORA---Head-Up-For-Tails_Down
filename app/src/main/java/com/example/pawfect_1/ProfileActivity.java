package com.example.pawfect_1;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.apache.log4j.chainsaw.Main;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ActionBar actionBar;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ImageView avatarTv,coverTv;
    FloatingActionButton fab;
    TextView nameTv,emailTv,phoneTv,cityTv;
    ProgressDialog pd;
    public static final String NAME = "NAME";
    public static final String NAME1 = "NAME1";
    //FOR NEW TRYING
    String PROFILE_IMAGE_URL=null;
     int TAKE_IMAGE_CODE=10001;
    //storage
    StorageReference storageReference;
    //path where images of user profile and cover profile will be stored
    String storagePath="Users_Profile_Cover_Imgs/";
    //permission constants
    private static final int CAMERA_REQUEST_CODE=100;
    private static final int STORAGE_REQUEST_CODE=200;
    private static final int IMAGE_PICK_GALLERY_CODE=300;
    private static final int IMAGE_PICK_CAMERA_CODE=400;
    //array of permissions to be requested
    String cameraPermissions[];
    String storagePermissions[];
    //Uri of picked image
    Uri image_uri;
    //for checking profile or cover photo
    String profileOrCoverPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//        actionBar=getSupportActionBar();
//        actionBar.setTitle("Profile");
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Users");
        storageReference= FirebaseStorage.getInstance().getReference();//firebase storage referance
        fab= findViewById(R.id.fab);
        pd=new ProgressDialog(ProfileActivity.this);
        avatarTv=findViewById(R.id.avatarTv);
        coverTv=findViewById(R.id.coverTv);
        nameTv=findViewById(R.id.nameTv);
        emailTv=findViewById(R.id.emailTv);
        phoneTv=findViewById(R.id.phoneTv);
        cityTv=findViewById(R.id.cityTv);
        //initialising permissions
        cameraPermissions=new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};


        Query query=databaseReference.orderByChild("email").equalTo(firebaseUser.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                //for loop for until required
                for(DataSnapshot ds: snapshot.getChildren()){
                    //get data
                    String name=""+ds.child("name").getValue();
                    String email=""+ds.child("email").getValue();
                    String phone=""+ds.child("phone").getValue();
                    String city=""+ds.child("city").getValue();
                    String image=""+ds.child("image").getValue();
                    String cover=""+ds.child("cover").getValue();
                    //setdata
                    nameTv.setText(name);
                    emailTv.setText(email);
                    phoneTv.setText(phone);
                    cityTv.setText(city);
                    String city_1 = cityTv.getText().toString();
                    String name_1 = nameTv.getText().toString();
                    Intent intent = new Intent(ProfileActivity.this, Veteranians_Near_Me.class);
                    String message = String.valueOf(intent.putExtra(NAME, city_1));
                    Intent intent1 = new Intent(ProfileActivity.this, MainActivity.class);
                    String message1 = String.valueOf(intent.putExtra(NAME1, name_1));
                    try {
                        //If things go fine then this
                        Picasso.get().load(image).into(avatarTv);

                    }catch(Exception e){
//If there is any error in getting image then setiing this as a default
                        Picasso.get().load(R.drawable.ic_default_img_white).into(avatarTv);
                    }

                    try {
                        //If things go fine then this
                        Picasso.get().load(cover).into(coverTv);

                    }catch(Exception e){
//If there is any error in getting image then setiing this as a default

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {


                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        return true;
                    case R.id.dashboardnav:
                        startActivity(new Intent(getApplicationContext(),DashboardNav.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.calender:
                        startActivity(new Intent(getApplicationContext(),CalenderActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditProfileDialog();
            }
        });

    }
    private boolean checkStoragePermission(){
        //returns true if permission granted
        //return false if permission denied
        boolean result= ContextCompat.checkSelfPermission(ProfileActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ==(PackageManager.PERMISSION_GRANTED);
        return  result;

    }

    private void requestStoragePermission()
    {
        //requesting runtime storage permission
       requestPermissions(storagePermissions,STORAGE_REQUEST_CODE);
    }
    private boolean checkCameraPermission(){
        //returns true if permission granted
        //return false if permission denied
        boolean result= ContextCompat.checkSelfPermission(ProfileActivity.this,Manifest.permission.CAMERA)
                ==(PackageManager.PERMISSION_GRANTED);
        boolean result1= ContextCompat.checkSelfPermission(ProfileActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ==(PackageManager.PERMISSION_GRANTED);
        return  result && result1;

    }

    private void requestCameraPermission()
    {
        //requesting runtime storage permission
       requestPermissions(storagePermissions,STORAGE_REQUEST_CODE);
    }

    private void showEditProfileDialog()
    {
        //Options Shown in dialoug
        String options[]={"Edit Profile Photo","Edit Cover Photo","Edit Name","Edit Phone","Edit City"};
        //Alert Box
        AlertDialog.Builder builder=new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Choose an action");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //handling the clicks
                if(i==0){
//profile
                    pd.setMessage("Updating Profile Picture");
                    profileOrCoverPhoto="image";//changing profile photo
                    showImagePicDialog();

                }
                else  if(i==1)
                {
//cover
                    pd.setMessage("Updating cover Picture");
                    profileOrCoverPhoto="cover";//changing cover photo
                    showImagePicDialog();
                }
                else  if(i==2)
                {
//name

                    pd.setMessage("Updating Name");
                    //passing key name in the argument to update name
                    showNamePhoneCityUpdateDailog("name");
                }
                else  if(i==3)
                {
//phone
                    pd.setMessage("Updating Phone number");
                    showNamePhoneCityUpdateDailog("phone");
                }
                else  if(i==4)
                {
//City
                    pd.setMessage("Updating City");
                    showNamePhoneCityUpdateDailog("city");
                }


            }
        });
        //create and show dialog
        builder.create().show();
    }

    private void showNamePhoneCityUpdateDailog(String key)
    {
        //key used here is used with three option
        //"name" to update name
        //"phone" to update phone
        //"city" to update city
        //custom dialog
        AlertDialog.Builder builder=new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Update"+" "+key);//Changing title along with key
        //editing dialog
        LinearLayout linearLayout=new LinearLayout(ProfileActivity.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(10,10,10,10);
        //add edit text
        EditText editText=new EditText(ProfileActivity.this);
        editText.setHint("Enter"+key);//Updating hint based on hint
        linearLayout.addView(editText);
        builder.setView(linearLayout);
        //adding buttons in the dialog box to udpate
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //input text from edit text
                String value=editText.getText().toString().trim();
                //Validating if user has entered something or not
                if(!TextUtils.isEmpty(value))
                {
                    pd.show();
                    HashMap<String,Object> result=new HashMap<>();
                    result.put(key,value);
                    databaseReference.child(firebaseUser.getUid()).updateChildren(result)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    //updated,dismiss progress bar
                                    pd.dismiss();
                                    Toast.makeText(ProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //if failed dismiss the progress bar and display the error
                            pd.dismiss();
                            Toast.makeText(ProfileActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
                else
                {
                    Toast.makeText(ProfileActivity.this, "Please enter"+key, Toast.LENGTH_SHORT).show();
                }

            }
        });
        //ading button to cancel
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
dialogInterface.dismiss();


            }
        });
        //showing the alert /dialog box
        builder.create().show();



    }

    private void showImagePicDialog()
    {
        //Options Shown in dialoug
        String options[]={"Camera","Gallery"};
        //Alert Box
        AlertDialog.Builder builder=new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Pic image from");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //handling the clicks
                if(i==0){
//Camera
                    if(!checkCameraPermission()){
                        requestCameraPermission();
                    }
                    else{
                        pickFromCamera();
                    }


                }
                else  if(i==1)
                {
//Gallery
                    if(!checkStoragePermission()){
                        requestStoragePermission();
                    }
                    else{
                        pickFromGallery();
                    }

                }

            }
        });
        //create and show dialog
        builder.create().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //handling allowed and denied permissions here
        switch(requestCode){
            case CAMERA_REQUEST_CODE:{
                //checking if camera and storage permissions allowed or not

                if(grantResults.length>0){
                    boolean cameraAccepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted=grantResults[1]==PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted && writeStorageAccepted){
                        //permission enabled
                        pickFromCamera();
                    }
                    else
                    {
                        //permission denied
                        Toast.makeText(this, "Please enable camera & storage permission", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            break;
            case STORAGE_REQUEST_CODE:{
                if(grantResults.length>0){
                    //picking from gallery
                    boolean writeStorageAccepted=grantResults[1]==PackageManager.PERMISSION_GRANTED;
                    if(writeStorageAccepted){
                        //permission enabled
                        pickFromGallery();
                    }
                    else
                    {
                        //permission denied
                        Toast.makeText(this, "Please enable storage permission", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
    @Override

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == IMAGE_PICK_GALLERY_CODE){
            if (resultCode == Activity.RESULT_OK){
                assert data != null;
                image_uri = data.getData();
                uploadProfileCoverPhoto(image_uri);
            }
        } if (requestCode == IMAGE_PICK_CAMERA_CODE){
            if (resultCode == RESULT_OK){
                assert data != null;

                uploadProfileCoverPhoto(image_uri);
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadProfileCoverPhoto(Uri uri)
    {
        //function for both profile and cover photo
        pd.show();
        String filePathAndName=storagePath+""+profileOrCoverPhoto+"_"+ firebaseUser.getUid();
        StorageReference storageReference2nd=storageReference.child(filePathAndName);
        storageReference2nd.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //image is uploaded to storage ,now get it's uri and store in user's database
                        Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isSuccessful());
                        Uri downloadUri=uriTask.getResult();
                        //check if image is uploaded or not and uri is recieved
                        if(uriTask.isSuccessful())
                        {
                            //image uploaded
                            //add updated uri in users database
                            HashMap<String,Object> results=new HashMap<>();
                            results.put(profileOrCoverPhoto,downloadUri.toString());
                            databaseReference.child(firebaseUser.getUid()).updateChildren(results)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
//url in database of user is added successfully
                                            //dismiss progress Bar
                                            pd.dismiss();
                                            Toast.makeText(ProfileActivity.this, "Image updated", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //error adding url in users database
                                    
                                    //dismiss progress Bar
                                    pd.dismiss();
                                    Toast.makeText(ProfileActivity.this, "Error in updating image", Toast.LENGTH_SHORT).show();

                                }
                            });

                        }
                        else
                        {
                            //error
                            pd.dismiss();
                            Toast.makeText(ProfileActivity.this, "Some error occured", Toast.LENGTH_SHORT).show();
                        }



                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //there were some error(s) ,get and show error message ,dismiss progress dialog
                pd.dismiss();
                Toast.makeText(ProfileActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void pickFromCamera()
    {
        //Intent of picking image from device camera
        ContentValues values=new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"temp pic");
        values.put(MediaStore.Images.Media.DESCRIPTION,"temp description");
//putting image uri
        image_uri=ProfileActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        //intent to start camera
        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(cameraIntent,IMAGE_PICK_CAMERA_CODE);

    }

    private void pickFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(galleryIntent,"Select Picture"),IMAGE_PICK_GALLERY_CODE);
    }



}