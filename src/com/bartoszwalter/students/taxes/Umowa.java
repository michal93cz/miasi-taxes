package com.bartoszwalter.students.taxes;

import java.math.BigDecimal;


public abstract class Umowa {
    protected BigDecimal podstawa;
    protected BigDecimal skladkaEmerytalna; // 9,76% podstawyy
    protected BigDecimal skladkaRentowa; // 1,5% podstawy
    protected BigDecimal ubezpieczenieChorobowe; // 2,45% podstawy
    protected BigDecimal podstawaPoSkladkach;

    // składki na ubezpieczenia zdrowotne
    protected BigDecimal skladkaZdrowotna1; // od podstawy wymiaru 9%
    protected BigDecimal skladkaZdrowotna2; // od podstawy wymiaru 7,75 %

    protected BigDecimal podstawaOpodatakowana;
    protected BigDecimal kosztyUzyskania;

    protected BigDecimal zaliczkaNaPodatek; // zaliczka na podatek dochodowy 18%
    protected BigDecimal kwotaZmiejszajacaPodatek; // kwota zmienjszająca podatek 46,33 PLN
    protected BigDecimal zaliczkaUS;

    Umowa(BigDecimal podstawaConstructor) {
        podstawa = podstawaConstructor;
        obliczPodstawePoSkladkach();
        obliczUbezpieczenia();
        obliczKosztUzyskaniaPrzychodu();
        podstawaOpodatakowana = podstawaPoSkladkach.subtract(kosztyUzyskania);
        obliczPodatek();
        obliczZaliczke();
    }

    void obliczZaliczke() {
        zaliczkaUS = zaliczkaNaPodatek.subtract(skladkaZdrowotna2).subtract(kwotaZmiejszajacaPodatek);
    }

    void obliczPodatek() {
        zaliczkaNaPodatek = podstawaOpodatakowana.multiply(new BigDecimal(18)).divide(new BigDecimal(100));
    }

    void obliczPodstawePoSkladkach() {
        skladkaEmerytalna = podstawa.multiply(new BigDecimal(9.76)).divide(new BigDecimal(100));
        skladkaRentowa = podstawa.multiply(new BigDecimal(1.5)).divide(new BigDecimal(100));
        ubezpieczenieChorobowe = podstawa.multiply(new BigDecimal(2.45)).divide(new BigDecimal(100));
        podstawaPoSkladkach = podstawa.subtract(skladkaEmerytalna).subtract(skladkaRentowa).subtract(ubezpieczenieChorobowe);
    }

    void obliczUbezpieczenia() {
        skladkaZdrowotna1 = podstawaPoSkladkach.multiply(new BigDecimal(9)).divide(new BigDecimal(100));
        skladkaZdrowotna2 = podstawaPoSkladkach.multiply(new BigDecimal(7.75)).divide(new BigDecimal(100));
    }

    abstract void obliczKosztUzyskaniaPrzychodu();

    BigDecimal obliczWynagrodzenie() {
        return podstawa.subtract(skladkaEmerytalna.add(skladkaRentowa).add(ubezpieczenieChorobowe).add(skladkaZdrowotna1).add(zaliczkaUS));
    }

    protected void wypiszKwoty() {
        System.out.println("________________________________________________________________________________________________");
        System.out.println("Podstawa wymiaru składek: " + podstawa.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println("Składka na ubezpieczenie emerytalne: " + skladkaEmerytalna.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println("Składka na ubezpieczenie rentowe: " + skladkaRentowa.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println("Składka na ubezpieczenie chorobowe: " + ubezpieczenieChorobowe.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println("Składka na ubezpieczenie zdrowotne: 9% = " + skladkaZdrowotna1.setScale(2, BigDecimal.ROUND_HALF_UP) + " 7,75% = " + skladkaZdrowotna2.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println("Koszty uzyskania przychodu: " + kosztyUzyskania.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println("Podstawa opodatkowania: " + podstawaOpodatakowana.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println("Zaliczka na podatek dochodowy 18% = " + zaliczkaNaPodatek.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println("Zaliczka do urzędu skarbowego: " + zaliczkaUS.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println();
        System.out.println("Pracownik otrzyma wynagrodzenie netto w wysokości: " + obliczWynagrodzenie().setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}
