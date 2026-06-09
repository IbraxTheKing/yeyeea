import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideMockStore } from '@ngrx/store/testing';
import { ClientComponent } from './client.component';

describe('ClientComponent', () => {
    let component: ClientComponent;
    let fixture: ComponentFixture<ClientComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [ClientComponent],
            providers: [provideMockStore()]
        }).compileComponents();

        fixture = TestBed.createComponent(ClientComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});