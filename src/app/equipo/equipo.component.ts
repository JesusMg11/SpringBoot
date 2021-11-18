import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DatosService } from '../datos.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-equipo',
  templateUrl: './equipo.component.html',
  styleUrls: ['./equipo.component.css']
})
export class EquipoComponent implements OnInit {

  constructor(private router:Router, private datos: DatosService) { }

  id_equipo:any;
  nombre_equipo:any;
  jugadores:any;

  jugadorRegistro = {nombre:'', edad:'', dorsal:''};
  jugadorEditar = {id:'', nombre:'', edad:'', dorsal:''};

  obtenerJugadores(){
    this.datos.obtenerJugadoresEquipo(this.id_equipo).subscribe(resp=>{
      this.jugadores = resp;
    },error=>{
     // console.log(error);
    });
  }

  guardarJugador(){
    this.datos.guardarJugador(this.jugadorRegistro, this.id_equipo).subscribe(resp=>{
      Swal.fire({
        icon: 'success',
        title: '¡Bien!',
        text: 'Jugador agregado exitosamente.',
        timer: 2000
      });
      this.jugadorRegistro.nombre = "";
      this.jugadorRegistro.edad = "";
      this.jugadorRegistro.dorsal = "";
      this.obtenerJugadores();
    }, error =>{
      Swal.fire({
        icon: 'error',
        title: '¡Ups!',
        text: 'Ocurrio un error al guardar al jugador.',
        timer: 2000
      });
     // console.log(error);
    })
  }

  limpiar(){
    this.jugadorRegistro.dorsal = "";
    this.jugadorRegistro.edad = "";
    this.jugadorRegistro.nombre = "";
  }

  limpiarEditar(){
    this.jugadorEditar.id = "";
    this.jugadorEditar.dorsal = "";
    this.jugadorEditar.edad = "";
    this.jugadorEditar.nombre = "";
  }

  eliminarJugador(item){
    this.datos.eliminarJugador(item.id).subscribe(resp=>{
      if(resp){
        Swal.fire({
          icon: 'success',
          title: '¡Hecho!',
          text: 'Jugador eliminado exitosamente.',
          timer: 2000
        });
        this.obtenerJugadores();
      }else{
        Swal.fire({
          icon: 'error',
          title: '¡Ups!',
          text: 'Ocurrio un error al eliminar al jugador.',
          timer: 2000
        });
        //console.log(resp);
      }
      
    }, error=>{
      Swal.fire({
        icon: 'error',
        title: '¡Ups!',
        text: 'Ocurrio un error al eliminar al jugador.',
        timer: 2000
      });
      //console.log(error);
    });
  }

  datosEditar(item){
    this.jugadorEditar.id = item.id;
    this.jugadorEditar.nombre = item.nombre;
    this.jugadorEditar.edad = item.edad;
    this.jugadorEditar.dorsal = item.dorsal;
  }

  actualizarJugador(){
    this.datos.actualizarJugador(this.jugadorEditar, this.id_equipo).subscribe(resp=>{
      Swal.fire({
        icon: 'success',
        title: '¡Hecho!',
        text: 'Jugador actualizado exitosamente.',
        timer: 2000
      });
      this.obtenerJugadores();
    }, error=>{
      Swal.fire({
        icon: 'error',
        title: '¡Ups!',
        text: 'Ocurrio un error al actualizar al jugador.',
        timer: 2000
      });
    });
  }

  ngOnInit(): void {
    this.id_equipo = this.datos.getEquipoActivo().id;
    this.nombre_equipo = this.datos.getEquipoActivo().nombre;
    this.obtenerJugadores();
  }

}
