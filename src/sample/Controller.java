package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import sample.Model.ModelMatematyczny;

public class Controller {

    public javafx.scene.control.TextField textFieldLiczbaW;
    public javafx.scene.control.TextField textFieldPrawdopodobienstwoW;
    public javafx.scene.control.TextField textFieldWygenerowanaLiczba;
    public javafx.scene.control.Button buttonStworzWykresy;
    public javafx.scene.control.Button buttonWyczyscWykres;
    public javafx.scene.control.Button buttonGenerujLiczbe;
    public javafx.scene.chart.BarChart chartRozklad;
    public javafx.scene.chart.LineChart chartDystrybuanta;

    private ModelMatematyczny modelMat = new ModelMatematyczny();

    @FXML
    private void stworzWykresy() {
        Integer n = Integer.parseInt(textFieldLiczbaW.getText());
        Double p = Double.parseDouble(textFieldPrawdopodobienstwoW.getText());

        chartRozklad.getData().clear();
        chartDystrybuanta.getData().clear();

        XYChart.Series rozkladFunkcji = modelMat.wykresRozkladuFunkcji(n, p);
//        chartRozklad.getData().add(rozkladFunkcji);

        XYChart.Series rozkladProbyLosowej = modelMat.wykresProbyLosowej(n, p);
//        chartRozklad.getData().add(rozkladProbyLosowej);
        chartRozklad.getData().addAll(rozkladFunkcji, rozkladProbyLosowej);

        XYChart.Series rozkladDystrybuanty = modelMat.wykresDystrybuanty(n, p);
        chartDystrybuanta.getData().add(rozkladDystrybuanty);

    }

    @FXML
    private void wyczyscDane() {
        chartRozklad.getData().clear();
        chartDystrybuanta.getData().clear();

        textFieldLiczbaW.setText("40");
        textFieldPrawdopodobienstwoW.setText("0.5");
        textFieldWygenerowanaLiczba.clear();
    }

    @FXML
    private void generujLiczbe() {
        Integer n = Integer.parseInt(textFieldLiczbaW.getText());
        Double p = Double.parseDouble(textFieldPrawdopodobienstwoW.getText());

        Integer x = modelMat.generujLosowaLiczbe(n, p);
        textFieldWygenerowanaLiczba.setText(x.toString());
    }

}
