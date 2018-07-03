package br.com.quaddro.clinica;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;

public class ProfissionalResource {

	private ProfissionalService profissionalService;
	
	public ProfissionalResource(ProfissionalService profissionalService) {
		this.profissionalService = profissionalService;
		verbos();
	}
	
	public void verbos() {

		//-- Retornar um profissional
		get("/profissionais/:id", "application/json", (request, response) -> 
			
			profissionalService.get(request.params(":id")), new ConversorJSON()
		
		);
		//-- Retornar todos os profissionais
		get("/profissionais", "application/json", (request, response) -> 
		
			profissionalService.all(), new ConversorJSON()
	
		);
		//-- Inserir um novo profissional
		post("/profissionais", "application/json", (request, response) -> {
		
			profissionalService.ins(request.body());
			response.status(201);
			return response;

		});
		//-- Atualizar os dados de um profissional
		put("/profissionais", "application/json", (request, response) -> {
			
			profissionalService.upd(request.body());
			response.status(202);
			return response;

		});
		//-- Excluir os dados de um profissional
		delete("/profissionais/:id", "application/json", (request, response) -> {
			
			profissionalService.del(request.params(":id"));
			response.status(200);
			return response;

		});
	}
}





