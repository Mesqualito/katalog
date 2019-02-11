/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KatalogTestModule } from '../../../test.module';
import { AusdruckUpdateComponent } from 'app/entities/ausdruck/ausdruck-update.component';
import { AusdruckService } from 'app/entities/ausdruck/ausdruck.service';
import { Ausdruck } from 'app/shared/model/ausdruck.model';

describe('Component Tests', () => {
    describe('Ausdruck Management Update Component', () => {
        let comp: AusdruckUpdateComponent;
        let fixture: ComponentFixture<AusdruckUpdateComponent>;
        let service: AusdruckService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [AusdruckUpdateComponent]
            })
                .overrideTemplate(AusdruckUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AusdruckUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AusdruckService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Ausdruck('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.ausdruck = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Ausdruck();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.ausdruck = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
