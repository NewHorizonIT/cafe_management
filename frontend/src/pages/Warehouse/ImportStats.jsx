import { Modal } from "@/components/ui";
import React, { useState } from "react";

const ImportStats = () => {
  const data = [
    {
      id: 1,
      product_name: "Cà phê Arabica",
      supplier_name: "Nhà cung cấp A",
      quantity: 100,
      unit_price: 15000,
      total_price: 1500000,
      responsible_person: "Nguyễn Văn A",
      import_date: "2025-04-10",
    },
    {
      id: 2,
      product_name: "Cà phê Robusta",
      supplier_name: "Nhà cung cấp B",
      quantity: 200,
      unit_price: 12000,
      total_price: 2400000,
      responsible_person: "Trần Thị B",
      import_date: "2025-04-11",
    },
  ];

  const [monthFilter, setMonthFilter] = useState("All");
  const [yearFilter, setYearFilter] = useState("All");
  const [selectedStat, setSelectedStat] = useState(null);

  const months = [
    "All",
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
  ];
  const years = ["All", "2024", "2025"];

  const filteredStats = data.filter((stat) => {
    return (
      (monthFilter === "All" ||
        new Date(stat.import_date).getMonth() ===
          months.indexOf(monthFilter)) &&
      (yearFilter === "All" ||
        new Date(stat.import_date).getFullYear().toString() === yearFilter)
    );
  });

  return (
    <div className="p-5">
      <h2 className="text-xl font-bold mb-4">Import Statistics</h2>

      {/* Bộ lọc tháng, năm */}
      <div className="flex items-center gap-4 mb-4">
        <div>
          <label className="mr-2 font-medium">Month:</label>
          <select
            className="border px-3 py-1 rounded"
            value={monthFilter}
            onChange={(e) => setMonthFilter(e.target.value)}
          >
            {months.map((m) => (
              <option key={m} value={m}>
                {m}
              </option>
            ))}
          </select>
        </div>

        <div>
          <label className="mr-2 font-medium">Year:</label>
          <select
            className="border px-3 py-1 rounded"
            value={yearFilter}
            onChange={(e) => setYearFilter(e.target.value)}
          >
            {years.map((y) => (
              <option key={y} value={y}>
                {y}
              </option>
            ))}
          </select>
        </div>
      </div>

      {/* Bảng dữ liệu */}
      <table className="w-full border-collapse border border-gray-300">
        <thead>
          <tr className="bg-gray-100">
            <th className="border px-4 py-2">ID</th>
            <th className="border px-4 py-2">Product Name</th>
            <th className="border px-4 py-2">Supplier</th>
            <th className="border px-4 py-2">Quantity</th>
          </tr>
        </thead>
        <tbody>
          {filteredStats.map((stat) => (
            <tr
              key={stat.id}
              className="cursor-pointer hover:bg-blue-100"
              onClick={() => setSelectedStat(stat)}
            >
              <td className="border px-4 py-2">{stat.id}</td>
              <td className="border px-4 py-2">{stat.product_name}</td>
              <td className="border px-4 py-2">{stat.supplier_name}</td>
              <td className="border px-4 py-2">{stat.quantity}</td>
            </tr>
          ))}
          {filteredStats.length === 0 && (
            <tr>
              <td colSpan={4} className="border px-4 py-2 text-center">
                No records found.
              </td>
            </tr>
          )}
        </tbody>
      </table>

      {/* Xem chi tiết */}
      {selectedStat && (
        <Modal
          title="Chi tiết phiếu nhập"
          isOpen={!!selectedStat}
          onClose={() => setSelectedStat(null)}
        >
          <p className="modal-field">
            <strong>ID:</strong> {selectedStat.id}
          </p>
          <p className="modal-field">
            <strong>Nguyên liệu:</strong> {selectedStat.product_name}
          </p>
          <p className="modal-field">
            <strong>Nhà cung cấp:</strong> {selectedStat.supplier_name}
          </p>
          <p className="modal-field">
            <strong>Số lượng:</strong> {selectedStat.quantity}
          </p>
          <p className="modal-field">
            <strong>Giá:</strong> {selectedStat.unit_price.toLocaleString()}
          </p>
          <p className="modal-field">
            <strong>Tổng tiền:</strong>{" "}
            {selectedStat.total_price.toLocaleString()}
          </p>
          <p className="modal-field">
            <strong>Quản kho:</strong> {selectedStat.responsible_person}
          </p>
          <p className="modal-field">
            <strong>Ngày nhập:</strong> {selectedStat.import_date}
          </p>
        </Modal>
      )}
    </div>
  );
};

export default ImportStats;
