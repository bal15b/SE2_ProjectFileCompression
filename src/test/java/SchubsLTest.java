import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;
import java.io.*;
import java.util.*;


/******************************************************************************
 *  Author : bal15b Ben Lamont
 *  Class  : 2020 Dr. Reeves CS375
 *  Program: SchubsL test cases
 ******************************************************************************/

public class SchubsLTest
{
    @Test
	public void SchubsL_A() throws IOException
	{
		//tests if file is created
		String file = "tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "A" + File.separatorChar + "stuff.txt";
		SchubsL.main(new String[] {file});

		File f = new File("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "A" + File.separatorChar + "stuff.txt.ll");
		assertTrue(f.exists());
	}


	@Test
	public void SchubsL_B() throws IOException
	{
		//tests if in input file doesn't exists
		String file = "tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "B" + File.separatorChar + "stuff.txt";
		SchubsL.main(new String[] {file});

		File f = new File("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "B" + File.separatorChar + "stuff.txt.ll");
		assertTrue(!f.exists());
	}


	@Test
	public void SchubsL_C() throws IOException
	{
		//tests if file is empty
		String file = "tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "C" + File.separatorChar + "stuff.txt";
		SchubsL.main(new String[] {file});

		File f = new File("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "C" + File.separatorChar + "stuff.txt.ll");
		assertTrue(!f.exists());
	}


	@Test
	public void SchubsL_D() throws IOException
	{
		//tests if multiple files are created
		String file = "tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "D" + File.separatorChar + "stuff.txt";
		String file2 = "tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "D" + File.separatorChar + "stuff2.txt";
		SchubsL.main(new String[] {file});
		SchubsL.main(new String[] {file2});

		File f = new File("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "D" + File.separatorChar + "stuff.txt.ll");
		File f2 = new File("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "D" + File.separatorChar + "stuff2.txt.ll");
		assertTrue(f.exists());
		assertTrue(f2.exists());
	}


	@Test
	public void SchubsL_E() throws IOException
	{
		//tests if one file exists but the other doesn't
		String file = "tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "E" + File.separatorChar + "stuff.txt";
		String file2 = "tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "E" + File.separatorChar + "stuff2.txt";
		SchubsL.main(new String[] {file});
		SchubsL.main(new String[] {file2});

		File f = new File("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "E" + File.separatorChar + "stuff.txt.ll");
		File f2 = new File("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "E" + File.separatorChar + "stuff2.txt.ll");
		assertTrue(f.exists());
		assertTrue(!f2.exists());
	}


	@Test
	public void SchubsL_F() throws IOException
	{
		//tests if one file is empty but the other doesn't
		String file = "tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "F" + File.separatorChar + "stuff.txt";
		String file2 = "tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "F" + File.separatorChar + "stuff2.txt";
		SchubsL.main(new String[] {file});
		SchubsL.main(new String[] {file2});

		File f = new File("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "F" + File.separatorChar + "stuff.txt.ll");
		File f2 = new File("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "F" + File.separatorChar + "stuff2.txt.ll");
		assertTrue(f.exists());
		assertTrue(!f2.exists());
	}


	@Test
	public void SchubsL_G() throws IOException
	{
		//tests if compresses when provided with a non text file
		String file = "tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "G" + File.separatorChar + "stuff.pdf";
		SchubsL.main(new String[] {file});

		File f = new File("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "G" + File.separatorChar + "stuff.pdf.ll");
		assertTrue(f.exists());
	}


	@Test
	public void SchubsL_H() throws IOException
	{
		//tests if created file is not empty
		String file = "tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "H" + File.separatorChar + "stuff.txt";
		SchubsL.main(new String[] {file});

		File f = new File("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "H" + File.separatorChar + "stuff.txt.ll");
		assertTrue(f.length() != 0);
	}


	@Test
	public void SchubsL_I() throws IOException
	{
		//tests if file contains expected output
		String file = "tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "I" + File.separatorChar + "stuff.txt";
		SchubsL.main(new String[] {file});

		File f = new File("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "I" + File.separatorChar + "stuff.txt.ll");

		BinaryIn in1 = new BinaryIn("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "I" + File.separatorChar + "stuff.txt.ll");
		BinaryIn in2 = new BinaryIn("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "I" + File.separatorChar + "answer.ll");

		String out1 = in1.readString();
		String out2 = in2.readString();
		assertTrue(out1.equals(out2));
	}

	
	@Test
	public void SchubsL_J() throws IOException
	{
		//tests if file contains expected output for large file (was 1,000,000 randomly generated numbers but was lowered to 100,000 due to taking too long)
		String file = "tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "J" + File.separatorChar + "stuff.txt";
		SchubsL.main(new String[] {file});

		File f = new File("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "J" + File.separatorChar + "stuff.txt.ll");
		
		BinaryIn in1 = new BinaryIn("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "J" + File.separatorChar + "stuff.txt.ll");
		BinaryIn in2 = new BinaryIn("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "J" + File.separatorChar + "answer.ll");

		String out1 = in1.readString();
		String out2 = in2.readString();
		assertTrue(out1.equals(out2));
	}

	@Test
	public void SchubsL_K() throws IOException
	{
		//tests if created file will override an existing file

		File empty = new File("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "K" + File.separatorChar + "stuff.txt.ll");

		if (empty.exists())
		{
			empty.delete();
		}
		empty.createNewFile();

		String file = "tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "K" + File.separatorChar + "stuff.txt";
		SchubsL.main(new String[] {file});

		File f = new File("tests"+ File.separatorChar + "SchubsL_Tests" + File.separatorChar + "K" + File.separatorChar + "stuff.txt.ll");
		assertTrue(f.length() != 0);
	}
	
}










