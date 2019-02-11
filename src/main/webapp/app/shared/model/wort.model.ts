import { ISprache } from 'app/shared/model/sprache.model';
import { IGruppe } from 'app/shared/model/gruppe.model';
import { IWort } from 'app/shared/model/wort.model';
import { IBezeichnung } from 'app/shared/model/bezeichnung.model';
import { IAusdruck } from 'app/shared/model/ausdruck.model';

export interface IWort {
    id?: string;
    eWort?: string;
    sprache?: ISprache;
    gruppe?: IGruppe;
    einzelworts?: IWort[];
    worts?: IWort[];
    bezeichnungs?: IBezeichnung[];
    ausdrucks?: IAusdruck[];
}

export class Wort implements IWort {
    constructor(
        public id?: string,
        public eWort?: string,
        public sprache?: ISprache,
        public gruppe?: IGruppe,
        public einzelworts?: IWort[],
        public worts?: IWort[],
        public bezeichnungs?: IBezeichnung[],
        public ausdrucks?: IAusdruck[]
    ) {}
}
