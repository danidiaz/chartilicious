package org.danisoft.chartilicious;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

// https://zetcode.com/java/jfreechart/
// https://stackoverflow.com/questions/40105094/jfreechart-horizontal-stacked-bar-chart-with-date-axis
// https://www.jfree.org/jfreechart/api/javadoc/org/jfree/data/category/CategoryDataset.html
// https://www.jfree.org/jfreechart/api/javadoc/org/jfree/data/jdbc/JDBCCategoryDataset.html
// https://twitter.com/GeoffreyDeSmet/status/1373614799125426183
// https://twitter.com/dawntraoz/status/1380521399723438088
// https://www.codewall.co.uk/best-javascript-chart-libraries/
// https://www.jfree.org/jfreechart/samples.html
// https://www.jfree.org/forum/viewtopic.php?t=26013
// https://stackoverflow.com/questions/28494575/how-to-make-jfree-chart-to-take-non-empty-series-data-set-to-plot-range-axis
// check "TimeSeriesCollection"
// check "createTimeSeriesChart"
// https://www.hebergementwebs.com/tutoriel-jfreechart/jfreechart-guide-rapide
// no excel-like tables-below-chart, sadly https://www.jfree.org/forum/viewtopic.php?t=14389
// How to combine line chart and bar chart in JFreeChart https://www.boraji.com/how-to-combine-line-chart-and-bar-chart-in-jfreechart
@RestController
public class ChartController2 {

    @GetMapping(
            value = "/chart2",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public byte [] chart() throws IOException {

        // Create Category plot
        CategoryPlot plot = new CategoryPlot();

        // Add the first dataset and render as bar
        CategoryItemRenderer lineRenderer = new LineAndShapeRenderer();
        plot.setDataset(0, createDataset());
        plot.setRenderer(0, lineRenderer);

        // Add the second dataset and render as lines
        CategoryItemRenderer baRenderer = new BarRenderer();
        plot.setDataset(1, createDataset());
        plot.setRenderer(1, baRenderer);

        // Set Axis
        plot.setDomainAxis(new CategoryAxis("Time"));
        plot.setRangeAxis(new NumberAxis("Value"));

        JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("Combined Bar And Line Chart | BORAJI.COM");

        try (final ByteArrayOutputStream o = new ByteArrayOutputStream()) {
            ChartUtils.writeChartAsPNG(o, chart, 450, 400);
            return o.toByteArray();
        }
    }

    private DefaultCategoryDataset createDataset() {

        // First Series
        String series1 = "Vistor";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(200, series1, "2016-12-19");
        dataset.addValue(150, series1, "2016-12-20");
        dataset.addValue(100, series1, "2016-12-21");
        dataset.addValue(210, series1, "2016-12-22");
        dataset.addValue(240, series1, "2016-12-23");
        dataset.addValue(195, series1, "2016-12-24");
        dataset.addValue(245, series1, "2016-12-25");

        // Second Series
        String series2 = "Unique Visitor";
        dataset.addValue(150, series2, "2016-12-19");
        dataset.addValue(130, series2, "2016-12-20");
        dataset.addValue(95, series2, "2016-12-21");
        dataset.addValue(195, series2, "2016-12-22");
        dataset.addValue(200, series2, "2016-12-23");
        dataset.addValue(180, series2, "2016-12-24");
        dataset.addValue(230, series2, "2016-12-25");

        return dataset;
    }
}
