import { AuthService } from './../service/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private usuarioService: AuthService) { }

  ngOnInit(): void {
  }



  deslogar(){
    this.usuarioService.deslogar();
  }

}
