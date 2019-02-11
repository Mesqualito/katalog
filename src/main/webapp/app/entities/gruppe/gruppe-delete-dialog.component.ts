import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGruppe } from 'app/shared/model/gruppe.model';
import { GruppeService } from './gruppe.service';

@Component({
    selector: 'jhi-gruppe-delete-dialog',
    templateUrl: './gruppe-delete-dialog.component.html'
})
export class GruppeDeleteDialogComponent {
    gruppe: IGruppe;

    constructor(protected gruppeService: GruppeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.gruppeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'gruppeListModification',
                content: 'Deleted an gruppe'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-gruppe-delete-popup',
    template: ''
})
export class GruppeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ gruppe }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GruppeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.gruppe = gruppe;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/gruppe', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/gruppe', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
