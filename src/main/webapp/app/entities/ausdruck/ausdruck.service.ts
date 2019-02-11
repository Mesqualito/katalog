import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAusdruck } from 'app/shared/model/ausdruck.model';

type EntityResponseType = HttpResponse<IAusdruck>;
type EntityArrayResponseType = HttpResponse<IAusdruck[]>;

@Injectable({ providedIn: 'root' })
export class AusdruckService {
    public resourceUrl = SERVER_API_URL + 'api/ausdrucks';

    constructor(protected http: HttpClient) {}

    create(ausdruck: IAusdruck): Observable<EntityResponseType> {
        return this.http.post<IAusdruck>(this.resourceUrl, ausdruck, { observe: 'response' });
    }

    update(ausdruck: IAusdruck): Observable<EntityResponseType> {
        return this.http.put<IAusdruck>(this.resourceUrl, ausdruck, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IAusdruck>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAusdruck[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
