import { IWort } from 'app/shared/model/wort.model';

export interface IAusdruck {
    id?: number;
    ausdruck?: string;
    spracheSprachCode?: string;
    spracheId?: number;
    gruppeGruppenCode?: string;
    gruppeId?: number;
    einzelworts?: IWort[];
}

export class Ausdruck implements IAusdruck {
    constructor(
        public id?: number,
        public ausdruck?: string,
        public spracheSprachCode?: string,
        public spracheId?: number,
        public gruppeGruppenCode?: string,
        public gruppeId?: number,
        public einzelworts?: IWort[]
    ) {}
}
