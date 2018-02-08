package sample.Model;

import javafx.scene.chart.XYChart;

public class ModelMatematyczny {

    private double silniaN(Integer n) {
        Double q = 1.0;

        for (int i = 1; i <= n; i++) {
            q = q * (double)i;
        }

        return q;
    }

    private double fPok(Integer n, Integer k) {
        Double nPok = silniaN(n) / (silniaN((n - k)) * silniaN(k));

        return nPok;
    }

    public XYChart.Series wykresRozkladuFunkcji(Integer n, Double p) {
        XYChart.Series rozkladFunkcji = new XYChart.Series();
        Double y;

        for (int k = 0; k <= n ; k++) {
            Double nPok = fPok(n, k);
            y = nPok * Math.pow(p, (double)k) * Math.pow((1 - p), (double)(n - k));

            rozkladFunkcji.getData().add(new XYChart.Data<String, Double>(Integer.toString(k), y));
        }

        return rozkladFunkcji;
    }

    public XYChart.Series wykresDystrybuanty(Integer n, Double p) {
        XYChart.Series rozkladDystrybuanty = new XYChart.Series();
        Double yF = 0.0;
        Double yD = 0.0;

        for (int k = 0; k <= n ; k++) {
            Double nPok = fPok(n, k);
            yF = nPok * Math.pow(p, (double)k) * Math.pow((1 - p), (double)(n - k));
            yD = yD + yF;

            rozkladDystrybuanty.getData().add(new XYChart.Data<String, Double>(Integer.toString(k), yD));
        }

        return rozkladDystrybuanty;
    }

    public Integer generujLosowaLiczbe(Integer n, Double p) {
        Integer x = 0;

        for (int i = 0; i < n; i++) {
            if (Math.random() < p) {
                x++;
            }
        }

        return x;
    }

}
