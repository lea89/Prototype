package com.vuelo247.prototype;

public class Cancion 
{
	int id_cancion;
	String nombre;
	String duracion;
	String album;
	String path;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public String getAlbum() {
		return album;
	}
	public int getId_cancion() {
		return id_cancion;
	}
	public void setId_cancion(int id_cancion) {
		this.id_cancion = id_cancion;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
}
