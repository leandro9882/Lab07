package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	int numMax = 0;
	List<Blackout> soluzione;

	public Model() {
		podao = new PowerOutageDAO();
	}

	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	/**
	 * 
	 * @param n    Nerc considerato
	 * @param anni differenza di anni fine ed inizio blackout
	 * @param ore  differenza di ore fine ed inizio blackout Mi rida' il numero
	 *             massimo di persone con quei dati di input
	 */
	public List<Blackout> Soluzione(Nerc n, int anni, int ore) {
		Set<Blackout> boList = new HashSet<>(podao.getEventi(n));
		List<Blackout> parziale = new ArrayList<>();
		cerca(parziale, 0, anni, ore, boList);

		return soluzione;
	}

	private void cerca(List<Blackout> parziale, int livello, int anni, int ore, Set<Blackout> boList) {
		// condizione di terminazione
		// SI ma quando cazzo finisce parziale di ciclare??????????
		if (numParziale(parziale) > numMax) {
			numMax = numParziale(parziale);
			this.soluzione = new ArrayList<>(parziale);
		}
//		if(parziale.size()>boList.size()) {
//			return;
//		}

		for (Blackout b : boList) {
			if (soluzioneValida(b, parziale, ore, anni)) {
				parziale.add(b);
				cerca(parziale, livello + 1, anni, ore, boList);
				parziale.remove(b);
			}
		}
	}

	/**
	 * è valida se rispetta il vincolo annuale e delle ore
	 * 
	 * @param parziale
	 * @return
	 */
	private boolean soluzioneValida(Blackout b, List<Blackout> parziale, int ore, int anni) {
		if(parziale.contains(b)) {
			return false;
		}
		
		long MaxOre = ore * 3600;
		long oTemp = 0;
		int aTemp = 0;
		int Yinf = 3000, Ysup = 0;
		// calcolo temporaneo
		for (Blackout bo : parziale) {
			oTemp += bo.getOre();
			if (bo.getAnnoFine() > Ysup)
				Ysup = bo.getAnnoFine();
			if (bo.getAnnoInizio() < Yinf)
				Yinf = bo.getAnnoInizio();
		}
		// calcolo con aggiunta evento b
		oTemp += b.getOre();
		if (b.getAnnoFine() > Ysup)
			Ysup = b.getAnnoFine();
		if (b.getAnnoInizio() < Yinf)
			Yinf = b.getAnnoInizio();

		aTemp = Ysup - Yinf;
		if (oTemp <= ore && aTemp < anni)
			return true;

		return false;
	}

	/**
	 * Calcolo il numero di persone della soluzione parziale
	 * 
	 * @param parziale
	 * @return
	 */
	public int numParziale(List<Blackout> parziale) {
		int num = 0;
		for (Blackout b : parziale) {
			num += b.getPersone();
		}
		return num;
	}

	public int ore(List<Blackout> lista) {
		int ore=0;
		
		for (Blackout bo : lista) {
			ore += bo.getOre();
		}
	
		return ore;
	}
}
/*
 * il problema parla di lista di eventi di blackout quindi devo creare la classe
 * evento e andrò a memorizzare in una lista tutti gli eventi. questi devono
 * sottostare a dei vincoli su ore e anni man mano devo salvare l'anno più
 * vecchio della sequenza e sottrarlo con il più recente e questa differenza
 * dovrà essere minore di "anni"; cosi come dovrò creare una variabile che
 * conterà le ore evento dopo evento e dovra essere <di " ore"
 * 
 * il livello corrisponderà all'evento la condizione di terminazione sarà il
 * rispetto dei vincoli finali
 */
