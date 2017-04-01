package bbj.file;

import java.io.IOException;
import java.util.Vector;

public class TestTextFileReader
{
   /**
    * Simple test. Edit name of text file for environment.
    * @param args
    */
   public static void main(String[] args)
   {
      for (String tFilename: args)
      {
         System.out.println("arg: " + tFilename);

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
}
