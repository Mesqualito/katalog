import { ISprache } from 'app/shared/model/sprache.model';
import { IGruppe } from 'app/shared/model/gruppe.model';
import { ISingle } from 'app/shared/model/single.model';

export interface IBezeichnung {
    id?: string;
    bezeichnung?: string;
    sprachCode?: ISprache;
    gruppenCode?: IGruppe;
    singles?: ISingle[];
}

export class Bezeichnung implements IBezeichnung {
    constructor(
        public id?: string,
        public bezeichnung?: string,
        public sprachCode?: ISprache,
        public gruppenCode?: IGruppe,
        public singles?: ISingle[]
    ) {}
}
