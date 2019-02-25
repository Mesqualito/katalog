import { IWort } from 'app/shared/model/wort.model';
import { IBezeichnung } from 'app/shared/model/bezeichnung.model';
import { IAusdruck } from 'app/shared/model/ausdruck.model';

export interface IWort {
    id?: number;
    intId?: number;
    eWort?: string;
    spracheSprachCode?: string;
    spracheId?: number;
    gruppeGruppenCode?: string;
    gruppeId?: number;
    einzelworts?: IWort[];
    worts?: IWort[];
    bezeichnungs?: IBezeichnung[];
    ausdrucks?: IAusdruck[];
}

export class Wort implements IWort {
    constructor(
        public id?: number,
        public intId?: number,
        public eWort?: string,
        public spracheSprachCode?: string,
        public spracheId?: number,
        public gruppeGruppenCode?: string,
        public gruppeId?: number,
        public einzelworts?: IWort[],
        public worts?: IWort[],
        public bezeichnungs?: IBezeichnung[],
        public ausdrucks?: IAusdruck[]
    ) {}
}
