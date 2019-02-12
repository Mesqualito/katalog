import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBezeichnung } from 'app/shared/model/bezeichnung.model';

type EntityResponseType = HttpResponse<IBezeichnung>;
type EntityArrayResponseType = HttpResponse<IBezeichnung[]>;

@Injectable({ providedIn: 'root' })
export class BezeichnungService {
    public resourceUrl = SERVER_API_URL + 'api/bezeichnungs';

    constructor(protected http: HttpClient) {}

    create(bezeichnung: IBezeichnung): Observable<EntityResponseType> {
        return this.http.post<IBezeichnung>(this.resourceUrl, bezeichnung, { observe: 'response' });
    }

    update(bezeichnung: IBezeichnung): Observable<EntityResponseType> {
        return this.http.put<IBezeichnung>(this.resourceUrl, bezeichnung, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IBezeichnung>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBezeichnung[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
