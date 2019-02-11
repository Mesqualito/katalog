import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISprache } from 'app/shared/model/sprache.model';

type EntityResponseType = HttpResponse<ISprache>;
type EntityArrayResponseType = HttpResponse<ISprache[]>;

@Injectable({ providedIn: 'root' })
export class SpracheService {
    public resourceUrl = SERVER_API_URL + 'api/spraches';

    constructor(protected http: HttpClient) {}

    create(sprache: ISprache): Observable<EntityResponseType> {
        return this.http.post<ISprache>(this.resourceUrl, sprache, { observe: 'response' });
    }

    update(sprache: ISprache): Observable<EntityResponseType> {
        return this.http.put<ISprache>(this.resourceUrl, sprache, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<ISprache>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISprache[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
