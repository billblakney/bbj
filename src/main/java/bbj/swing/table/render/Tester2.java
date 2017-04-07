package bbj.swing.table.render;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
 
public class Tester2 extends JFrame
{
    public Tester2()
    {
        //headers for the table
        String[] columns = new String[] {
            "Id", "Name", "Hourly Rate", "Part Time", "Int"
        };
         
        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
            {1, "John", 40.0, false, 1000000 },
            {2, "Rambo", 170.0, false, 599000 },
            {3, "Zorro", 60.0, true, 35 },
            {4, "Chance", 90.0, true, 4500 },
            {5, "Stanley", 110.0, false, 4045998 },
        };
        
        //create table with data
        JTable tTable = new JTable(data, columns);

        Vector<RenderRule> tRenderInfo = new Vector<RenderRule>();

        /*
         * Render cells with different color.
         * Use the sum of row and column as the index into the color scheme.
         */
        int tRows = data.length;
        int tCols = data[0].length;
        int tMaxIndex = tRows + tCols;
        
        /*
         * Produce the color scheme by just artificially scaling the colors
         * per the index. (The colors produced this way might not be too
         * pleasing, but will be ok for this example.)
         */
        Vector<TableCellColorSet> tColorSets = new Vector<TableCellColorSet>();
        for (int i = 0; i < (tRows-1)+(tCols-1)+1; i++)
        {
           TableCellColorSet tSet = new TableCellColorSet();
           float tFraction = (float)i/(float)tMaxIndex;
           int tStrength = (int)(tFraction*(float)255);
           tSet._bgNormal = new Color(tStrength,tStrength,0);
           tSet._fgNormal = new Color(255-tStrength,255-tStrength,255);
           tColorSets.add(tSet);
        }

        /*
         * As an index chooser, just add the cell column and row.
         */
        TableCellColorIndexChooser tChooser = (JLabel label,JTable table, Object value,
              boolean isSelected, boolean hasFocus, int row, int column)
              -> { return (row+column);};

        RenderColorsRule tRenderCells = new RenderColorsRule(tColorSets,tChooser);

        /*
         * Add render info to the custom renderer.
         */
        CustomTableCellRenderer r = new CustomTableCellRenderer(tRenderCells);

        /*
         * Set the custom renderer to the table.
         */
        tTable.setDefaultRenderer(Object.class,r);
         
        //add the table to the frame
        this.add(new JScrollPane(tTable));
         
        this.setTitle("Table Example");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.pack();
        this.setVisible(true);
    }
     
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Tester2();
            }
        });
    }   
}
