import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAusdruck } from 'app/shared/model/ausdruck.model';

@Component({
    selector: 'jhi-ausdruck-detail',
    templateUrl: './ausdruck-detail.component.html'
})
export class AusdruckDetailComponent implements OnInit {
    ausdruck: IAusdruck;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ausdruck }) => {
            this.ausdruck = ausdruck;
        });
    }

    previousState() {
        window.history.back();
    }
}
