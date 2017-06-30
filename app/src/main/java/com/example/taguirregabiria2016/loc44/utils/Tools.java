package com.example.taguirregabiria2016.loc44.utils;

import com.example.taguirregabiria2016.loc44.model.Location;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by ojeanmarie2016 on 29/06/2017.
 */

public class Tools {

    private static final String[] dayOfWeek = {"Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
    private static final long MILLISEC_A_DAY = 86400000;

    /**
     * @param dateStr chaine de caractère au format "dd/mm/yyyy hh:mm"
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
        newCal.set(year, mounth - 1, day, hour, minute);

        return newCal;
    }

    /**
     * @param dateStr chaine de caractère au format "yyyy/mm/dd hh:mm"
     * @return un objet Calendar
     */
    public static Calendar StrBDD2Cal(String dateStr) {

        Calendar newCal = Calendar.getInstance();
        String[] dummies = dateStr.split(" ");
        String[] dParts = dummies[0].split("/");
        String[] hParts = dummies[1].split(":");
        int year = Integer.parseInt(dParts[0]);
        int mounth = Integer.parseInt(dParts[1]);
        int day = Integer.parseInt(dParts[2]);
        int hour = Integer.parseInt(hParts[0]);
        int minute = Integer.parseInt(hParts[1]);
        newCal.set(year, mounth - 1, day, hour, minute);

        return newCal;
    }

    /**
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

        newStr = String.format(Locale.FRANCE, "%02d/%02d/%d %02d:%02d", day, mounth + 1, year, hour, minute);

        return newStr;
    }

    /**
     * @param cal objet Calendar
     * @return une chaine de caractère au format yyyy/mm/dd hh:mm
     */
    public static String Cal2StrBDD(Calendar cal) {

        String newStr;

        int day = cal.get(Calendar.DAY_OF_MONTH);
        int mounth = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        newStr = String.format(Locale.FRANCE, "%d/%02d/%02d %02d:%02d", year, mounth + 1, day, hour, minute);

        return newStr;
    }

    /**
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

    /**
     * @param debutStr date de début au format yyyy/mm/dd hh:mm
     * @param finStr   date de fin au format yyyy/mm/dd hh:mm
     * @param prixJour
     * @return
     */
    public static double getPrice(String debutStr, String finStr, double prixJour) {

        Calendar debut = Tools.StrBDD2Cal(debutStr);
        Calendar fin = Tools.StrBDD2Cal(finStr);

        double ddays = ((fin.getTimeInMillis() - debut.getTimeInMillis()) / (double) (24 * 60 * 60 * 1000));

        int days = (int) ddays;

        if ((double) days - ddays > 0.5) days++;

        if (days == 0) days = 1;
        double prix = (double) days * prixJour;

        return prix;
    }

    public static String humanDate(String dateStr) {

        String result = dateStr;

        Calendar today = Calendar.getInstance();
        String toDayStr = Cal2Str(today);
        toDayStr = toDayStr.substring(0, toDayStr.length() - 5) + "00:00";
        today = Str2Cal(toDayStr);

        Calendar theDay = Calendar.getInstance();
        String[] dummies = dateStr.split(" ");
        String[] dParts = dummies[0].split("/");
        String[] hParts = dummies[1].split(":");
        int year = Integer.parseInt(dParts[2]);
        int mounth = Integer.parseInt(dParts[1]);
        int day = Integer.parseInt(dParts[0]);
        int hour = Integer.parseInt(hParts[0]);
        int minute = Integer.parseInt(hParts[1]);
        theDay.set(year, mounth - 1, day, hour, minute);

        double ddays = ((theDay.getTimeInMillis() - today.getTimeInMillis()) / (double) (MILLISEC_A_DAY));

        if (ddays < 0.0) {

            if (ddays >= -1.0) {
                result = String.format(Locale.FRANCE, "Hier à %02d:%02d", hour, minute);
            } else {
                if (ddays >= -2.0) {
                    result = String.format(Locale.FRANCE, "Avant-hier à %02d:%02d", hour, minute);
                } else {
                    if (ddays > -7.0) {
                        int dow = theDay.get(Calendar.DAY_OF_WEEK) - 1;
                        result = dayOfWeek[dow] + " dernier";
                        result = String.format(Locale.FRANCE, "%s dernier à %02d:%02d", dayOfWeek[dow], hour, minute);
                    }
                }
            }
        } else {

            if (ddays <= 1.0) {
                result = String.format(Locale.FRANCE, "Aujourd'hui à %02d:%02d", hour, minute);
            } else {
                if (ddays <= 2.0) {
                    result = String.format(Locale.FRANCE, "Demain à %02d:%02d", hour, minute);
                } else {
                    if (ddays < 7.0) {
                        int dow = theDay.get(Calendar.DAY_OF_WEEK) - 1;
                        result = dayOfWeek[dow] + " prochain";
                        result = String.format(Locale.FRANCE, "%s prochain à %02d:%02d", dayOfWeek[dow], hour, minute);
                    }
                }
            }
        }
        return result;
    }

    public static String daysOfFuturePast (String theDay) {

        String result = "futur";
        Calendar today = Calendar.getInstance();
        Calendar target = Str2Cal(theDay);

        String toDayStr = Cal2Str(today);
        toDayStr = toDayStr.substring(0, toDayStr.length() - 5) + "00:00";
        today = Str2Cal(toDayStr);

        if ((target.getTimeInMillis() - today.getTimeInMillis())<0) {
            result = "past";
        }
        return result;
    }

    public static String convertDate (String date) {

        String[] dummies = date.split(" ");
        String[] parts = dummies[0].split("/");

        return parts[2]+"/"+parts[1]+"/"+parts[0]+" "+dummies[1];
    }

    public static String convertDateOnly (String date) {

        String[] parts = date.split("/");

        return parts[2]+"/"+parts[1]+"/"+parts[0];
    }

}

