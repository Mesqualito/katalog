/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { KatalogTestModule } from '../../../test.module';
import { SpracheDeleteDialogComponent } from 'app/entities/sprache/sprache-delete-dialog.component';
import { SpracheService } from 'app/entities/sprache/sprache.service';

describe('Component Tests', () => {
    describe('Sprache Management Delete Component', () => {
        let comp: SpracheDeleteDialogComponent;
        let fixture: ComponentFixture<SpracheDeleteDialogComponent>;
        let service: SpracheService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [SpracheDeleteDialogComponent]
            })
                .overrideTemplate(SpracheDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SpracheDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SpracheService);
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
