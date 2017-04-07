package bbj.swing.table.render;
import javax.swing.JTable;
import javax.swing.JLabel;

public interface TableCellColorIndexChooser
{
	public int getIndex(JLabel label,JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column);
}