import { IWort } from 'app/shared/model/wort.model';

export interface IBezeichnung {
    id?: number;
    bezeichnung?: string;
    spracheSprachCode?: string;
    spracheId?: number;
    gruppeGruppenCode?: string;
    gruppeId?: number;
    einzelworts?: IWort[];
}

export class Bezeichnung implements IBezeichnung {
    constructor(
        public id?: number,
        public bezeichnung?: string,
        public spracheSprachCode?: string,
        public spracheId?: number,
        public gruppeGruppenCode?: string,
        public gruppeId?: number,
        public einzelworts?: IWort[]
    ) {}
}
