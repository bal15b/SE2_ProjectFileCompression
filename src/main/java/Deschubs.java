import java.io.*;
import java.util.*;

/*************************************************************************
 *  Assignment: Project File Compression
 *  Program: DeSchubs.java
 *  Author : Ben Lamont
 *  Date   : Fall 2020
 *  Course : CS375 Software Engineering II
 *  Compilation:  javac DeSchubs.java
 *  Execution:    java DeSchubs <compressed file name>
 *  Dependencies: BinaryIn.java BinaryOut.java StdIn.java StdOut.java
 *
 *  Decompresses .hh, .ll, and .zl files
 *
 *
 *************************************************************************/

public class Deschubs 
{
    private static final int R = 256; 
    private static final int L = 4096;   
    private static final int W = 12;

    
    public static void add_node(Node root, Node leaf, String path, String path_0, Map<String,Character> m )
    {
        if(path.length() == 2 && root.left == null)
        {
            leaf.s += "0";
            m.put(leaf.s,leaf.c);
           root.left = leaf;
        }
        else if(path_0.length() == 1 && root.right == null && root.left.done == true)
        {
            leaf.s += "1";
            m.put(leaf.s,leaf.c);
            root.right = leaf;
        }
        else if(root.left == null)
        {
            Node n = new Node();
            n.left = null;
            n.right = null;
            n.s = "";
            n.done = false;
            root.left = n;
            String temp = "";
            leaf.s += "0";
            for(int i = 1; i < path.length(); i++)
            {
                temp += path.charAt(i);
            }
            add_node(root.left,leaf,temp,path_0,m);
        }
        else if(root.left.done == true && root.right == null)
        {
            Node n = new Node();
            n.left = null;
            n.right = null;
            n.s = "";
            n.done = false;
            root.right = n;
            leaf.s += "1";
            add_node(root.right,leaf,path_0,path_0,m);
        }
        else if(root.left.done == true)
        {
            leaf.s += "1";
            add_node(root.right,leaf,path_0,path_0,m);
        }
        else
        {
            String temp = "";
            for(int i = 1; i < path.length(); i++)
            {
                temp += path.charAt(i);
            }
            leaf.s += "0";
            add_node(root.left,leaf,temp,path_0,m);
        }
        if(root.left != null && root.right != null)
        {
            if(root.left.done == true && root.right.done == true)
            {
                root.done = true;   
            }
        }
        
        
    }

    public static void Huffman_decompress(BinaryIn in, BinaryOut out) throws java.io.IOException
    {
        String input = in.readString();
        char[] data = input.toCharArray();
        Map<String,Character> m = new HashMap<String,Character>();

        Node r = new Node();
        r.left = null;
        r.right = null;
        r.s = "";
        r.done = false;

        String s = "";

        int count = 0;
        int i;
        for(i = 0; i < data.length; i++)
        {
            if (count == 16)
            {
                i += 16;
                break;
            }
            if (data[i] == '1')
            {
                i++;
                count = 0;
                s += data[i];
                String letter = "";
                for(int j = i; j < i+8; j++)
                {
                    letter += data[j];
                }
                i += 7;
                Node n = new Node();
                n.left = null;
                n.right = null;
                n.done = true;
                n.s = "";
                n.c = (char)Integer.parseInt(letter,2);
                add_node(r,n,s,s,m);
                s = "";
                letter = "";
            }
            else
            {
                s+= data[i];
                count ++;
            }
        }
        String temp = "";
        for(i=i; i < data.length; i++)
        {
            temp += data[i];
            if(m.containsKey(temp))
            {
                out.write(""+m.get(temp));
                temp = "";
            }
        }

    }

    public static String Huffman_decompress(BinaryIn in) throws java.io.IOException
    {
        String input = in.readString();
        String result = "";
        char[] data = input.toCharArray();
        Map<String,Character> m = new HashMap<String,Character>();

        Node r = new Node();
        r.left = null;
        r.right = null;
        r.s = "";
        r.done = false;

        String s = "";

        int count = 0;
        int i;
        for(i = 0; i < data.length; i++)
        {
            if (count == 16)
            {
                i += 16;
                break;
            }
            if (data[i] == '1')
            {
                i++;
                count = 0;
                s += data[i];
                String letter = "";
                for(int j = i; j < i+8; j++)
                {
                    letter += data[j];
                }
                i += 7;
                Node n = new Node();
                n.left = null;
                n.right = null;
                n.done = true;
                n.s = "";
                n.c = (char)Integer.parseInt(letter,2);
                add_node(r,n,s,s,m);
                s = "";
                letter = "";
            }
            else
            {
                s+= data[i];
                count ++;
            }
        }
        String temp = "";
        for(i=i; i < data.length; i++)
        {
            temp += data[i];
            if(m.containsKey(temp))
            {
                result += m.get(temp);
                temp = "";
            }
        }
        return result;
    }

    public static void LZW_Decompress(BinaryIn in, BinaryOut out) 
    {
        String[] st = new String[L];
        int i;

        for (i = 0; i < R; i++)
        {
            st[i] = "" + (char) i;
        }
        st[i++] = "";                   
        int codeword = in.readInt(W);
        String val = st[codeword];

        while (true) 
        {
            out.write(val);
            codeword = in.readInt(W);
            if (codeword == R) break;
            String s = st[codeword];
            if (i == codeword)
            {
                s = val + val.charAt(0); 
            }  
            if (i < L)
            {
                st[i++] = val + s.charAt(0);
            } 
            val = s;
        }
    }

    public static String LZW_Decompress(BinaryIn in) 
    {
        String result = "";
        String[] st = new String[L];
        int i;

        for (i = 0; i < R; i++)
        {
            st[i] = "" + (char) i;
        }
        st[i++] = "";                   
        int codeword = in.readInt(W);
        String val = st[codeword];

        while (true) 
        {
            result += val;
            codeword = in.readInt(W);
            if (codeword == R)
            {
                return result;
            }
            String s = st[codeword];
            if (i == codeword)
            {
                s = val + val.charAt(0); 
            }  
            if (i < L)
            {
                st[i++] = val + s.charAt(0);
            } 
            val = s;
        }
    }

    public static void Archive_decompress(String result) 
    {
        char separator = (char) 255;
        String temp = "";
        int count = 0;
        String name_size = "";
        String name = "";
        String size = "";
        String contents = "";
        BinaryOut out = null;
        for(int i = 0; i < result.length();i++)
        {
            if(result.charAt(i) == separator)
            {
                if(count == 0)
                {
                    name_size = temp;
                    count ++;
                    temp = "";
                }
                else if (count == 1)
                {
                    name = temp;
                    count ++;
                    temp = "";
                }
                else if (count == 2)
                {
                    size = temp;
                    count ++;
                    temp = "";
                }
                else if (count == 3)
                {
                    contents = temp;
                    count = 0;

                    out = new BinaryOut(name);

                    out.write(contents);
                    out.close();
                    temp = "";
                }
            }
            else
            {
                temp += result.charAt(i);
            }
        }
    }

    
    
    public static void main(String[] args) throws java.io.IOException 
    {
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
                    String name = "";
                    for(int j = 0; j < args[i].length()-3; j++)
                    {
                        name += args[i].charAt(j);
                    }
                    out = new BinaryOut(args[i]);
                }
                else
                {
                    in = new BinaryIn(args[i]);
                    String name = "";
                    for(int j = 0; j < args[i].length()-3; j++)
                    {
                        name += args[i].charAt(j);
                    }
                    out = new BinaryOut(name);
                    String suffix = "" + args[i].charAt(args[i].length()-3) + args[i].charAt(args[i].length()-2) + args[i].charAt(args[i].length()-1);
                    if(suffix.equals(".ll"))
                    {
                        LZW_Decompress(in,out);
                    }
                    else if(suffix.equals(".hh"))
                    {
                        Huffman_decompress(in,out);
                    }
                    else if(suffix.equals(".zl"))
                    {
                        String result = LZW_Decompress(in);

                        Archive_decompress(result);
                    }
                    else
                    {
                        System.out.println("This file type not supported");
                    }
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
