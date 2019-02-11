/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KatalogTestModule } from '../../../test.module';
import { SingleDetailComponent } from 'app/entities/single/single-detail.component';
import { Single } from 'app/shared/model/single.model';

describe('Component Tests', () => {
    describe('Single Management Detail Component', () => {
        let comp: SingleDetailComponent;
        let fixture: ComponentFixture<SingleDetailComponent>;
        const route = ({ data: of({ single: new Single('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KatalogTestModule],
                declarations: [SingleDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SingleDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SingleDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.single).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
