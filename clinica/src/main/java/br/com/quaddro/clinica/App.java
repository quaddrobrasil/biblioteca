package br.com.quaddro.clinica;

import static spark.Spark.get;
import static spark.Spark.port;

import com.mongodb.client.MongoDatabase;

public class App {
	
    public static void main(String[] args) {
        
    	//-- Ativando a porta para o funcionamento do RestFul (sparkJava) no Heroku
    	port(getHerokuPorta());
    	
    	//-- Ativando o Banco de Dados
    	DatabaseMongoDB databaseMongoDB = new DatabaseMongoDB();
    	MongoDatabase mongoDatabase = databaseMongoDB.mongoDBOn();
    	
    	//-- Ativando os servicos de CRUD e VERBOS para o objeto Profissional
        ProfissionalService profissionalService = new ProfissionalService(mongoDatabase);
        new ProfissionalResource(profissionalService);
        
        //-- Capturando a raiz do webservice
    	get("/", (req,res) -> welcome());
        
    }
    //-------------------------------------------------
    public static int getHerokuPorta() {
    	
    	ProcessBuilder processBuilder = new ProcessBuilder();
    	
    	if(processBuilder.environment().get("PORT") != null) {
    		return Integer.parseInt(processBuilder.environment().get("PORT"));
    	}
    	
    	return 4567;
    }
    //-------------------------------------------------
 	public static String welcome() {
  		
  		StringBuffer sb = new StringBuffer();
  		sb.append("<html><body><center>");
  		sb.append("<br><br><br>");
  		
  		sb.append("<hr>");
  		sb.append("<br><br>");
  		
  		sb.append("<font face=verdana size=5 color=#FF5722>");
  		sb.append("RESTful Web Service: Java + Spark + Mongo");
  		sb.append("<br><br>");
  		sb.append("+-+-+-+-+-+ API Clinica Digital™ +-+-+-+-+-+ ");
  		sb.append("</b></font>");
  		
  		sb.append("<br><br>");
  		
  		sb.append("<font face=verdana size=3>");
  		sb.append(new java.util.Date().toString());
  		sb.append("</b></font>");
  		
  		sb.append("<br><br><hr><br><br>");
  		
  		sb.append("<font face=verdana size=5>");
  		sb.append("<b>Comandos:</b>");
  		sb.append("<br><br>");
  		
  		sb.append("<font face=verdana size=2>");
  		sb.append("/profissionais -> Listagem de todos os profissionais da clínica.");
  		sb.append("<br><br>");
  		sb.append("<font face=verdana size=2>");
  		sb.append("/profissionais/id -> Apresentação de um profissional da clínica.");
  		sb.append("<br><br>");
  		
  		sb.append("<hr>");
  		
  		sb.append("</center></body></html>");
  				
  		return sb.toString();		
  	}
    //-------------------------------------------------
    
}
