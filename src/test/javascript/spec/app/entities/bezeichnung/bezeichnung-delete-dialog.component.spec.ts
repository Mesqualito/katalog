/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { KatalogTestModule } from '../../../test.module';
import { BezeichnungDeleteDialogComponent } from 'app/entities/bezeichnung/bezeichnung-delete-dialog.component';
import { BezeichnungService } from 'app/entities/bezeichnung/bezeichnung.service';

describe('Component Tests', () => {
    describe('Bezeichnung Management Delete Component', () => {
        let comp: BezeichnungDeleteDialogComponent;
        let fixture: ComponentFixture<BezeichnungDeleteDialogComponent>;
        let service: BezeichnungService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [BezeichnungDeleteDialogComponent]
            })
                .overrideTemplate(BezeichnungDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BezeichnungDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BezeichnungService);
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
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
