import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IBezeichnung } from 'app/shared/model/bezeichnung.model';
import { BezeichnungService } from './bezeichnung.service';
import { ISprache } from 'app/shared/model/sprache.model';
import { SpracheService } from 'app/entities/sprache';
import { IGruppe } from 'app/shared/model/gruppe.model';
import { GruppeService } from 'app/entities/gruppe';
import { ISingle } from 'app/shared/model/single.model';
import { SingleService } from 'app/entities/single';

@Component({
    selector: 'jhi-bezeichnung-update',
    templateUrl: './bezeichnung-update.component.html'
})
export class BezeichnungUpdateComponent implements OnInit {
    bezeichnung: IBezeichnung;
    isSaving: boolean;

    sprachcodes: ISprache[];

    gruppes: IGruppe[];

    singles: ISingle[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected bezeichnungService: BezeichnungService,
        protected spracheService: SpracheService,
        protected gruppeService: GruppeService,
        protected singleService: SingleService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ bezeichnung }) => {
            this.bezeichnung = bezeichnung;
        });
        this.spracheService
            .query({ filter: 'bezeichnung-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<ISprache[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISprache[]>) => response.body)
            )
            .subscribe(
                (res: ISprache[]) => {
                    if (!this.bezeichnung.sprachCode || !this.bezeichnung.sprachCode.id) {
                        this.sprachcodes = res;
                    } else {
                        this.spracheService
                            .find(this.bezeichnung.sprachCode.id)
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
        if (this.bezeichnung.id !== undefined) {
            this.subscribeToSaveResponse(this.bezeichnungService.update(this.bezeichnung));
        } else {
            this.subscribeToSaveResponse(this.bezeichnungService.create(this.bezeichnung));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IBezeichnung>>) {
        result.subscribe((res: HttpResponse<IBezeichnung>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
