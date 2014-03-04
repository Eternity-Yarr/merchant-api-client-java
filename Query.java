package com.ll.wikimart;


import com.ll.Log;
import com.ll.wikimart.json.OrdersList;
import com.ll.wikimart.json.Serialize;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

import static javax.xml.bind.DatatypeConverter.printHexBinary;

public class Query {

    public static String getHmac(String content)
    {
        SecretKeySpec ks = new SecretKeySpec(Config.APP_SECRET.val.getBytes(), "HmacSHA1");
        Mac mac;
        String signature = "";
        try
        {
            mac = Mac.getInstance("HmacSHA1");
            mac.init(ks);
            byte[] res = mac.doFinal(content.getBytes());
            signature =  printHexBinary(res);
        }
        catch (NoSuchAlgorithmException|InvalidKeyException e)
        {
            Log.Log(e);
        }
        return signature;
    }

    private static String[] monthNames = new String[]
    {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    private static String getMonthName(int number)
    {
        if(number>=0 && number<monthNames.length)
        {
            return monthNames[number];
        }
        else return "";
    }

    public static String dateToRfc2822(Date date)
    {

        Calendar deviceTime = Calendar.getInstance();
        deviceTime.setTime(date);

        String dayweek = "";
        int dayOfWeek = deviceTime.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek)
        {
            case 1 : dayweek = "Sun"; break;
            case 2 : dayweek = "Mon"; break;
            case 3 : dayweek = "Tue"; break;
            case 4 : dayweek = "Wed"; break;
            case 5 : dayweek = "Thu"; break;
            case 6 : dayweek = "Fri"; break;
            case 7 : dayweek = "Sat"; break;
        }

        int dayOfMonth = deviceTime.get(Calendar.DAY_OF_MONTH);
        String monthInYear = getMonthName(deviceTime.get(Calendar.MONTH));
        int year = deviceTime.get(Calendar.YEAR);
        int hourOfDay = deviceTime.get(Calendar.HOUR_OF_DAY);
        int minutes = deviceTime.get(Calendar.MINUTE);
        int seconds = deviceTime.get(Calendar.SECOND);

        return  dayweek + ", " +                                        // Tue
                dayOfMonth + " " +                                      // 7
                monthInYear + " " +                                     // Nov
                year + " " +                                            // 2006
                hourOfDay + ":" + minutes + ":" + seconds + " " +       // 14:13:26
                "+0400";                                                //FIXME: TZ +0400
    }

    public static HttpURLConnection Sign (HttpURLConnection con, String content, Date date)
    {

        String d = dateToRfc2822(date);
        con.setRequestProperty("X-WM-Authentication", Config.APP_ID.val + ":" + getHmac(content));
        con.setRequestProperty("X-WM-Date", d);
        return con;
    }

    public static OrdersList getOpenedOrdersList()
    {
        StringBuilder sb = new StringBuilder(50000);
        Date date = new Date();
        try
        {
            String content = "GET \n" + "d41d8cd98f00b204e9800998ecf8427e\n" + dateToRfc2822(date) + "\n" + "/api/1.0/orders";
            String req = "/api/1.0/orders?status=opened";
            URL url = new URL("http://merchant.wikimart.ru" + req);

            HttpURLConnection c = (HttpURLConnection) (url.openConnection());
            c.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(Sign(c,content, date).getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine())!= null)
            {
                sb.append( line );
            }
            System.out.println(sb.toString());

        }
        catch (IOException e)
        {
          Log.Log(e);
        }
        return Serialize.createGson().fromJson(sb.toString(), OrdersList.class);
    }
}
