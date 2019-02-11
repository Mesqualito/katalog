/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KatalogTestModule } from '../../../test.module';
import { SingleUpdateComponent } from 'app/entities/single/single-update.component';
import { SingleService } from 'app/entities/single/single.service';
import { Single } from 'app/shared/model/single.model';

describe('Component Tests', () => {
    describe('Single Management Update Component', () => {
        let comp: SingleUpdateComponent;
        let fixture: ComponentFixture<SingleUpdateComponent>;
        let service: SingleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [SingleUpdateComponent]
            })
                .overrideTemplate(SingleUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SingleUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SingleService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Single('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.single = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Single();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.single = entity;
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
