import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWort } from 'app/shared/model/wort.model';
import { WortService } from './wort.service';

@Component({
    selector: 'jhi-wort-delete-dialog',
    templateUrl: './wort-delete-dialog.component.html'
})
export class WortDeleteDialogComponent {
    wort: IWort;

    constructor(protected wortService: WortService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.wortService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'wortListModification',
                content: 'Deleted an wort'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-wort-delete-popup',
    template: ''
})
export class WortDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ wort }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(WortDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.wort = wort;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/wort', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/wort', { outlets: { popup: null } }]);
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
