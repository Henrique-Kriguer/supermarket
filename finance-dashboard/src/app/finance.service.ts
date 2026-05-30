import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { FinanceItem } from './finance.model';

@Injectable({
  providedIn: 'root'
})
export class FinanceService {
  private apiUrl = `${environment.apiUrl}/finances`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<FinanceItem[]> {
    return this.http.get<FinanceItem[]>(this.apiUrl);
  }

  add(item: FinanceItem): Observable<FinanceItem> {
    return this.http.post<FinanceItem>(this.apiUrl, item);
  }

  update(item: FinanceItem): Observable<FinanceItem> {
    if (!item.id) {
      throw new Error('Cannot update item without an id');
    }
    return this.http.put<FinanceItem>(`${this.apiUrl}/${item.id}`, item);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
