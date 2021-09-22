package models;

import java.time.LocalDate;
import java.util.Random;

public class IslaiduIrasas {

    private int numeris;
    private double suma;
    private LocalDate data;
    private String kategorija;
    private String atsiskaitymoBudas;
    private String papildomaInfo;

    public int getNumeris() {
        return numeris;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getAtsiskaitymoBudas() {
        return atsiskaitymoBudas;
    }

    public void setAtsiskaitymoBudas(String atsiskaitymoBudas) {
        this.atsiskaitymoBudas = atsiskaitymoBudas;
    }

    public String getPapildomaInfo() {
        return papildomaInfo;
    }

    public void setPapildomaInfo(String papildomaInfo) {
        this.papildomaInfo = papildomaInfo;
    }

    public IslaiduIrasas(double suma, LocalDate data, String kategorija, String atsiskaitymoBudas, String papildomaInfo) {
        numeris = new Random(1000000).nextInt();
        this.suma = suma;
        this.data = data;
        this.kategorija = kategorija;
        this.atsiskaitymoBudas = atsiskaitymoBudas;
        this.papildomaInfo = papildomaInfo;
    }

    @Override
    public String toString() {
        return "IslaiduIrasas{" +
                "numeris = " + numeris +
                ", suma = " + suma +
                ", dataSuLaiku = " + data +
                ", kategorija = " + kategorija +
                ", atsiskaitymoBudas = " + atsiskaitymoBudas +
                ", papildomaInfo = " + papildomaInfo +
                '}';
    }
}
