package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import sample.Model.ModelMatematyczny;

public class Controller {

    public javafx.scene.control.TextField textFieldLiczbaW;
    public javafx.scene.control.TextField textFieldPrawdopodobienstwoW;
    public javafx.scene.control.TextField textFieldIloscLosowan;
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
        Integer N = Integer.parseInt(textFieldIloscLosowan.getText());

        chartRozklad.getData().clear();
        chartDystrybuanta.getData().clear();

        XYChart.Series rozkladFunkcji = modelMat.wykresRozkladuTeoretycznego(n, p);
        rozkladFunkcji.setName("Gęstość teoretyczna");

        XYChart.Series rozkladProbyLosowej = modelMat.wykresProbyLosowej(n, p, N);
        rozkladProbyLosowej.setName("Gęstość z próby losowej");

        XYChart.Series rozkladDystrybuanty = modelMat.wykresDystrybuanty(n, p);
        rozkladDystrybuanty.setName("Dystrybuanta teoretyczna");

        chartRozklad.getData().addAll(rozkladFunkcji, rozkladProbyLosowej);
        chartDystrybuanta.getData().add(rozkladDystrybuanty);
    }

    @FXML
    private void wyczyscDane() {
        chartRozklad.getData().clear();
        chartDystrybuanta.getData().clear();

        textFieldLiczbaW.setText("40");
        textFieldPrawdopodobienstwoW.setText("0.5");
        textFieldIloscLosowan.setText("10000");
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
