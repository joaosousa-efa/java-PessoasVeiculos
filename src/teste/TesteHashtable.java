package teste;

import business.Veiculo;
import java.util.Hashtable;

/**
 *
 * @author João Sousa
 * 
 * 
 * As tabelas hash em Java são usadas para resolver problemas relacionados à eficiência de tempo de execução nas operações de inserção, remoção e busca de elementos.
 * Elas permitem uma correspondência direta entre uma chave única (por exemplo, matrícula de um aluno) e o índice em que o elemento está localizado no array
 * 
 */
public class TesteHashtable {
    
    public static void main(String[] args) {
        Hashtable<String, Veiculo> veiculos = new Hashtable<>();
        
        Veiculo v = new Veiculo();
        Veiculo v02 = new Veiculo();
        Veiculo v03 = new Veiculo();
        Veiculo v04 = new Veiculo();
        
        v.setMatricula("aa1122");
        veiculos.put("aa1122", v);
        
        v02.setMatricula("bb1122");
        veiculos.put("bb1122", v02);
        
        v03.setMatricula("cc1122");
        veiculos.put("cc1122", v03);
        
        v04.setMatricula("dd1122");
        veiculos.put("dd1122", v04);
        
        System.out.println("Teste " + v.toString());
        
        //fore
        for (String matricula : veiculos.keySet()) {
            System.out.println(veiculos.get(matricula));
        }
        
    }
    
}
