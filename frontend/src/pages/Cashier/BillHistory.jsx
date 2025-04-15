import React, { useState } from "react";

const BillHistory = () => {
  const [bills, setBills] = useState([
    { id: 1, date: "2025-04-01", total: 50.0, customer: "John Doe" },
    { id: 2, date: "2025-04-05", total: 75.5, customer: "Jane Smith" },
    { id: 3, date: "2025-04-08", total: 120.0, customer: "Alice Johnson" },
  ]);
  const [filterDate, setFilterDate] = useState("");

  const filteredBills = filterDate
    ? bills.filter((bill) => bill.date === filterDate)
    : bills;

  return (
    <div className="p-5">
      <h2 className="text-xl font-bold mb-4">Bill History</h2>
      <div className="mb-4">
        <label className="block mb-2 font-medium">Filter by Date:</label>
        <input
          type="date"
          value={filterDate}
          onChange={(e) => setFilterDate(e.target.value)}
          className="border border-gray-300 rounded px-3 py-2"
        />
      </div>
      <table className="w-full border-collapse border border-gray-300">
        <thead>
          <tr className="bg-gray-100">
            <th className="border border-gray-300 px-4 py-2">Bill ID</th>
            <th className="border border-gray-300 px-4 py-2">Date</th>
            <th className="border border-gray-300 px-4 py-2">Customer</th>
            <th className="border border-gray-300 px-4 py-2">Total</th>
          </tr>
        </thead>
        <tbody>
          {filteredBills.map((bill) => (
            <tr key={bill.id}>
              <td className="border border-gray-300 px-4 py-2">{bill.id}</td>
              <td className="border border-gray-300 px-4 py-2">{bill.date}</td>
              <td className="border border-gray-300 px-4 py-2">
                {bill.customer}
              </td>
              <td className="border border-gray-300 px-4 py-2">
                ${bill.total.toFixed(2)}
              </td>
            </tr>
          ))}
          {filteredBills.length === 0 && (
            <tr>
              <td
                colSpan="4"
                className="border border-gray-300 px-4 py-2 text-center"
              >
                No bills found for the selected date.
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default BillHistory;
