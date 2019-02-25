import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { KatalogSharedModule } from 'app/shared';
import {
    GruppeComponent,
    GruppeDetailComponent,
    GruppeUpdateComponent,
    GruppeDeletePopupComponent,
    GruppeDeleteDialogComponent,
    gruppeRoute,
    gruppePopupRoute
} from './';

const ENTITY_STATES = [...gruppeRoute, ...gruppePopupRoute];

@NgModule({
    imports: [KatalogSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [GruppeComponent, GruppeDetailComponent, GruppeUpdateComponent, GruppeDeleteDialogComponent, GruppeDeletePopupComponent],
    entryComponents: [GruppeComponent, GruppeUpdateComponent, GruppeDeleteDialogComponent, GruppeDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KatalogGruppeModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
