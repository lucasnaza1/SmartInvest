package br.com.nazadev.SmartInvest;

import br.com.nazadev.smartinvest.modelos.Investimento;
import br.com.nazadev.smartinvest.modelos.tipos.CDB;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Simulação de Investimento Genérico ===");
        Investimento investimentoA = new Investimento(14000, 0.15, 90, false);

        System.out.println(investimentoA.toString());
        System.out.printf("Rendimento Bruto: R$%.2f%n", investimentoA.calcularRendimentoBruto());
        System.out.printf("Rendimento Líquido (15%% IR): R$%.2f%n%n", investimentoA.calcularRendimentoLiquido(0.15));

        System.out.println("=== Simulação de CDB (ex: Caixinha Nubank) ===");
        CDB cdbNubank = new CDB(1000.0, 90, false, 1.10, 0.1065);

        System.out.println(cdbNubank.toString());
        System.out.printf("Rendimento Bruto: R$%.2f%n", cdbNubank.calcularRendimentoBruto());
        System.out.printf("Rendimento Líquido (c/ IR automático): R$%.2f%n", cdbNubank.calcularRendimentoLiquido(0));
    }
}
