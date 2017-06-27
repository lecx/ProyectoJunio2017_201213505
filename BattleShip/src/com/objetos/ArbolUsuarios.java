package com.objetos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ArbolUsuarios {
	public NodoUsuario raiz = null;

	/*** insercion de nodo ***/
	public void insertarNodo(String nombre, String pass, String tipo,
			int conectado, ListaJuego listaJuegos) throws Exception {
		NodoUsuario user = new NodoUsuario(nombre, pass, tipo, conectado,
				listaJuegos);
		raiz = insertar(raiz, user);

	}

	protected NodoUsuario insertar(NodoUsuario raizSub, NodoUsuario dato)
			throws Exception {

		if (raizSub == null) {
			raizSub = new NodoUsuario(dato.getNombre(), dato.getClave(),
					dato.getTipo(), 0, new ListaJuego());
		} else if (dato.getNombre().compareTo(raizSub.getNombre()) < 0) {
			NodoUsuario iz;
			iz = insertar(raizSub.getIzq(), dato);
			raizSub.setIzq(iz);
		} else if (dato.getNombre().compareTo(raizSub.getNombre()) > 0) {
			NodoUsuario dr;
			dr = insertar(raizSub.getDer(), dato);
			raizSub.setDer(dr);
		} else
			throw new Exception("Nodo duplicado");
		return raizSub;
	}

	/*** busqueda nodo ***/
	public boolean existe(String usuario) {

		Object resultado = buscar(usuario, "usuario");
		if (resultado != null) {
			return true;
		}
		return false;
	}

	public Object buscar(Object buscado, String tipoBusqueda) {

		Object response = null;
		if (tipoBusqueda.equalsIgnoreCase("nodo")) {

			NodoUsuario dato;
			dato = (NodoUsuario) buscado;
			if (raiz == null)
				response = null;
			else
				response = localizarPorNodo(raiz, dato);

		} else if (tipoBusqueda.equalsIgnoreCase("usuario")) {
			if (raiz == null) {
				response = null;
			} else {
				String usuarioBuscado = buscado.toString();
				response = localizarPorUsuario(raiz, usuarioBuscado);
			}
		}
		return response;
	}

	protected NodoUsuario localizarPorNodo(NodoUsuario raiz, NodoUsuario buscado) {
		if (raiz == null)
			return null;
		else if (buscado.getNombre().compareTo(raiz.getNombre()) == 0)
			return raiz;
		else if (buscado.getNombre().compareTo(raiz.getNombre()) < 0)
			return localizarPorNodo(raiz.getIzq(), buscado);
		else
			return localizarPorNodo(raiz.getDer(), buscado);
	}

	protected NodoUsuario localizarPorUsuario(NodoUsuario raiz, String usuario) {
		if (raiz == null)
			return null;
		else if (usuario.compareTo(raiz.getNombre()) == 0)
			return raiz;
		else if (usuario.compareTo(raiz.getNombre()) < 0)
			return localizarPorUsuario(raiz.getIzq(), usuario);
		else
			return localizarPorUsuario(raiz.getDer(), usuario);
	}

	protected NodoUsuario validarUsuarioLogin(NodoUsuario raiz, String usuario,
			String clave) {
		if (raiz == null)
			return null;
		else if (usuario.compareTo(raiz.getNombre()) == 0
				&& clave.compareTo(raiz.getClave()) == 0)
			return raiz;
		else if (usuario.compareTo(raiz.getNombre()) < 0)
			return validarUsuarioLogin(raiz.getIzq(), usuario, clave);
		else
			return validarUsuarioLogin(raiz.getDer(), usuario, clave);
	}

	protected NodoUsuario modificarUsuario(NodoUsuario raiz, String usuario,
			String clave, String claveNueva) {

		if (raiz == null) {
			return null;
		} else if (usuario.compareTo(raiz.getNombre()) == 0
				&& clave.compareTo(raiz.getClave()) == 0) {
			raiz.setClave(claveNueva);
			return raiz;
		} else if (usuario.compareTo(raiz.getNombre()) < 0) {
			return modificarUsuario(raiz.getIzq(), usuario, clave, claveNueva);
		} else {
			return modificarUsuario(raiz.getDer(), usuario, clave, claveNueva);
		}
	}

	protected NodoUsuario agregarListaJuego(NodoUsuario raiz, String usuario,
			List<NodoJuego> listaJuego) {
		if (raiz == null)
			return null;
		else if (usuario.compareTo(raiz.getNombre()) == 0) {
			for (NodoJuego nodoJuego : listaJuego) {
				raiz.getListaJuego().agregarFin(nodoJuego.getUsuarioBase(),
						nodoJuego.getUsuarioOponente(),
						nodoJuego.getTirosRealizados(),
						nodoJuego.getTirosAcertados(),
						nodoJuego.getTirosFallados(), nodoJuego.getGanador(),
						nodoJuego.getDanioRecibido());
			}
			return raiz;
		} else if (usuario.compareTo(raiz.getNombre()) < 0)
			return agregarListaJuego(raiz.getIzq(), usuario, listaJuego);
		else
			return agregarListaJuego(raiz.getDer(), usuario, listaJuego);
	}

	public List<String> listaUsuarios = new ArrayList<String>();

	/*** Recorrido de un árbol binario en postorden ***/
	public void postorden(NodoUsuario r) {
		if (r != null) {
			postorden(r.getIzq());
			postorden(r.getDer());
			listaUsuarios = agregar(r.getNombre());
		}
	}

	private List<String> agregar(String usuario) {
		if (!listaUsuarios.contains(usuario)) {
			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}

	/*** valida usuario ***/
	public NodoUsuario validaUsuarioLogin(String usuario, String clave) {
		Object resultado = validarUsuarioLogin(raiz, usuario, clave);

		if (resultado != null) {
			NodoUsuario response = (NodoUsuario) resultado;
			return response;
		} else {
			return null;
		}
	}

	/*** elimina usuario ***/
	public void eliminar(String usuario) throws Exception {

		NodoUsuario dato;
		dato = localizarPorUsuario(raiz, usuario);
		raiz = eliminar(raiz, dato);
	}

	// método interno para realizar la operación
	protected NodoUsuario eliminar(NodoUsuario raiz, NodoUsuario elimina)
			throws Exception {
		if (raiz == null)
			throw new Exception("No encontrado el nodo con la clave");
		else if (elimina.getNombre().compareTo(raiz.getNombre()) < 0) {
			NodoUsuario iz;
			iz = eliminar(raiz.getIzq(), elimina);
			raiz.setIzq(iz);
		} else if (elimina.getNombre().compareTo(raiz.getNombre()) > 0) {
			NodoUsuario dr;
			dr = eliminar(raiz.getDer(), elimina);
			raiz.setDer(dr);
		} else {// Nodo encontrado
			NodoUsuario q;
			q = raiz; // nodo a quitar del árbol
			if (q.getIzq() == null)
				raiz = q.getDer();
			else if (q.getDer() == null)
				raiz = q.getIzq();
			else { // tiene rama izquierda y derecha
				q = reemplazar(q);
			}
			q = null;
		}
		return raiz;
	}

	// método interno para susutituir por el mayor de los menores
	private NodoUsuario reemplazar(NodoUsuario actual) {
		NodoUsuario a, p;
		p = actual;
		a = actual.getIzq(); // rama de nodos menores
		while (a.getDer() != null) {
			p = a;
			a = a.getDer();
		}

		actual.setNombre(a.getNombre());
		actual.setClave(a.getClave());
		actual.setConectado(a.getConectado());
		actual.setListaJuego(a.getListaJuego());
		actual.setIzq(a.getIzq());
		actual.setDer(a.getDer());
		actual.setTipo(a.getTipo());
		actual.setNombreNodo(a.getNombreNodo());
		actual.i = a.i;

		if (p == actual)
			p.setIzq(a.getIzq());
		else
			p.setDer(a.getIzq());
		return a;
	}

	/*** modificar usuario ***/
	public boolean modificar(String usuario, String clave, String claveNueva) {

		System.out.println("valores recibidos: User metodo modifica:" + usuario
				+ " clave:" + clave + " nuevaClave :" + claveNueva);
		NodoUsuario modifica = modificarUsuario(raiz, usuario, clave,
				claveNueva);
		if (modifica != null) {
			return true;
		}
		return false;
	}

	public void inOrden(NodoUsuario raiz) {
		if (raiz != null) {
			inOrden(raiz.getIzq());
			System.out.println("usuario: " + raiz.getNombre());
			inOrden(raiz.getDer());
		}
	}

	private void imprimirUs(NodoUsuario reco, PrintWriter p) {
		if (reco != null) {
			p.println("     struct" + reco.getNombreNodo()
					+ "[ label=\"<ptr_izq> | { <clave>" + reco.getNombre()
					+ "|" + reco.getClave() + "|" + reco.getTipo()
					+ "} | <ptr_der> \"];");
			imprimirUs(reco.getIzq(), p);
			imprimirUs(reco.getDer(), p);
		}
	}

	private void imprimirCon(NodoUsuario reco, PrintWriter p) {
		if (reco != null) {
			if (reco.getIzq() != null) {
				p.println("     struct" + reco.getNombreNodo() + ":ptr_izq"
						+ " -> struct" + reco.getIzq().getNombreNodo()
						+ ":clave;");
			}
			if (reco.getDer() != null) {
				p.println("     struct" + reco.getNombreNodo() + ":ptr_der"
						+ " -> struct" + reco.getDer().getNombreNodo()
						+ ":clave;");
			}
			imprimirCon(reco.getIzq(), p);
			imprimirCon(reco.getDer(), p);
		}
	}

	/*** Generar e imprimir grafo ***/
	public void imprimirGrafo() {
		// System.out.println("inicio imprimir grafo.");
		String codigot = "C:\\temp\\arbolUsuarios.txt";
		try {
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(codigot));
			bw1.write("");
			bw1.close();

			FileWriter fw = new FileWriter(codigot, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("digraph g{ ");
			salida.println("");
			salida.println("color = grey;");
			salida.println("style=filled;");
			salida.println("label=\"Arbol\";");
			salida.println("node[shape = record];");
			salida.println("paqueteInfo[shape=polygon,sides=4,peripheries=3,color=red,style=filled,label = \"Arbol Usuarios\"];");
			salida.println("subgraph clusterArbol{");
			salida.println("color = lightgrey;");
			imprimirUs(raiz, salida);
			imprimirCon(raiz, salida);
			salida.println("    }");
			salida.println("}");
			salida.close();
			// System.out.println("fin imprime grafo.");
		} catch (Exception e) {
		}
	}

	public void generarGrafo() {
		try {
			// System.out.println("inicio generar grafo.");
			String dotPath = "C:\\Program Files (x86)\\graphviz-2.38\\release\\bin\\dot.exe";

			String fileInputPath = "C:\\temp\\arbolUsuarios.txt";
			String fileOutputPath = "C:\\temp\\ArbolDeUsuarios.jpg";

			String tParam = "-Tjpg";
			String tOParam = "-o";

			String[] cmd = new String[5];

			cmd[0] = dotPath;
			cmd[1] = tParam;
			cmd[2] = fileInputPath;
			cmd[3] = tOParam;
			cmd[4] = fileOutputPath;

			Runtime rt = Runtime.getRuntime();
			rt.exec(cmd);

			// System.out.println("fin genera grafo.");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}

	/*** llenar lista usuarios combobox ***/
	public List<String> llenarCombo() {
		postorden(raiz);
		return listaUsuarios;
	}

	/*** agregar lista juegos ***/
	public void agregarListaJuego(String usuarioBase, List<NodoJuego> listaJuego) {
		
	}
}
