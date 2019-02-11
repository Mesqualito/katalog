export interface ISprache {
    id?: string;
    sprachCode?: string;
    sprachBezeichnung?: string;
}

export class Sprache implements ISprache {
    constructor(public id?: string, public sprachCode?: string, public sprachBezeichnung?: string) {}
}
