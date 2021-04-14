package com.banzaraktsaeva.console.ui.slideshow;

import com.banzaraktsaeva.console.ui.gallery.Invoice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class Order {
    private String buyer;
    private String date;
    private boolean status;
    private double price;


    Order(String date, String buyer, boolean status, double price){
        this.date = date;
        this.buyer = buyer;
        this.status = status;
        this.price = price;
    }

    String getBuyer() {
        return this.buyer;
    }

    String getDate() {
        return this.date;
    }

    boolean getStatus(){ return  this.status;}

    double getPrice() {
        return this.price;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setStatus(boolean status){this.status = status;}
    public void setPrice(double price) {
        this.price = price;
    }

    static void sortListBy(ArrayList<Order> list) {
        Collections.sort(list, byDate);
    }

    private static final Comparator<Order> byDate = new Comparator<Order>() {

        final Locale DEFAULT = Locale.getDefault();

        @Override
        public int compare(Order order1, Order order2) {
            String dateStr1 = order1.getDate();
            String dateStr2 = order2.getDate();

            String buyer1 = order1.getBuyer();
            String buyer2 = order2.getBuyer();

            DateFormat date = new SimpleDateFormat("dd.MM.yyyy", DEFAULT);

            Date date1 = new Date();
            Date date2 = new Date();
            try {
                date1 = date.parse(dateStr1);
                date2 = date.parse(dateStr2);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            assert date1 != null;
            int result = -(date1.compareTo(date2));
            if (result == 0) {
                result = buyer1.compareTo(buyer2);
            }
            return result;
        }
    };
}
