//package com.example.pawfect_1;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentSender;
//import android.content.pm.PackageManager;
//import android.location.LocationManager;
//import android.location.LocationRequest;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Looper;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.SearchView;
//import android.widget.Toast;
//
//
//public class MainActivity_1 extends AppCompatActivity {
//    Button generalsymptoms,veteranian, trainer, pethouse, rehabs, pet_care;
//    ImageView chatbot;
//    SearchView searchView;
////    ImageView veteranians, trainers;
//    public static final String NAME = "NAME";
//    public static final String NAME1 = "NAME1";
////    TextView addressText;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        generalsymptoms = findViewById(R.id.generalsymptoms);
//        searchView = findViewById(R.id.searchView);
//        veteranian = findViewById(R.id.veteranian);
//        trainer = findViewById(R.id.trainer);
//        rehabs = findViewById(R.id.Rehabilitation);
//        pet_care = findViewById(R.id.pet_day_care);
//        chatbot = findViewById(R.id.chatbot);
//        pethouse = findViewById(R.id.pet_house);
//        chatbot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity_1.this, com.example.pawfect_1.MainActivity_chatBot.class);
//                startActivity(intent);
//
//            }
//        });
//
//        generalsymptoms.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity_1.this, "Choose Animals", Toast.LENGTH_SHORT).show();
//                Intent intent1 = new Intent(MainActivity_1.this, General_Symptoms.class);
//                startActivity(intent1);
//            }
//        });
//        veteranian.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity_1.this, Veteranians_Near_Me.class);
//                startActivity(intent);
////                getCurrentLocation();
//            }
//        });
//        trainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity_1.this, Pet_Trainers_Near_Me.class);
//                startActivity(intent);
////                getCurrentLocation();
//                Toast.makeText(MainActivity_1.this, "Displaying Desired Results!!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        pethouse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity_1.this, Pet_House.class);
//                startActivity(intent);
////                getCurrentLocation();
//                Toast.makeText(MainActivity_1.this, "Displaying Desired Results!!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        rehabs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity_1.this, Rehabilitation_Centres_Near_Me.class);
//                startActivity(intent);
////                getCurrentLocation();
//                Toast.makeText(MainActivity_1.this, "Displaying Desired Results!!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        pet_care.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity_1.this, Pet_Day_Care.class);
//                startActivity(intent);
////                getCurrentLocation();
//                Toast.makeText(MainActivity_1.this, "Displaying Desired Results!!", Toast.LENGTH_SHORT).show();
//            }
//        });
////        locationRequest = LocationRequest.create();
////        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
////        locationRequest.setInterval(5000);
////        locationRequest.setFastestInterval(2000);
//    }
////    @Override
////    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
////        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////
////        if (requestCode == 1){
////            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
////
////                if (isGPSEnabled()) {
////
////                    getCurrentLocation();
////
////                }else {
////
////                    turnOnGPS();
////                }
////            }
////        }
////    }
////    @Override
////    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////
////        if (requestCode == 2) {
////            if (resultCode == Activity.RESULT_OK) {
////
////                getCurrentLocation();
////            }
////        }
////    }
////    private void getCurrentLocation() {
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////            if (ActivityCompat.checkSelfPermission(MainActivity_1.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
////
////                if (isGPSEnabled()) {
////
////                    LocationServices.getFusedLocationProviderClient(MainActivity_1.this)
////                            .requestLocationUpdates(locationRequest, new LocationCallback() {
////                                @SuppressLint("SetTextI18n")
////                                @Override
////                                public void onLocationResult(@NonNull LocationResult locationResult) {
////                                    super.onLocationResult(locationResult);
////
////                                    LocationServices.getFusedLocationProviderClient(MainActivity_1.this)
////                                            .removeLocationUpdates(this);
////
////                                    if (locationResult != null && locationResult.getLocations().size() >0){
////
////                                        int index = locationResult.getLocations().size() - 1;
////                                        double latitude = locationResult.getLocations().get(index).getLatitude();
////                                        double longitude = locationResult.getLocations().get(index).getLongitude();
////                                        Intent intent = new Intent(MainActivity_1.this, Veteranians_Near_Me.class);
////                                        intent.putExtra(NAME, latitude);
////                                        intent.putExtra(NAME1, longitude);
////                                    }
////                                }
////                            },Looper.getMainLooper());
////
////                } else {
////                    turnOnGPS();
////                }
////
////            } else {
////                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
////            }
////        }
////    }
////    private void turnOnGPS() {
////        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
////                .addLocationRequest(locationRequest);
////        builder.setAlwaysShow(true);
////
////        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
////                .checkLocationSettings(builder.build());
////
////        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
////            @Override
////            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
////
////                try {
////                    LocationSettingsResponse response = task.getResult(ApiException.class);
////                    Toast.makeText(MainActivity_1.this, "GPS is already turned on", Toast.LENGTH_SHORT).show();
////
////                } catch (ApiException e) {
////
////                    switch (e.getStatusCode()) {
////                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
////
////                            try {
////                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
////                                resolvableApiException.startResolutionForResult(MainActivity_1.this, 2);
////                            } catch (IntentSender.SendIntentException ex) {
////                                ex.printStackTrace();
////                            }
////                            break;
////
////                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
////                            //Device does not have location
////                            break;
////                    }
////                }
////            }
////        });
////
////    }
////    private boolean isGPSEnabled() {
////        LocationManager locationManager = null;
////        boolean isEnabled = false;
////
////        if (locationManager == null) {
////            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
////        }
////
////        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
////        return isEnabled;
////    }
//}
//
//
