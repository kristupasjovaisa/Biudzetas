package models;
import java.time.LocalDate;

public class PajamuIrasas extends Irasas {

    private boolean pozymisArIBanka;

    public boolean isPozymisArIBanka() {
        return pozymisArIBanka;
    }

    public void setPozymisArIBanka(boolean pozymisArIBanka) {
        this.pozymisArIBanka = pozymisArIBanka;
    }

    public PajamuIrasas(double suma, LocalDate data, String kategorija, boolean pozymisArIBanka, String papildomaInfo) {
        super(suma, data, kategorija, papildomaInfo);
        this.pozymisArIBanka = pozymisArIBanka;
    }

    @Override
    public String toString() {
        String string = super.toString();
        string += ", pozymisArIBanka = " + (pozymisArIBanka ? "Taip" : "Ne");
        return string;
    }
}
