import { ISprache } from 'app/shared/model/sprache.model';
import { IGruppe } from 'app/shared/model/gruppe.model';
import { ISingle } from 'app/shared/model/single.model';

export interface ISingle {
    id?: string;
    wort?: string;
    sprachCode?: ISprache;
    gruppenCode?: IGruppe;
    singles?: ISingle[];
}

export class Single implements ISingle {
    constructor(
        public id?: string,
        public wort?: string,
        public sprachCode?: ISprache,
        public gruppenCode?: IGruppe,
        public singles?: ISingle[]
    ) {}
}
