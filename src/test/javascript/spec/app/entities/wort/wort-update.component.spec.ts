/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KatalogTestModule } from '../../../test.module';
import { WortUpdateComponent } from 'app/entities/wort/wort-update.component';
import { WortService } from 'app/entities/wort/wort.service';
import { Wort } from 'app/shared/model/wort.model';

describe('Component Tests', () => {
    describe('Wort Management Update Component', () => {
        let comp: WortUpdateComponent;
        let fixture: ComponentFixture<WortUpdateComponent>;
        let service: WortService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [WortUpdateComponent]
            })
                .overrideTemplate(WortUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(WortUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WortService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Wort('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.wort = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Wort();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.wort = entity;
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
