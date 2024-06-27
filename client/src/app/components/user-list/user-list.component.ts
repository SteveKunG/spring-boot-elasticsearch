import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent implements OnInit {
  users: any[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe(data => {
      this.users = data;
    });
  }

  deleteUser(id: string) {
    this.userService.deleteUserById(id).subscribe(() => {
      this.users = this.users.filter(user => user.id !== id);
    });
  }
}