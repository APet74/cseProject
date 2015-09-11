package com.airamerica.utils;

/* Needed to calculate the distance between two airports (Phase II,IV and V)
 * List of latitudes and longitudes of various cities: http://www.infoplease.com/ipa/A0001796.html
 * Example :http://rosettacode.org/wiki/Haversine_formula#Java
 */
public class Haversine {
	
	//Radius of Earth in Miles
	public static final double R = 3959.87;
	
	//Calculate distance using Latitudes and longitudes of two places
    public static double getMiles(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
 
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
    
	//Calculate distance using Latitudes and longitudes of two places
    public static double getMiles(int latdeg1, int latmin1, int londeg1, int lonmin1,
    		int latdeg2, int latmin2, int londeg2, int lonmin2) {
    	double lat1 = latdeg1 + (double)(latmin1)/60;
    	double lon1 = londeg1 + (double)(lonmin1)/60;
    	double lat2 = latdeg2 + (double)(latmin2)/60;
    	double lon2 = londeg2 + (double)(lonmin2)/60;
    	return getMiles(lat1,lon1,lat2,lon2);
    }
    
    
    // Distance between Atlanta(ATL) and (PHX)
    //ATL -(33�45'N84�23'W) PHX -(33�29'N112�04'W)
    public static void main(String[] args) {
    	System.out.println("Distance between ATL and PHX:"+getMiles(33,45,84,23,33,29,112,4));
        System.out.println("Distance between ATL and PHX:"+getMiles(33+45.0/60,84+23.0/60,33+29.0/60,112+4.0/60));
    }
}