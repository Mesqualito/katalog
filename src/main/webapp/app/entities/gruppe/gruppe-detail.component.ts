import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGruppe } from 'app/shared/model/gruppe.model';

@Component({
    selector: 'jhi-gruppe-detail',
    templateUrl: './gruppe-detail.component.html'
})
export class GruppeDetailComponent implements OnInit {
    gruppe: IGruppe;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ gruppe }) => {
            this.gruppe = gruppe;
        });
    }

    previousState() {
        window.history.back();
    }
}
