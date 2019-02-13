import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWort } from 'app/shared/model/wort.model';

@Component({
    selector: 'jhi-wort-detail',
    templateUrl: './wort-detail.component.html'
})
export class WortDetailComponent implements OnInit {
    wort: IWort;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ wort }) => {
            this.wort = wort;
        });
    }

    previousState() {
        window.history.back();
    }
}
