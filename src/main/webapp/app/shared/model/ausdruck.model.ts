import { ISprache } from 'app/shared/model/sprache.model';
import { IGruppe } from 'app/shared/model/gruppe.model';
import { ISingle } from 'app/shared/model/single.model';

export interface IAusdruck {
    id?: string;
    ausdruck?: string;
    sprachCode?: ISprache;
    gruppenCode?: IGruppe;
    singles?: ISingle[];
}

export class Ausdruck implements IAusdruck {
    constructor(
        public id?: string,
        public ausdruck?: string,
        public sprachCode?: ISprache,
        public gruppenCode?: IGruppe,
        public singles?: ISingle[]
    ) {}
}
