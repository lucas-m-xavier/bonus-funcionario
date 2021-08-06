package br.ufes.projetos01;

import br.ufes.calculodebonus.ProcessadoraBonus;
import br.ufes.model.Funcionario;

public class Main {

    public static void main(String args[]) {
        try {
            Funcionario funcionario = 
                    new Funcionario("Joao", 20000.00, "Gerente");

            funcionario.setFaltas(2);
            funcionario.setDistanciaMoradia(1000);

            ProcessadoraBonus pb = new ProcessadoraBonus();
            pb.processar(funcionario);

            System.out.println(funcionario);
        } catch (Exception e) {
            System.out.println("Erro(s):" + e.getMessage());
        }

    }
}
