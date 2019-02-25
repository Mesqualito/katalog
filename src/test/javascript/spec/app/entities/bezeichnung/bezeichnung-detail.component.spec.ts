/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KatalogTestModule } from '../../../test.module';
import { BezeichnungDetailComponent } from 'app/entities/bezeichnung/bezeichnung-detail.component';
import { Bezeichnung } from 'app/shared/model/bezeichnung.model';

describe('Component Tests', () => {
    describe('Bezeichnung Management Detail Component', () => {
        let comp: BezeichnungDetailComponent;
        let fixture: ComponentFixture<BezeichnungDetailComponent>;
        const route = ({ data: of({ bezeichnung: new Bezeichnung(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [BezeichnungDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BezeichnungDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BezeichnungDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.bezeichnung).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
