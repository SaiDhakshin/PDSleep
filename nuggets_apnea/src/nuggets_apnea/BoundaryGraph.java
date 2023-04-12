/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nuggets_apnea;
import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author seabirds
 */
public class BoundaryGraph extends ApplicationFrame 
{
    String title;
    double data[][];
    String sr;
    BoundaryGraph(String t,double g[][],String t1)
    {
        super(t);
        data=g;
        sr=t1;
        final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }
    private XYDataset createDataset() {
        
        final XYSeries series1 = new XYSeries(sr);       		
	
        for(int i=0;i<data.length;i++)
        {
            series1.add(data[i][0],data[i][1]);
        }
      
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
		
        
                
        return dataset;
        
    }
    
  
    private JFreeChart createChart(final XYDataset dataset) 
    {
        
        
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Boundary Graph",      // chart title
            "Points",                      // x axis label
            "Score",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

       
        chart.setBackgroundPaint(Color.white);

        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);

        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);


        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
                
        return chart;
        
    }
}
