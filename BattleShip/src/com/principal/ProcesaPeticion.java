package com.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.consume.servicio.Service;
import com.objetos.NodoJuego;
import com.objetos.NodoUsuario;

public class ProcesaPeticion {

	private Service servicio = new Service();

	public NodoUsuario validaUsuarioLogin(String usuario, String clave) {
		NodoUsuario response = null;
		try {
			List<String> name = new ArrayList<String>();
			List<String> value = new ArrayList<String>();

			name.add("usuario");
			value.add(usuario);
			name.add("clave");
			value.add(clave);

			Map<String, String> result = servicio.formRequest("login", name,
					value);

			if (result.size() > 0) {
				response = new NodoUsuario(result.get("usuario"), "",
						result.get("tipo"), 0, null);
			}
		} catch (Exception e) {
		}
		return response;
	}

	public boolean existe(String usuario) {
		boolean response = false;
		try {
			List<String> name = new ArrayList<String>();
			List<String> value = new ArrayList<String>();

			name.add("usuario");
			name.add("tipoBusqueda");
			value.add(usuario);
			value.add("usuario");

			Map<String, String> result = servicio.formRequest("existUser",
					name, value);
			if (result.size() > 0) {
				String temp = result.get("existe");
				if (temp.equalsIgnoreCase("true")) {
					return true;
				}
			}
		} catch (Exception e) {
		}
		return response;
	}

	public void insertarUser(String usuario, String clave, String tipo,
			int conectado) {
		try {
			List<String> name = new ArrayList<String>();
			List<String> value = new ArrayList<String>();

			name.add("usuario");
			value.add(usuario);
			name.add("clave");
			value.add(clave);
			name.add("tipo");
			value.add(tipo);
			name.add("conectado");
			value.add(Integer.toString(conectado));

			Map<String, String> result = servicio.formRequest("insertUser",
					name, value);

			if (result.size() > 0) {
				System.out.println("creado usuario: " + result.get("usuario")
						+ " tipo: " + result.get("tipo"));
			}
		} catch (Exception e) {
		}

	}

	public void eliminar(String usuario) {
		try {
			List<String> name = new ArrayList<String>();
			List<String> value = new ArrayList<String>();

			name.add("usuario");
			value.add(usuario);

			Map<String, String> result = servicio.formRequest("deleteUser",
					name, value);
			if (result.size() > 0) {
				System.out.println("usuario eliminado: "
						+ result.get("usuario"));
			}
		} catch (Exception e) {
		}
	}

	public void modificar(String usuario, String clave, String claveNueva) {
		try {
			List<String> name = new ArrayList<String>();
			List<String> value = new ArrayList<String>();

			name.add("usuario");
			value.add(usuario);
			name.add("clave");
			value.add(clave);
			name.add("claveNueva");
			value.add(claveNueva);

			Map<String, String> result = servicio.formRequest("modifyUser",
					name, value);

			if (result.size() > 0) {
				System.out.println("usuario modificado: "
						+ result.get("usuario") + " clave: "
						+ result.get("clave"));
			}
		} catch (Exception e) {
		}
	}

	public void agregarListaJuego(String usuarioBase, List<NodoJuego> listaJuego) {
		try {
			for (NodoJuego juego : listaJuego) {
				List<String> name = new ArrayList<String>();
				List<String> value = new ArrayList<String>();

				name.add("usuarioBase");
				value.add(juego.getUsuarioBase());
				name.add("usuarioOponente");
				value.add(juego.getUsuarioOponente());
				name.add("tRealizados");
				value.add(Integer.toString(juego.getTirosRealizados()));
				name.add("tAcertados");
				value.add(Integer.toString(juego.getTirosAcertados()));
				name.add("tFallados");
				value.add(Integer.toString(juego.getTirosFallados()));
				name.add("ganador");
				value.add(Integer.toString(juego.getGanador()));
				name.add("danioRecibido");
				value.add(Integer.toString(juego.getDanioRecibido()));

				Map<String, String> result = servicio.formRequest(
						"addGameUser", name, value);
				if (result.size() > 0) {
					System.out.println("juego agregado: user:"
							+ result.get("usuario") + ","
							+ result.get("usuarioOp"));
				}
			}
		} catch (Exception e) {
		}
	}

	public List<String> listaUsuarios = new ArrayList<String>();

	public List<String> llenarCombo(String usuarioSession) {
		try {
			listaUsuarios = new ArrayList<String>();

			List<String> name = new ArrayList<String>();
			List<String> value = new ArrayList<String>();

			name.add("usuario");
			value.add(usuarioSession);

			Map<String, String> result = servicio.formRequest("getListUser",
					name, value);

			if (result.size() > 0) {
				String usuarios = result.get("usuario");
				System.out.println("usuario: " + usuarios);

				String[] separaUsuario = usuarios.split(",");

				if (separaUsuario != null && separaUsuario.length > 0) {
					for (String user : separaUsuario) {
						if (!user.trim().equals("") && !listaUsuarios.contains(user)) {
							listaUsuarios.add(user);	
						}						
					}
				}
			}
		} catch (Exception e) {
		}
		return listaUsuarios;
	}

	public void imprimirGrafo() {
		// TODO Auto-generated method stub

	}

	public void generarGrafo() {
		// TODO Auto-generated method stub

	}
}
