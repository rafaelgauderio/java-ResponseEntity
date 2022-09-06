package com.br.delucahigiene.Classe.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "produtos")
public class ResourceProduct {



	@GetMapping
	public ResponseEntity<String> produtos( ) {
		//http://localhost:8080/produtos		
		return 	ResponseEntity.ok("Success. Código 200");	

	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> responder(@PathVariable("id") Long id) throws URISyntaxException {
		if (id==1) {	
			//http://localhost:8080/produtos/1
			Produto prod = new Produto();
			prod.setId(2L);
			prod.setNome("Notebook Lenovo");
			prod.setPreco(3500.80);			
			return ResponseEntity.ok().body(prod);
		}

		else if (id==2) {
			//http://localhost:8080/produtos/2
			ResponseEntity<Produto> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST); //codigo 400
			return response;		
		}
		else if(id==3) {
			//http://localhost:8080/produtos/3
			return ResponseEntity.badRequest().build();
		}

		else if(id==4) {
			return ResponseEntity.accepted().build();// accepted 202
		}

		else if(id==5) {
			//http://localhost:8080/produtos/4			
			Produto prod = new Produto();
			prod.setId(1L);
			prod.setNome("Celular Sansung");
			prod.setPreco(2500.99);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(prod.getId()).toUri();
			return ResponseEntity.created(uri).body(prod); //created codigo 201

		} else if (id ==6) {
			return 	new ResponseEntity<>(HttpStatus.BAD_GATEWAY); // codigo 502

		} else if (id ==7) {
			return 	new ResponseEntity<>(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED); // codigo 509

		} else if (id==8) {
			Produto produto = new Produto(3L,"Chuteira",450.50);
			HttpHeaders cabecalho = new HttpHeaders();
			cabecalho.add("custom-header", "header personalizado");			
			return new ResponseEntity<>(produto,cabecalho,HttpStatus.ALREADY_REPORTED); //codigo 208

		}

		else {
			Produto produto = new Produto();	
			produto.setId(2L);
			produto.setNome("Bicicleta");
			produto.setPreco(1500.99);		

			HttpHeaders cabecalho = new HttpHeaders();
			cabecalho.add("header-padrao", "header criacao produto");	

			return 	ResponseEntity
					.status(HttpStatus.CREATED)
					.headers(cabecalho)
					.body(produto);
		}

	}


}
