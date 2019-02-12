import { IWort } from 'app/shared/model/wort.model';
import { IBezeichnung } from 'app/shared/model/bezeichnung.model';
import { IAusdruck } from 'app/shared/model/ausdruck.model';

export interface IGruppe {
    id?: number;
    gruppenCode?: string;
    gruppenBezeichnung?: string;
    singles?: IWort[];
    bezeichnungs?: IBezeichnung[];
    ausdrucks?: IAusdruck[];
}

export class Gruppe implements IGruppe {
    constructor(
        public id?: number,
        public gruppenCode?: string,
        public gruppenBezeichnung?: string,
        public singles?: IWort[],
        public bezeichnungs?: IBezeichnung[],
        public ausdrucks?: IAusdruck[]
    ) {}
}
