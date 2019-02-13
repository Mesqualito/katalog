import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { KatalogSharedModule } from 'app/shared';
import {
    AusdruckComponent,
    AusdruckDetailComponent,
    AusdruckUpdateComponent,
    AusdruckDeletePopupComponent,
    AusdruckDeleteDialogComponent,
    ausdruckRoute,
    ausdruckPopupRoute
} from './';

const ENTITY_STATES = [...ausdruckRoute, ...ausdruckPopupRoute];

@NgModule({
    imports: [KatalogSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AusdruckComponent,
        AusdruckDetailComponent,
        AusdruckUpdateComponent,
        AusdruckDeleteDialogComponent,
        AusdruckDeletePopupComponent
    ],
    entryComponents: [AusdruckComponent, AusdruckUpdateComponent, AusdruckDeleteDialogComponent, AusdruckDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KatalogAusdruckModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
