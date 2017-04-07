package bbj.swing.table.render;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 * Class to implement a render rule for formatting integers in a table.
 * <p>
 * This render rule takes a format string and a column specification as inputs.
 * The format string specifies the format to be used for the integers, and the
 * column specification specifies whether the format is to be applied to a
 * specific column or to all columns.
 */
public class RenderIntegerRule implements RenderRule
{
   /** Comma separated format. */
	static public final String COMMA_FORMAT = "###,###,###,##0";
   
	/** Format to be used. */
	protected DecimalFormat _format;
	
	/** Column to be formatted. */
	protected Integer _column = null;

	/**
	 * Constructor to format all columns.
	 * 
	 * @param aDecimalFormat
	 */
   public RenderIntegerRule(String aDecimalFormat)
   {
      _format = new DecimalFormat(aDecimalFormat);
   }

   /**
    * Constructor to format a specified column.
    * 
    * @param aDecimalFormat
    * @param aColumn
    */
   public RenderIntegerRule(String aDecimalFormat,Integer aColumn)
   {
      _format = new DecimalFormat(aDecimalFormat);
	   _column = aColumn;
   }

   /**
    * Get the text string to be used for the integer, based on the cell
    * location and state.
    */
   @Override
	public TableCellInfo getInfo(JLabel label,JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column)
	{
	   TableCellInfo tInfo = null;

	   /*
	    * If _column is null, then apply format to all Integer columns.
	    * If _column is not null, then apply format to the specified column.
	    */
	   if ((_column == null && value.getClass() == Integer.class)
	    || (_column != null && column == _column.intValue()))
	   {
	      tInfo = new TableCellInfo();
	      tInfo._text = _format.format(value);
	   }
	   
	   return tInfo;
	}
}
