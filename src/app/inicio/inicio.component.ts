import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DatosService } from '../datos.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  constructor(private router:Router, private datos: DatosService) { }

  equipos:any;

  equipoRegistro = {nombre:""};

  obtenerEquipos(){
    this.datos.obtenerEquipos().subscribe(resp =>{
      this.equipos = resp;
    },error =>{
      //console.log("Error: "+error);
    });
  }

  irEquipo(item){
    this.datos.setEquipoActivo(item.id, item.nombre);
    this.router.navigate(['/equipo']);
  }

  limpiar(){
    this.equipoRegistro.nombre = "";
  }

  guardarEquipo(){
    this.datos.guardarEquipo(this.equipoRegistro).subscribe(resp=>{
      Swal.fire({
        icon: 'success',
        title: '¡Bien!',
        text: 'Equipo agregado exitosamente.',
        timer: 2000
      });
      this.limpiar();
      this.obtenerEquipos();
    },error=>{
      Swal.fire({
        icon: 'error',
        title: '¡Ups!',
        text: 'Error al guardar.',
        timer: 2000
      });
    });
  }

  ngOnInit(): void {
    this.obtenerEquipos();
  }

}
