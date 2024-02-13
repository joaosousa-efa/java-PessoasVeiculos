package presentation;

import business.Pessoa;
import business.Veiculo;
import exceptions.IdadeException;
import exceptions.LimiteDeVeiculosException;
import exceptions.NomeInvalidoException;
import exceptions.NumeroCCException;
import java.util.Hashtable;
import java.util.Scanner;


/**
 *
 * @author João Sousa
 *
 */
public class StartConsole {

    public static void main(String[] args) {

        Hashtable<String, Pessoa> pessoas = new Hashtable<String, Pessoa>();

        Pessoa pessoa = new Pessoa();

        Scanner sc = new Scanner(System.in);
        String option;
        do {
            listarMenu();
            System.out.println("Escolha uma opção:");
            option = sc.nextLine();

            switch (option) {
                case "1": //############################################################################################ opcao 1
                {
                    String numeroCC = "";
                    // Só termina os loops, se não haver exceptions
                    boolean nomeOk = false;
                    boolean ccOk = false;
                    boolean idadeOk = false;

                    System.out.println("--> Criar pessoa.");

                    // Nome
                    do {
                        System.out.println("Introduza o nome. (tem de ter mais de 6 caracteres)");
                        String nome = sc.nextLine();
                        try {
                            pessoa.setNome(nome);
                            nomeOk = true;
                        } catch (NomeInvalidoException ex) {
                            System.out.println("Erro: " + ex.getMessage());
                            //Logger.getLogger(TestePessoa.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } while (!nomeOk);
                    // Cartao cidadao
                    do {
                        System.out.println("Introduza o número do cartão de cidadão. (tem de ter mais de 9 digitos)");
                        numeroCC = sc.nextLine();
                        try {
                            pessoa.setNumeroCC(numeroCC);
                            ccOk = true;
                        } catch (NumeroCCException ex) {
                            System.out.println("Erro: " + ex.getMessage());
                            //Logger.getLogger(TestePessoa.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } while (!ccOk);
                    // Idade
                    do {
                        System.out.println("Introduza a idade da pessoa.");
                        Integer idade = sc.nextInt();
                        try {
                            pessoa.setIdade(idade);
                            idadeOk = true;
                        } catch (IdadeException ex) {
                            System.out.println("Erro: " + ex.getMessage());
                            //Logger.getLogger(TestePessoa.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } while (!idadeOk);

                    // Adicionar contactos
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
                            System.out.println("ERRO: " + ex.getMessage());
                        }

                    } while (!contacto.equals(""));

                    pessoas.put(numeroCC, pessoa);

                    break;
                }
                case "2": //############################################################################################ opcao 2
                    System.out.println("--> Remover pessoa.");

                    listarPessoas(pessoas);

                    System.out.println("Introduza o número do Cartão de cidadao da pessoa a remover:");
                    String nCCremover = sc.nextLine();
                    // Rermove da hashtable
                    pessoas.remove(nCCremover);

                    break;
                case "3": //############################################################################################ opcao 3
                    System.out.println("--> Adicionar Veículo.");

                    Pessoa p = new Pessoa();

                    listarPessoas(pessoas);

                    System.out.println("Introduza o número do cartão de cidadao do proprietário do veículo:");
                    String proprietarioCC = sc.nextLine();
                    if (pessoas.containsKey(proprietarioCC)) {
                        p = pessoas.get(proprietarioCC);

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
                        

                        Veiculo veiculo = new Veiculo(marca, modelo, matricula, nChassi, cilindrada, nPortas, proprietarioCC);
                        try {
                            p.adicionarVeiculo(veiculo);
                        } catch (LimiteDeVeiculosException ex) {
                            System.out.println("Erro: " + ex.getMessage());
                            //Logger.getLogger(StartConsole.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        System.out.println("Não existe nenhuma pessoa com esse número de CC.");
                    }

                    break;
                case "4": //############################################################################################ opcao 4
                    System.out.println("-->  Remover Veículo.");

                    System.out.println("");
                    for (String nCC : pessoas.keySet()) {
                        p = pessoas.get(nCC);

                        if (p.listaVeiculos().size() > 0) {
                            System.out.println("Lista de veiculos do " + p.getNome());

                            // ArrayList lista de veiculos
                            for (int i = 0; i < p.listaVeiculos().size(); i++) {
                                System.out.println(p.listaVeiculos().get(i).toString());
                            }
                        }
                    }

                    System.out.println("");

                    // Remover veículo
                    String matricula;
                    do {
                        System.out.println("Introduza a matricula do veículo a remover. (vazio para terminar):");
                        matricula = sc.nextLine();

                        for (String nCC : pessoas.keySet()) { // loop key value
                            Pessoa obj = pessoas.get(nCC);

                            if (obj.listaVeiculos().size() > 0) {

                                for (int i = 0; i < obj.listaVeiculos().size(); i++) {
                                    if (obj.listaVeiculos().get(i).getMatricula().equals(matricula)) {

                                        // remove veiculo
                                        if (obj.removerVeiculo(obj.listaVeiculos().get(i))) {
                                            System.out.println("Veículo removido.");
                                        } else {
                                            System.out.println("Falha ao remover o veículo.");
                                        }
                                        // atualiza objecto na hashtable
                                        pessoas.put(obj.getNumeroCC(), obj);
                                    }
                                }
                            }
                        }

                    } while (!matricula.equals("")); // vazio termina o loop

                    break;
                case "5": //############################################################################################ opcao 5
                    System.out.println("--> Listar Veículos de uma pessoa.");

                    listarPessoas(pessoas);

                    System.out.println("Introduza o número do cartão de cidadao do proprietário do veículo:");
                    String nCCProprietarioVeiculo = sc.nextLine();

                    for (String nCC : pessoas.keySet()) { // loop na hash table
                        p = pessoas.get(nCC);

                        limparConsola();

                        if (p.getNumeroCC().equals(nCCProprietarioVeiculo)) { // se for o CC da pesssoa

                            // ArrayList lista de veiculos
                            System.out.println("    Lista de veiculos do " + p.getNome());
                            System.out.println("");
                            if (p.listaVeiculos().size() > 0) { // se tem veículos
                                for (int i = 0; i < p.listaVeiculos().size(); i++) {
                                    System.out.println(p.listaVeiculos().get(i).toString());
                                }
                            } else {
                                System.out.println("    A pessoa não tem veículos.");
                            }
                            System.out.println("");
                        }
                    }
                    break;
                case "6": //############################################################################################ opcao 6

                    System.out.println("--> Listar todas as propriedades.");

                    for (String nCC : pessoas.keySet()) {
                        System.out.println(pessoas.get(nCC));
                    }

                    break;
                case "7": //############################################################################################ opcao 9

                    System.out.println("--> Adiciona 3 Pessoas na HasTable (apaga antiga).");

                    preencherDados(pessoas);

                    break;
                case "0":
                    System.out.println("Sair.");
                    System.exit(0);
                default:
                //throw new AssertionError();
            }

        } while (option != "0");

    }

    public final static void listarMenu() {
        limparConsola();
        System.out.println("");
        System.out.println("########################################");
        System.out.println("1 - Criar pessoa.");
        System.out.println("2 - Remover pessoa.");
        System.out.println("3 - Adicionar Veículo.");
        System.out.println("4 - Remover Veículo.");
        System.out.println("5 - Listar Veículos de uma pessoa.");
        System.out.println("6 - Listar todas as propriedades.");
        System.out.println("7 - Adiciona 3 Pessoas na HasTable (apaga antiga.");
        System.out.println("########################################");
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

    private static void preencherDados(Hashtable<String, Pessoa> p) {
        Pessoa obj;

        obj = new Pessoa("Daniela Silva", "222222333", 23);
        p.put("222222333", obj);

        obj = new Pessoa("Sara Coimbra", "333222333", 25);
        p.put("333222333", obj);

        Veiculo v1;
        v1 = new Veiculo("BMW", "340", "aassdd", 1234567, 4000, 4, "333222333");
        try {
            obj.adicionarVeiculo(v1);
        } catch (LimiteDeVeiculosException ex) {
            System.out.println("Erro: " + ex.getMessage());
            //Logger.getLogger(StartConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
