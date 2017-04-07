package bbj.swing.table.render;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 * A renderer for cells in a table that are labels.
 * <p>
 * The renderer is constructed with a set of "render rules", each of which may
 * provide table cell info for drawing a table cell. The rules are applied in
 * the order that they appear in the list of render rules.
 */
@SuppressWarnings("serial")
public class CustomTableCellRenderer extends JLabel
                                     implements TableCellRenderer {

//TODO these can be removed; they're here as a note, but may not be useful
//   Color _defaultForegroundColor = null;
//   Color _defaultBackgroundColor = null;
//		_defaultBackgroundColor = UIManager.getColor ( "Panel.background" );
//		_defaultForegroundColor = UIManager.getColor ( "Panel.foreground" );
   
   
   Vector<RenderRule> _renderRules = new Vector<RenderRule>();
	
   /**
    * Construct a renderer with one render rule.
    * 
    * @param aRenderRule
    */
	public CustomTableCellRenderer(RenderRule aRenderRule) {
		super();
		init();
		_renderRules.add(aRenderRule);
	}
	
	/**
	 * Construct a renderer with a vector of render rules.
	 * The rules will be applied in the order that they appear in the vector.
	 * 
	 * @param aRenderRules
	 */
	public CustomTableCellRenderer(Vector<RenderRule> aRenderRules) {
		super();
		init();
		_renderRules = aRenderRules;
	}
	
	/**
	 * Apply the render rules to this table cell renderer and return a reference
	 * to it.
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column)
	{
	   String tText = null;
	   Font tFont = null;
	   Color tBackground = null;
	   Color tForeground = null;

	   /*
	    * Loop through all render rules, and let them apply their changes to
	    * this table cell renderer.
	    */
	   for (RenderRule tRule: _renderRules)
	   {
	      TableCellInfo tInfo = (TableCellInfo)tRule.getInfo(
	            this, table, value, isSelected, hasFocus, row, column);

	      if (tInfo != null)
	      {
	         if (tInfo._text != null)
	         {
	            tText = tInfo._text;
	         }
	         if (tInfo._font != null)
	         {
	            tFont = tInfo._font;
	         }
	         if (tInfo._bgColor != null)
	         {
	            tBackground = tInfo._bgColor;
	         }
	         if (tInfo._fgColor != null)
	         {
	            tForeground = tInfo._fgColor;
	         }
	      }
	   }
	   
	   if (tText != null)
	   {
	      setText(tText);
	   }
	   else
	   {
	      setText(value.toString());
	   }

	   if (tFont != null)
	   {
	      setFont(tFont);
	   }
	   else
	   {
	      setFont(null);
	   }
	   
	   if (tBackground != null)
	   {
	      setBackground(tBackground);
	   }
	   else
	   {
	      if (isSelected)
	      {
	         setBackground(table.getSelectionBackground());
	      }
	      else
	      {
	         setBackground(table.getBackground());
	      }
	   }
	   
	   if (tForeground != null)
	   {
	      setForeground(tForeground);
	   }
	   else
	   {
	      if (isSelected)
	      {
	         setForeground(table.getSelectionForeground());
	      }
	      else
	      {
	         setForeground(table.getForeground());
	      }
	   }

		return this;
	}

	/**
	 * Initialize this table cell renderer.
	 */
	private void init(){
		setOpaque(true);
		setFont(getFont().deriveFont(Font.PLAIN));
		setHorizontalAlignment(SwingConstants.RIGHT);
	}
}
