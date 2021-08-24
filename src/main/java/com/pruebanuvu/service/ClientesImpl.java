package com.pruebanuvu.service;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.pruebanuvu.model.DatosTC;
import com.pruebanuvu.model.InformacionClientes;

@Service
public class ClientesImpl implements IClientes {

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public String crearRegistro(DatosTC info) throws Exception {
		InformacionClientes clientes = new InformacionClientes();
		List<DatosTC> informacion = new ArrayList<DatosTC>();
		clientes = readFile();
		for (DatosTC datos : clientes.getClientes()) {
			informacion.add(datos);
		}
		informacion.add(info);
		clientes.setClientes(informacion);
		writeFile(clientes);
		return "El registro se creo correctamente";
	}

	@Override
	public List<DatosTC> consultarRegistro() throws IOException {
		InformacionClientes clientes = new InformacionClientes();
		List<DatosTC> informacion = new ArrayList<DatosTC>();
		clientes = readFile();
		for (DatosTC datos : clientes.getClientes()) {
			informacion.add(datos);
		}
		return informacion;
	}

	@Override
	public String actualizarRegistro(DatosTC info, String numcedula) throws IOException {
		InformacionClientes clientes = new InformacionClientes();
		List<DatosTC> informacion = new ArrayList<DatosTC>();
		clientes = readFile();
		for (DatosTC datos : clientes.getClientes()) {
			if (!datos.getNumeroCedula().equals(numcedula)) {
				informacion.add(datos);
			}
		}
		informacion.add(info);
		clientes.setClientes(informacion);
		writeFile(clientes);
		return "El registro se actualizo correctamente";
	}

	public InformacionClientes readFile() throws IOException {
		InformacionClientes clientes = new InformacionClientes();
		InputStream inputstream;
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			inputstream = new FileInputStream("clientes.json");
			clientes = mapper.readValue(inputstream, InformacionClientes.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	public void writeFile(InformacionClientes clientes) throws IOException {
		Gson gson = new Gson();
		String json = gson.toJson(clientes);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("clientes.json"))) {
		    bw.write(json);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
