import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Wort } from 'app/shared/model/wort.model';
import { WortService } from './wort.service';
import { WortComponent } from './wort.component';
import { WortDetailComponent } from './wort-detail.component';
import { WortUpdateComponent } from './wort-update.component';
import { WortDeletePopupComponent } from './wort-delete-dialog.component';
import { IWort } from 'app/shared/model/wort.model';

@Injectable({ providedIn: 'root' })
export class WortResolve implements Resolve<IWort> {
    constructor(private service: WortService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IWort> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Wort>) => response.ok),
                map((wort: HttpResponse<Wort>) => wort.body)
            );
        }
        return of(new Wort());
    }
}

export const wortRoute: Routes = [
    {
        path: '',
        component: WortComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'katalogApp.wort.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: WortDetailComponent,
        resolve: {
            wort: WortResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.wort.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: WortUpdateComponent,
        resolve: {
            wort: WortResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.wort.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: WortUpdateComponent,
        resolve: {
            wort: WortResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.wort.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const wortPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: WortDeletePopupComponent,
        resolve: {
            wort: WortResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.wort.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
