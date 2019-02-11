/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { KatalogTestModule } from '../../../test.module';
import { GruppeDeleteDialogComponent } from 'app/entities/gruppe/gruppe-delete-dialog.component';
import { GruppeService } from 'app/entities/gruppe/gruppe.service';

describe('Component Tests', () => {
    describe('Gruppe Management Delete Component', () => {
        let comp: GruppeDeleteDialogComponent;
        let fixture: ComponentFixture<GruppeDeleteDialogComponent>;
        let service: GruppeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [GruppeDeleteDialogComponent]
            })
                .overrideTemplate(GruppeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GruppeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GruppeService);
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
