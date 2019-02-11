import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISprache } from 'app/shared/model/sprache.model';
import { SpracheService } from './sprache.service';

@Component({
    selector: 'jhi-sprache-delete-dialog',
    templateUrl: './sprache-delete-dialog.component.html'
})
export class SpracheDeleteDialogComponent {
    sprache: ISprache;

    constructor(protected spracheService: SpracheService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.spracheService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'spracheListModification',
                content: 'Deleted an sprache'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sprache-delete-popup',
    template: ''
})
export class SpracheDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sprache }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SpracheDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sprache = sprache;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/sprache', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/sprache', { outlets: { popup: null } }]);
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
