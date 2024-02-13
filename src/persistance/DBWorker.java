package persistance;

import business.Pessoa;
import business.Veiculo;
import exceptions.IdadeException;
import exceptions.NomeInvalidoException;
import exceptions.NumeroCCException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author João Sousa
 * 
 * Os métodos para guardar, apagar ou listar pessoas ou veiculos tem de ser o mais genérico possível
 * Isso irá permitir maior flexibilidade na eventualidade de mudar de base de dados para outro tipo (exemplo mudar de MySQL para SQLServer)
 */
public class DBWorker {

    private  DBAdapter db;
    
    public DBWorker() throws SQLException {
        db = new DBAdapter();
    }
    
    public ArrayList<Pessoa> listaPessoas() throws SQLException, NomeInvalidoException, NumeroCCException, IdadeException{
        return db.listaPessoas();
    }
    
    public void guardarPessoa(Pessoa pessoa) throws SQLException{
        db.adicionaPessoa(pessoa);
        
    }
    
    public void removerPessoa(String numeroCC) throws SQLException{
        db.removePessoa(numeroCC);
    }
    
    public ArrayList<Veiculo> listaVeiculos() throws SQLException, NomeInvalidoException, NumeroCCException, IdadeException{
        return db.listaVeiculos();
    }
    
    public void guardarVeiculo(Veiculo veiculo) throws SQLException{
        db.adicionaVeiculo(veiculo);
    }
    
    public void removerVeiculo(String matricula) throws SQLException{
        db.removeVeiculo(matricula);
    }
    
    public void CloseDBConnection() throws SQLException{
        db.CloseDBConnection();
    }
    
}
