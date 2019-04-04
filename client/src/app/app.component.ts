import {AfterViewInit, Component, ElementRef, Inject, OnInit} from '@angular/core';
import {DataSource} from "@angular/cdk/collections";
import {HttpService} from "./http.service";

import {Contact} from "./contact";
import {Observable} from "rxjs";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {async} from "@angular/core/testing";
import {delay} from "rxjs/operators";
import {log} from "util";

@Component({
    selector: 'contact',
    templateUrl: 'app.component.html',
    styleUrls: ['app.component.css']
})

export class AppComponent implements OnInit{

    dataSource: ContactDataSource;

    displayedColumns: string[] = ['firstName', 'lastName', 'phone', 'edit', 'remove'];

    constructor(private httpService: HttpService, private dialog: MatDialog){}

    ngOnInit(): void {
        this.dataSource = new ContactDataSource(this.httpService);
    }

    public openUpdateDialog(contact: Contact): void {
        const dialogRef = this.dialog.open(UpdateDialog,
            {
                height: '400px',
                width: '400px',
                data: {id: contact.id, firstName: contact.firstName, lastName: contact.lastName, phone: contact.phone}
            });

        dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed');
            this.ngOnInit()
        });
    }

    public openAddDialog(): void {
        const dialogRef = this.dialog.open(AddDialog,
            {
                height: '400px',
                width: '400px',
                data: {firstName: '', lastName: '', phone: ''}
            });

        dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed');
            this.ngOnInit()
        });
    }

    public removeContact(id: number): void {
        this.httpService.deleteContact(id);

        this.ngOnInit();
    }
}

@Component({
    selector: 'update-dialog',
    templateUrl: 'update-dialog.html',
})
export class UpdateDialog implements OnInit{
    public contactForm: FormGroup;

    constructor(
        public dialogRef: MatDialogRef<UpdateDialog>,
        @Inject(MAT_DIALOG_DATA) public data: Contact,
        protected httpService: HttpService) {
    }

    ngOnInit(): void {
        this.contactForm = new FormGroup({
            firstName: new FormControl('', [Validators.required]),
            lastName: new FormControl('', [Validators.required]),
            phone: new FormControl('', [Validators.pattern('^\\+?\\d{10}$'), Validators.required])
        });
    }

    public checkError(controlName: string, errorName: string): boolean {
        return this.contactForm.controls[controlName].hasError(errorName);
    }

    private updateCont(contact: Contact) {
        this.httpService.updateContact(contact);
    }

    onNoClick(): void {
        this.dialogRef.close();
    }
}
@Component({
    selector: 'add-dialog',
    templateUrl: 'add-dialog.html',
})
export class AddDialog extends UpdateDialog{

    private addContact(contact: Contact) {
        this.httpService.addContact(contact);

    }
}

export class ContactDataSource extends DataSource<any> {

    constructor(private httpService: HttpService) {
        super();
    }

    connect(): Observable<Contact[]> {
        return this.httpService.getContacts();
    }

    disconnect(){}
}