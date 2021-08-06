package br.ufes.model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Funcionario {

    private String nome;
    private double salarioBase;
    private double salarioTotal;
    private int distanciaMoradia;
    private int faltas;
    private String cargo;
    private final ArrayList<Bonus> bonusRecebidos;

    public Funcionario(String nome, double salarioBase, String cargo) throws Exception {
        String exceptions = "";

        Pattern p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(nome);
        boolean b = m.find();

        if (nome == null || b) {
            exceptions = exceptions.concat("\n#1 Informe um nome válido");
        }

        if (cargo == null) {
            exceptions = exceptions.concat("\n#2 Informe um cargo válido");
        }
        if (salarioBase <= 998.0) {
            exceptions = exceptions.concat("\n#3 O salário base deve ser >= R$ 998,00");
        }

        if (exceptions.length() > 0) {
            throw new Exception(exceptions);
        }
        this.nome = nome;
        this.cargo = cargo;
        this.salarioBase = salarioBase;
        this.bonusRecebidos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalarioBase() {
        return this.salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) throws Exception {
        if (faltas >= 0) {
            this.faltas = faltas;
        }   else {
            throw new Exception("\n#5 Informe um número de faltas válido");
        }
    }

    public int getDistanciaMoradia() {
        return distanciaMoradia;
    }

    public void setDistanciaMoradia (int distanciaMoradia) throws Exception {
        if (distanciaMoradia >= 0) {
            this.distanciaMoradia = distanciaMoradia;
        }   else {
            throw new Exception("\n#4 Informe uma distância válida");
        }
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return this.calculaSalario();
    }

    private double calculaSalario() {
        this.salarioTotal = this.salarioBase + this.calculaTotalBonus();
        return this.salarioTotal;
    }

    public void addBonus(Bonus bonus) {
        this.bonusRecebidos.add(bonus);
    }

    public double calculaTotalBonus() {
        double totalBonus = 0;
        for (Bonus bonus : bonusRecebidos) {
            totalBonus += bonus.getValor();
        }

        return totalBonus;
    }

    @Override
    public String toString() {
        String strBonusRecebidos = "";
        for (Bonus bonusRecebido : bonusRecebidos) {
            strBonusRecebidos += "\n\t" + bonusRecebido;
        }
        String strFuncionario = "Funcionario {"
                + "nome: " + this.nome + ", "
                + "salarioBase: " + this.salarioBase + ", "
                + "totalBonus: " + this.calculaTotalBonus() + ", "
                + "salarioTotal: " + this.getSalario()
                + '}';

        if (bonusRecebidos.size() > 0) {
            strFuncionario += "\nBonus recebidos: " + strBonusRecebidos;
        }

        return strFuncionario;
    }
}
