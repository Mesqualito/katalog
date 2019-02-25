import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IWort } from 'app/shared/model/wort.model';
import { WortService } from './wort.service';
import { ISprache } from 'app/shared/model/sprache.model';
import { SpracheService } from 'app/entities/sprache';
import { IGruppe } from 'app/shared/model/gruppe.model';
import { GruppeService } from 'app/entities/gruppe';
import { IBezeichnung } from 'app/shared/model/bezeichnung.model';
import { BezeichnungService } from 'app/entities/bezeichnung';
import { IAusdruck } from 'app/shared/model/ausdruck.model';
import { AusdruckService } from 'app/entities/ausdruck';

@Component({
    selector: 'jhi-wort-update',
    templateUrl: './wort-update.component.html'
})
export class WortUpdateComponent implements OnInit {
    wort: IWort;
    isSaving: boolean;

    spraches: ISprache[];

    gruppes: IGruppe[];

    worts: IWort[];

    bezeichnungs: IBezeichnung[];

    ausdrucks: IAusdruck[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected wortService: WortService,
        protected spracheService: SpracheService,
        protected gruppeService: GruppeService,
        protected bezeichnungService: BezeichnungService,
        protected ausdruckService: AusdruckService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ wort }) => {
            this.wort = wort;
        });
        this.spracheService
            .query({ filter: 'wort-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<ISprache[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISprache[]>) => response.body)
            )
            .subscribe(
                (res: ISprache[]) => {
                    if (!this.wort.sprache || !this.wort.sprache.id) {
                        this.spraches = res;
                    } else {
                        this.spracheService
                            .find(this.wort.sprache.id)
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
        this.bezeichnungService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IBezeichnung[]>) => mayBeOk.ok),
                map((response: HttpResponse<IBezeichnung[]>) => response.body)
            )
            .subscribe((res: IBezeichnung[]) => (this.bezeichnungs = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.ausdruckService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IAusdruck[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAusdruck[]>) => response.body)
            )
            .subscribe((res: IAusdruck[]) => (this.ausdrucks = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.wort.id !== undefined) {
            this.subscribeToSaveResponse(this.wortService.update(this.wort));
        } else {
            this.subscribeToSaveResponse(this.wortService.create(this.wort));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IWort>>) {
        result.subscribe((res: HttpResponse<IWort>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackBezeichnungById(index: number, item: IBezeichnung) {
        return item.id;
    }

    trackAusdruckById(index: number, item: IAusdruck) {
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
