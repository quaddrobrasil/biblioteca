package br.com.quaddro.clinica;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class DatabaseMongoDB {

	private MongoDatabase mongoDatabase;
	private MongoClient   mongoClient;
	//------------------------------------------------
	public MongoDatabase mongoDBOn() {
		
		try {
		
			String URL = "mongodb://usuario:usu2018@ds161960.mlab.com:61960/warehouse2018";
			String BDM = "warehouse2018";
			
			//-- Elaborado o local onde está o banco de dados
			MongoClientURI mongoClientURI = new MongoClientURI(URL,
					MongoClientOptions.builder().cursorFinalizerEnabled(false));
			
			//-- Ativando o Client do MongoDB
			mongoClient = new MongoClient(mongoClientURI);
			
			//-- Abrindo o banco de dados via URI
			mongoDatabase = mongoClient.getDatabase(BDM);
			
			System.out.println(BDM + ", foi ativado com sucesso!");
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return mongoDatabase;
	}
	//------------------------------------------------
	public void mongoDBOff() {
		mongoClient.close();
	}
	//------------------------------------------------
}
