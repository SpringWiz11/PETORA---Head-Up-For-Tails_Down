package com.example.pawfect_1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Location_Activity {
    public static void main(String[] args) {
            //double latitude=19.9975;
            //double longitude=73.7898;
            //final String url = "https://www.google.com/maps/search/veternarians+near+me/"+String.valueOf(latitude)+","+String.valueOf(longitude)+",9z/data=!3m1!4b1";
            String location="kolkata";
            String url="https://www.google.com/search?tbs=lf:1,lf_ui:14&tbm=lcl&sxsrf=APq-WBs1LPWZEVlFYAcSn0xVZEuU-RceAQ:1648135948289&q=veterinarians+near+"+location+"&rflfq=1&num=10&sa=X&ved=2ahUKEwi_3_TtiN_2AhVJJaYKHYGECucQjGp6BAgCEEo#rlfi=hd:;si:;mv:[[22.743514899999997,88.50829569999999],[22.4504949,88.3057398]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!2m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:14";
            System.out.println(url);
            final String[] names=new String[20];
            //final String[] href= new String[20];
            final String[] address=new String[20];
            final String[] mobno=new String[20];
            final String[] website= new String[20];
            //final String[] direction=new String[20];
            final String[] star=new String[20];
            String name,add,mob,web,starView;
            int i=0;
            try {
                Document document = Jsoup.connect(url).get();
                //System.out.println("start");
                ////System.out.println(document.getElementsByClass("ifM90"));
                //System.out.println(document.getElementsByClass("VkpGBb"));
                //System.out.println("end");
                for (Element result : document.getElementsByClass("VkpGBb")){
                    System.out.println("Entered");
                    name="";add="";mob="";web="";starView="";
                    name=((result.getElementsByAttributeValue("role","heading")).text());
                    web=((result.getElementsByClass("yYlJEf Q7PwXb L48Cpd")).select("a")).attr("href");
                    starView=(result.getElementsByClass("YDIN4c")).text();
                    String details=(result.getElementsByClass("rllt__details")).toString();
                    String mob1;
                    String div=((details).substring((details.lastIndexOf("<div>"))));
                    Document doc = (Document) Jsoup.parse(div);
                    if(div.contains("span"))
                    {
                        mob1 = (((doc.select("span").first()).nextSibling()).toString());
                        if(mob1.length()>1){
                            mob=mob1.substring(3);

                        }
                        else{
                            mob="";
                        }
                    }
                    else{
                        if(div.contains("Open 24 hours"))
                        {
                            mob=div.substring(21+3,div.indexOf("</div>"));
                        }
                        else{
                            mob=doc.text();}
                    }

                    doc=Jsoup.parse(details);
                    Element ele=(doc.select("div")).get(3);
                    add=(ele.text());
                    System.out.println(name);
                    System.out.println(add);
                    System.out.println(mob);
                    System.out.println(web);
                    System.out.println(starView);
                    names[i]=name;
                    address[i]=add;
                    mobno[i]=mob;
                    website[i]=web;
                    star[i]=starView;
                    i++;
                }
            }
            catch (Exception ex) {
                System.out.println("There is an exception");
                ex.printStackTrace();
            }

        }
    }

