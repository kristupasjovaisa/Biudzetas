import java.util.Scanner;

public class EntryPoint {

    public static void main(String[] args) {
        Biudzetas biudzetas = new Biudzetas();

        Scanner sc = new Scanner(System.in);

        // Pajamus ir Islaidu ivedimas
        System.out.println("Pasirinkite norima komanda israso ivedimui: `pajamos`, `islaidos`, `testi`." +
                "\nPasirinkus komanda `pajamos` galesite prideti pajamu israsa." +
                "\nPasirinkus komanda `islaidos` galesite prideti islaidu israsa." +
                "\nPasirinkus komanda `testi` tesite toliau");

        String ivedimoKomanda = "";
        while (true) {
            System.out.println("Iveskite ivedimo komanda");
            ivedimoKomanda = sc.next();
            if (ivedimoKomanda.equals("testi")) {
                break;
            }

            switch (ivedimoKomanda) {
                case "pajamos":
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
                    break;

                case "islaidos":
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

                    biudzetas.pridetiIslaiduIrasa(islaiduSuma, islaiduData, islaiduKategorija, atsiskaitymoBudas, islaiduPapildomaInfo);
                    break;

                default:
                    System.out.println("Nezinoma komanda: " + ivedimoKomanda);
            }
        }

        // Balanso spausdinimas
        System.out.println("Ar norite patikrinti balansa? (Taip/Ne)");

        if (sc.next().toLowerCase().equals("taip")) {
            System.out.println("Jusu balansas yra: " + biudzetas.balansas());
        }

        // Pajamu ir islaidu spausdinimas
        System.out.println("Ar noretumete atspausdinti pajamu ir islaidu israsa? (Taip/Ne)");
        if (sc.next().toLowerCase().equals("taip")) {
            System.out.println("Pajamu israsas: " + biudzetas.getPajamos());
            System.out.println("Islaidu israsas: " + biudzetas.getIslaidos());
        }

        // Pajamus ir Islaidu salinimas
        System.out.println("Pasirinkite norima komanda israso istrinimui: `pajamos`, `islaidos`, `testi`." +
                "\nPasirinkus komanda `pajamos` galesite istrinti pajamu israsa." +
                "\nPasirinkus komanda `islaidos` galesite istrinti islaidu israsa." +
                "\nPasirinkus komanda `testi` tesite toliau");

        String pasalinimoKomanda = "";
        while (true) {
            System.out.println("Iveskite pasalinimo komanda");
            pasalinimoKomanda = sc.next();
            if (pasalinimoKomanda.equals("testi")) {
                break;
            }

            switch (pasalinimoKomanda) {
                case "pajamos":
                    System.out.println("Iveskite ID numeri");
                    int pajamuIsrasoNumeris = sc.nextInt();
                    biudzetas.pasalintiPajamuIsrasa(pajamuIsrasoNumeris);
                    System.out.println("Jusu pajamu israsai po israso " + pajamuIsrasoNumeris + " pasalinimo " + biudzetas.getPajamos());
                    break;

                case "islaidos":
                    System.out.println("Iveskite ID numeri");
                    int islaiduIsrasoNumeris = sc.nextInt();
                    biudzetas.pasalintiIslaiduIsrasa(islaiduIsrasoNumeris);
                    System.out.println("Jusu islaidu israsai po israso " + islaiduIsrasoNumeris + " pasalinimo " + biudzetas.getIslaidos());
                    break;

                default:
                    System.out.println("Nezinoma komanda: " + pasalinimoKomanda);
            }
        }

        sc.close();
    }
}
