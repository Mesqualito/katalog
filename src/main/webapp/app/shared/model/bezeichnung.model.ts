import { ISprache } from 'app/shared/model/sprache.model';
import { IGruppe } from 'app/shared/model/gruppe.model';
import { IWort } from 'app/shared/model/wort.model';

export interface IBezeichnung {
    id?: string;
    bezeichnung?: string;
    sprache?: ISprache;
    gruppe?: IGruppe;
    einzelworts?: IWort[];
}

export class Bezeichnung implements IBezeichnung {
    constructor(
        public id?: string,
        public bezeichnung?: string,
        public sprache?: ISprache,
        public gruppe?: IGruppe,
        public einzelworts?: IWort[]
    ) {}
}
