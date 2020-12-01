import java.io.*;
import java.util.*;

/*************************************************************************
 *  Assignment: Project File Compression
 *  Program: SchubsH.java
 *  Author : Ben Lamont
 *  Date   : Fall 2020
 *  Course : CS375 Software Engineering II
 *  Compilation:  javac SchubsH.java
 *  Execution:    java SchubsH input.txt   (compress)
 *  Dependencies: BinaryIn.java BinaryOut.java StdIn.java StdOut.java
 *
 *  Compress (one or many) file(s) using Huffman into <input file names>+.hh
 *
 *
 *************************************************************************/

abstract class HuffmanTree implements Comparable<HuffmanTree> 
{
    public int frequency; 
    public HuffmanTree(int freq) 
    {
        frequency = freq;
    }

    public int compareTo(HuffmanTree tree) 
    {
        return frequency - tree.frequency;
    }
}

class HuffmanLeaf extends HuffmanTree 
{
    public  char value; 

    public HuffmanLeaf(int freq, char val) 
    {
        super(freq);
        value = val;
    }
}

class HuffmanNode extends HuffmanTree 
{
    public HuffmanTree left;
    public HuffmanTree right; 
    public HuffmanNode(HuffmanTree l, HuffmanTree r) 
    {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }
}
class Node 
{
    public char c;
    public String s;

    public Node left;
    public Node right;
    public boolean done;
}
public class SchubsH 
{
    
    public static HuffmanTree buildTree(int[] charFreqs) {
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();

        for (int i = 0; i < charFreqs.length; i++)
            if (charFreqs[i] > 0)
            {
                trees.offer(new HuffmanLeaf(charFreqs[i], (char)i));
            }
 
        assert trees.size() > 0;
        while (trees.size() > 1) 
        {
            HuffmanTree a = trees.poll();
            HuffmanTree b = trees.poll();
 
            trees.offer(new HuffmanNode(a, b));
        }
        return trees.poll();
    }

    public static void printCodes(HuffmanTree tree, StringBuffer prefix, String path, BinaryOut out, Map<Character,String> m)  throws java.io.IOException 
    {
        assert tree != null;
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
            m.put(leaf.value,prefix.toString());
            out.write(path);
            String stuff = Integer.toBinaryString((int)leaf.value);
            String better_stuff = String.format("%8s", stuff).replace(' ', '0');
            out.write(better_stuff);
 
        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;
 
            prefix.append('0');
            printCodes(node.left, prefix,"0"+path, out,m);
            prefix.deleteCharAt(prefix.length()-1);
 
            prefix.append('1');
            printCodes(node.right, prefix,"1", out,m);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }

    public static void compress(BinaryIn in, BinaryOut out) throws java.io.IOException 
    {
        String data = in.readString();
        int[] charFreqs = new int[256];
        for (char c : data.toCharArray())
        {
            charFreqs[c]++;
        }
        HuffmanTree tree = buildTree(charFreqs);
        Map<Character,String> m = new HashMap<Character,String>();

        printCodes(tree, new StringBuffer(),"1", out,m);
        String stuff = Integer.toBinaryString(data.length());

        String better_stuff = String.format("%16s", stuff).replace(' ', '0');

        out.write("0000000000000000"+better_stuff);
        for(int i = 0; i < data.length();i++)
        {
            out.write(m.get(data.charAt(i)));
        }
    }

    public static void compress(String data, BinaryOut out) throws java.io.IOException 
    {
        int[] charFreqs = new int[256];
        for (char c : data.toCharArray())
        {
            charFreqs[c]++;
        }
        HuffmanTree tree = buildTree(charFreqs);
        Map<Character,String> m = new HashMap<Character,String>();

        printCodes(tree, new StringBuffer(),"1", out,m);
        String stuff = Integer.toBinaryString(data.length());

        String better_stuff = String.format("%16s", stuff).replace(' ', '0');

        out.write("0000000000000000"+better_stuff);
        for(int i = 0; i < data.length();i++)
        {
            out.write(m.get(data.charAt(i)));
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
                    System.out.println("File:" + args[i] + " is empty and could not be compressed");
                }
                else
                {
                    in = new BinaryIn(args[i]);
                    out = new BinaryOut(args[i] + ".hh");
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
