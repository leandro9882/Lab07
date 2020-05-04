package it.polito.tdp.poweroutages.model;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Blackout {
	int persone;
	LocalDateTime dataFine;
	LocalDateTime dataInizio;

	public Blackout(int persone, LocalDateTime dataFine, LocalDateTime dataInizio) {

		this.persone = persone;
		this.dataFine = dataFine;
		this.dataInizio = dataInizio;
	}

	public int getPersone() {
		return persone;
	}

	public LocalDateTime getDataFine() {
		return dataFine;
	}

	public LocalDateTime getDataInizio() {
		return dataInizio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataFine == null) ? 0 : dataFine.hashCode());
		result = prime * result + ((dataInizio == null) ? 0 : dataInizio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blackout other = (Blackout) obj;
		if (dataFine == null) {
			if (other.dataFine != null)
				return false;
		} else if (!dataFine.equals(other.dataFine))
			return false;
		if (dataInizio == null) {
			if (other.dataInizio != null)
				return false;
		} else if (!dataInizio.equals(other.dataInizio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return persone + ", " + dataInizio + ", " + dataFine;
	}

	public long getOre() {
		Duration l = Duration.between(dataInizio, dataFine);
		return l.getSeconds()/3600;
	}

	public int getAnnoInizio() {
		return dataInizio.getYear();
	}

	public int getAnnoFine() {
		return dataFine.getYear();
	}
}
