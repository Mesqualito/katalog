import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBezeichnung } from 'app/shared/model/bezeichnung.model';
import { BezeichnungService } from './bezeichnung.service';

@Component({
    selector: 'jhi-bezeichnung-delete-dialog',
    templateUrl: './bezeichnung-delete-dialog.component.html'
})
export class BezeichnungDeleteDialogComponent {
    bezeichnung: IBezeichnung;

    constructor(
        protected bezeichnungService: BezeichnungService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.bezeichnungService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'bezeichnungListModification',
                content: 'Deleted an bezeichnung'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-bezeichnung-delete-popup',
    template: ''
})
export class BezeichnungDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bezeichnung }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BezeichnungDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.bezeichnung = bezeichnung;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/bezeichnung', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/bezeichnung', { outlets: { popup: null } }]);
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
