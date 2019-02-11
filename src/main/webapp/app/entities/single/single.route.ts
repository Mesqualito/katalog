import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Single } from 'app/shared/model/single.model';
import { SingleService } from './single.service';
import { SingleComponent } from './single.component';
import { SingleDetailComponent } from './single-detail.component';
import { SingleUpdateComponent } from './single-update.component';
import { SingleDeletePopupComponent } from './single-delete-dialog.component';
import { ISingle } from 'app/shared/model/single.model';

@Injectable({ providedIn: 'root' })
export class SingleResolve implements Resolve<ISingle> {
    constructor(private service: SingleService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISingle> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Single>) => response.ok),
                map((single: HttpResponse<Single>) => single.body)
            );
        }
        return of(new Single());
    }
}

export const singleRoute: Routes = [
    {
        path: '',
        component: SingleComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'katalogApp.single.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SingleDetailComponent,
        resolve: {
            single: SingleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.single.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SingleUpdateComponent,
        resolve: {
            single: SingleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.single.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SingleUpdateComponent,
        resolve: {
            single: SingleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.single.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const singlePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SingleDeletePopupComponent,
        resolve: {
            single: SingleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.single.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
