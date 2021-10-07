package domain.models;

import java.time.LocalDate;

public class IslaiduIrasas extends Irasas {

    public static final String ISLAIDOS = "islaidos";

    private String atsiskaitymoBudas;

    public IslaiduIrasas(int numeris, double suma, LocalDate data, String atsiskaitymoBudas, String papildomaInfo) {
        super(numeris, suma, data, ISLAIDOS, papildomaInfo);
        this.atsiskaitymoBudas = atsiskaitymoBudas;
    }

    public IslaiduIrasas(double suma, LocalDate data, String atsiskaitymoBudas, String papildomaInfo) {
        super(suma, data, ISLAIDOS, papildomaInfo);
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
        return String.format(string += ", atsiskaitymo budas = %s", atsiskaitymoBudas);
    }
}
