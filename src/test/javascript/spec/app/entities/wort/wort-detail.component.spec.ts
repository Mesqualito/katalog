/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KatalogTestModule } from '../../../test.module';
import { WortDetailComponent } from 'app/entities/wort/wort-detail.component';
import { Wort } from 'app/shared/model/wort.model';

describe('Component Tests', () => {
    describe('Wort Management Detail Component', () => {
        let comp: WortDetailComponent;
        let fixture: ComponentFixture<WortDetailComponent>;
        const route = ({ data: of({ wort: new Wort('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [WortDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(WortDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(WortDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.wort).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
