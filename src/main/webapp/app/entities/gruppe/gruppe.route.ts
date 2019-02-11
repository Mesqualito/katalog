import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Gruppe } from 'app/shared/model/gruppe.model';
import { GruppeService } from './gruppe.service';
import { GruppeComponent } from './gruppe.component';
import { GruppeDetailComponent } from './gruppe-detail.component';
import { GruppeUpdateComponent } from './gruppe-update.component';
import { GruppeDeletePopupComponent } from './gruppe-delete-dialog.component';
import { IGruppe } from 'app/shared/model/gruppe.model';

@Injectable({ providedIn: 'root' })
export class GruppeResolve implements Resolve<IGruppe> {
    constructor(private service: GruppeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGruppe> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Gruppe>) => response.ok),
                map((gruppe: HttpResponse<Gruppe>) => gruppe.body)
            );
        }
        return of(new Gruppe());
    }
}

export const gruppeRoute: Routes = [
    {
        path: '',
        component: GruppeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.gruppe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: GruppeDetailComponent,
        resolve: {
            gruppe: GruppeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.gruppe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: GruppeUpdateComponent,
        resolve: {
            gruppe: GruppeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.gruppe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: GruppeUpdateComponent,
        resolve: {
            gruppe: GruppeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.gruppe.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const gruppePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: GruppeDeletePopupComponent,
        resolve: {
            gruppe: GruppeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.gruppe.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
