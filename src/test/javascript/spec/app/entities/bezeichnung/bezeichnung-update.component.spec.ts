/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KatalogTestModule } from '../../../test.module';
import { BezeichnungUpdateComponent } from 'app/entities/bezeichnung/bezeichnung-update.component';
import { BezeichnungService } from 'app/entities/bezeichnung/bezeichnung.service';
import { Bezeichnung } from 'app/shared/model/bezeichnung.model';

describe('Component Tests', () => {
    describe('Bezeichnung Management Update Component', () => {
        let comp: BezeichnungUpdateComponent;
        let fixture: ComponentFixture<BezeichnungUpdateComponent>;
        let service: BezeichnungService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [BezeichnungUpdateComponent]
            })
                .overrideTemplate(BezeichnungUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BezeichnungUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BezeichnungService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Bezeichnung(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.bezeichnung = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Bezeichnung();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.bezeichnung = entity;
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
