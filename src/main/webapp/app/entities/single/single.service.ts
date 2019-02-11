import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISingle } from 'app/shared/model/single.model';

type EntityResponseType = HttpResponse<ISingle>;
type EntityArrayResponseType = HttpResponse<ISingle[]>;

@Injectable({ providedIn: 'root' })
export class SingleService {
    public resourceUrl = SERVER_API_URL + 'api/singles';

    constructor(protected http: HttpClient) {}

    create(single: ISingle): Observable<EntityResponseType> {
        return this.http.post<ISingle>(this.resourceUrl, single, { observe: 'response' });
    }

    update(single: ISingle): Observable<EntityResponseType> {
        return this.http.put<ISingle>(this.resourceUrl, single, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<ISingle>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISingle[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
