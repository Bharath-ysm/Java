package com.onlinebankingapplication.www.utility;


public class Helper 
{
	public static String getAlphanumerString()
	{
		
		String alphanumericString="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z" + "0,1,2,3,4,5,6,7,8,9" +"a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
		
		StringBuilder sb=new StringBuilder(16);
		
		for(int i=0;i<16;i++)
		{
			int index=(int)(alphanumericString.length()* Math.random());
			sb.append(alphanumericString.charAt(index));
		}
		
		return sb.toString().toUpperCase();
	}
}
