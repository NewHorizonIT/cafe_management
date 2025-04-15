import React, { useState } from "react";

const ImportStats = () => {
  const [stats, setStats] = useState([
    { id: 1, month: "April", year: 2025, totalImports: 500 },
    { id: 2, month: "March", year: 2025, totalImports: 300 },
  ]);

  return (
    <div className="p-5">
      <h2 className="text-xl font-bold mb-4">Import Statistics</h2>
      <table className="w-full border-collapse border border-gray-300">
        <thead>
          <tr className="bg-gray-100">
            <th className="border px-4 py-2">ID</th>
            <th className="border px-4 py-2">Month</th>
            <th className="border px-4 py-2">Year</th>
            <th className="border px-4 py-2">Total Imports</th>
          </tr>
        </thead>
        <tbody>
          {stats.map((stat) => (
            <tr key={stat.id}>
              <td className="border px-4 py-2">{stat.id}</td>
              <td className="border px-4 py-2">{stat.month}</td>
              <td className="border px-4 py-2">{stat.year}</td>
              <td className="border px-4 py-2">{stat.totalImports}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ImportStats;
