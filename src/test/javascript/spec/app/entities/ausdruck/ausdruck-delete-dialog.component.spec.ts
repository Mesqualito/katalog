/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { KatalogTestModule } from '../../../test.module';
import { AusdruckDeleteDialogComponent } from 'app/entities/ausdruck/ausdruck-delete-dialog.component';
import { AusdruckService } from 'app/entities/ausdruck/ausdruck.service';

describe('Component Tests', () => {
    describe('Ausdruck Management Delete Component', () => {
        let comp: AusdruckDeleteDialogComponent;
        let fixture: ComponentFixture<AusdruckDeleteDialogComponent>;
        let service: AusdruckService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [AusdruckDeleteDialogComponent]
            })
                .overrideTemplate(AusdruckDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AusdruckDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AusdruckService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete('123');
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith('123');
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
