import { Component, OnInit } from '@angular/core';
import { FinanceItem } from './finance.model';
import { FinanceService } from './finance.service';
import { Product } from './product.model';
import { ProductService } from './product.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  financeItems: FinanceItem[] = [];
  product: Product | null = null;
  productId = 1;
  form: FinanceItem = this.createEmptyItem();
  isEditing = false;
  loading = false;
  errorMessage = '';
  productLoading = false;
  productErrorMessage = '';

  fetchProduct(): void {
    this.loadProduct(this.productId);
  }

  constructor(private financeService: FinanceService, private productService: ProductService) {}

  ngOnInit(): void {
    this.loadItems();
    this.loadProduct(1);
  }

  private createEmptyItem(): FinanceItem {
    return {
      description: '',
      category: 'Income',
      amount: 0,
      date: new Date().toISOString().substring(0, 10)
    };
  }

  loadItems(): void {
    this.loading = true;
    this.financeService.getAll().subscribe({
      next: (items) => {
        this.financeItems = items;
        this.loading = false;
      },
      error: (error) => {
        this.errorMessage = 'Unable to load finance items. Check your API connection.';
        console.error(error);
        this.loading = false;
      }
    });
  }

  loadProduct(id: number): void {
    this.productLoading = true;
    this.productService.get(id).subscribe({
      next: (p) => {
        this.product = p;
        this.productLoading = false;
      },
      error: (error) => {
        this.productErrorMessage = 'Unable to load product. Check your API connection.';
        console.error(error);
        this.productLoading = false;
      }
    });
  }

  submitForm(): void {
    this.errorMessage = '';

    const request = this.isEditing && this.form.id
      ? this.financeService.update(this.form)
      : this.financeService.add(this.form);

    request.subscribe({
      next: () => {
        this.loadItems();
        this.resetForm();
      },
      error: (error) => {
        this.errorMessage = 'Unable to save item. Please verify the form and API endpoint.';
        console.error(error);
      }
    });
  }

  editItem(item: FinanceItem): void {
    this.form = { ...item };
    this.isEditing = true;
  }

  deleteItem(item: FinanceItem): void {
    if (!item.id) {
      return;
    }
    this.financeService.delete(item.id).subscribe({
      next: () => this.loadItems(),
      error: (error) => {
        this.errorMessage = 'Unable to delete item. Please verify the API endpoint.';
        console.error(error);
      }
    });
  }

  resetForm(): void {
    this.form = this.createEmptyItem();
    this.isEditing = false;
  }

  get totalAmount(): number {
    return this.financeItems.reduce((acc, item) => acc + item.amount, 0);
  }

  get totalIncome(): number {
    return this.financeItems
      .filter((item) => item.amount >= 0)
      .reduce((acc, item) => acc + item.amount, 0);
  }

  get totalExpense(): number {
    return this.financeItems
      .filter((item) => item.amount < 0)
      .reduce((acc, item) => acc + item.amount, 0);
  }
}
