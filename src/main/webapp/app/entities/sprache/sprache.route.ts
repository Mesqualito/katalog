import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Sprache } from 'app/shared/model/sprache.model';
import { SpracheService } from './sprache.service';
import { SpracheComponent } from './sprache.component';
import { SpracheDetailComponent } from './sprache-detail.component';
import { SpracheUpdateComponent } from './sprache-update.component';
import { SpracheDeletePopupComponent } from './sprache-delete-dialog.component';
import { ISprache } from 'app/shared/model/sprache.model';

@Injectable({ providedIn: 'root' })
export class SpracheResolve implements Resolve<ISprache> {
    constructor(private service: SpracheService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISprache> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Sprache>) => response.ok),
                map((sprache: HttpResponse<Sprache>) => sprache.body)
            );
        }
        return of(new Sprache());
    }
}

export const spracheRoute: Routes = [
    {
        path: '',
        component: SpracheComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.sprache.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SpracheDetailComponent,
        resolve: {
            sprache: SpracheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.sprache.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SpracheUpdateComponent,
        resolve: {
            sprache: SpracheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.sprache.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SpracheUpdateComponent,
        resolve: {
            sprache: SpracheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.sprache.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sprachePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SpracheDeletePopupComponent,
        resolve: {
            sprache: SpracheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.sprache.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
