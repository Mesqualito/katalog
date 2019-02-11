import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISprache } from 'app/shared/model/sprache.model';

@Component({
    selector: 'jhi-sprache-detail',
    templateUrl: './sprache-detail.component.html'
})
export class SpracheDetailComponent implements OnInit {
    sprache: ISprache;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sprache }) => {
            this.sprache = sprache;
        });
    }

    previousState() {
        window.history.back();
    }
}
