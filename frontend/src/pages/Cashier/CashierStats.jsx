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

// Register Chart.js components
ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

const CashierStats = () => {
  const [stats, setStats] = useState({
    totalSales: 0,
    totalBills: 0,
    averageBill: 0,
  });

  useEffect(() => {
    // Simulated data fetching
    const fetchedData = {
      totalSales: 2450.5,
      totalBills: 35,
      averageBill: 70.01,
    };
    setStats(fetchedData);
  }, []);

  const chartData = {
    labels: ["Total Sales", "Total Bills", "Average Bill"],
    datasets: [
      {
        label: "Cashier Statistics",
        data: [stats.totalSales, stats.totalBills, stats.averageBill],
        backgroundColor: ["#60A5FA", "#34D399", "#FBBF24"],
        borderColor: ["#3B82F6", "#10B981", "#F59E0B"],
        borderWidth: 1,
      },
    ],
  };

  const chartOptions = {
    responsive: true,
    plugins: {
      legend: {
        position: "top",
      },
      title: {
        display: true,
        text: "Cashier Statistics Overview",
      },
    },
  };

  return (
    <div className="p-5">
      <h2 className="text-xl font-bold mb-4">Cashier Statistics</h2>
      <div className="grid grid-cols-1 sm:grid-cols-3 gap-4 mb-6">
        <div className="bg-blue-100 p-4 rounded shadow">
          <h3 className="text-lg font-semibold">Total Sales</h3>
          <p className="text-2xl font-bold">${stats.totalSales.toFixed(2)}</p>
        </div>
        <div className="bg-green-100 p-4 rounded shadow">
          <h3 className="text-lg font-semibold">Total Bills</h3>
          <p className="text-2xl font-bold">{stats.totalBills}</p>
        </div>
        <div className="bg-yellow-100 p-4 rounded shadow">
          <h3 className="text-lg font-semibold">Average Bill</h3>
          <p className="text-2xl font-bold">${stats.averageBill.toFixed(2)}</p>
        </div>
      </div>
      <div className="bg-white p-4 rounded shadow">
        <Bar data={chartData} options={chartOptions} />
      </div>
    </div>
  );
};

export default CashierStats;
