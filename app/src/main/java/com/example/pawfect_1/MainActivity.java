package com.example.pawfect_1;

import android.content.Intent;
import android.location.LocationRequest;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FirebaseAuth firebaseAuth;
    ActionBar actionBar;
    ImageView generalsymptoms,veteranian, trainer, pethouse, rehabs, pet_care,  petshop;
    SearchView searchView;
    TextView textView;
    ImageView chatbot;
    //    ImageView veteranians, trainers;
    public LocationRequest locationRequest;
    public static final String NAME = "NAME";
    public static final String NAME1 = "NAME1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        generalsymptoms = findViewById(R.id.general_diseases);
        searchView = findViewById(R.id.searchView);
        veteranian = findViewById(R.id.veteranian);
        trainer = findViewById(R.id.pet_trainers);
        rehabs = findViewById(R.id.rehab);
        pet_care = findViewById(R.id.pet_care);
        textView = findViewById(R.id.textView);
        chatbot = findViewById(R.id.chatbot);
        pethouse = findViewById(R.id.pet_house);
        chatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity_chatBot.class);
                startActivity(intent);

            }
        });
        generalsymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Choose Animals", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(MainActivity.this, General_Symptoms.class);
                startActivity(intent1);
            }
        });
        veteranian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Veteranians_Near_Me.class);
                startActivity(intent);
//                getCurrentLocation();
            }
        });
        trainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Pet_Trainers_Near_Me.class);
                startActivity(intent);
//                getCurrentLocation();
                Toast.makeText(MainActivity.this, "Displaying Desired Results!!", Toast.LENGTH_SHORT).show();
            }
        });
        pethouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Pet_House.class);
                startActivity(intent);
//                getCurrentLocation();
                Toast.makeText(MainActivity.this, "Displaying Desired Results!!", Toast.LENGTH_SHORT).show();
            }
        });
        rehabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Rehabilitation_Centres_Near_Me.class);
                startActivity(intent);
//                getCurrentLocation();
                Toast.makeText(MainActivity.this, "Displaying Desired Results!!", Toast.LENGTH_SHORT).show();
            }
        });
        pet_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Pet_Day_Care.class);
                startActivity(intent);
//                getCurrentLocation();
                Toast.makeText(MainActivity.this, "Displaying Desired Results!!", Toast.LENGTH_SHORT).show();
            }
        });
//        locationRequest = LocationRequest.create();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(5000);
//        locationRequest.setFastestInterval(2000);
//    -------------------------------------------------------------------------------------------------------------------------
//        actionBar=getSupportActionBar();
//        actionBar.setTitle("Home");
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.dashboardnav:
                        startActivity(new Intent(getApplicationContext(),DashboardNav.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.calender:
                        startActivity(new Intent(getApplicationContext(), com.example.pawfect_1.CalenderActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
//    ------------------------------------------------------------------------------------------------------------
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == 1){
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
//
//                if (isGPSEnabled()) {
//
//                    getCurrentLocation();
//
//                }else {
//
//                    turnOnGPS();
//                }
//            }
//        }
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 2) {
//            if (resultCode == Activity.RESULT_OK) {
//
//                getCurrentLocation();
//            }
//        }
//    }
//    private void getCurrentLocation() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ActivityCompat.checkSelfPermission(Home_Activity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                if (isGPSEnabled()) {
//
//                    LocationServices.getFusedLocationProviderClient(Home_Activity.this)
//                            .requestLocationUpdates(locationRequest, new LocationCallback() {
//                                @SuppressLint("SetTextI18n")
//                                @Override
//                                public void onLocationResult(@NonNull LocationResult locationResult) {
//                                    super.onLocationResult(locationResult);
//
//                                    LocationServices.getFusedLocationProviderClient(Home_Activity.this)
//                                            .removeLocationUpdates(this);
//
//                                    if (locationResult != null && locationResult.getLocations().size() >0){
//
//                                        int index = locationResult.getLocations().size() - 1;
//                                        double latitude = locationResult.getLocations().get(index).getLatitude();
//                                        double longitude = locationResult.getLocations().get(index).getLongitude();
//                                        Intent intent = new Intent(Home_Activity.this, Veteranians_Near_Me.class);
//                                        intent.putExtra(NAME, latitude);
//                                        intent.putExtra(NAME1, longitude);
//                                    }
//                                }
//                            }, Looper.getMainLooper());
//
//                } else {
//                    turnOnGPS();
//                }
//
//            } else {
//                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//            }
//        }
//    }
//    private void turnOnGPS() {
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
//                .addLocationRequest(locationRequest);
//        builder.setAlwaysShow(true);
//
//        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
//                .checkLocationSettings(builder.build());
//
//        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
//            @Override
//            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
//
//                try {
//                    LocationSettingsResponse response = task.getResult(ApiException.class);
//                    Toast.makeText(Home_Activity.this, "GPS is already turned on", Toast.LENGTH_SHORT).show();
//
//                } catch (ApiException e) {
//
//                    switch (e.getStatusCode()) {
//                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//
//                            try {
//                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
//                                resolvableApiException.startResolutionForResult(Home_Activity.this, 2);
//                            } catch (IntentSender.SendIntentException ex) {
//                                ex.printStackTrace();
//                            }
//                            break;
//
//                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                            //Device does not have location
//                            break;
//                    }
//                }
//            }
//        });
//
//    }
//    private boolean isGPSEnabled() {
//        LocationManager locationManager = null;
//        boolean isEnabled = false;
//
//        if (locationManager == null) {
//            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        }
//
//        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        return isEnabled;
//    }

    //--------------------------------------------------------------------------------------------------
    private void checkUserStatus(){
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null){
            //user is signed in
            //mProfileTv.setText(user.getEmail());
        }
        else{
            startActivity(new Intent(MainActivity.this, MainActivity_Login.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStart() {
        checkUserStatus();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menu
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //menu options click

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.action_logout){
            firebaseAuth.signOut();
            checkUserStatus();

        }
        return super.onOptionsItemSelected(item);
    }

}