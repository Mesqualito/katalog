import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { KatalogSharedModule } from 'app/shared';
import {
    SingleComponent,
    SingleDetailComponent,
    SingleUpdateComponent,
    SingleDeletePopupComponent,
    SingleDeleteDialogComponent,
    singleRoute,
    singlePopupRoute
} from './';

const ENTITY_STATES = [...singleRoute, ...singlePopupRoute];

@NgModule({
    imports: [KatalogSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [SingleComponent, SingleDetailComponent, SingleUpdateComponent, SingleDeleteDialogComponent, SingleDeletePopupComponent],
    entryComponents: [SingleComponent, SingleUpdateComponent, SingleDeleteDialogComponent, SingleDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KatalogSingleModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
