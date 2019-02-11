/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KatalogTestModule } from '../../../test.module';
import { AusdruckDetailComponent } from 'app/entities/ausdruck/ausdruck-detail.component';
import { Ausdruck } from 'app/shared/model/ausdruck.model';

describe('Component Tests', () => {
    describe('Ausdruck Management Detail Component', () => {
        let comp: AusdruckDetailComponent;
        let fixture: ComponentFixture<AusdruckDetailComponent>;
        const route = ({ data: of({ ausdruck: new Ausdruck('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [AusdruckDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AusdruckDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AusdruckDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.ausdruck).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
