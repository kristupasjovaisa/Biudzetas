package models;

import java.time.LocalDate;
import java.util.Random;

public class IslaiduIrasas extends Irasas {

    private String atsiskaitymoBudas;

    public IslaiduIrasas(double suma, LocalDate data, String kategorija, String atsiskaitymoBudas, String papildomaInfo) {
        super(suma,data,kategorija,papildomaInfo);
        this.atsiskaitymoBudas = atsiskaitymoBudas;
    }

    public String getAtsiskaitymoBudas() {
        return atsiskaitymoBudas;
    }

    public void setAtsiskaitymoBudas(String atsiskaitymoBudas) {
        this.atsiskaitymoBudas = atsiskaitymoBudas;
    }

    @Override
    public String toString() {
       String string = super.toString();
       string +=  ", atsiskaitymo budas = " + atsiskaitymoBudas;
       return string;
    }
}
