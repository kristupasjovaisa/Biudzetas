package models;

import java.time.LocalDate;
import java.util.Random;

public class PajamuIrasas {

    private int numeris;
    private double suma;
    private LocalDate data;
    private String kategorija;
    private boolean pozymisArIBanka;
    private String papildomaInfo;

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

    public boolean isPozymisArIBanka() {
        return pozymisArIBanka;
    }

    public void setPozymisArIBanka(boolean pozymisArIBanka) {
        this.pozymisArIBanka = pozymisArIBanka;
    }

    public String getPapildomaInfo() {
        return papildomaInfo;
    }

    public void setPapildomaInfo(String papildomaInfo) {
        this.papildomaInfo = papildomaInfo;
    }

    public int getNumeris() {
        return numeris;
    }


    public PajamuIrasas(double suma, LocalDate data, String kategorija, boolean pozymisArIBanka, String papildomaInfo) {
        numeris = new Random().nextInt(2000000 - 1000000) + 1000000;
        this.suma = suma;
        this.data = data;
        this.kategorija = kategorija;
        this.pozymisArIBanka = pozymisArIBanka;
        this.papildomaInfo = papildomaInfo;
    }

    @Override
    public String toString() {
        return "PajamuIrasas{" +
                "numeris = " + numeris +
                ", suma = " + suma +
                ", data = " + data +
                ", kategorija = " + kategorija +
                ", pozymisArIBanka = " + (pozymisArIBanka ? "Taip" : "Ne") +
                ", papildomaInfo = " + papildomaInfo +
                '}';
    }
}
