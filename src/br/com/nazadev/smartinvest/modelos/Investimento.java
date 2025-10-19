package modelos;

//Construtor padrão
public class Investimento {
    private double valorInicial;
    private double taxaAnual;
    private int prazoDias;
    private boolean isento;

    public double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(double valorInicial) {
        if (valorInicial <= 0) {
            throw new IllegalArgumentException("O valor inicial deve ser maior que zero.");
        }
        this.valorInicial = valorInicial;
    }

    public double getTaxaAnual() {
        return taxaAnual;
    }

    public void setTaxaAnual(double taxaAnual) {
        if (taxaAnual <= 0) {
            throw new IllegalArgumentException("A taxa anual deve ser maior que zero.");
        }
        this.taxaAnual = taxaAnual;
    }

    public int getPrazoDias() {
        return prazoDias;
    }

    public void setPrazoDias(int prazoDias) {
        if (prazoDias <= 0) {
            throw new IllegalArgumentException("O prazo de dias deve ser maior que zero.");
        }
        this.prazoDias = prazoDias;
    }

    public boolean isIsentoIR() {
        return isento;
    }

    public void setIsento(boolean isento) {
        this.isento = isento    ;
    }
// Construtor para recepção dos parâmetros
    public Investimento(double valorInicial, double taxaAnual, int prazoDias, boolean isento) {
        this.valorInicial = valorInicial;
        this.taxaAnual = taxaAnual;
        this.prazoDias = prazoDias;
        this.isento = isento;
    }
// Métodos de comportamento

public double calcularRendimentoBruto(){
        return valorInicial * (Math.pow(1 + taxaAnual, prazoDias/365) - 1);
}
public double calcularRendimentoLiquido(double aliquotaIR){
        double rendimentoBruto = calcularRendimentoBruto();
        if (isento) {
            return rendimentoBruto;
        }  else {
            return rendimentoBruto * (1 - aliquotaIR);
        }
}

@Override
    public String toString(){
        return String.format("modelos.Investimento [valorInicial=R$%.2f, taxaAnual=%.2f%%, prazo=%d dias, isento=%s]",
                valorInicial, taxaAnual * 100, prazoDias, isento ? "Sím" : "Não");
}
}