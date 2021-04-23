package it.prova.gestioneautomobililogin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "automobile")
public class Automobile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "marca")
	private String marca;
	@Column(name = "modello")
	private String modello;
	@Column(name = "cilindrata")
	private Integer cilindrata;
	@Column(name = "dataImmatricolazione")
	private Date dataImmatricolazione;

	@Enumerated(EnumType.STRING)
	private StatoUtente stato = StatoUtente.CREATO;

	public Automobile() {
	}
	
	
	
	public Automobile(String marca, String modello, Integer cilindrata, Date dataImmatricolazione) {
		this.marca = marca;
		this.modello = modello;
		this.cilindrata = cilindrata;
		this.dataImmatricolazione = dataImmatricolazione;
	}



	public Automobile(String marca, String modello, Integer cilindrata, Date dataImmatricolazione, StatoUtente stato) {
		this.marca = marca;
		this.modello = modello;
		this.cilindrata = cilindrata;
		this.dataImmatricolazione = dataImmatricolazione;
		this.stato = stato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public Integer getCilindrata() {
		return cilindrata;
	}

	public void setCilindrata(Integer cilindrata) {
		this.cilindrata = cilindrata;
	}

	public Date getDataImmatricolazione() {
		return dataImmatricolazione;
	}

	public void setDataImmatricolazione(Date dataImmatricolazione) {
		this.dataImmatricolazione = dataImmatricolazione;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

}
