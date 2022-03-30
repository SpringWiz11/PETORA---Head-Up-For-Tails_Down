package com.example.pawfect_1;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;


public class Birds_Diseases extends AppCompatActivity {
    RecyclerView recyclerView;
    AsyncHttpClient client;
    Workbook workbook;
    List<String> diseases, causes, treatment;
    ProgressBar progressBar;
    SearchView searchView2;
    Dog_diseases_adapter ad;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);
        String url = "https://github.com/Yash-Mawalkar/Database_For_App/blob/main/Bird_Diseases1.xls?raw=true";
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        diseases = new ArrayList<>();
        causes = new ArrayList<>();
        treatment = new ArrayList<>();
        client = new AsyncHttpClient();
//        searchView2 = findViewById(R.id.searchView2);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                filterList(s);
//                return true;
//            }
//        });
        progressBar.setVisibility(View.VISIBLE);
        try {
            client.get(url, new FileAsyncHttpResponseHandler(this) {

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Birds_Diseases.this, "Download Failed !!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, File file) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Birds_Diseases.this, "File Downloaded !!!", Toast.LENGTH_SHORT).show();
                    WorkbookSettings ws = new WorkbookSettings();
                    ws.setGCDisabled(true);
                    if (file != null) {
                        try {
                            workbook = Workbook.getWorkbook(file);
                            Sheet sheet = workbook.getSheet(0);
                            for (int i = 2; i < sheet.getRows(); i++) {
                                Cell[] row = sheet.getRow(i);
                                diseases.add(row[1].getContents());
                                causes.add(row[2].getContents());
                                treatment.add(row[3].getContents());
                            }
                            showData();
                        }
                        catch (IOException | BiffException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void filterList(String s) {
//        List<String> filteredList = new ArrayList<>();
//        for(String item : diseases){
//            if(item.toLowerCase().contains(s.toLowerCase())){
//                filteredList.add(item);
//            }
//        }
//        if (filteredList.isEmpty()) {
//            Toast.makeText(this, "NO data Found", Toast.LENGTH_SHORT).show();
//
//
//        }
//        else {
//            Dog_diseases_adapter ad = new Dog_diseases_adapter(this,R.layout.dog_diseases_layout, diseases, causes, treatment);
//            ad.setFilteredList(filteredList);
//        }
//    }

    public void showData()
    {
        recyclerView.findViewById(R.id.recyclerView);
        Dog_diseases_adapter ad = new Dog_diseases_adapter(this,R.layout.dog_diseases_layout, diseases, causes, treatment);
        recyclerView.setLayoutManager(new LinearLayoutManager(this) );
        recyclerView.setAdapter(ad);
    }

//    public boolean onCreateOptionMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.menu_1, menu);
//        MenuItem menuItem = menu.findItem(R.id.search);
//        searchView2 = (SearchView) menuItem.getActionView();
//        searchView2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                ad.getFilter().filter(s.toString());
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        MenuItem item = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) item.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }
}
