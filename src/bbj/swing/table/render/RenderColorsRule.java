package bbj.swing.table.render;
import java.awt.Font;
import java.util.List;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 * Class to implement a render rule for coloring rows in a table.
 * <p>
 * This render rule takes a list of row color specifications, which is called
 * a "color scheme", and a test that is applied to a cell to determine which
 * of the row color specifications is to be used (by returning an index into
 * the color scheme).
 */
public class RenderColorsRule implements RenderRule
{
   /**
    * The list of color schemes that can be applied to a row.
    * */
	List<TableCellColorSet> _scheme;
	
	/**
	 * The color index getter, which specifies the index of the color scheme
	 * to use for a row.
	 */
	TableCellColorIndexChooser _colorIndexGetter;
	
	/**
	 * The columns to which the color scheme is to be applied.
	 * If null, the color scheme is applied to all columns.
	 */
	Set<Integer> _columns;

	/**
	 * Construct a colored rows render rule to be applied to all columns.
	 * 
	 * @param aColorScheme A color scheme, which is a list of row color
	 *    specifications.
	 * @param aTest A test to determine the color scheme index to use.
	 */
   public RenderColorsRule(List<TableCellColorSet> aColorScheme,
         TableCellColorIndexChooser aTest)
   {
      _scheme = aColorScheme;
      _colorIndexGetter = aTest;
   }

   /**
	 * Construct a colored rows render rule to be applied to specified columns.
    * 
	 * @param aColorScheme A color scheme, which is a list of row color
	 *    specifications.
	 * @param aTest A test to determine the color scheme index to use.
    * @param aColumns The columns to apply the color scheme to. The color
    *    scheme is appliked to all columns if the value is null.
    */
   public RenderColorsRule(List<TableCellColorSet> aColorScheme,
         TableCellColorIndexChooser aTest,Set<Integer> aColumns)
   {
      _scheme = aColorScheme;
      _colorIndexGetter = aTest;
      _columns = aColumns;
   }

   /**
    * Get the colors and font to be applied to specific cell based on the
    * colored rows render rule.
    */
   @Override
	public TableCellInfo getInfo(JLabel label,JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column)
	{
      /*
       * If there is a column specification and the column of this cell doesn't
       * meet that specification, just return null.
       */
      if (_columns != null && !_columns.contains(column))
      {
         return null;
      }

      /*
       * 
       */
      int tIndex = _colorIndexGetter.getIndex(label, table, value, isSelected, hasFocus, row, column);
      
      if (tIndex < 0 || tIndex >= _scheme.size())
      {
         System.out.print("ERROR: invalid color set");
         return null;
      }

      TableCellInfo tInfo = new TableCellInfo();

      TableCellColorSet tColorSet = _scheme.get(tIndex);

		// cell in normal row (isSelected == false)
		if (isSelected == false) {
			tInfo._font = label.getFont().deriveFont(Font.PLAIN);
			tInfo._bgColor = tColorSet._bgNormal;
			tInfo._fgColor = tColorSet._fgNormal;
		// cell in selected row without focus
		} else if ( isSelected == true && hasFocus == false ){
			tInfo._font = label.getFont().deriveFont(Font.ITALIC);
			tInfo._bgColor = tColorSet._bgSelected;
			tInfo._fgColor = tColorSet._fgSelected;
		// cell in selected row with focus
		} else {
			tInfo._font = label.getFont().deriveFont(Font.PLAIN);
			tInfo._bgColor = tColorSet._bgFocus;
			tInfo._fgColor = tColorSet._fgFocus;
		}
	   
	   return tInfo;
	}
}
