package domain.models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

public class Irasas {

    private int numeris;
    private double suma;
    private LocalDate data;
    private String kategorija;
    private String papildomaInfo;

    public Irasas(int numeris, double suma, LocalDate data, String kategorija, String papildomaInfo) {
        this.numeris = numeris;
        this.suma = suma;
        this.data = data;
        this.kategorija = kategorija;
        this.papildomaInfo = papildomaInfo;
    }

    public Irasas(double suma, LocalDate data, String kategorija, String papildomaInfo) {
        this(new Random().nextInt(1000000) + 1000000, suma, data, kategorija, papildomaInfo);
    }

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

    public String getPapildomaInfo() {
        return papildomaInfo;
    }

    public void setPapildomaInfo(String papildomaInfo) {
        this.papildomaInfo = papildomaInfo;
    }

    @Override
    public String toString() {
        return String.format(
                "numeris = %d, suma = %.2f, data = %s, kategorija = %s, papildomaInfo = %s",
                numeris,
                suma,
                data,
                kategorija,
                papildomaInfo
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Irasas irasas = (Irasas) o;
        return numeris == irasas.numeris;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeris);
    }
}
