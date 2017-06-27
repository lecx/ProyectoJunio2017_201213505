package com.carga.archivo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.objetos.ListaJuego;
import com.objetos.NodoJuego;
import com.objetos.NodoUsuario;

public class CSVReader {

	public static void main(String[] args) {
		String csvFile = "C:\\temp\\test1.csv";
		CSVReader temp = new CSVReader();
		for (NodoUsuario nodo : temp.cargaUsuarios(csvFile)) {
			System.out.println(nodo.getNombre() + "-->" + nodo.getClave()
					+ "-->" + nodo.getConectado());
		}
	}

	public List<NodoUsuario> cargaUsuarios(String pathFile) {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<NodoUsuario> resultado = new ArrayList<NodoUsuario>();

		try {
			br = new BufferedReader(new FileReader(pathFile));
			int pos = 0;
			while ((line = br.readLine()) != null) {

				if (pos == 0) {
					System.out.println("linea:" + line);
				} else {
					String[] usuario = line.split(cvsSplitBy);

					if (usuario[0] != null && !usuario[0].equals("")) {
						if (usuario[1] != null && !usuario[1].equals("")) {
							int conectado = 0;
							if (usuario[2] != null && !usuario[2].equals("")) {
								conectado = Integer.parseInt(usuario[2]);
							}
							resultado.add(new NodoUsuario(usuario[0],
									usuario[1], "usuario", conectado,
									new ListaJuego()));
						}
					}
				}
				pos++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("archivo invalido.");
		} catch (IOException e) {
			System.out.println("no se cargo archivo.");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return resultado;
	}

	public Map<String, List<NodoJuego>> cargaJuegos(String pathFile) {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		Map<String, List<NodoJuego>> resultado = new HashMap<String, List<NodoJuego>>();
		try {
			br = new BufferedReader(new FileReader(pathFile));
			int pos = 0;
			while ((line = br.readLine()) != null) {

				if (pos == 0) {
					System.out.println("linea:" + line);
				} else {
					String[] juego = line.split(cvsSplitBy);

					if (juego[0] != null && !juego[0].equals("")) {
						if (juego[1] != null && !juego[1].equals("")) {
							int tRealizados = 0;
							if (juego[2] != null && !juego[2].equals("")) {
								tRealizados = Integer.parseInt(juego[2]);
							}
							int tAcertados = 0;
							if (juego[3] != null && !juego[3].equals("")) {
								tAcertados = Integer.parseInt(juego[3]);
							}
							int tFallados = 0;
							if (juego[4] != null && !juego[4].equals("")) {
								tFallados = Integer.parseInt(juego[4]);
							}
							int ganador = 0;
							if (juego[5] != null && !juego[5].equals("")) {
								ganador = Integer.parseInt(juego[5]);
							}
							int danioRecibido = 0;
							if (juego[6] != null && !juego[6].equals("")) {
								danioRecibido = Integer.parseInt(juego[6]);
							}
							if (resultado.containsKey(juego[0])) {
								List<NodoJuego> listaTemp = resultado
										.get(juego[0]);

								NodoJuego nodoTemp = new NodoJuego(juego[1],
										tRealizados, tAcertados, tFallados,
										ganador, danioRecibido);
								listaTemp.add(nodoTemp);

								resultado.put(juego[0], listaTemp);
							} else {
								List<NodoJuego> listaTemp = new ArrayList<NodoJuego>();

								NodoJuego nodoTemp = new NodoJuego(juego[0],
										juego[1], tRealizados, tAcertados,
										tFallados, ganador, danioRecibido);
								listaTemp.add(nodoTemp);

								resultado.put(juego[0], listaTemp);
							}

						}
					}
				}
				pos++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("archivo invalido.");
		} catch (IOException e) {
			System.out.println("no se cargo archivo.");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return resultado;
	}
}