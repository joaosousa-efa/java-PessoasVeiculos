package business;

import exceptions.IdadeException;
import exceptions.LimiteDeVeiculosException;
import exceptions.NomeInvalidoException;
import exceptions.NumeroCCException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import persistance.DBWorker;
import persistance.JSONWorker;

/**
 *
 * @author João Sousa
 */
public class GestorPessoas {

    private  DBWorker dbw;
    
    public GestorPessoas() throws SQLException {
        dbw = new DBWorker();
    }
    
    Hashtable<String, Pessoa> pessoas = new Hashtable<String, Pessoa>();

    public Pessoa getPessoa(String cc) {
        return pessoas.get(cc);
    }

    public void adicionarPessoa(Pessoa pessoa) throws SQLException{
        dbw.guardarPessoa(pessoa);
        pessoas.put(pessoa.getNumeroCC(), pessoa);
    }
    
    public void pessoasDBParaHashtable() throws SQLException, NomeInvalidoException, NumeroCCException, IdadeException{
        ArrayList<Pessoa> p = dbw.listaPessoas();
        
        for (int i = 0; i < p.size(); i++) {
            
            pessoas.put(p.get(i).getNumeroCC(), p.get(i));
        }
    }
    public void removerPessoa(String numeroCC) throws SQLException {
        dbw.removerPessoa(numeroCC);
        pessoas.remove(numeroCC);
    }

    public boolean contains(Pessoa pessoa) {
        return pessoas.contains(pessoa.getNumeroCC());
    }

    public Hashtable<String, Pessoa> listaPessoas() {
        return pessoas;
    }

    public void adicionaVeiculoAPessoa(String numeroCC, Veiculo veiculo) throws LimiteDeVeiculosException {

        if (pessoas.get(numeroCC) != null) {
            pessoas.get(numeroCC).adicionarVeiculo(veiculo);
        }
    }

    public void removeVeiculoAPessoa(String matricula) {
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
    }

    public ArrayList<String> listaVeiculos(String numeroCC) {
        Pessoa p = new Pessoa();
        ArrayList<String> lista = new ArrayList<>();
        for (String nCC : pessoas.keySet()) { // loop na hash table
            p = pessoas.get(nCC);

            if (p.getNumeroCC().equals(numeroCC)) { // se for o CC da pesssoa

                // ArrayList lista de veiculos
                if (p.listaVeiculos().size() > 0) { // se tem veículos
                    for (int i = 0; i < p.listaVeiculos().size(); i++) {
                        lista.add(p.listaVeiculos().get(i).toString());
                    }
                }
            }
        }
        return lista;
    }
    
    public void salvarJsonFile() throws IOException{
        JSONWorker jw = new JSONWorker();
        jw.salvar(pessoas);
    }
    
    public void carregarJsonFile() throws IOException, ClassNotFoundException{
        JSONWorker jw = new JSONWorker();
        pessoas = jw.carregar();
    }

    @Override
    public String toString() {
        return "GestorPessoas{" + "pessoas=" + pessoas + '}';
    }

}
