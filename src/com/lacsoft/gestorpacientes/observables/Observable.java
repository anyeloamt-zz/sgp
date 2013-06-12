package com.lacsoft.gestorpacientes.observables;

import java.util.ArrayList;

import com.lacsoft.gestorpacientes.observadores.Observador;

public interface Observable {
	void agregarObservador(Observador o);
	void removerObservador(Observador o);
	void notificarObservadores(ArrayList<String> datos);
}
