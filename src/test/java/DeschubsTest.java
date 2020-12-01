import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;
import java.io.*;
import java.util.*;


/******************************************************************************
 *  Author : bal15b Ben Lamont
 *  Class  : 2020 Dr. Reeves CS375
 *  Program: DeSchubs test cases
 ******************************************************************************/

public class DeschubsTest
{

	@Test
	public void Deschubs_A() throws IOException
	{
		//tests if file is created from .ll
		String file = "tests"+ File.separatorChar + "Deschubs_Tests" + File.separatorChar + "A" + File.separatorChar + "stuff.txt.ll";
		Deschubs.main(new String[] {file});

		File f = new File("tests"+ File.separatorChar + "Deschubs_Tests" + File.separatorChar + "A" + File.separatorChar + "stuff.txt");
		assertTrue(f.exists());
	}

	@Test
	public void Deschubs_B() throws IOException
	{
		//tests if file is created from .ll
		String file = "tests"+ File.separatorChar + "Deschubs_Tests" + File.separatorChar + "B" + File.separatorChar + "stuff.txt";

		String words = "this stuff goes in the file";

		BinaryOut out = new BinaryOut(file);

		out.write(words);
		out.close();
		

		SchubsL.main(new String[] {file});

		Deschubs.main(new String[] {file+".ll"});

		BinaryIn in = new BinaryIn(file);

		String result = in.readString();
		assertTrue(words.equals(result));
	}

	@Test
	public void Deschubs_C() throws IOException
	{
		//tests if file is created from .hh
		String file = "tests"+ File.separatorChar + "Deschubs_Tests" + File.separatorChar + "C" + File.separatorChar + "stuff.txt";

		String words = "this stuff goes in the file";

		BinaryOut out = new BinaryOut(file);

		out.write(words);
		out.close();

		SchubsH.main(new String[] {file});

		Deschubs.main(new String[] {file+".hh"});

		BinaryIn in = new BinaryIn(file);

		String result = in.readString();
		assertTrue(words.equals(result));
	}

	@Test
	public void Deschubs_D() throws IOException
	{
		//tests if file is created from .zl
		String file = "tests"+ File.separatorChar + "Deschubs_Tests" + File.separatorChar + "D" + File.separatorChar + "stuff.txt";
		String archive = "tests"+ File.separatorChar + "Deschubs_Tests" + File.separatorChar + "D" + File.separatorChar + "archive.zl";

		String words = "this stuff goes in the file";

		BinaryOut out = new BinaryOut(file);

		out.write(words);
		out.close();

		SchubsH.main(new String[] {archive,file});

		Deschubs.main(new String[] {file+".zl"});

		BinaryIn in = new BinaryIn(file);

		String result = in.readString();
		assertTrue(words.equals(result));
	}

	@Test
	public void Deschubs_E() throws IOException
	{
		//tests if file is created from .zl but with multiple files
		String file = "tests"+ File.separatorChar + "Deschubs_Tests" + File.separatorChar + "E" + File.separatorChar + "stuff.txt";
		String file2 = "tests"+ File.separatorChar + "Deschubs_Tests" + File.separatorChar + "E" + File.separatorChar + "stuff2.txt";
		String archive = "tests"+ File.separatorChar + "Deschubs_Tests" + File.separatorChar + "E" + File.separatorChar + "archive.zl";

		String words = "this stuff goes in the file";

		BinaryOut out = new BinaryOut(file);
		BinaryOut out2 = new BinaryOut(file2);


		out.write(words);
		out.close();
		out2.write(words);
		out2.close();

		SchubsH.main(new String[] {archive,file, file2});

		Deschubs.main(new String[] {file+".zl"});

		BinaryIn in = new BinaryIn(file);
		BinaryIn in2 = new BinaryIn(file2);


		String result = in.readString();
		assertTrue(words.equals(result));
		String result2 = in2.readString();
		assertTrue(words.equals(result2));
	}

	@Test
	public void Deschubs_F() throws IOException
	{
		//tests if file is missing
		String file = "tests"+ File.separatorChar + "Deschubs_Tests" + File.separatorChar + "F" + File.separatorChar + "stuff.txt.ll";
		Deschubs.main(new String[] {file});

		File f = new File("tests"+ File.separatorChar + "Deschubs_Tests" + File.separatorChar + "F" + File.separatorChar + "stuff.txt");
		assertTrue(!f.exists());
	}

	@Test
	public void Deschubs_G() throws IOException
	{
		//tests if file is empty
		String file = "tests"+ File.separatorChar + "Deschubs_Tests" + File.separatorChar + "G" + File.separatorChar + "stuff.txt.ll";
		Deschubs.main(new String[] {file});

		File f = new File("tests"+ File.separatorChar + "Deschubs_Tests" + File.separatorChar + "G" + File.separatorChar + "stuff.txt");
		assertTrue(!f.exists());
	}
	


}










