import { BrowserModule } from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AddDialog, AppComponent, UpdateDialog} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
    MatButton, MatCardModule,
    MatDialog,
    MatDialogModule,
    MatFormField,
    MatFormFieldModule,
    MatInputModule, MatRippleModule,
    MatTableModule
} from '@angular/material'
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from "@angular/common/http";
import {HttpService} from "./http.service";
import {Overlay} from "@angular/cdk/overlay";
@NgModule({
    declarations: [
        AppComponent, UpdateDialog, AddDialog, MatButton
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        MatTableModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        MatDialogModule,
        MatInputModule,
        MatFormFieldModule,
        MatRippleModule,
        MatCardModule
    ],
    entryComponents: [AppComponent, UpdateDialog, AddDialog, MatFormField, MatButton],
    providers: [HttpService, MatDialog, Overlay],
    bootstrap: [AppComponent]
})
export class AppModule { }