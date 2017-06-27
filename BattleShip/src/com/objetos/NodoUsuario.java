package com.objetos;

public class NodoUsuario {

	private String nombre;
	private String clave;
	private int conectado;
	private ListaJuego listaJuego = new ListaJuego();
	private NodoUsuario izq;
	private NodoUsuario der;
	private String tipo;
	private String nombreNodo = "usuario";
	public static int i = 0;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getConectado() {
		return conectado;
	}

	public void setConectado(int conectado) {
		this.conectado = conectado;
	}

	public ListaJuego getListaJuego() {
		return listaJuego;
	}

	public void setListaJuego(ListaJuego listaJuegos) {
		this.listaJuego = listaJuegos;
	}

	public NodoUsuario getIzq() {
		return izq;
	}

	public void setIzq(NodoUsuario izq) {
		this.izq = izq;
	}

	public NodoUsuario getDer() {
		return der;
	}

	public void setDer(NodoUsuario der) {
		this.der = der;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombreNodo() {
		return nombreNodo;
	}

	public void setNombreNodo(String nombreNodo) {
		this.nombreNodo = nombreNodo;
	}

	public NodoUsuario(String nombre, String contraseña, String tipo,
			int conectado, ListaJuego listaJuegos) {
		i++;
		this.nombreNodo = "usuario" + i;
		this.nombre = nombre;
		this.clave = contraseña;
		this.tipo = tipo;
		this.conectado = conectado;
		this.listaJuego = listaJuegos;
		this.izq = null;
		this.der = null;
	}
}