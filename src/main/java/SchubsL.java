/*************************************************************************
 *  Assignment: Project File Compression
 *  Program: SchubsH.java
 *  Author : Ben Lamont
 *  Date   : Fall 2020
 *  Course : CS375 Software Engineering II
 *  Compilation:  javac SchubsL.java
 *  Execution:    java SchubsL input.txt   (compress)
 *  Dependencies: BinaryIn.java BinaryOut.java TST.java Queue.java MinPQ.java StdIn.java StdOut.java
 *
 *  Compress (one or many) file(s) using LZW into <input file names>+.ll
 *
 *
 *************************************************************************/
import java.io.*;
import java.util.*;
public class SchubsL {
     
    private static final int R = 256; 
    private static final int L = 4096;   
    private static final int W = 12;
    
    public static void compress(BinaryIn in, BinaryOut out) { 
        String input = in.readString();
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
        {
            st.put("" + (char) i, i);
        }
        int code = R+1;

        while (input.length() > 0) 
        {
            String s = st.longestPrefixOf(input);
            out.write(st.get(s), W); 
            int t = s.length();
            if (t < input.length() && code < L)
            {
                st.put(input.substring(0, t + 1), code++);
            } 
            input = input.substring(t);
        }
        out.write(R, W);
        out.close();
    } 

    public static void compress(String input, BinaryOut out) { 
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
        {
            st.put("" + (char) i, i);
        }
        int code = R+1;
        
        while (input.length() > 0) 
        {
            String s = st.longestPrefixOf(input);
            out.write(st.get(s), W); 
            int t = s.length();
            if (t < input.length() && code < L)
            {
                st.put(input.substring(0, t + 1), code++);
            } 
            input = input.substring(t);
        }
        out.write(R, W);
    } 

    public static void main(String[] args) {
        BinaryIn in = null;
        BinaryOut out = null;
        for(int i = 0; i < args.length; i++)
        {
            try
            {
                //conditionals
                File f = new File(args[i]);
                if(!f.exists() || f.isDirectory()) { 
                    System.out.println("File could not be found");
                }
                else if(f.length() == 0)
                {
                    System.out.println("File:" + args[i] + " is empty and could not be compressed");
                }
                else
                {
                    in = new BinaryIn(args[i]);
                    out = new BinaryOut(args[i] + ".ll");
                    compress(in, out);
                }
                
            }
            finally{
                if (out != null)
                {
                    out.close();
                }
            }

            
        }
    }

}
