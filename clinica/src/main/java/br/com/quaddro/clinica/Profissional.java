package br.com.quaddro.clinica;

import java.io.Serializable;

import com.mongodb.BasicDBObject;

public class Profissional implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String _id;
	public String crm;
	public String nome;
	public int    idade;
	public String profissao;
	
	public Profissional(BasicDBObject basicDBObject) {
		
		this._id       = basicDBObject.getObjectId("_id").toString();
		this.crm       = basicDBObject.getString("crm");
		this.nome      = basicDBObject.getString("nome");
		this.idade     = basicDBObject.getInt("idade");
		this.profissao = basicDBObject.getString("profissao");
		
	}
	
	public String toString() {
		
		return "_id:"        + _id      + 
			   " crm:"       + crm      + 
			   " nome:"      + nome     + 
			   " idade:"     + idade    + 
			   " profissão:" + profissao;
		
	}
	
}
