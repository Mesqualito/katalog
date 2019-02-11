import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { KatalogSharedModule } from 'app/shared';
import {
    SpracheComponent,
    SpracheDetailComponent,
    SpracheUpdateComponent,
    SpracheDeletePopupComponent,
    SpracheDeleteDialogComponent,
    spracheRoute,
    sprachePopupRoute
} from './';

const ENTITY_STATES = [...spracheRoute, ...sprachePopupRoute];

@NgModule({
    imports: [KatalogSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SpracheComponent,
        SpracheDetailComponent,
        SpracheUpdateComponent,
        SpracheDeleteDialogComponent,
        SpracheDeletePopupComponent
    ],
    entryComponents: [SpracheComponent, SpracheUpdateComponent, SpracheDeleteDialogComponent, SpracheDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KatalogSpracheModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
