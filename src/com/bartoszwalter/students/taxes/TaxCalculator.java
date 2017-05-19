package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;

public class TaxCalculator {

	private static BigDecimal podstawa;
	private static char wybranaUmowa = ' ';

	public static void main(String[] args) {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

			System.out.print("Podaj kwotę dochodu: ");
			podstawa = new BigDecimal(br.readLine(), MathContext.DECIMAL64);

			System.out.print("Typ umowy: (P)raca, (Z)lecenie: ");
			wybranaUmowa = br.readLine().charAt(0);

		} catch (Exception e) {
			System.out.println("Błędna kwota");
			System.err.println(e);
			return;
		}

		Umowa umowa;
		try {
			if (wybranaUmowa == 'P') {
				umowa = new Praca(podstawa);
			} else if (wybranaUmowa == 'Z') {
				umowa = new Zlecenie(podstawa);
			} else {
				throw new IllegalArgumentException();
			}
			umowa.wypiszKwoty();
		} catch(IllegalArgumentException e) {
			System.out.println("Nieznany typ umowy!");
		}
	}
}
