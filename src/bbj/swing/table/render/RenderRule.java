package bbj.swing.table.render;
import javax.swing.JTable;
import javax.swing.JLabel;

/**
 * Interface to get the information for rendering a particular cell in a table
 * based on its row and column conditions such as whether it is selected or has
 * focus.
 */
public interface RenderRule
{
	public TableCellInfo getInfo(JLabel label,JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column);
}