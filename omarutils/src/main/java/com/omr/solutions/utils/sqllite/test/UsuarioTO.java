package com.omr.solutions.utils.sqllite.test;

import com.omr.solutions.utils.sqllite.ColType;
import com.omr.solutions.utils.sqllite.Table;
import com.omr.solutions.utils.sqllite.TableType;

import java.util.Date;

public class UsuarioTO extends Table {

	@TableType(tableName="tblUsuarios")
	private String tableName;
	@ColType(colName = "idUsuario", acceptNull = false,descriptionName = "ID" , isAutoIncrement = true,isPrimary = true, colType = SQL_TYPE_INTEGER)
    private int idUsuario = 0;
	@ColType(colName = "nombre",descriptionName = "Nombre")
    private String nombre;
	@ColType(colName = "direccion",descriptionName = "Direccion")
    private String direccion;
	@ColType(colName = "fechaNacimiento",descriptionName = "F. Nacimiento",dateFormat = "dd/MM/yyyy" )
    private Date fechaNacimiento;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}
