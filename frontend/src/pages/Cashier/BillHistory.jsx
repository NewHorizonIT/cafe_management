import React, { useState, useEffect } from "react";
import { Bar } from "react-chartjs-2";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import { Button, Modal } from "@/components/ui";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

const BillHistory = () => {
  const [bills, setBills] = useState([
    {
      id: 1,
      date: "2025-04-01",
      total: 50.0,
      customer: "John Doe",
      details: "2 Cappuccinos, 1 Latte",
      paymentMethod: "Cash",
    },
    {
      id: 2,
      date: "2025-04-05",
      total: 75.5,
      customer: "Jane Smith",
      details: "3 Espressos, 2 Teas",
      paymentMethod: "Credit Card",
    },
    {
      id: 3,
      date: "2025-04-08",
      total: 120.0,
      customer: "Alice Johnson",
      details: "5 Cappuccinos, 3 Lattes",
      paymentMethod: "Mobile Payment",
    },
  ]);
  const [selectedBill, setSelectedBill] = useState(null);
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");

  const handleFilter = () => {
    const filtered = bills.filter((bill) => {
      const billDate = new Date(bill.date);
      const start = startDate ? new Date(startDate) : null;
      const end = endDate ? new Date(endDate) : null;
      return (!start || billDate >= start) && (!end || billDate <= end);
    });
    setBills(filtered);
  };

  return (
    <div className="p-5">
      <h2 className="text-xl font-bold mb-4">Bill History</h2>

      <div className="mb-4">
        <label className="block mb-2 font-medium">Filter by Date Range:</label>
        <div className="flex gap-4">
          <input
            type="date"
            value={startDate}
            onChange={(e) => setStartDate(e.target.value)}
            className="border border-gray-300 rounded px-3 py-2"
          />
          <input
            type="date"
            value={endDate}
            onChange={(e) => setEndDate(e.target.value)}
            className="border border-gray-300 rounded px-3 py-2"
          />
          <Button
            className="py-2 px-3 bg-primary text-primary-content"
            eventHandler={handleFilter}
          >
            Thống kê
          </Button>
        </div>
      </div>

      <table className="w-full border-collapse border border-gray-300">
        <thead>
          <tr className="bg-gray-100">
            <th className="border border-gray-300 px-4 py-2">Bill ID</th>
            <th className="border border-gray-300 px-4 py-2">Date</th>
            <th className="border border-gray-300 px-4 py-2">Customer</th>
            <th className="border border-gray-300 px-4 py-2">Total</th>
            <th className="border border-gray-300 px-4 py-2">Actions</th>
          </tr>
        </thead>
        <tbody>
          {bills.map((bill) => (
            <tr key={bill.id}>
              <td className="border border-gray-300 px-4 py-2">{bill.id}</td>
              <td className="border border-gray-300 px-4 py-2">{bill.date}</td>
              <td className="border border-gray-300 px-4 py-2">
                {bill.customer}
              </td>
              <td className="border border-gray-300 px-4 py-2">
                ${bill.total.toFixed(2)}
              </td>
              <td className="border border-gray-300 px-4 py-2">
                <button
                  onClick={() => setSelectedBill(bill)}
                  className="text-blue-500 hover:underline"
                >
                  View Details
                </button>
              </td>
            </tr>
          ))}
          {bills.length === 0 && (
            <tr>
              <td
                colSpan="5"
                className="border border-gray-300 px-4 py-2 text-center"
              >
                No bills found for the selected date.
              </td>
            </tr>
          )}
        </tbody>
      </table>

      {selectedBill && (
        <Modal
          isOpen={!!selectedBill}
          onClose={() => setSelectedBill(null)}
          title="Bill Details"
        >
          <div className="mt-4 p-4 rounded">
            <p className="bill-field">
              <strong>Bill ID:</strong> {selectedBill.id}
            </p>
            <p className="bill-field">
              <strong>Date:</strong> {selectedBill.date}
            </p>
            <p className="bill-field">
              <strong>Customer:</strong> {selectedBill.customer}
            </p>
            <p className="bill-field">
              <strong>Total:</strong> ${selectedBill.total.toFixed(2)}
            </p>
            <p className="bill-field">
              <strong>Details:</strong> {selectedBill.details}
            </p>
            <p className="bill-field">
              <strong>Payment Method:</strong> {selectedBill.paymentMethod}
            </p>
          </div>
        </Modal>
      )}
    </div>
  );
};

export default BillHistory;
