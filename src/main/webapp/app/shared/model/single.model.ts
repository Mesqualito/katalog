import { ISprache } from 'app/shared/model/sprache.model';
import { IGruppe } from 'app/shared/model/gruppe.model';
import { ISingle } from 'app/shared/model/single.model';
import { IBezeichnung } from 'app/shared/model/bezeichnung.model';
import { IAusdruck } from 'app/shared/model/ausdruck.model';

export interface ISingle {
    id?: string;
    wort?: string;
    sprache?: ISprache;
    gruppe?: IGruppe;
    worts?: ISingle[];
    singles?: ISingle[];
    bezeichnungs?: IBezeichnung[];
    ausdrucks?: IAusdruck[];
}

export class Single implements ISingle {
    constructor(
        public id?: string,
        public wort?: string,
        public sprache?: ISprache,
        public gruppe?: IGruppe,
        public worts?: ISingle[],
        public singles?: ISingle[],
        public bezeichnungs?: IBezeichnung[],
        public ausdrucks?: IAusdruck[]
    ) {}
}
