package teste;

import business.Pessoa;
import business.Veiculo;
import business.presentation.controller.ProgramController;
import exceptions.IdadeException;
import exceptions.LimiteDeVeiculosException;
import exceptions.NomeInvalidoException;
import exceptions.NumeroCCException;
import exceptions.VeiculoNaoExiste;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Scanner;


/**
 *
 * @author João Sousa
 *
 */
public class TesteStartConsole {

    public static void main(String[] args) throws SQLException, LimiteDeVeiculosException {

        ProgramController programController = new ProgramController();

//        try {
//            programController.dbPessoasToHashtable();
//        } catch (SQLException | NomeInvalidoException | NumeroCCException | IdadeException ex) {
//            Logger.getLogger(TesteStartConsole.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Scanner sc = new Scanner(System.in);
        String option;
        System.out.println("########################################");
        System.out.println("Tecla \"m\" para ver o menu. 0 para Sair.");
        System.out.println("########################################");
        do {

            option = sc.nextLine();

            switch (option) {
                case "1": //############################################################################################ opcao 1
                {
                    // Só termina os loops, se não haver exceptions
                    boolean nomeOk = false;
                    boolean ccOk = false;
                    boolean idadeOk = false;

                    System.out.println("--> Criar pessoa.");

                    Pessoa p01 = new Pessoa();

                    do {
                        System.out.println("Introduza o nome. (tem de ter mais de 6 caracteres)");
                        String nome = sc.nextLine();
                        try {
                            p01.setNome(nome);
                            nomeOk = true;
                        } catch (NomeInvalidoException ex) {
                            msg(ex.getMessage());
                        }
                    } while (!nomeOk);

                    do {
                        System.out.println("Introduza o número do cartão de cidadão. (tem de ter mais de 9 digitos)");
                        String numeroCC = sc.nextLine();
                        try {
                            p01.setNumeroCC(numeroCC);
                            ccOk = true;
                        } catch (NumeroCCException ex) {
                            msg(ex.getMessage());
                        }
                    } while (!ccOk);

                    do {
                        System.out.println("Introduza a idade da pessoa.");
                        Integer idade = sc.nextInt();
                        try {
                            p01.setIdade(idade);
                            idadeOk = true;
                        } catch (IdadeException ex) {
                            msg(ex.getMessage());
                        }
                    } while (!idadeOk);

                    try {
                        programController.adicionarPessoa(p01);
                    } catch (SQLException ex) {
                        msg(ex.getMessage());
                    }

                    break;
                }

                case "2": //############################################################################################ opcao 2
                    System.out.println("--> Remover pessoa.");

                    listarPessoas(programController.listaPessoas());

                    System.out.println("Introduza o número do Cartão de cidadao da pessoa a remover:");
                    String nCCremover = sc.nextLine();
                    programController.removerPessoa(nCCremover);

                    break;
                case "3": //############################################################################################ opcao 3
                    System.out.println("--> Adicionar Veículo.");

                    listarPessoas(programController.listaPessoas());

                    System.out.println("Introduza a marca veículo:");
                    String marca = sc.nextLine();
                    System.out.println("Introduza o modelo do veículo:");
                    String modelo = sc.nextLine();
                    System.out.println("Introduza a matricula do veículo:");
                    String matricula = sc.nextLine();
                    System.out.println("Introduza o número de chassis veículo:");
                    Integer nChassi = sc.nextInt();
                    System.out.println("Introduza a cilindrada do veículo:");
                    Integer cilindrada = sc.nextInt();
                    System.out.println("Introduza o número de portas do veículo:");
                    Integer nPortas = sc.nextInt();

                    System.out.println("Introduza o número do cartão de cidadao do proprietário do veículo:");
                    String proprietarioCC = sc.nextLine();
                    sc.nextLine();
                    Veiculo v01 = new Veiculo(marca, modelo, matricula, nChassi, cilindrada, nPortas, proprietarioCC);
                    programController.adicionarVeiculo(v01);
                     
                    break;

                case "4": //############################################################################################ opcao 4
                    System.out.println("-->  Remover Veículo.");

                    System.out.println("Introduza a matricula do veículo a remover:");
                    matricula = sc.nextLine();

                    try {
                        programController.removerVeiculo(matricula);
                    } catch (VeiculoNaoExiste ex) {
                        //Logger.getLogger(TesteStartConsole.class.getName()).log(Level.SEVERE, null, ex);
                        msg(ex.getMessage());
                    }

                    break;

                case "5": //############################################################################################ opcao 5
                    System.out.println("--> Listar Veículos.");

                    //listarPessoas(programController.listaPessoas());
                    //listarVeiculos(programController.listaVeiculos());
                    System.out.println("Introduza o número do cartão de cidadao do proprietário do veículo:");
                    proprietarioCC = sc.nextLine();

                    System.out.println(programController.listaVeiculosPessoa(proprietarioCC).toString());

                    break;
                case "6": //############################################################################################ opcao 6

                    System.out.println("--> Listar todas as propriedades.");

                    System.out.println(programController.listaPropriedadesPessoa());
                    System.out.println(programController.listaPropriedadesVeiculo());
                    System.out.println("");

                    listarPessoas(programController.listaPessoas());
                    listarVeiculos(programController.listaVeiculos());

                    break;
                case "7": //############################################################################################ opcao 9

                    System.out.println("--> Adiciona 3 Pessoas.");

                     {
                        try {
                            preencherDados(programController);
                        } catch (Exception ex) {
                            msg(ex.getMessage());
                        }
                    }

                    break;
                case "8": //############################################################################################ opcao 8

                    System.out.println("--> JSON.");

                     {
                        try {
                            programController.salvarJsonFile();
                        } catch (Exception ex) {
                            msg(ex.getMessage());
                        }
                    }

                    break;
                case "9": //############################################################################################ opcao 9

                    System.out.println("--> Load JSON.");

                     {
                        try {
                            programController.carregarJsonFile();
                        } catch (IOException | ClassNotFoundException ex) {
                            //Logger.getLogger(TesteStartConsole.class.getName()).log(Level.SEVERE, null, ex);
                            msg(ex.getMessage());
                        }
                    }

                    break;
                case "l": //############################################################################################ opcao l

                    System.out.println("--> Load da DB.");

                     {
                        try {
                            programController.dbPessoasToHashtable();
                        } catch (SQLException | NomeInvalidoException | NumeroCCException | IdadeException ex) {
                            msg(ex.getMessage());
                        }
                    }
                    break;
                case "m": //############################################################################################ opcao m
                    listarMenu();
                    break;
                case "0":
                    System.out.println("Sair.");
                    System.exit(0);
                default:
                    System.out.println("########################################");
                    System.out.println("Tecla \"m\" para ver o menu. 0 para Sair.");
                    System.out.println("########################################");
                //throw new AssertionError();
            }

        } while (option != "0");

    }

    public final static void listarMenu() {
        limparConsola();
        System.out.println("########################################");
        System.out.println("1 - Criar pessoa.");
        System.out.println("2 - Remover pessoa.");
        System.out.println("3 - Adicionar Veículo.");
        System.out.println("4 - Remover Veículo.");
        System.out.println("5 - Listar Veículos de uma pessoa.");
        System.out.println("6 - Listar todas as propriedades.");
        System.out.println("7 - Adiciona 3 Pessoas na HasTable e DB.");
        System.out.println("8 - Grava hashtable para um ficheiro JSON.");
        System.out.println("9 - JSON para hashtable.");
        System.out.println("l - DB para hashtable.");
        System.out.println("0 - Sair.");
        System.out.println("########################################");
        System.out.println("Escolha uma opção:");

    }

    public final static void msg(String ex) {
        System.out.println("###############################################################");
        System.out.println("Erro: " + ex);
        System.out.println("###############################################################");
        System.out.println("");
        //Logger.getLogger(TesteStartConsole.class.getName()).log(Level.SEVERE, null, ex);
    }

    public static void limparConsola() { // Nota: não funciona no output do netbeans.
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void listarPessoas(Hashtable<String, Pessoa> p) {
        Pessoa pessoaLista = new Pessoa();
        System.out.println("Lista de pessoas:");
        System.out.println("");
        for (String nCC : p.keySet()) {
            pessoaLista = p.get(nCC);
            System.out.println("    Nome: " + pessoaLista.getNome() + " | Número CC: " + pessoaLista.getNumeroCC());
        }
        System.out.println("");
    }

    private static void listarVeiculos(Hashtable<String, Veiculo> v) {
        Veiculo veiculoLista = new Veiculo();
        System.out.println("Lista de veiculos:");
        System.out.println("");
        for (String nCC : v.keySet()) {
            veiculoLista = v.get(nCC);
            System.out.println("    Marca: " + veiculoLista.getMarca() + " | Matricula: " + veiculoLista.getMatricula());
        }
        System.out.println("");
    }

    private static void preencherDados(ProgramController pc) throws Exception {
        Pessoa obj;

        obj = new Pessoa("Daniela Silva", "222222333", 23);
        pc.adicionarPessoa(obj);

        obj = new Pessoa("Sara Coimbra", "333222333", 25);
        pc.adicionarPessoa(obj);

        Veiculo v1;
        v1 = new Veiculo("BMW", "340", "aassdd", 1234567, 4000, 4, "333222333");
        pc.adicionarVeiculo(v1);
        
    }
}
