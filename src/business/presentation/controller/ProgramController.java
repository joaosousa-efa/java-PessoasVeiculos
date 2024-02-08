package business.presentation.controller;

import business.GestorPessoas;
import business.GestorVeiculos;
import business.Pessoa;
import business.Veiculo;
import exceptions.IdadeException;
import exceptions.LimiteDeVeiculosException;
import exceptions.NomeInvalidoException;
import exceptions.NumeroCCException;
import exceptions.VeiculoNaoExiste;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author João Sousa
 *
 */
public class ProgramController {

    private final GestorPessoas gestorPessoas;
    private final GestorVeiculos gestorVeiculos;
    
    public ProgramController() throws SQLException {
        gestorPessoas = new GestorPessoas();
        gestorVeiculos = new GestorVeiculos();
    }
    
    public void adicionarPessoa(Pessoa pessoa) throws SQLException{
        gestorPessoas.adicionarPessoa(pessoa);
    }
    
    public void dbPessoasToHashtable() throws SQLException, NomeInvalidoException, NumeroCCException, IdadeException{
        gestorPessoas.pessoasDBParaHashtable();
    }

    public void removerPessoa(String numeroCC) throws SQLException {
        gestorPessoas.removerPessoa(numeroCC);
    }

    public void adicionarVeiculo(Veiculo veiculo) throws LimiteDeVeiculosException, SQLException {
        
        gestorPessoas.adicionaVeiculoAPessoa(veiculo.getMatricula(), veiculo);
        
        gestorVeiculos.adicionarVeiculo(veiculo);
        
    }
    
    public void removerVeiculo(String matricula) throws VeiculoNaoExiste{
        
        gestorPessoas.removeVeiculoAPessoa(matricula);
        gestorVeiculos.removerVeiculo(matricula);
       
    }

    public Hashtable<String, Pessoa> listaPessoas() {
        return gestorPessoas.listaPessoas();
    }

    public Hashtable<String, Veiculo> listaVeiculos() {
        return gestorVeiculos.listaVeiculos();
    }
    
    public String listaPropriedadesPessoa(){
        return gestorPessoas.toString();
    }
    
    public String listaPropriedadesVeiculo(){
        return gestorVeiculos.toString();
    }
    
    public ArrayList<String> listaVeiculosPessoa(String numeroCC){
        return gestorPessoas.listaVeiculos(numeroCC);
    }
    
    public void salvarJsonFile() throws IOException{
        gestorPessoas.salvarJsonFile();
    }
    
    public void carregarJsonFile() throws IOException, ClassNotFoundException{
        gestorPessoas.carregarJsonFile();
    }
}
