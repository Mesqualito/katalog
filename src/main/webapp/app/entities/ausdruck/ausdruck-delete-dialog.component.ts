import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAusdruck } from 'app/shared/model/ausdruck.model';
import { AusdruckService } from './ausdruck.service';

@Component({
    selector: 'jhi-ausdruck-delete-dialog',
    templateUrl: './ausdruck-delete-dialog.component.html'
})
export class AusdruckDeleteDialogComponent {
    ausdruck: IAusdruck;

    constructor(protected ausdruckService: AusdruckService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.ausdruckService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'ausdruckListModification',
                content: 'Deleted an ausdruck'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ausdruck-delete-popup',
    template: ''
})
export class AusdruckDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ausdruck }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AusdruckDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.ausdruck = ausdruck;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/ausdruck', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/ausdruck', { outlets: { popup: null } }]);
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
