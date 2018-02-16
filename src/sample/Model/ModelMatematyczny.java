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
        Double d = (double) n;
        Double y;

        for (double k = 0; k <= d ; k = k + 1.0) {
            Double nPok = fPok(n, (int) k);
            y = nPok * Math.pow(p, (double)k) * Math.pow((1 - p), (double)(n - k));

            rozkladFunkcji.getData().add(new XYChart.Data<String, Double>(String.valueOf(k), y));
        }

        return rozkladFunkcji;
    }

    public XYChart.Series wykresProbyLosowej(Integer n, Double p) {
        XYChart.Series rozkladProbyLosowej = new XYChart.Series();
        int N = 10000; // ilość losowań
//        int n1 = 40; // liczba przedziałów
        int n1 = n; // teraz działa
        double h = 1; // długość przedziału
        double[] a = new double[n1]; // krańce przedziałów
        double[] y = new double[n1]; // suma trafień do danego przedziału
        double x; // liczba pseudolosowa z przedziału

        for (int i = 0; i < n1; i++) {
            a[i] = i * h;
            y[i] = 0;
        }

        for (int i = 0; i < N; i++) {
            x = generujLosowaLiczbe(n, p);

            if (x < a[0]) {
                y[0]++;
            }
            for (int j = 0; j < n1 - 1; j++) {
                if (x > a[j] && x <= a[j + 1]) {
                    y[j]++;
                }
            }
            if (x > a[n - 1]) {
                y[n - 1]++;
            }
        }

        double oh = 1.0 / h;
        double yy;
        for (int i = 0; i < n1; i++) {
            yy = oh * y[i] / N;
            rozkladProbyLosowej.getData().add(new XYChart.Data<>(String.valueOf(a[i]), yy));
        }

        return rozkladProbyLosowej;
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
