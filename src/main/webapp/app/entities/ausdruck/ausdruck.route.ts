import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Ausdruck } from 'app/shared/model/ausdruck.model';
import { AusdruckService } from './ausdruck.service';
import { AusdruckComponent } from './ausdruck.component';
import { AusdruckDetailComponent } from './ausdruck-detail.component';
import { AusdruckUpdateComponent } from './ausdruck-update.component';
import { AusdruckDeletePopupComponent } from './ausdruck-delete-dialog.component';
import { IAusdruck } from 'app/shared/model/ausdruck.model';

@Injectable({ providedIn: 'root' })
export class AusdruckResolve implements Resolve<IAusdruck> {
    constructor(private service: AusdruckService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAusdruck> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Ausdruck>) => response.ok),
                map((ausdruck: HttpResponse<Ausdruck>) => ausdruck.body)
            );
        }
        return of(new Ausdruck());
    }
}

export const ausdruckRoute: Routes = [
    {
        path: '',
        component: AusdruckComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'katalogApp.ausdruck.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AusdruckDetailComponent,
        resolve: {
            ausdruck: AusdruckResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.ausdruck.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AusdruckUpdateComponent,
        resolve: {
            ausdruck: AusdruckResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.ausdruck.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AusdruckUpdateComponent,
        resolve: {
            ausdruck: AusdruckResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.ausdruck.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ausdruckPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AusdruckDeletePopupComponent,
        resolve: {
            ausdruck: AusdruckResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.ausdruck.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
