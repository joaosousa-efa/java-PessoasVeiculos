package teste;

import business.Pessoa;
import business.Veiculo;
import exceptions.IdadeException;
import exceptions.LimiteDeVeiculosException;
import exceptions.NomeInvalidoException;
import exceptions.NumeroCCException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author João Sousa
 * 
 */
public class TestePessoa {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        // criar instancia pessoa
        Pessoa pessoa = new Pessoa();
        
        System.out.println("Introduza o nome. (tem de ter mais de 6 caracteres)");
        String nome = sc.nextLine();
        try {
            pessoa.setNome(nome);
        } catch (NomeInvalidoException ex) {
            Logger.getLogger(TestePessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Introduza o número do Cartão do cidadão. (tem de ter mais de 9 digitos)");
        String numeroCC = sc.nextLine();
        try {
            pessoa.setNumeroCC(numeroCC);
        } catch (NumeroCCException ex) {
            Logger.getLogger(TestePessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        System.out.println("Introduza a idade.");
        int idade = sc.nextInt();
        try {
            pessoa.setIdade(idade);
        } catch (IdadeException ex) {
            Logger.getLogger(TestePessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        sc.nextLine();
        
        String contacto;
        do {
            System.out.println("Introduza o contacto. (vazio para terminar):");
            contacto = sc.nextLine();
            
            try {
                if (!pessoa.adicionarContacto(contacto)) {
                    System.out.println("Erro a guardar um contacto.");
                }
                
            } catch (NullPointerException ex) {
                System.out.println("ERROR TERRIVEL: " + ex.getMessage());
            }
            
        } while (!contacto.equals(""));
        
        
        Veiculo veiculo = new Veiculo("bmw", "340", "aazzaa", 1234, 1000, 4, "333222222");
        
        try {
            pessoa.adicionarVeiculo(veiculo);
        } catch (LimiteDeVeiculosException ex) {
            Logger.getLogger(TestePessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("DADOS DA PESSOA " +  pessoa.toString());
        
        
//        System.out.println("Introduza o contacto a remover:");
//        String contactoRemover = sc.nextLine();
//        
//        if (pessoa.removerContacto(contactoRemover)){
//            System.out.println("Contacto removido com sucesso.");
//        } else {
//            System.out.println("Erro a remover contacto.");
//        
//        }

        System.out.println("Introduza a maricula do veiculo a remover:");
        String veiculoPosToRemove = sc.nextLine();
        
        if (pessoa.removerVeiculo(pessoa.listaVeiculos().get(0))){
            System.out.println("Veículo removido com sucesso.");
        } else {
            System.out.println("Erro a remover veiculo.");
        
        }
        
        System.out.println("DADOS DA PESSOA " +  pessoa.toString());
    }
}