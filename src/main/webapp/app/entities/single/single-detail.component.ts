import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISingle } from 'app/shared/model/single.model';

@Component({
    selector: 'jhi-single-detail',
    templateUrl: './single-detail.component.html'
})
export class SingleDetailComponent implements OnInit {
    single: ISingle;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ single }) => {
            this.single = single;
        });
    }

    previousState() {
        window.history.back();
    }
}
