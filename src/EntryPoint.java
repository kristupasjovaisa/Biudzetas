import java.util.Scanner;

public class EntryPoint {

    public static void main(String[] args) {
        Biudzetas biudzetas = new Biudzetas();

        Scanner sc = new Scanner(System.in);
        System.out.println("Iveskite pajamu suma");
        double pajamuSuma = sc.nextDouble();
        System.out.println("Iveskite pajamu data formatu YYYY-MM-DD");
        String pajamuData = sc.next();
        System.out.println("Iveskite pajamu kategorija");
        String pajamuKategorija = sc.next();
        System.out.println("Iveskite ar pajamos gautos i banka (Taip/Ne)");
        String pajamuPozymisArIBanka = sc.next();
        System.out.println("Iveskite papildoma informacija");
        String pajamuPapildomaInfo = sc.next();

        biudzetas.pridetiPajamuIrasa(pajamuSuma, pajamuData, pajamuKategorija, pajamuPozymisArIBanka, pajamuPapildomaInfo);
        System.out.println(biudzetas.getPajamos());

        System.out.println("Iveskite islaidu suma");
        double islaiduSuma = sc.nextDouble();
        System.out.println("Iveskite islaidu data formatu YYYY-MM-DD");
        String islaiduData = sc.next();
        System.out.println("Iveskite islaidu kategorija");
        String islaiduKategorija = sc.next();
        System.out.println("Iveskite atsiskaitymo buda");
        String atsiskaitymoBudas = sc.next();
        System.out.println("Iveskite papildoma informacija");
        String islaiduPapildomaInfo = sc.next();
        sc.close();

        biudzetas.pridetiIslaiduIrasa(islaiduSuma, islaiduData, islaiduKategorija, atsiskaitymoBudas, islaiduPapildomaInfo);
        System.out.println(biudzetas.getIslaidos());
    }
}
