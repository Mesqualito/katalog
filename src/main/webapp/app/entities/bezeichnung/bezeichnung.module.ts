import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { KatalogSharedModule } from 'app/shared';
import {
    BezeichnungComponent,
    BezeichnungDetailComponent,
    BezeichnungUpdateComponent,
    BezeichnungDeletePopupComponent,
    BezeichnungDeleteDialogComponent,
    bezeichnungRoute,
    bezeichnungPopupRoute
} from './';

const ENTITY_STATES = [...bezeichnungRoute, ...bezeichnungPopupRoute];

@NgModule({
    imports: [KatalogSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BezeichnungComponent,
        BezeichnungDetailComponent,
        BezeichnungUpdateComponent,
        BezeichnungDeleteDialogComponent,
        BezeichnungDeletePopupComponent
    ],
    entryComponents: [BezeichnungComponent, BezeichnungUpdateComponent, BezeichnungDeleteDialogComponent, BezeichnungDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KatalogBezeichnungModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
