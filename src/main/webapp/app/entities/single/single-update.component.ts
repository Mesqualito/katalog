import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ISingle } from 'app/shared/model/single.model';
import { SingleService } from './single.service';
import { ISprache } from 'app/shared/model/sprache.model';
import { SpracheService } from 'app/entities/sprache';
import { IGruppe } from 'app/shared/model/gruppe.model';
import { GruppeService } from 'app/entities/gruppe';

@Component({
    selector: 'jhi-single-update',
    templateUrl: './single-update.component.html'
})
export class SingleUpdateComponent implements OnInit {
    single: ISingle;
    isSaving: boolean;

    sprachcodes: ISprache[];

    gruppes: IGruppe[];

    singles: ISingle[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected singleService: SingleService,
        protected spracheService: SpracheService,
        protected gruppeService: GruppeService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ single }) => {
            this.single = single;
        });
        this.spracheService
            .query({ filter: 'single-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<ISprache[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISprache[]>) => response.body)
            )
            .subscribe(
                (res: ISprache[]) => {
                    if (!this.single.sprachCode || !this.single.sprachCode.id) {
                        this.sprachcodes = res;
                    } else {
                        this.spracheService
                            .find(this.single.sprachCode.id)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<ISprache>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<ISprache>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: ISprache) => (this.sprachcodes = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.gruppeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IGruppe[]>) => mayBeOk.ok),
                map((response: HttpResponse<IGruppe[]>) => response.body)
            )
            .subscribe((res: IGruppe[]) => (this.gruppes = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.singleService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISingle[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISingle[]>) => response.body)
            )
            .subscribe((res: ISingle[]) => (this.singles = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.single.id !== undefined) {
            this.subscribeToSaveResponse(this.singleService.update(this.single));
        } else {
            this.subscribeToSaveResponse(this.singleService.create(this.single));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISingle>>) {
        result.subscribe((res: HttpResponse<ISingle>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackSpracheById(index: number, item: ISprache) {
        return item.id;
    }

    trackGruppeById(index: number, item: IGruppe) {
        return item.id;
    }

    trackSingleById(index: number, item: ISingle) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
