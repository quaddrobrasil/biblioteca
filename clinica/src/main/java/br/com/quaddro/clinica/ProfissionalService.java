package br.com.quaddro.clinica;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.util.JSON;

//-- Esta classe irá gerenciar o CRUD
//-- Ins, Upd, Del, Get, All
public class ProfissionalService {

	//-------------------------------------------------------------------------
	private final MongoCollection<BasicDBObject> mongoCollection;
	//-------------------------------------------------------------------------
	public ProfissionalService(MongoDatabase mongoDatabase) {
		mongoCollection = 
		mongoDatabase.getCollection("profissionais", BasicDBObject.class);
	}
	//-------------------------------------------------------------------------
	//-- Retornar todos os profissionais (ALL)
	//-------------------------------------------------------------------------
	public List<Profissional> all() {
		
		List<Profissional> profissionais = new ArrayList<Profissional>();
		
		//-- Capturar os dados(registros) da collections profissionais
		MongoCursor<BasicDBObject> mongoCursor = mongoCollection.find().iterator();
		
		//-- Leitura até o final de todos os registros
		while(mongoCursor.hasNext()) {
			profissionais.add(new Profissional(mongoCursor.next()));
		}
		
		System.out.println("ativado ALL -> Coleção de Profissionais! " + new java.util.Date());
		
		return profissionais;
		
	}
	//-------------------------------------------------------------------------
	//-- Retornar somente um profissional via id (GET)
	//-------------------------------------------------------------------------
	public Profissional get(String _id) {
		
		Profissional profissional;
		
		Bson filtro = Filters.eq("_id", new ObjectId(_id));
		
		BasicDBObject basicDBObject = mongoCollection.find(filtro).first();
		
		profissional = new Profissional(basicDBObject);
		
		System.out.println("ativado GET -> Retornando um profissional! " + new java.util.Date());
		
		return profissional;
	}
	//-------------------------------------------------------------------------
	//-- Excluir o profissional do banco de dados (DEL)
	//-------------------------------------------------------------------------
	public void del(String _id) {
		
		Bson filtro = Filters.eq("_id", new ObjectId(_id));
		
		DeleteResult deleteResult  = mongoCollection.deleteOne(filtro);
		
		System.out.println("ativado DEL -> Excluindo um profissional! " + new java.util.Date());
		
	}
	//-------------------------------------------------------------------------
	//-- Atualizar os dados do profissional (UPD)
	//-------------------------------------------------------------------------
	public void upd(String json) {
		
		BasicDBObject de   = (BasicDBObject) JSON.parse(json);
		
		BasicDBObject para = new BasicDBObject();
		
		ObjectId objectId = new ObjectId(de.getString("_id"));
		
		para.put("_id"		, objectId);
		para.put("crm"		, de.getString("crm"));
		para.put("nome"		, de.getString("nome"));
		para.put("idade"	, de.getInt("idade"));
		para.put("profissao", de.getString("profissao"));
		
		Bson bson = new Document("$set", para);
		
		Bson filtro = Filters.eq("_id", objectId);
		
		BasicDBObject resultado = 
		mongoCollection.findOneAndUpdate(filtro, bson, 
		new FindOneAndUpdateOptions().upsert(true));
		
		System.out.println("ativado UPD -> Atualizando um profissional! " + new java.util.Date());
	}
	//-------------------------------------------------------------------------
	//-- Inserir um novo profissional
	//-------------------------------------------------------------------------
	public void ins(String json) {
		
		BasicDBObject basicDBObject = (BasicDBObject) JSON.parse(json);
		mongoCollection.insertOne(basicDBObject);
		
		System.out.println("ativado INS -> Profissional inserido! " + new java.util.Date());
	}
	//-------------------------------------------------------------------------
}
