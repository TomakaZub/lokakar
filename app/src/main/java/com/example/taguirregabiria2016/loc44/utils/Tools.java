package com.example.taguirregabiria2016.loc44.utils;

import com.example.taguirregabiria2016.loc44.model.Location;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by ojeanmarie2016 on 29/06/2017.
 */

public class Tools {

    /**
     *
     * @param dateStr chaine de caractère au format "yyyy/mm/dd hh:mm"
     * @return un objet Calendar
     */
    public static Calendar Str2Cal(String dateStr) {

        Calendar newCal = Calendar.getInstance();
        String[] dummies = dateStr.split(" ");
        String[] dParts = dummies[0].split("/");
        String[] hParts = dummies[1].split(":");
        int year = Integer.parseInt(dParts[2]);
        int mounth = Integer.parseInt(dParts[1]);
        int day = Integer.parseInt(dParts[0]);
        int hour = Integer.parseInt(hParts[0]);
        int minute = Integer.parseInt(hParts[1]);
        newCal.set(year, mounth, day, hour, minute);

        return newCal;
    }

    /**
     *
     * @param cal objet Calendar
     * @return une chaine de caractère au format yyyy/mm/dd hh:mm
     */
    public static String Cal2Str(Calendar cal) {

        String newStr;

        int day = cal.get(Calendar.DAY_OF_MONTH);
        int mounth = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        newStr = String.format(Locale.FRANCE, "%d/%02d/%02d %02d:%02d", year, mounth, day, hour, minute);

        return newStr;
    }

    /**
     *
     * @param location : objet location
     * @return double : le coût de la location
     */
    public static double getPrice(Location location) {

        Calendar debut = Tools.Str2Cal(location.getDebut());
        Calendar fin = Tools.Str2Cal(location.getFin());

        double ddays = ((fin.getTimeInMillis() - debut.getTimeInMillis()) / (double) (24 * 60 * 60 * 1000));

        int days = (int) ddays;

        if (ddays - (double) days > 0.5) days++;

        if (days == 0) days = 1;
        double prix = (double) days * location.getVehicule().getPrixJour();

        return prix;
    }

}
