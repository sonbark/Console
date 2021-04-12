package com.banzaraktsaeva.console.ui.gallery;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class Invoice {
    private String buyer;
    private String date;
    private double price;

    Invoice(String date, String buyer, double price){
        this.date = date;
        this.buyer = buyer;
        this.price = price;
    }

    String getBuyer() {
        return this.buyer;
    }

    String getDate() {
        return this.date;
    }

    double getPrice() {
        return this.price;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    static void sortListBy(ArrayList<Invoice> list) {
        Collections.sort(list, byDate);
    }

    private static final Comparator<Invoice> byDate = new Comparator<Invoice>() {

        final Locale DEFAULT = Locale.getDefault();

        @Override
        public int compare(Invoice invoice1, Invoice invoice2) {
            String dateStr1 = invoice1.getDate();
            String dateStr2 = invoice2.getDate();

            String buyer1 = invoice1.getBuyer();
            String buyer2 = invoice2.getBuyer();

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
