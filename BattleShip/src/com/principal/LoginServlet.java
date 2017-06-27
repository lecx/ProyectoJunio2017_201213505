package com.principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carga.archivo.CSVReader;
import com.objetos.NodoJuego;
import com.objetos.NodoUsuario;
import com.utils.GuardarXML;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//public static ArbolUsuarios users = new ArbolUsuarios();
	GuardarXML save = new GuardarXML();
	private String tipoUsuario = "admin";
	private String usuarioSession = "administrador";
	private ProcesaPeticion procesos = new ProcesaPeticion();

	public LoginServlet() {
		procesos.imprimirGrafo();
		procesos.generarGrafo();
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		procesos = new ProcesaPeticion();
		
		if (request.getParameter("action").equalsIgnoreCase("login")) {
			ejecutarAccion(1, request, response);
		} else if (request.getParameter("action").equalsIgnoreCase(
				"agregarUsuario")) {
			ejecutarAccion(2, request, response);
		} else if (request.getParameter("action")
				.equalsIgnoreCase("cargaDatos")) {
			ejecutarAccion(3, request, response);
		} else if (request.getParameter("action").equalsIgnoreCase(
				"eliminarUsuario")) {
			ejecutarAccion(4, request, response);
		} else if (request.getParameter("action").equalsIgnoreCase(
				"modificarUsuario")) {
			ejecutarAccion(5, request, response);
		}
		cbxUsuarios(request);
	}

	private void ejecutarAccion(int operacion, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			switch (operacion) {
			case 1:
				try {
					String usuario = request.getParameter("txtUserName");
					String pass = request.getParameter("txtPass");
					if (!validaUsuarioLogin(usuario, pass)) {
						response.sendRedirect("login.jsp");
					} else {
						String page = "";
						HttpSession session = request.getSession();
						session.setAttribute("logon.isDone", usuario);
						session.setAttribute("resultado", "");
						session.setAttribute("resultadoE", "");
						session.setAttribute("resultadoMod", "");
						session.setAttribute("resultadoCarga", "");

						if (tipoUsuario.equalsIgnoreCase("admin")) {
							page = "MenuAdmin.jsp";
							response.sendRedirect("menuAdmin.jsp");
						} else if (tipoUsuario.equalsIgnoreCase("usuario")) {
							page = "MenuUser.jsp";
							response.sendRedirect("menuUsuario.jsp");
						} else {
							response.sendRedirect("login.jsp");
						}
						session.setAttribute("pagina", page);
						session.setAttribute("tipousuario", tipoUsuario);
					}
				} catch (Exception e) {
				}
				break;
			case 2:
				try {
					String message = "";
					System.out.println("inicio crear usuario.");

					String usuario = request.getParameter("txtNombre");
					String pass = request.getParameter("txtPass");

					if (usuario != null && !usuario.equals("") && pass != null
							&& !pass.equals("")) {
						if (!procesos.existe(usuario)) {
							crearUsuario(usuario, pass, "usuario", 0);
							message = "Usuario creado con Exito.";
						} else {
							message = "Usuario ya existe.";
						}
					} else {
						message = "Datos invalidos.";
					}

					request.getSession().setAttribute("resultado", message);
					response.sendRedirect(request.getContextPath()
							+ "/funcionesAdmin/GestionUsuarios.jsp");

					System.out.println("fin crear usuario.");
				} catch (Exception e) {
				}
				break;
			case 3:
				try {
					String message = "";
					String pathFile = request.getParameter("txtPath");
					String tipoCarga = request.getParameter("txtTipoCarga");
					if (tipoCarga != null && !tipoCarga.equals("")) {
						CSVReader carga = new CSVReader();
						if (tipoCarga.equalsIgnoreCase("cargaUsuario")) {
							List<NodoUsuario> listaCarga = carga
									.cargaUsuarios(pathFile);
							int cont = 0;
							for (NodoUsuario temp : listaCarga) {
								if (!procesos.existe(temp.getNombre())) {
									crearUsuario(temp.getNombre(),
											temp.getClave(), temp.getTipo(),
											temp.getConectado());
									cont++;
								}
							}
							System.out.println("cantidad usuarios creados: "
									+ cont);

							message = "Usuarios cargados con exito.";
						} else if (tipoCarga.equalsIgnoreCase("juegos")) {
							Map<String, List<NodoJuego>> listaJuego = carga
									.cargaJuegos(pathFile);
							int cont = 0;
							for (Entry<String, List<NodoJuego>> temp : listaJuego
									.entrySet()) {

								String usuarioBase = temp.getKey();
								List<NodoJuego> listaTemp = temp.getValue();
								if (procesos.existe(usuarioBase)) {
									agregarJuegosUsuario(usuarioBase, listaTemp);
									cont++;
								}
							}
							System.out.println("Se agregaron juegos para "
									+ cont + " usuarios.");

							message = "Juegos agregados a usuarios.";
						} else if (tipoCarga.equalsIgnoreCase("naves")) {
							message = "Naves cargadas.";
						} else if (tipoCarga.equalsIgnoreCase("juegoActual")) {
							message = "Juegos actual cargado.";
						}
					} else {
						message = "Seleccione tipo de carga.";
					}
					request.getSession()
							.setAttribute("resultadoCarga", message);
					response.sendRedirect(request.getContextPath()
							+ "/funcionesAdmin/CargarDatos.jsp");

					System.out.println("fin carga datos.");
				} catch (Exception e) {
				}
				break;
			case 4:
				try {
					String message = "";
					System.out.println("inicio eliminar usuario.");

					String usuario = request.getParameter("txtNombreE");
					System.out.println("uauario a eliminiar: " + usuario);
					if (usuario != null && !usuario.equals("")) {

						if (procesos.existe(usuario)) {
							eliminarUsuario(usuario);
							message = "Usuario eliminado con Exito.";
						} else {
							message = "Usuario no existe.";
						}
					} else {
						message = "Datos invalidos.";
					}

					request.getSession().setAttribute("resultadoE", message);
					response.sendRedirect(request.getContextPath()
							+ "/funcionesAdmin/GestionUsuarios.jsp");

					System.out.println("fin eliminar usuario.");

				} catch (Exception e) {
				}
				break;
			case 5:
				try {
					String message = "";
					System.out.println("inicio modificar usuario.");

					String usuario = request.getParameter("txtNombreMod");
					String clave = request.getParameter("txtClaveMod");
					String claveNueva = request.getParameter("txtPassMod");
					String confirmaClaveNueva = request
							.getParameter("txtConfPassMod");
					if ((claveNueva != null && !claveNueva.equals(""))
							&& (confirmaClaveNueva != null && !confirmaClaveNueva
									.equals(""))) {
						if (claveNueva.equals(confirmaClaveNueva)) {
							NodoUsuario existe = procesos.validaUsuarioLogin(
									usuario, clave);
							if (existe != null) {
								modificarUsuario(usuario, clave, claveNueva);
								message = "Usuario modificado con Exito.";
							} else {
								message = "Usuario no encontrado.";
							}
						} else {
							message = "contraseñas no coinciden.";
						}
					} else {
						message = "Usuario no encontrado.";
					}
					request.getSession().setAttribute("resultadoMod", message);
					response.sendRedirect(request.getContextPath()
							+ "/funcionesAdmin/GestionUsuarios.jsp");

					System.out.println("fin modificar usuario.");
				} catch (Exception e) {
				}
				break;
			}
		} catch (Exception e) {
		}
	}

	private void crearUsuario(String nombre, String pass, String tipo,
			int conectado) {
		try {
			procesos.insertarUser(nombre, pass, tipo, conectado);
		} catch (Exception e) {
		}
		procesos.imprimirGrafo();
		procesos.generarGrafo();
	}

	private void eliminarUsuario(String usuario) {
		try {
			procesos.eliminar(usuario);
		} catch (Exception e) {
		}
		procesos.imprimirGrafo();
		procesos.generarGrafo();
	}

	private void modificarUsuario(String usuario, String clave,
			String claveNueva) {
		try {
			procesos.modificar(usuario, clave, claveNueva);
		} catch (Exception e) {

		}
		procesos.imprimirGrafo();
		procesos.generarGrafo();
	}

	private void agregarJuegosUsuario(String usuarioBase,
			List<NodoJuego> listaJuego) {
		try {
			procesos.agregarListaJuego(usuarioBase, listaJuego);
		} catch (Exception e) {
		}
		procesos.imprimirGrafo();
		procesos.generarGrafo();
	}

	public List<String> cbxUsuarios(HttpServletRequest request) {
		List<String> response = new ArrayList<String>();
		try {
			HttpSession session = request.getSession();
			procesos.listaUsuarios = new ArrayList<String>();
			response = procesos.llenarCombo(usuarioSession);
			// for (String string : response) {
			// System.out.println("usuario: " + string);
			// }
			session.setAttribute("cbxUsuarios", response);
		} catch (Exception e) {
			System.err.println("no cargo usuarios.");
		}
		return response;
	}

	protected boolean validaUsuarioLogin(String usuario, String clave) {

		NodoUsuario resultado = procesos.validaUsuarioLogin(usuario, clave);
		if (resultado != null) {
			tipoUsuario = resultado.getTipo();
			usuarioSession = resultado.getNombre();
			return true;
		}
		return false;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getUsuarioAdmin() {
		return usuarioSession;
	}

	public void setUsuarioAdmin(String usuarioAdmin) {
		this.usuarioSession = usuarioAdmin;
	}

}