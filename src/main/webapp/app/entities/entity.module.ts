import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'sprache',
                loadChildren: './sprache/sprache.module#KatalogSpracheModule'
            },
            {
                path: 'gruppe',
                loadChildren: './gruppe/gruppe.module#KatalogGruppeModule'
            },
            {
                path: 'wort',
                loadChildren: './wort/wort.module#KatalogWortModule'
            },
            {
                path: 'bezeichnung',
                loadChildren: './bezeichnung/bezeichnung.module#KatalogBezeichnungModule'
            },
            {
                path: 'ausdruck',
                loadChildren: './ausdruck/ausdruck.module#KatalogAusdruckModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KatalogEntityModule {}
