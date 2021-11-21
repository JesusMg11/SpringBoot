import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { CookieService } from "ngx-cookie-service";

//URL para peticiones a Spring
const URL:string = "http://localhost:8080/api/";

@Injectable({
  providedIn: 'root'
})
export class DatosService {

  constructor(private http: HttpClient, private galletaEquipo: CookieService) { } 

  private equipoActivo = {id:"", nombre:""};  

  setEquipoActivo(id, nombre){
    this.equipoActivo.id = id;
    this.equipoActivo.nombre = nombre;

    this.galletaEquipo.set('id_equipo', id);
    this.galletaEquipo.set('nombre_equipo', nombre);
  }

  getEquipoActivo(){
    this.equipoActivo.id = this.galletaEquipo.get('id_equipo');
    this.equipoActivo.nombre = this.galletaEquipo.get('nombre_equipo');

    return this.equipoActivo;
  }

  obtenerEquipos(){
    return this.http.get(URL + "equipo/buscar");
  }

  obtenerJugadoresEquipo(id){
   // let Params = new HttpParams();
   // Params = Params.append('id', id);
    return this.http.get(URL + "equipo/buscarJugadores/" + id);
  }

  guardarJugador(jugador, equipo){
    let formData = new FormData();
    formData.append('nombre', jugador.nombre);
    formData.append('edad', jugador.edad);
    formData.append('dorsal', jugador.dorsal);
    formData.append('equipo', equipo);

    return this.http.post(URL + 'jugador/agregar', formData);
  }

  guardarEquipo(equipo){
    let formData = new FormData();
    formData.append('nombre', equipo.nombre);

    return this.http.post(URL + 'equipo/agregar', formData);
  }

  eliminarJugador(id){
    return this.http.delete(URL + 'jugador/eliminar/' + id);
  }

  actualizarJugador(jugador, equipo){
    let Params = new HttpParams();
    Params = Params.append('id', jugador.id);
    Params = Params.append('nombre', jugador.nombre);
    Params = Params.append('edad', jugador.edad);
    Params = Params.append('dorsal', jugador.dorsal);
    Params = Params.append('equipo', equipo);

    return this.http.put(URL + 'jugador/actualizar', Params);
  }
}
