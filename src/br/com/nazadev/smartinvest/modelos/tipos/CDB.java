package br.com.nazadev.smartinvest.modelos.tipos;

import br.com.nazadev.smartinvest.modelos.Investimento;

/**
 * Representa um investimento do tipo CDB (Certificado de Depósito Bancário),
 * que possui rendimento atrelado a um percentual do CDI.
 */
public class CDB extends Investimento {

    private double percentualCDI; // Ex: 1.10 → 110% do CDI
    private double anualCDI;      // CDI de referência (ex: 0.1065 → 10,65% ao ano)

    /**
     * Construtor principal para CDB.
     * A taxa anual do investimento será calculada com base no CDI e no percentual informado.
     */
    public CDB(double valorInicial, int prazoDias, boolean isento, double percentualCDI, double anualCDI) {
        super(valorInicial, percentualCDI * anualCDI, prazoDias, isento);
        this.percentualCDI = percentualCDI;
        this.anualCDI = anualCDI;
    }

    // Getters e Setters
    public double getAnualCDI() {
        return anualCDI;
    }

    public void setAnualCDI(double anualCDI) {
        this.anualCDI = anualCDI;
    }

    public double getPercentualCDI() {
        return percentualCDI;
    }

    public void setPercentualCDI(double percentualCDI) {
        this.percentualCDI = percentualCDI;
    }

    /**
     * Calcula o rendimento bruto com base no percentual do CDI e no prazo.
     * Fórmula: valorInicial * ((1 + taxaEfetiva)^(dias/365) - 1)
     */
    @Override
    public double calcularRendimentoBruto() {
        double taxaEfetiva = anualCDI * percentualCDI;
        return getValorInicial() * (Math.pow(1 + taxaEfetiva, getPrazoDias() / 365.0) - 1);
    }

    /**
     * Calcula a alíquota de Imposto de Renda conforme o prazo do investimento.
     * - Até 180 dias: 22,5%
     * - Até 360 dias: 20%
     * - Até 720 dias: 17,5%
     * - Acima de 720 dias: 15%
     */
    public double calcularAliquotaIR() {
        int dias = getPrazoDias();
        if (dias <= 180) return 0.225;
        if (dias <= 360) return 0.20;
        if (dias <= 720) return 0.175;
        return 0.15;
    }

    /**
     * Calcula o rendimento líquido, considerando a alíquota de IR (se aplicável).
     */
    @Override
    public double calcularRendimentoLiquido(double ignorado) {
        double rendimentoBruto = calcularRendimentoBruto();
        if (isIsentoIR()) {
            return rendimentoBruto;
        } else {
            double aliquota = calcularAliquotaIR();
            return rendimentoBruto * (1 - aliquota);
        }
    }

    /**
     * Exibe as informações formatadas do investimento.
     */
    @Override
    public String toString() {
        return String.format(
                "CDB [Valor Inicial=R$%.2f, CDI=%.2f%%, Percentual=%.0f%%, Prazo=%d dias, Isento IR=%s]",
                getValorInicial(), anualCDI * 100, percentualCDI * 100,
                getPrazoDias(), isIsentoIR() ? "Sim" : "Não"
        );
    }
}
