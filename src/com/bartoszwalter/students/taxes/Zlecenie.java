package com.bartoszwalter.students.taxes;

import java.math.BigDecimal;


public class Zlecenie extends Umowa {
    public Zlecenie(BigDecimal podstawa) {
        super(podstawa);
    }

    @Override
    void obliczKosztUzyskaniaPrzychodu() {
        kwotaZmiejszajacaPodatek = new BigDecimal(0);
        kosztyUzyskania = podstawaPoSkladkach.multiply(new BigDecimal(20)).divide(new BigDecimal(100));
    }

    @Override
    public void wypiszKwoty() {
        System.out.println("Umowa zlecenie");
        super.wypiszKwoty();
    }
}
