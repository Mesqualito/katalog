import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Bezeichnung } from 'app/shared/model/bezeichnung.model';
import { BezeichnungService } from './bezeichnung.service';
import { BezeichnungComponent } from './bezeichnung.component';
import { BezeichnungDetailComponent } from './bezeichnung-detail.component';
import { BezeichnungUpdateComponent } from './bezeichnung-update.component';
import { BezeichnungDeletePopupComponent } from './bezeichnung-delete-dialog.component';
import { IBezeichnung } from 'app/shared/model/bezeichnung.model';

@Injectable({ providedIn: 'root' })
export class BezeichnungResolve implements Resolve<IBezeichnung> {
    constructor(private service: BezeichnungService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBezeichnung> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Bezeichnung>) => response.ok),
                map((bezeichnung: HttpResponse<Bezeichnung>) => bezeichnung.body)
            );
        }
        return of(new Bezeichnung());
    }
}

export const bezeichnungRoute: Routes = [
    {
        path: '',
        component: BezeichnungComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'katalogApp.bezeichnung.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: BezeichnungDetailComponent,
        resolve: {
            bezeichnung: BezeichnungResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.bezeichnung.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: BezeichnungUpdateComponent,
        resolve: {
            bezeichnung: BezeichnungResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.bezeichnung.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: BezeichnungUpdateComponent,
        resolve: {
            bezeichnung: BezeichnungResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.bezeichnung.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bezeichnungPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: BezeichnungDeletePopupComponent,
        resolve: {
            bezeichnung: BezeichnungResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'katalogApp.bezeichnung.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
