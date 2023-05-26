package com.tiendaOnline.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//Anotaciones para el uso de Lombook
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name ="cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findById_cliente", query = "SELECT c FROM Cliente c WHERE c.id_cliente = :id_cliente"),
    @NamedQuery(name = "Cliente.findByApellido", query = "SELECT c FROM Cliente c WHERE c.apellido = :apellido"),
    @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Cliente.findByDni", query = "SELECT c FROM Cliente c WHERE c.dni = :dni"),
    @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email"),
    @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre")
})


public class Cliente implements Serializable{

	private static final long serialVersionUID = 1l;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_cliente")
	private Integer id_cliente;
	
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
	
	@Basic(optional = false)
	@Column(name = "apellido")
	private String apellido;
	
	@Basic(optional = false)
	@Column(name = "direccion")
	private String direccion;
	
	@Basic(optional = false)
	@Column(name = "dni")
	private String dni;
	
	@Basic(optional = false)
	@Column(name = "email")
	private String email;
	
	}