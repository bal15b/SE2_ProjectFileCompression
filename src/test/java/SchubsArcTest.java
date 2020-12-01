import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;
import java.io.*;
import java.util.*;


/******************************************************************************
 *  Author : bal15b Ben Lamont
 *  Class  : 2020 Dr. Reeves CS375
 *  Program: SchubsArc test cases
 ******************************************************************************/

public class SchubsArcTest
{
	
    @Test
	public void SchubsArc_A() throws IOException
	{
		//tests if archive is created
		String file = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "A" + File.separatorChar + "stuff.txt";
		String archive = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "A" + File.separatorChar + "archive.zl";

		SchubsArc.main(new String[] {archive,file});

		File f = new File("tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "A" + File.separatorChar + "archive.zl");
		assertTrue(f.exists());
	}

	@Test
	public void SchubsArc_B() throws IOException
	{
		//tests if text file doesn't exist
		String file = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "B" + File.separatorChar + "stuff.txt";
		String archive = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "B" + File.separatorChar + "archive.zl";

		SchubsArc.main(new String[] {archive,file});

		File f = new File("tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "B" + File.separatorChar + "archive.zl");
		assertTrue(f.length() == 0);
	}

	@Test
	public void SchubsArc_C() throws IOException
	{
		//tests if still reads text file even when empty
		String file = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "C" + File.separatorChar + "stuff.txt";
		String archive = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "C" + File.separatorChar + "archive.zl";

		SchubsArc.main(new String[] {archive,file});

		File f = new File("tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "C" + File.separatorChar + "archive.zl");
		assertTrue(f.length() != 0);
	}

	@Test
	public void SchubsArc_D() throws IOException
	{
		//tests if archive reads in multiple files
		String file = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "D" + File.separatorChar + "stuff.txt";
		String file2 = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "D" + File.separatorChar + "stuff2.txt";
		String archive = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "D" + File.separatorChar + "archive.zl";

		SchubsArc.main(new String[] {archive,file, file2});

		File f = new File("tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "D" + File.separatorChar + "archive.zl");
		assertTrue(f.length() > 100);
	}

	@Test
	public void SchubsArc_E() throws IOException
	{
		//tests if archive reads in multiple files but one is not found
		String file = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "E" + File.separatorChar + "stuff.txt";
		String file2 = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "E" + File.separatorChar + "stuff2.txt";
		String archive = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "E" + File.separatorChar + "archive.zl";

		SchubsArc.main(new String[] {archive,file, file2});

		File f = new File("tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "E" + File.separatorChar + "archive.zl");
		assertTrue(f.length() == 0);
	}

	@Test
	public void SchubsArc_F() throws IOException
	{
		//tests if archive reads in multiple files but one is empty
		String file = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "F" + File.separatorChar + "stuff.txt";
		String file2 = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "F" + File.separatorChar + "stuff2.txt";
		String archive = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "F" + File.separatorChar + "archive.zl";

		SchubsArc.main(new String[] {archive,file, file2});

		File f = new File("tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "F" + File.separatorChar + "archive.zl");
		assertTrue(f.length() < 100);
	}

	@Test
	public void SchubsArc_G() throws IOException
	{
		//tests to make sure archive is not created with non archive file names
		String file = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "G" + File.separatorChar + "stuff.txt";
		String archive = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "G" + File.separatorChar + "archive.txt";

		SchubsArc.main(new String[] {archive,file});

		File f = new File("tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "G" + File.separatorChar + "archive.txt");
		assertTrue(!f.exists());
	}

	@Test
	public void SchubsArc_H() throws IOException
	{
		//tests to make sure archive is only made with .zl file names and not .zh
		String file = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "H" + File.separatorChar + "stuff.txt";
		String archive = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "H" + File.separatorChar + "archive.zh";

		SchubsArc.main(new String[] {archive,file});

		File f = new File("tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "H" + File.separatorChar + "archive.zh");
		assertTrue(!f.exists());
	}

	@Test
	public void SchubsArc_I() throws IOException
	{
		//tests to make it will override existing archives of the same name
		File empty = new File("tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "I" + File.separatorChar + "archive.zl");

		if (empty.exists())
		{
			empty.delete();
		}
		empty.createNewFile();

		String file = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "I" + File.separatorChar + "stuff.txt";
		String archive = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "I" + File.separatorChar + "archive.zl";

		SchubsArc.main(new String[] {archive,file});

		File f = new File("tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "I" + File.separatorChar + "archive.zl");
		assertTrue(f.length() != 0);
	}

	@Test
	public void SchubsArc_J() throws IOException
	{
		//tests if archive contains expected output
		String file = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "J" + File.separatorChar + "stuff.txt";
		String archive = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "J" + File.separatorChar + "archive.zl";

		SchubsArc.main(new String[] {archive,file});

		File f = new File("tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "J" + File.separatorChar + "archive.zl");
		//assertTrue(f.exists());
	}

	@Test
	public void SchubsArc_K() throws IOException
	{
		//tests if file contains expected output for 10 files
		String file = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "K" + File.separatorChar + "stuff.txt";
		String file2 = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "K" + File.separatorChar + "stuff2.txt";
		String file3 = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "K" + File.separatorChar + "stuff3.txt";
		String file4 = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "K" + File.separatorChar + "stuff4.txt";
		String file5 = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "K" + File.separatorChar + "stuff5.txt";
		String file6 = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "K" + File.separatorChar + "stuff6.txt";
		String file7 = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "K" + File.separatorChar + "stuff7.txt";
		String file8 = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "K" + File.separatorChar + "stuff8.txt";
		String file9 = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "K" + File.separatorChar + "stuff9.txt";
		String file10 = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "K" + File.separatorChar + "stuff10.txt";

		String archive = "tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "K" + File.separatorChar + "archive.zl";

		SchubsArc.main(new String[] {archive,file,file2,file3,file4,file5,file6,file7,file8,file9,file10});

		File f = new File("tests"+ File.separatorChar + "SchubsArc_Tests" + File.separatorChar + "K" + File.separatorChar + "archive.zl");
		//assertTrue(f.exists());
	}


}










