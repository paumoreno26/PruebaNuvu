package com.pruebanuvu.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pruebanuvu.model.DatosTC;
import com.pruebanuvu.model.InformacionClientes;
import com.pruebanuvu.service.IClientes;

@RestController
@RequestMapping("clientes")
public class ClientesController {

	@Autowired
	IClientes clientes;
	
	@PostMapping("/crear")
	public String crear(@RequestBody DatosTC info) throws Exception {
		return clientes.crearRegistro(info);
	}
	
	@GetMapping("/consultar")
	public List<DatosTC> consultar() throws IOException{
		return clientes.consultarRegistro();
	}
	
	@PutMapping("/actualizar")
	public String actualizar(@RequestBody DatosTC info, @RequestParam("numeroCedula") String numeroCedula) throws Exception {
		return clientes.actualizarRegistro(info,numeroCedula);
	}
}
