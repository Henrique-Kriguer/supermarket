# Finance Dashboard

This Angular frontend calls your Java backend API to manage finance transactions.

## Setup

1. Open a terminal in `C:\Projects\finance-dashboard`
2. Run `npm install`
3. Run `npm start`

The app will open in your browser and proxy API requests to `http://localhost:8080/api`.

## API contract

The app expects these endpoints:

- `GET /api/finances`
- `POST /api/finances`
- `PUT /api/finances/{id}`
- `DELETE /api/finances/{id}`

If your Java backend uses a different path, update `src/environments/environment.ts` and `src/environments/environment.prod.ts`.

## Usage

- Add or update transactions with the form.
- Click a row to load the transaction into the form.
- Click the "Delete" button to remove a transaction.
