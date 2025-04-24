import React, { useState, useEffect } from "react";
import { Bar, Doughnut, Pie, Line } from "react-chartjs-2";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
  ArcElement,
  PointElement,
  LineElement,
} from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  ArcElement,
  Title,
  Tooltip,
  Legend,
  PointElement,
  LineElement
);

const CashierStats = () => {
  const [timeRange, setTimeRange] = useState("day");
  const [stats, setStats] = useState({
    totalSales: 0,
    totalBills: 0,
    averageBill: 0,
    topProducts: [],
    paymentMethods: [],
    orderStatus: [],
  });

  const fetchStats = (range) => {
    // Giả lập dữ liệu theo thời gian
    let data;
    switch (range) {
      case "day":
        data = {
          totalSales: 350.0,
          totalBills: 5,
          averageBill: 70.0,
          topProducts: [
            { name: "Product A", sold: 2 },
            { name: "Product B", sold: 2 },
            { name: "Product C", sold: 1 },
          ],
          paymentMethods: [
            { method: "COD", count: 3 },
            { method: "Credit Card", count: 1 },
            { method: "e-Wallet", count: 1 },
          ],
          orderStatus: [
            { status: "Completed", count: 4 },
            { status: "Pending", count: 1 },
            { status: "Cancelled", count: 0 },
          ],
        };
        break;
      case "week":
        data = {
          totalSales: 2450.5,
          totalBills: 35,
          averageBill: 70.01,
          topProducts: [
            { name: "Product A", sold: 15 },
            { name: "Product B", sold: 10 },
            { name: "Product C", sold: 8 },
          ],
          paymentMethods: [
            { method: "COD", count: 20 },
            { method: "Credit Card", count: 10 },
            { method: "e-Wallet", count: 5 },
          ],
          orderStatus: [
            { status: "Completed", count: 28 },
            { status: "Pending", count: 5 },
            { status: "Cancelled", count: 2 },
          ],
        };
        break;
      case "month":
        data = {
          totalSales: 10250.75,
          totalBills: 150,
          averageBill: 68.34,
          topProducts: [
            { name: "Product A", sold: 50 },
            { name: "Product B", sold: 45 },
            { name: "Product C", sold: 40 },
          ],
          paymentMethods: [
            { method: "COD", count: 70 },
            { method: "Credit Card", count: 50 },
            { method: "e-Wallet", count: 30 },
          ],
          orderStatus: [
            { status: "Completed", count: 130 },
            { status: "Pending", count: 15 },
            { status: "Cancelled", count: 5 },
          ],
        };
        break;
      default:
        data = {};
    }
    setStats(data);
  };

  useEffect(() => {
    fetchStats(timeRange);
  }, [timeRange]);

  const salesChartData = {
    labels: ["Total Sales", "Total Bills", "Average Bill"],
    datasets: [
      {
        label: `Stats - ${timeRange}`,
        data: [stats.totalSales, stats.totalBills, stats.averageBill],
        backgroundColor: ["#60A5FA", "#34D399", "#FBBF24"],
        borderColor: ["#3B82F6", "#10B981", "#F59E0B"],
        borderWidth: 1,
      },
    ],
  };

  const productChartData = {
    labels: stats.topProducts.map((p) => p.name),
    datasets: [
      {
        label: "Top Selling Products",
        data: stats.topProducts.map((p) => p.sold),
        backgroundColor: ["#F87171", "#FB923C", "#34D399"],
        borderWidth: 1,
      },
    ],
  };

  const paymentChartData = {
    labels: stats.paymentMethods.map((m) => m.method),
    datasets: [
      {
        label: "Payment Methods",
        data: stats.paymentMethods.map((m) => m.count),
        backgroundColor: ["#4ADE80", "#60A5FA", "#FBBF24"],
        borderWidth: 1,
      },
    ],
  };

  const orderStatusChartData = {
    labels: stats.orderStatus.map((s) => s.status),
    datasets: [
      {
        label: "Order Status",
        data: stats.orderStatus.map((s) => s.count),
        backgroundColor: ["#10B981", "#FACC15", "#EF4444"],
        borderWidth: 1,
      },
    ],
  };

  const chartBlockClass =
    "bg-white p-4 rounded shadow space-y-4 w-full max-w-full overflow-x-auto";

  return (
    <div className="p-5 space-y-10">
      <div className="flex justify-between items-center">
        <h2 className="text-2xl font-bold">Thống kê tổng quanquan</h2>
        <select
          className="border px-3 py-1 rounded"
          value={timeRange}
          onChange={(e) => setTimeRange(e.target.value)}
        >
          <option value="day">Ngày</option>
          <option value="week">Tuần</option>
          <option value="month">Tháng</option>
        </select>
      </div>

      {/* Summary Cards */}
      <div className="grid grid-cols-1 sm:grid-cols-3 gap-4">
        <div className="bg-blue-100 p-4 rounded shadow">
          <h3 className="text-lg font-semibold">Tổng bán</h3>
          <p className="text-2xl font-bold">${stats.totalSales.toFixed(2)}</p>
        </div>
        <div className="bg-green-100 p-4 rounded shadow">
          <h3 className="text-lg font-semibold">Tổng hóa đơn</h3>
          <p className="text-2xl font-bold">{stats.totalBills}</p>
        </div>
        <div className="bg-yellow-100 p-4 rounded shadow">
          <h3 className="text-lg font-semibold">Trung bình hóa đơn</h3>
          <p className="text-2xl font-bold">${stats.averageBill.toFixed(2)}</p>
        </div>
      </div>

      {/* Charts Section */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        <div className={chartBlockClass}>
          <h3 className="text-lg font-semibold">So đồ bán hàng</h3>
          <Bar data={salesChartData} />
        </div>

        <div className={chartBlockClass}>
          <h3 className="text-lg font-semibold">Top đồ uống bán chạy</h3>
          <Bar data={productChartData} />
        </div>

        <div className={chartBlockClass}>
          <h3 className="text-lg font-semibold">Phương thưc thanh toán</h3>
          <Doughnut data={paymentChartData} />
        </div>

        <div className={chartBlockClass}>
          <h3 className="text-lg font-semibold">Trạng thái đặt hàng</h3>
          <Pie data={orderStatusChartData} />
        </div>
      </div>
    </div>
  );
};

export default CashierStats;
