/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KatalogTestModule } from '../../../test.module';
import { GruppeDetailComponent } from 'app/entities/gruppe/gruppe-detail.component';
import { Gruppe } from 'app/shared/model/gruppe.model';

describe('Component Tests', () => {
    describe('Gruppe Management Detail Component', () => {
        let comp: GruppeDetailComponent;
        let fixture: ComponentFixture<GruppeDetailComponent>;
        const route = ({ data: of({ gruppe: new Gruppe('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [GruppeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GruppeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GruppeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.gruppe).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
