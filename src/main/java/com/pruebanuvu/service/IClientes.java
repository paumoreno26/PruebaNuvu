package com.pruebanuvu.service;

import java.io.IOException;
import java.util.List;

import com.pruebanuvu.model.DatosTC;
import com.pruebanuvu.model.InformacionClientes;


public interface IClientes {

	public String crearRegistro(DatosTC info) throws Exception;
	
	public List<DatosTC> consultarRegistro() throws IOException;

	public String actualizarRegistro(DatosTC info, String numcedula) throws IOException;
}
