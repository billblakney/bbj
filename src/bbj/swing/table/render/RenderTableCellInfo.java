package bbj.swing.table.render;
import javax.swing.JTable;
import javax.swing.JLabel;

public interface RenderTableCellInfo
{
	public TableCellInfo getInfo(JLabel label,JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column);
}