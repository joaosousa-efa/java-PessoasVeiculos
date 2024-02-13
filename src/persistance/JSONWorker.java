package persistance;

import business.Pessoa;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;

/**
 *
 * @author João Sousa
 *
 */
public class JSONWorker {

    private final String dirName = System.getProperty("user.home").toString() + "/" + "dataHelloWorldJava";
    private final String fileName = dirName + "/" + "data.json";

    public JSONWorker() {

        // Cria uma pasta caso não exista
        Path dirPath = Paths.get(dirName);
        if (!Files.exists(dirPath)) {
            try {
                Files.createDirectory(dirPath);
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    public void salvar(Hashtable<String, Pessoa> obj) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(obj);

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(json);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(json);
    }

    public Hashtable<String, Pessoa> carregar() throws IOException, ClassNotFoundException {

        Gson gson = new Gson();
        Hashtable<String, Pessoa> ht = new Hashtable<String, Pessoa>();
        try {
            //Converte String JSON para objeto Java
            
            // Lendo o conteúdo do arquivo JSON
            FileReader reader = new FileReader(fileName);
            BufferedReader in = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
            in.close();

            // Convertendo a string JSON em Hashtable
            Type type = new TypeToken<Hashtable<String, Pessoa>>(){}.getType();
            ht = gson.fromJson(sb.toString(), type);

            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ht;

    }
}

// Pagina do projecto Gson
// https://google.github.io/gson/
// Pagina download do jar file 
//https://search.maven.org/artifact/com.google.code.gson/gson/2.10.1/jar?eh=
// instalar no netbeans -> clicke direito em cima de Libraries -> Add JAR/Folder -> selecionar o jar file e marcar "Copy Libraries to folder"