import { ISprache } from 'app/shared/model/sprache.model';
import { IGruppe } from 'app/shared/model/gruppe.model';
import { IWort } from 'app/shared/model/wort.model';

export interface IAusdruck {
    id?: number;
    ausdruck?: string;
    sprache?: ISprache;
    gruppe?: IGruppe;
    einzelworts?: IWort[];
}

export class Ausdruck implements IAusdruck {
    constructor(
        public id?: number,
        public ausdruck?: string,
        public sprache?: ISprache,
        public gruppe?: IGruppe,
        public einzelworts?: IWort[]
    ) {}
}
