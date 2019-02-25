/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KatalogTestModule } from '../../../test.module';
import { GruppeUpdateComponent } from 'app/entities/gruppe/gruppe-update.component';
import { GruppeService } from 'app/entities/gruppe/gruppe.service';
import { Gruppe } from 'app/shared/model/gruppe.model';

describe('Component Tests', () => {
    describe('Gruppe Management Update Component', () => {
        let comp: GruppeUpdateComponent;
        let fixture: ComponentFixture<GruppeUpdateComponent>;
        let service: GruppeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [GruppeUpdateComponent]
            })
                .overrideTemplate(GruppeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GruppeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GruppeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Gruppe(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.gruppe = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Gruppe();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.gruppe = entity;
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
