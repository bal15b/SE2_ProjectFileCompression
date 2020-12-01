import java.io.*;
import java.util.*;

/*************************************************************************
 *  Assignment: Project File Compression
 *  Program: SchubsArc.java
 *  Author : Ben Lamont
 *  Date   : Fall 2020
 *  Course : CS375 Software Engineering II
 *  Compilation:  javac SchubsArc.java
 *  Execution:    java SchubsArc <archive> <file names>   (compress)
 *  Dependencies: BinaryIn.java BinaryOut.java StdIn.java StdOut.java
 *
 *  Compress (one or many) file(s) using LZW into a single archive name <archive's name>+.zl
 *
 *************************************************************************/

public class SchubsArc 
{
    public static Boolean FileExists = true;
    public static Boolean correct = true;
    public static char separator = (char) 255;
    public static BinaryOut out = null;

    public static Boolean FileExists()
    {
        return FileExists;
    }

    public static Boolean correct_input()
    {
        return correct;
    }

    public static String archiveInput(String archive, String name, String data) throws IOException
    {
        BinaryIn in = new BinaryIn(name);

        File f = new File(name);

        if(!f.exists() || !f.isFile())
        {
            System.out.println(name + " is and invalid file or does not exist");
            FileExists = false;
            return "";
        }

        long size = f.length();
        int name_size = name.length();
        
        data += name_size;
        data += separator;
        data += name;
        data += separator;
        data += size;
        data += separator;

        while(!in.isEmpty())
        {
            data += in.readChar();
        }
        data += separator;
        return data;
    }

    public static void main(String[] args)
    {
        File archive = null;

        if (args.length <= 1)
        {
            correct = false;
            System.out.println("Too few files detected.");
            return;
        }

        String suffix = "" + args[0].charAt(args[0].length()-3) + args[0].charAt(args[0].length()-2) + args[0].charAt(args[0].length()-1);

        if (!suffix.equals(".zl"))
        {
            System.out.println("That is not an archive file");
            return;
        }


        try{
            archive = new File(args[0]);
            if (archive.exists())
            {
                archive.delete();
            }
            archive.createNewFile();
            
            out = new BinaryOut(args[0]);

            String data = "";
            
            for(int i = 1; i < args.length;i++)
            {
                data = archiveInput(args[0],args[i], data);

                File t = new File(args[i]);
                if(!t.exists())
                {
                    System.out.println("File:" + args[i] + " could not be found");
                }
            }
            SchubsL comp = new SchubsL();
            comp.compress(data,out);
            if(out != null)
            {
                if (data.equals(""))
                {
                    return;
                }
                out.close();
            }

            
        } catch (Exception e) {
            System.out.println("Error archiving files" + e);
        }
    }



}
