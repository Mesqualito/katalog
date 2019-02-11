import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IGruppe } from 'app/shared/model/gruppe.model';
import { GruppeService } from './gruppe.service';

@Component({
    selector: 'jhi-gruppe-update',
    templateUrl: './gruppe-update.component.html'
})
export class GruppeUpdateComponent implements OnInit {
    gruppe: IGruppe;
    isSaving: boolean;

    constructor(protected gruppeService: GruppeService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ gruppe }) => {
            this.gruppe = gruppe;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.gruppe.id !== undefined) {
            this.subscribeToSaveResponse(this.gruppeService.update(this.gruppe));
        } else {
            this.subscribeToSaveResponse(this.gruppeService.create(this.gruppe));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IGruppe>>) {
        result.subscribe((res: HttpResponse<IGruppe>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
