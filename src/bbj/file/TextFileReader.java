package bbj.file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Vector;

/**
 * Class to read a text file and convert its lines into objects.
 * <p>
 * The file to be read is expected to contain a list of lines, with each line
 * representing one object. The 'readVector' method reads the lines of the
 * file, converts them into objects, and stores them in a Vector, which is
 * returned to the caller.
 *
 * The file to be read can contain comment lines (which will not be converted
 * to objects). By default, comment lines are indicated by a "#" character in
 * the first position (after the line is "trimmed").  This criteria can be
 * modified by overriding the 'isCommentLine' method.
 */
public class TextFileReader<T>
{
   /**
    * Interface used to convert a text file line to an instance of T.
    * @param <T> The type of instance to create from a text file line.
    */
   public interface LineConverter<T>
   {
      public T convertLine(String aLine);
   }
   
   /**
    * Trivial line converter that just returns the line "as-is".
    */
   public static final LineConverter<String> kIdentityLineConverter = 
		            (line)->{return line;};
   
   /**
    * Interface to decide if a text file line is a comment line.
    */
   public interface CommentLineTest
   {
      public boolean isCommentLine(String aLine);
   }
   
   /**
    * Test that considers lines that start with "#" to be comment lines.
    */
   public static final CommentLineTest kPoundCommentTest = 
		            (line)->((line.charAt(0) == '#')?true:false);
   
   /** Name of text file to convert to objects. */
   private String filename;
   
   /** The line converter. */
   private LineConverter<T> lineConverter;
   
   /** The comment line tester. Can be null for no test. */
   private CommentLineTest commentLineTest;

   /**
    * Constructs a text file reader.
    * @param aFileName
    * @param aLineConverter
    */
   public TextFileReader(String aFileName,LineConverter<T> aLineConverter)
   {
      filename = aFileName;
      lineConverter = aLineConverter;
   }

   /**
    * Constructs a text file reader with a comment line test.
    * @param aFileName
    * @param aLineConverter
    * @param aCommentLineTest
    */
   public TextFileReader(String aFileName,LineConverter<T> aLineConverter,
         CommentLineTest aCommentLineTest)
   {
      filename = aFileName;
      commentLineTest = aCommentLineTest;
      lineConverter = aLineConverter;
   }

   /**
    * Converts the lines of the text file into a vector of T instances.
    * If a comment line test was provided at construction, the comment lines
    * are ignored.
    */
   public final Vector<T> getVector() throws IOException
   {
      Vector<T> vector = new Vector<T>();

      try
      {
         FileReader fileReader = new FileReader(filename);
         LineNumberReader lineReader = new LineNumberReader(fileReader);

         String line;
         while ( (line = lineReader.readLine()) != null)
         {
            //System.out.println(line);
            if (commentLineTest != null
                  && commentLineTest.isCommentLine(line) == false)
            {
               T t = lineConverter.convertLine(line);
               vector.addElement(t);
            }
         }
         lineReader.close();
      }
      catch (FileNotFoundException e) {
         System.out.println("FileNotFoundException");
         System.exit(1);
      }
      catch (IOException e) {
         System.out.println("IOException");
         System.exit(1);
      }
      
      return vector;
   }

   /**
    * Simple test. Edit name of text file for environment.
    * @param args
    */
   public static void main(String[] args)
   {
      String tFilename = "M:/workspace/temp/test.txt";

		TextFileReader<String> tReader =
		      new TextFileReader<String>(
		            tFilename,
		            TextFileReader.kIdentityLineConverter,
		            TextFileReader.kPoundCommentTest);
		try
		{
		   Vector<String> tStrings = tReader.getVector();
		   System.out.println("tStrings.size(): " + tStrings.size());
		   for (String tString: tStrings)
		   {
		      System.out.println("line: " + tString);
		   }
		}
      catch (IOException e)
		{
         System.out.println("IOException reading file");
      }
   }
}
