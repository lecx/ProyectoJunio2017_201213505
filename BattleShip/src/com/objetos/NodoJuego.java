package com.objetos;

public class NodoJuego {

	private String usuarioBase = null;
	private String usuarioOponente = null;
	private int tirosRealizados = 0;
	private int tirosAcertados = 0;
	private int tirosFallados = 0;
	private int ganador = 0;
	private boolean terminado = false;
	private int danioRecibido = 0;
	public NodoJuego siguiente;
	public NodoJuego anterior;

	public NodoJuego(String oponente, int tRealizados, int tAcertados,
			int tFallados, int ganador, int danioRecibido) {
		this.usuarioOponente = oponente;
		this.tirosRealizados = tRealizados;
		this.tirosAcertados = tAcertados;
		this.tirosFallados = tFallados;
		this.ganador = ganador;
		this.danioRecibido = danioRecibido;
	}

	public NodoJuego(String usuarioBase, String oponente, int tRealizados,
			int tAcertados, int tFallados, int ganador, int danioRecibido) {
		this.usuarioBase = usuarioBase;
		this.usuarioOponente = oponente;
		this.tirosRealizados = tRealizados;
		this.tirosAcertados = tAcertados;
		this.tirosFallados = tFallados;
		this.ganador = ganador;
		this.danioRecibido = danioRecibido;
	}

	public String getUsuarioBase() {
		return usuarioBase;
	}

	public void setUsuarioBase(String usuarioBase) {
		this.usuarioBase = usuarioBase;
	}

	public NodoJuego(String oponente) {
		this.usuarioOponente = oponente;
	}

	public String getUsuarioOponente() {
		return usuarioOponente;
	}

	public void setUsuarioOponente(String usuarioOponente) {
		this.usuarioOponente = usuarioOponente;
	}

	public int getTirosRealizados() {
		return tirosRealizados;
	}

	public void setTirosRealizados(int tirosRealizados) {
		this.tirosRealizados = tirosRealizados;
	}

	public int getTirosAcertados() {
		return tirosAcertados;
	}

	public void setTirosAcertados(int tirosAcertados) {
		this.tirosAcertados = tirosAcertados;
	}

	public int getTirosFallados() {
		return tirosFallados;
	}

	public void setTirosFallados(int tirosFallados) {
		this.tirosFallados = tirosFallados;
	}

	public int getDanioRecibido() {
		return danioRecibido;
	}

	public void setDanioRecibido(int danioRecibido) {
		this.danioRecibido = danioRecibido;
	}

	public int getGanador() {
		return ganador;
	}

	public void setGanador(int ganador) {
		this.ganador = ganador;
	}

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}

}
