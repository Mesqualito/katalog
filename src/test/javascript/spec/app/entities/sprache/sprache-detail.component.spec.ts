/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KatalogTestModule } from '../../../test.module';
import { SpracheDetailComponent } from 'app/entities/sprache/sprache-detail.component';
import { Sprache } from 'app/shared/model/sprache.model';

describe('Component Tests', () => {
    describe('Sprache Management Detail Component', () => {
        let comp: SpracheDetailComponent;
        let fixture: ComponentFixture<SpracheDetailComponent>;
        const route = ({ data: of({ sprache: new Sprache('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [SpracheDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SpracheDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SpracheDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sprache).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
