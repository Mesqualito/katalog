import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { KatalogSharedModule } from 'app/shared';
import {
    WortComponent,
    WortDetailComponent,
    WortUpdateComponent,
    WortDeletePopupComponent,
    WortDeleteDialogComponent,
    wortRoute,
    wortPopupRoute
} from './';

const ENTITY_STATES = [...wortRoute, ...wortPopupRoute];

@NgModule({
    imports: [KatalogSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [WortComponent, WortDetailComponent, WortUpdateComponent, WortDeleteDialogComponent, WortDeletePopupComponent],
    entryComponents: [WortComponent, WortUpdateComponent, WortDeleteDialogComponent, WortDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KatalogWortModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
