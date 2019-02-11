/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KatalogTestModule } from '../../../test.module';
import { SpracheUpdateComponent } from 'app/entities/sprache/sprache-update.component';
import { SpracheService } from 'app/entities/sprache/sprache.service';
import { Sprache } from 'app/shared/model/sprache.model';

describe('Component Tests', () => {
    describe('Sprache Management Update Component', () => {
        let comp: SpracheUpdateComponent;
        let fixture: ComponentFixture<SpracheUpdateComponent>;
        let service: SpracheService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [SpracheUpdateComponent]
            })
                .overrideTemplate(SpracheUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SpracheUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SpracheService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Sprache('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sprache = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Sprache();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sprache = entity;
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
