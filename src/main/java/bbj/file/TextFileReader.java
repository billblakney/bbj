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
 * The file to be read can contain comment lines and/or terminating lines.
 * A comment line, which matches the comment line test, is ignored.
 * When a terminating line, which matches the terminating line test, is
 * encountered, that line and all of the ones that follow it are ignored.
 * By default, comment lines are indicated by a "#" character in
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
    * Interface to decide if a text file line meets some criteria.
    */
   public interface LineTest
   {
      public boolean matches(String aLine);
   }
   
   /**
    * Test that considers lines that start with "#" to be comment lines.
    */
   public static final LineTest kPoundCommentTest = 
		            (line)->((line.charAt(0) == '#')?true:false);
   
   /** Name of text file to convert to objects. */
   private String filename;
   
   /** The line converter. */
   private LineConverter<T> lineConverter;
   
   /** The comment line tester. Can be null for no test. */
   private LineTest commentLineTest;
   
   /** The terminator line tester. Can be null for no test. */
   private LineTest terminateLineTest;

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
         LineTest aCommentLineTest)
   {
      filename = aFileName;
      commentLineTest = aCommentLineTest;
      lineConverter = aLineConverter;
   }

   /**
    * Set the comment line test.
    * @param aTest Line tester to identify comment lines.
    */
   public void setCommentLineTest(LineTest aTest)
   {
      commentLineTest = aTest;
   }

   /**
    * Set the terminating line test.
    * When a terminating line is encountered, processing of the file is stopped.
    * @param aTest Line tester to identify a terminating line.
    */
   public void setTerminateLineTest(LineTest aTest)
   {
      terminateLineTest = aTest;
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
//            System.out.println(line);
            if (terminateLineTest != null
                  && terminateLineTest.matches(line))
            {
//               System.out.println("is terminating line");
               break;
            }
            else if (commentLineTest != null
                  && commentLineTest.matches(line))
            {
//               System.out.println("is comment line");
               continue;
            }
            else
            {
//               System.out.println("is convertible line");
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
}
