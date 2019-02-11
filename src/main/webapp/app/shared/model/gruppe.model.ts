export interface IGruppe {
    id?: string;
    gruppenCode?: string;
    gruppenBezeichnung?: string;
}

export class Gruppe implements IGruppe {
    constructor(public id?: string, public gruppenCode?: string, public gruppenBezeichnung?: string) {}
}
