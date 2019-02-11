export interface ISprache {
    id?: number;
    sprachCode?: string;
    sprachBezeichnung?: string;
}

export class Sprache implements ISprache {
    constructor(public id?: number, public sprachCode?: string, public sprachBezeichnung?: string) {}
}
