package br.com.nazadev.SmartInvest;

import br.com.nazadev.smartinvest.modelos.Investimento;

public class Main {
    public static void main(String[] args) {
        Investimento investimentoA = new Investimento(14000, 0.15,90,false);

        System.out.println(investimentoA.toString());
        System.out.printf("Rendimento bruto: R$%.2f%n", investimentoA.calcularRendimentoBruto());
    }
}
