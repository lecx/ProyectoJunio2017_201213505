package com.objetos;

public class ListaJuego {

	NodoJuego inicio = null;
	NodoJuego fin = null;

	public boolean validaVacia() {
		if (inicio == null)
			return true;
		else
			return false;
	}

	public void agregarInicio(String usuarioOponente) {
		if (validaVacia()) {
			inicio = fin = new NodoJuego(usuarioOponente);
		} else {
			NodoJuego temp = new NodoJuego(usuarioOponente);
			temp.siguiente = inicio;
			inicio.anterior = temp;
			inicio = temp;
		}
		enlazaNodos();
	}

	public void agregarFin(String usuarioOponente) {
		if (validaVacia())
			inicio = fin = new NodoJuego(usuarioOponente);
		else {
			NodoJuego temp = fin;
			fin = temp.siguiente = new NodoJuego(usuarioOponente);
			fin.anterior = temp;
		}
		enlazaNodos();
	}

	public void agregarFin(String usuarioBase, String usuarioOponente,
			int tRealizados, int tAcertados, int tFallados, int ganador,
			int danioRecibido) {
		if (validaVacia())
			inicio = fin = new NodoJuego(usuarioBase, usuarioOponente,
					tRealizados, tAcertados, tFallados, ganador, danioRecibido);
		else {
			NodoJuego temp = fin;
			fin = temp.siguiente = new NodoJuego(usuarioBase, usuarioOponente,
					tRealizados, tAcertados, tFallados, ganador, danioRecibido);
			fin.anterior = temp;
		}
		enlazaNodos();
	}

	private void enlazaNodos() {
		inicio.anterior = fin;
		fin.siguiente = inicio;
	}

	public boolean buscar(String usuarioOponente) {
		boolean resultado = false;
		NodoJuego temp = inicio;
		while (temp != null) {
			if (temp.getUsuarioOponente().equals(usuarioOponente))
				return true;
			else {
				temp = temp.siguiente;
				if (temp == inicio)
					return false;
			}
		}
		return resultado;
	}

	public void aumentarConteo(String usuarioOponente, int tipo) {
		NodoJuego temp = inicio;
		while (temp != null) {
			if (temp.getUsuarioOponente().equals(usuarioOponente)) {
				if (!temp.isTerminado()) {
					switch (tipo) {
					case 1:
						temp.setTirosRealizados(temp.getTirosRealizados() + 1);
						break;
					case 2:
						temp.setTirosAcertados(temp.getTirosAcertados() + 1);
						break;
					case 3:
						temp.setTirosFallados(temp.getTirosFallados() + 1);
						break;
					case 4:
						temp.setDanioRecibido(temp.getDanioRecibido() + 1);
						break;
					}
				}
			} else {
				temp = temp.siguiente;
				if (temp == inicio)
					break;
			}
		}
	}
}
