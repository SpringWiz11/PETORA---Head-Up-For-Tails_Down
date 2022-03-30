package com.example.pawfect_1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;

public class Pet_House extends AppCompatActivity {
    RecyclerView recyclerView2;
    AsyncHttpClient client;
    Workbook workbook;
    List<String> names, address, website, mobno, star;
    ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veteranians_near_me);
        recyclerView2 = findViewById(R.id.recyclerView);
        description_webscrape dw = new description_webscrape();
        dw.execute();

    }


    class description_webscrape extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //org.jsoup.nodes.Document document = null;
            if (Looper.myLooper()==null)
                Looper.prepare();
            String location = "Mumbai";
            String url = "https://www.google.com/search?tbs=lf:1,lf_ui:2&tbm=lcl&q=pet+house+near+"+location+"&rflfq=1&num=10&sa=X&ved=2ahUKEwitjvSZ7uv2AhWoy4sBHQ53D3QQjGp6BAgtEAE&biw=1920&bih=969#rlfi=hd:;si:;mv:[[22.720193899999998,88.4462104],[22.3435536,88.30643549999999]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!2m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:2";
            System.out.println(url);
            progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            List<String> names = new ArrayList<>();
            //final String[] href= new String[20];
            List<String> address = new ArrayList<>();
            List<String> mobno = new ArrayList<>();
            List<String> website = new ArrayList<>();
            //final String[] direction=new String[20];
            List<String> star = new ArrayList<>();
            String name, add, mob, web, starView;
            int i = 0;
            try {
                Document document = Jsoup.connect(url).get();
                for (Element result : document.getElementsByClass("VkpGBb")) {
                    name = "";
                    add = "";
                    mob = "";
                    web = "";
                    starView = "";
                    name = ((result.getElementsByAttributeValue("role", "heading")).text());
                    web = ((result.getElementsByClass("yYlJEf Q7PwXb L48Cpd")).select("a")).attr("href");
                    starView = (result.getElementsByClass("YDIN4c")).text();
                    String details = (result.getElementsByClass("rllt__details")).toString();
                    String mob1;
                    String div = ((details).substring((details.lastIndexOf("<div>"))));
                    Document doc = Jsoup.parse(div);
                    if (div.contains("span")) {
                        mob1 = (((doc.select("span").first()).nextSibling()).toString());
                        if (mob1.length() > 1) {
                            try{
                                mob = mob1.substring(3);}
                            catch(Exception e)
                            {
                                mob="";
                            }

                        } else {
                            mob = "";
                        }
                    } else {
                        if (div.contains("Open 24 hours")) {
                            try{
                                mob = div.substring(21 + 3, div.indexOf("</div>"));}
                            catch(Exception e) {
                                mob = "";
                            }
                        } else {
                            mob = doc.text();
                        }
                    }
                    doc = Jsoup.parse(details);
                    Element ele = (doc.select("div")).get(3);
                    add = (ele.text());
                    //System.out.println();
                    names.add(name);
                    address.add(add);
                    mobno.add(mob);
                    website.add(web);
                    star.add(starView);
                    i++;
                }
                System.out.println(names);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("Creating Adapter");
                            Location_Adapter Yash = new Location_Adapter(Pet_House.this, R.layout.location_layout, names, address, mobno, website, star);
                            recyclerView2.setLayoutManager(new LinearLayoutManager(Pet_House.this));
                            recyclerView2.setAdapter(Yash);
                            progressBar.setVisibility(View.GONE);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Exception 3");
                        }
                    }
                    //But we are using arraylist so we so not have string error
                });


            } catch (Exception ex) {
                System.out.println("There is an exception");
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
        }
    }

}


