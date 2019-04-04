import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {Contact} from "./contact";

@Injectable()
export class HttpService {

    constructor(private http: HttpClient) {}

    getContacts(): Observable<Contact[]>{
        return this.http.get<Contact[]>('http://localhost:8080/api/contact_book/contacts');
    }

    updateContact(contact: Contact) {

        const body = {id: contact.id, firstName: contact.firstName, lastName: contact.lastName, phone: contact.phone};

        return this.http.put('http://localhost:8080/api/contact_book/contact', body)
            .subscribe(data => {
                console.log("Contact is successfully updated", data)
            },
                error => {
                console.log("Error ", error)
                });
    }

    addContact(contact: Contact) {

        const body = {firstName: contact.firstName, lastName: contact.lastName, phone: contact.phone};

        return this.http.post('http://localhost:8080/api/contact_book/contact', body)
            .subscribe(data => {
                console.log("Contact is successfully added", data)
            },
                error => {
                console.log("Error ", error)
                });
    }

    deleteContact(id: number) {
        this.http.delete('http://localhost:8080/api/contact_book/contact/' + id)
            .subscribe(data => {
                    console.log("Contact is successfully deleted", data);
                },
                error => {
                    console.log("Error ", error)
                });
    }
}