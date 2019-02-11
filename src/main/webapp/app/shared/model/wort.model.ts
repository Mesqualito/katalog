import { ISprache } from 'app/shared/model/sprache.model';
import { IGruppe } from 'app/shared/model/gruppe.model';
import { IWort } from 'app/shared/model/wort.model';
import { IBezeichnung } from 'app/shared/model/bezeichnung.model';
import { IAusdruck } from 'app/shared/model/ausdruck.model';

export interface IWort {
    id?: string;
    wort?: string;
    sprachCode?: ISprache;
    gruppenCodes?: IGruppe[];
    worts?: IWort[];
    worts?: IWort[];
    worts?: IBezeichnung[];
    worts?: IAusdruck[];
}

export class Wort implements IWort {
    constructor(
        public id?: string,
        public wort?: string,
        public sprachCode?: ISprache,
        public gruppenCodes?: IGruppe[],
        public worts?: IWort[],
        public worts?: IWort[],
        public worts?: IBezeichnung[],
        public worts?: IAusdruck[]
    ) {}
}
