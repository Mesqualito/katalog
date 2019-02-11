import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IWort } from 'app/shared/model/wort.model';

type EntityResponseType = HttpResponse<IWort>;
type EntityArrayResponseType = HttpResponse<IWort[]>;

@Injectable({ providedIn: 'root' })
export class WortService {
    public resourceUrl = SERVER_API_URL + 'api/worts';

    constructor(protected http: HttpClient) {}

    create(wort: IWort): Observable<EntityResponseType> {
        return this.http.post<IWort>(this.resourceUrl, wort, { observe: 'response' });
    }

    update(wort: IWort): Observable<EntityResponseType> {
        return this.http.put<IWort>(this.resourceUrl, wort, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IWort>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IWort[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
