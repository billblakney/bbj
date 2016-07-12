package bbj.swing.table.render;
import java.awt.Color;
import java.awt.Font;

/**
 * Class that provides information needed to render a table cell, including
 * the text, font, and foreground and background colors.
 */
public class TableCellInfo
{
   public String _text;
   public Font   _font;
   public Color  _bgColor;
   public Color  _fgColor;
   
   public TableCellInfo()
   {
      _text = null;
      _font = null;
      _bgColor = null;
      _fgColor = null;
   }
}
