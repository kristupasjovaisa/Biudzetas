package domain.models;

import java.time.LocalDate;

public class PajamuIrasas extends Irasas {

    public static final String PAJAMOS = "pajamos";

    private boolean pozymisArIBanka;

    public boolean isPozymisArIBanka() {
        return pozymisArIBanka;
    }

    public void setPozymisArIBanka(boolean pozymisArIBanka) {
        this.pozymisArIBanka = pozymisArIBanka;
    }

    public PajamuIrasas(int numeris, double suma, LocalDate data, boolean pozymisArIBanka, String papildomaInfo) {
        super(numeris, suma, data, PAJAMOS, papildomaInfo);
        this.pozymisArIBanka = pozymisArIBanka;
    }

    public PajamuIrasas(double suma, LocalDate data, boolean pozymisArIBanka, String papildomaInfo) {
        super(suma, data, PAJAMOS, papildomaInfo);
        this.pozymisArIBanka = pozymisArIBanka;
    }

    @Override
    public String toString() {
        String string = super.toString();
        return String.format(string += ", pozymisArIBanka = %s", (pozymisArIBanka ? "Taip" : "Ne"));
    }
}
