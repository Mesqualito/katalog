import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IAusdruck } from 'app/shared/model/ausdruck.model';
import { AusdruckService } from './ausdruck.service';
import { ISprache } from 'app/shared/model/sprache.model';
import { SpracheService } from 'app/entities/sprache';
import { IGruppe } from 'app/shared/model/gruppe.model';
import { GruppeService } from 'app/entities/gruppe';
import { IWort } from 'app/shared/model/wort.model';
import { WortService } from 'app/entities/wort';

@Component({
    selector: 'jhi-ausdruck-update',
    templateUrl: './ausdruck-update.component.html'
})
export class AusdruckUpdateComponent implements OnInit {
    ausdruck: IAusdruck;
    isSaving: boolean;

    spraches: ISprache[];

    gruppes: IGruppe[];

    worts: IWort[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected ausdruckService: AusdruckService,
        protected spracheService: SpracheService,
        protected gruppeService: GruppeService,
        protected wortService: WortService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ausdruck }) => {
            this.ausdruck = ausdruck;
        });
        this.spracheService
            .query({ filter: 'ausdruck-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<ISprache[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISprache[]>) => response.body)
            )
            .subscribe(
                (res: ISprache[]) => {
                    if (!this.ausdruck.spracheId) {
                        this.spraches = res;
                    } else {
                        this.spracheService
                            .find(this.ausdruck.spracheId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<ISprache>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<ISprache>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: ISprache) => (this.spraches = [subRes].concat(res)),
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
        this.wortService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IWort[]>) => mayBeOk.ok),
                map((response: HttpResponse<IWort[]>) => response.body)
            )
            .subscribe((res: IWort[]) => (this.worts = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.ausdruck.id !== undefined) {
            this.subscribeToSaveResponse(this.ausdruckService.update(this.ausdruck));
        } else {
            this.subscribeToSaveResponse(this.ausdruckService.create(this.ausdruck));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAusdruck>>) {
        result.subscribe((res: HttpResponse<IAusdruck>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackWortById(index: number, item: IWort) {
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
