import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBezeichnung } from 'app/shared/model/bezeichnung.model';

@Component({
    selector: 'jhi-bezeichnung-detail',
    templateUrl: './bezeichnung-detail.component.html'
})
export class BezeichnungDetailComponent implements OnInit {
    bezeichnung: IBezeichnung;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bezeichnung }) => {
            this.bezeichnung = bezeichnung;
        });
    }

    previousState() {
        window.history.back();
    }
}
