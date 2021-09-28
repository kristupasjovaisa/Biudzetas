import models.Irasas;
import models.IslaiduIrasas;
import models.PajamuIrasas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EntryPoint {

    public static void main(String[] args) {
        Biudzetas biudzetas = new Biudzetas();
        Scanner sc = new Scanner(System.in);

        mockIvestiPajamasIrIslaidas(biudzetas);
        mockSpausdintiBalansa(biudzetas);
        mockSpausdintiPajamasIrIslaidas(biudzetas);

//        ivestiPajamasIslaidas(sc, biudzetas);
//        spausdintiBalansa(sc, biudzetas);
//        spausdintiPajamasIrIslaidas(sc, biudzetas);
//        pasalintiPajamasIrIslaidas(sc, biudzetas);
        redaguotiIrasa(sc, biudzetas);

        sc.close();
    }

    private static void ivestiPajamasIslaidas(Scanner sc, Biudzetas biudzetas) {
        System.out.println("Pasirinkite norima komanda israso ivedimui: `pajamos`, `islaidos`, `pereiti`." +
                "\nPasirinkus komanda `pajamos` galesite prideti pajamu israsa." +
                "\nPasirinkus komanda `islaidos` galesite prideti islaidu israsa." +
                "\nPasirinkus komanda `pereiti` pereisite i kita komanda");

        String komanda = "";
        while (true) {
            System.out.println("Iveskite komanda ( 'pajamos', 'islaidos', 'pereiti')");
            komanda = sc.next();
            if (komanda.equals("pereiti")) {
                break;
            }

            switch (komanda) {
                case "pajamos":
                    System.out.println("Iveskite pajamu suma");
                    double pajamuSuma = sc.nextDouble();
                    System.out.println("Iveskite pajamu data formatu YYYY-MM-DD");
                    String pajamuData = sc.next();
                    System.out.println("Iveskite ar pajamos gautos i banka (Taip/Ne)");
                    String pajamuPozymisArIBanka = sc.next();
                    System.out.println("Iveskite papildoma informacija");
                    String pajamuPapildomaInfo = sc.next();

                    biudzetas.pridetiIrasa(
                            new PajamuIrasas(
                                    pajamuSuma,
                                    LocalDate.parse(pajamuData, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                    "pajamos",
                                    pajamuPozymisArIBanka.toLowerCase().equals("taip") ? true : false,
                                    pajamuPapildomaInfo
                            )
                    );
                    break;

                case "islaidos":
                    System.out.println("Iveskite islaidu suma");
                    double islaiduSuma = sc.nextDouble();
                    System.out.println("Iveskite islaidu data formatu YYYY-MM-DD");
                    String islaiduData = sc.next();
                    System.out.println("Iveskite atsiskaitymo buda");
                    String atsiskaitymoBudas = sc.next();
                    System.out.println("Iveskite papildoma informacija");
                    String islaiduPapildomaInfo = sc.next();

                    biudzetas.pridetiIrasa(
                            new IslaiduIrasas(
                                    islaiduSuma,
                                    LocalDate.parse(islaiduData, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                    "islaidos",
                                    atsiskaitymoBudas,
                                    islaiduPapildomaInfo
                            )
                    );
                    break;

                default:
                    System.out.println("Nezinoma komanda: " + komanda);
            }
        }
    }

    private static void spausdintiBalansa(Scanner sc, Biudzetas biudzetas) {
        System.out.println("Ar norite patikrinti balansa? (Taip/Ne)");

        if (sc.next().toLowerCase().equals("taip")) {
            System.out.println("Jusu balansas yra: " + biudzetas.balansas());
        }
    }

    private static void spausdintiPajamasIrIslaidas(Scanner sc, Biudzetas biudzetas) {
        System.out.println("Ar noretumete atspausdinti pajamu ir islaidu israsa? (Taip/Ne)");
        if (sc.next().toLowerCase().equals("taip")) {
            System.out.println("Pajamu israsas: " + biudzetas.gautiPajamuIrasus());
            System.out.println("Islaidu israsas: " + biudzetas.gautiIslaiduIrasus());
        }
    }

    private static void pasalintiPajamasIrIslaidas(Scanner sc, Biudzetas biudzetas) {
        System.out.println("Pasirinkite norima komanda israso istrinimui: `pajamos`, `islaidos`, `pereiti`." +
                "\nPasirinkus komanda `pajamos` galesite istrinti pajamu israsa." +
                "\nPasirinkus komanda `islaidos` galesite istrinti islaidu israsa." +
                "\nPasirinkus komanda `pereiti` pereisite i kitas komandas");

        String komanda = "";
        while (true) {
            System.out.println("Iveskite komanda ('pajamos', 'islaidos', 'pereiti')");
            komanda = sc.next();
            if (komanda.equals("pereiti")) {
                break;
            }

            switch (komanda) {
                case "pajamos":
                    System.out.println("Iveskite ID numeri");
                    int pajamuIsrasoNumeris = sc.nextInt();
                    biudzetas.pasilintiIrasa(pajamuIsrasoNumeris);
                    System.out.println("Jusu pajamu israsai po israso " + pajamuIsrasoNumeris + " pasalinimo " + biudzetas.gautiPajamuIrasus());
                    break;

                case "islaidos":
                    System.out.println("Iveskite ID numeri");
                    int islaiduIsrasoNumeris = sc.nextInt();
                    biudzetas.pasilintiIrasa(islaiduIsrasoNumeris);
                    System.out.println("Jusu islaidu israsai po israso " + islaiduIsrasoNumeris + " pasalinimo " + biudzetas.gautiIslaiduIrasus());
                    break;

                default:
                    System.out.println("Nezinoma komanda: " + komanda);
            }
        }
    }

    private static void redaguotiIrasa(Scanner sc, Biudzetas biudzetas) {
        System.out.println("Pasirinkite norima komanda israso redagavimui: `redaguoti`, `pereiti`." +
                "\nPasirinkus komanda `redaguoti` galesite redaguoti israsa." +
                "\nPasirinkus komanda `pereiti` pereisite i kitas komandas");

        String komanda = "";
        while (true) {
            System.out.println("Iveskite komanda ('redaguoti', 'pereiti')");
            komanda = sc.next();
            if (komanda.equals("pereiti")) {
                break;
            }

            switch (komanda) {
                case "redaguoti":
                    for (int i = 0; i < biudzetas.getIrasai().size(); i++) {
                        System.out.println("Irasas" + (i + 1) + ": " + biudzetas.getIrasai().get(i));
                    }

                    System.out.println("Iveskite ID numeri");
                    int israsoNumeris = sc.nextInt();
                    Irasas irasas = biudzetas.gautiIrasa(israsoNumeris);
                    if (irasas != null) {
                        System.out.println("suma: " + irasas.getSuma() + ", redaguoti? (Taip/Ne)");
                        if (sc.next().toLowerCase().equals("taip")) {
                            System.out.println("Iveskite nauja suma");
                            irasas.setSuma(sc.nextDouble());
                        }
                        System.out.println("data: " + irasas.getData() + ", redaguoti? (Taip/Ne)");
                        if (sc.next().toLowerCase().equals("taip")) {
                            System.out.println("Iveskite nauja data formatu YYYY-MM-DD");
                            irasas.setData( LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        }
                        System.out.println("papildoma informacija: " + irasas.getPapildomaInfo() + ", redaguoti? (Taip/Ne)");
                        if (sc.next().toLowerCase().equals("taip")) {
                            System.out.println("Iveskite nauja informacija");
                            irasas.setPapildomaInfo(sc.next());
                        }
                        System.out.println("Jusu israsas po redagavimo: " + irasas);
                    } else {
                        System.out.println("Blogai ivestas numeris");
                    }

                    break;

                default:
                    System.out.println("Nezinoma komanda: " + komanda);
            }
        }
    }

    // Mocks

    private static void mockIvestiPajamasIrIslaidas(Biudzetas biudzetas) {
        biudzetas.pridetiIrasa(new PajamuIrasas(100, LocalDate.parse("2000-04-02", DateTimeFormatter.ofPattern("yyyy-MM-dd")),"pajamos",true,"pajamu papildoma informacija"));
        biudzetas.pridetiIrasa(new IslaiduIrasas(40, LocalDate.parse("2001-06-06",DateTimeFormatter.ofPattern("yyyy-MM-dd")),"islaidos","bankinis","islaidu papildoma informacija"));
    }

    private static void mockSpausdintiBalansa(Biudzetas biudzetas) {
        System.out.println("Jusu balansas yra: " + biudzetas.balansas());
    }

    private static void mockSpausdintiPajamasIrIslaidas(Biudzetas biudzetas) {
        System.out.println("Pajamu israsas: " + biudzetas.gautiPajamuIrasus());
        System.out.println("Islaidu israsas: " + biudzetas.gautiIslaiduIrasus());
    }
}
