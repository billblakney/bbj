package bbj.swing.table.render;

import java.awt.Font;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
 
public class Tester1 extends JFrame
{
    public Tester1()
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
         * Render integer in column 4 with commas.
         */
        RenderIntegerRule tRenderInteger = new RenderIntegerRule(RenderIntegerRule.COMMA_FORMAT,4);
        tRenderInfo.add(tRenderInteger);

        /*
         * Render alternating rows with different color.
         */
        Vector<TableCellColorSet> tColorSets = new Vector<TableCellColorSet>();
        TableCellColorSet tSet1 = TableCellColorSet.kDefaultColorSet1;
        TableCellColorSet tSet2 = TableCellColorSet.kDefaultColorSet2;
        tColorSets.add(tSet1);
        tColorSets.add(tSet2);

        TableCellColorIndexChooser tRowChooser = (JLabel label,JTable table, Object value,
              boolean isSelected, boolean hasFocus, int row, int column)
              -> { return row % 2;};

        RenderColorsRule tRenderRows = new RenderColorsRule(tColorSets,tRowChooser);
        tRenderInfo.add(tRenderRows);
        /*
         * Add render info to the custom renderer.
         */
        CustomTableCellRenderer r = new CustomTableCellRenderer(tRenderInfo);

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
                new Tester1();
            }
        });
    }   
}
