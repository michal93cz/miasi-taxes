package com.bartoszwalter.students.taxes;

import java.math.BigDecimal;


public class Praca extends Umowa {
    public Praca(BigDecimal podstawa) {
        super(podstawa);
    }

    @Override
    void obliczKosztUzyskaniaPrzychodu() {
        kwotaZmiejszajacaPodatek = new BigDecimal(46.33);
        kosztyUzyskania = new BigDecimal(111.25);
    }

    @Override
    public void wypiszKwoty() {
        System.out.println("Umowa o prace");
        super.wypiszKwoty();
    }
}
