import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGruppe } from 'app/shared/model/gruppe.model';

type EntityResponseType = HttpResponse<IGruppe>;
type EntityArrayResponseType = HttpResponse<IGruppe[]>;

@Injectable({ providedIn: 'root' })
export class GruppeService {
    public resourceUrl = SERVER_API_URL + 'api/gruppes';

    constructor(protected http: HttpClient) {}

    create(gruppe: IGruppe): Observable<EntityResponseType> {
        return this.http.post<IGruppe>(this.resourceUrl, gruppe, { observe: 'response' });
    }

    update(gruppe: IGruppe): Observable<EntityResponseType> {
        return this.http.put<IGruppe>(this.resourceUrl, gruppe, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IGruppe>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGruppe[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
