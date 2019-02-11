/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { KatalogTestModule } from '../../../test.module';
import { SingleDeleteDialogComponent } from 'app/entities/single/single-delete-dialog.component';
import { SingleService } from 'app/entities/single/single.service';

describe('Component Tests', () => {
    describe('Single Management Delete Component', () => {
        let comp: SingleDeleteDialogComponent;
        let fixture: ComponentFixture<SingleDeleteDialogComponent>;
        let service: SingleService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [SingleDeleteDialogComponent]
            })
                .overrideTemplate(SingleDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SingleDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SingleService);
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
