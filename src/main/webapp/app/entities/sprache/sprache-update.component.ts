import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ISprache } from 'app/shared/model/sprache.model';
import { SpracheService } from './sprache.service';

@Component({
    selector: 'jhi-sprache-update',
    templateUrl: './sprache-update.component.html'
})
export class SpracheUpdateComponent implements OnInit {
    sprache: ISprache;
    isSaving: boolean;

    constructor(protected spracheService: SpracheService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sprache }) => {
            this.sprache = sprache;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sprache.id !== undefined) {
            this.subscribeToSaveResponse(this.spracheService.update(this.sprache));
        } else {
            this.subscribeToSaveResponse(this.spracheService.create(this.sprache));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISprache>>) {
        result.subscribe((res: HttpResponse<ISprache>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
