package persistance;

import business.Pessoa;
import business.Veiculo;
import exceptions.IdadeException;
import exceptions.NomeInvalidoException;
import exceptions.NumeroCCException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author João Sousa
 */
public class DBAdapter {
   
    private String server = "";
    private String username = "";
    private String password = "";
    private String dbname = "";
    private String url = "";
    
    private Connection conn = null;
    private Statement st;

    public DBAdapter() throws SQLException {
        
        try (InputStream input = DBAdapter.class.getClassLoader().getResourceAsStream("db.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            // Usa as propriedades
            server = prop.getProperty("db.server");
            username = prop.getProperty("db.username");
            password = prop.getProperty("db.password");
            dbname = prop.getProperty("db.name");
            url = "jdbc:mysql://" + server + ":3306/" + dbname;

            // ... conecte ao banco de dados usando as credenciais acima
            conn = DriverManager.getConnection(url, username, password);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
     
    //    private final String server = "localhost";
    //    private final String username = "forasteiro";
    //    private final String password = "123";
    //    private final String dbname = "PessoasVeiculos";
    //    private final String url = "jdbc:mysql://" + server + ":3306/" + dbname;
        

    public ArrayList<Pessoa> listaPessoas() throws SQLException, NomeInvalidoException, NumeroCCException, IdadeException {

        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        //nome
        //numeroCC
        //idade

        st = conn.createStatement();
        String mysqlQuery = "SELECT * FROM " + dbname + ".Pessoa;";
        ResultSet rs = st.executeQuery(mysqlQuery);

        while (rs.next()) {

            Pessoa p1 = new Pessoa();

            p1.setNome(rs.getString("nome"));
            p1.setNumeroCC(rs.getString("numeroCC"));
            p1.setIdade(rs.getInt("idade"));

            pessoas.add(p1);

        }
        return pessoas;
    }

    public Integer adicionaPessoa(Pessoa pessoa) throws SQLException {
        //nome
        //numeroCC
        //idade

        conn.setCatalog(dbname);
        String mysqlQuery = "INSERT INTO Pessoa (nome,numeroCC,idade) VALUES (?,?,?)";

        PreparedStatement ps;
        //ps = conn.prepareStatement(mysqlQuery);
        ps = conn.prepareStatement(mysqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

        ps.setString(1, pessoa.getNome());
        ps.setString(2, pessoa.getNumeroCC());
        ps.setInt(3, pessoa.getIdade());

        int result = ps.executeUpdate();

        System.out.println(result > 0 ? "Inserido com sucesso" : "Erro");

        // Obter a chave automaticamente gerada
        int last_inserted_id = 0;
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            last_inserted_id = rs.getInt(1);
        }

        return last_inserted_id;
    }

    public Integer removePessoa(String numeroCC) throws SQLException {
        //nome
        //numeroCC
        //idade

        conn.setCatalog(dbname);
        String mysqlQuery = "DELETE FROM Pessoa WHERE numeroCC LIKE ?";

        PreparedStatement ps;
        ps = conn.prepareStatement(mysqlQuery);

        ps.setString(1, numeroCC);

        int result = ps.executeUpdate();

        System.out.println(result > 0 ? "Pessoa removida com sucesso" : "Erro");

        return result;
    }

    public ArrayList<Veiculo> listaVeiculos() throws SQLException, NomeInvalidoException, NumeroCCException, IdadeException {

        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
        //matricula varchar 10
        //marca varchar 45
        //modelo varchar 45
        //nChassis int
        //cinlindrada int
        //nPortas int
        //proprietario varchar 45

        st = conn.createStatement();
        String mysqlQuery = "SELECT * FROM " + dbname + ".Veiculo;";
        ResultSet rs = st.executeQuery(mysqlQuery);

        while (rs.next()) {

            Veiculo v1 = new Veiculo();

            v1.setMatricula(rs.getString("matricula"));
            v1.setMarca(rs.getString("marca"));
            v1.setModelo(rs.getString("modelo"));
            v1.setnChassi(rs.getInt("nChassis"));
            v1.setCilindrada(rs.getInt("cilindrada"));
            v1.setnPortas(rs.getInt("nPortas"));
            v1.setProprietario(rs.getString("proprietario"));

            veiculos.add(v1);

        }
        return veiculos;
    }

    public Integer adicionaVeiculo(Veiculo veiculo) throws SQLException {
        //matricula varchar 10
        //marca varchar 45
        //modelo varchar 45
        //nChassis int
        //cinlindrada int
        //nPortas int
        //proprietario varchar 45

        conn.setCatalog(dbname);
        String mysqlQuery = "INSERT INTO Veiculo (matricula, marca, modelo, nChassi, cilindrada, nPortas, Pessoa_numeroCC) VALUES (?,?,?,?,?,?,?)";

        PreparedStatement ps;
        //ps = conn.prepareStatement(mysqlQuery);
        ps = conn.prepareStatement(mysqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

        ps.setString(1, veiculo.getMatricula());
        ps.setString(2, veiculo.getMarca());
        ps.setString(3, veiculo.getModelo());
        ps.setInt(4, veiculo.getnChassi());
        ps.setInt(5, veiculo.getCilindrada());
        ps.setInt(6, veiculo.getnPortas());
        ps.setString(7, veiculo.getProprietario());

        int result = ps.executeUpdate();

        System.out.println(result > 0 ? "Veículo inserido com sucesso" : "Erro");

        // Obter a chave automaticamente gerada
        int last_inserted_id = 0;
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            last_inserted_id = rs.getInt(1);
        }

        return last_inserted_id;
    }

    public Integer removeVeiculo(String matricula) throws SQLException {
        //matricula varchar 10
        //marca varchar 45
        //modelo varchar 45
        //nChassis int
        //cinlindrada int
        //nPortas int
        //proprietario varchar 45

        conn.setCatalog(dbname);
        String mysqlQuery = "DELETE FROM Veiculo WHERE numeroCC='?'";

        PreparedStatement ps;
        ps = conn.prepareStatement(mysqlQuery);

        ps.setString(1, matricula);

        int result = ps.executeUpdate();

        System.out.println(result > 0 ? "Veículo removido com sucesso" : "Erro");

        return result;
    }

    public void CloseDBConnection() throws SQLException {
        conn.close();
    }
}
